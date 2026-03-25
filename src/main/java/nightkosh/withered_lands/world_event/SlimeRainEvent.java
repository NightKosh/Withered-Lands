package nightkosh.withered_lands.world_event;

import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import nightkosh.withered_lands.core.WLAdvancements;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.core.WLEntities;
import nightkosh.withered_lands.core.WLItems;
import nightkosh.withered_lands.entity.slime.ASlime;
import nightkosh.withered_lands.helper.TimeHelper;

import java.util.UUID;

import static nightkosh.withered_lands.WitheredLandsMod.LOGGER;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SlimeRainEvent {

    private static final Component SLIME_RAIN_NAME = Component.translatable("event.withered_lands.slime_rain");
    private static final Component SLIME_RAIN_START = Component.translatable("message.withered_lands.slime_rain.start")
            .withStyle(ChatFormatting.GREEN);
    private static final Component SLIME_RAIN_END = Component.translatable("message.withered_lands.slime_rain.end")
            .withStyle(ChatFormatting.GREEN);

    private static final WeightedList<EntityType<?>> SLIMES = WeightedList.<EntityType<?>>builder()
            .add(WLEntities.SLIME_VERDANT.get(), 9)
            .add(WLEntities.SLIME_SANDY.get(), 4)
            .add(WLEntities.SLIME_FROZEN.get(), 4)
            .add(WLEntities.SLIME_MUD.get(), 4)
            .add(WLEntities.SLIME_JUNGLE.get(), 3)
            .add(WLEntities.SLIME_CAVE.get(), 2)
            .add(WLEntities.SLIME_ABYSSAL.get(), 2)
            .build();

    private static final int SPAWN_HEIGHT = 320;
    private static final int SPAWN_RANGE_DIAMETER = 100;
    private static final int SPAWN_RANGE_HALF = SPAWN_RANGE_DIAMETER / 2;
    private static final int EVENT_TICKS = WLConfigs.SLIME_RAIN_DURATION.get();

    public static final int MIN_DAYS_BETWEEN_RAINS = WLConfigs.SLIME_RAIN_MIN_DAYS_BETWEEN_RAINS.get();

    private final ServerBossEvent progressBar = new ServerBossEvent(
            UUID.fromString("02b5ae77-5f16-4f8a-b36b-f1632b0e186d"),
            SLIME_RAIN_NAME,
            BossEvent.BossBarColor.GREEN,
            BossEvent.BossBarOverlay.NOTCHED_10);

    public static final Codec<SlimeRainEvent> CODEC = RecordCodecBuilder.create(
            inst -> inst.group(
                            Codec.BOOL.fieldOf("is_active").forGetter(e -> e.isActive),
                            Codec.INT.fieldOf("ticks").forGetter(e -> e.ticks),
                            Codec.LONG.fieldOf("last_event_day").forGetter(e -> e.lastEventDay))
                    .apply(inst, SlimeRainEvent::new));

    private long lastEventDay;
    private boolean isActive;
    private int ticks;
    private int secondPhaseTicks;
    private int thirdPhaseTicks;

    public SlimeRainEvent(boolean isActive, int ticks, long lastEventDay) {
        this.isActive = isActive;
        this.ticks = ticks;
        this.lastEventDay = lastEventDay;

        if (this.isActive) {
            this.setPhaseTicks();
            this.progressBar.setVisible(true);
            this.progressBar.setName(SLIME_RAIN_NAME);
            this.progressBar.setProgress(Mth.clamp(this.ticks / (float) EVENT_TICKS, 0, 1));
        } else {
            this.progressBar.setVisible(false);
        }
    }

    private void setPhaseTicks() {
        var phaseTime = EVENT_TICKS / 3;
        this.secondPhaseTicks = phaseTime;
        this.thirdPhaseTicks = phaseTime + phaseTime;
    }

    protected boolean start(ServerLevel level, boolean changeLastDayCounter) {
        if (!this.isActive) {
            var server = level.getServer();
            this.isActive = true;
            this.ticks = 0;
            this.setPhaseTicks();
            this.progressBar.setProgress(0);
            this.progressBar.setVisible(true);
            this.progressBar.setName(SLIME_RAIN_NAME);
            server.setWeatherParameters(0, EVENT_TICKS, true, false);
            server.getPlayerList().broadcastSystemMessage(SLIME_RAIN_START, false);
            if (changeLastDayCounter) {
                this.setLastEventDay(level);
            }
            return true;
        }
        return false;
    }

    protected void end(ServerLevel level) {
        if (this.isActive) {
            var server = level.getServer();
            this.isActive = false;
            this.ticks = 0;
            this.progressBar.setVisible(false);
            this.progressBar.removeAllPlayers();
            level.resetWeatherCycle();
            server.getPlayerList().broadcastSystemMessage(SLIME_RAIN_END, false);
            for (var player : server.getPlayerList().getPlayers()) {
                if (player.level().dimension().equals(Level.OVERWORLD)) {
                    WLAdvancements.giveAdvancement(player, level, WLAdvancements.SLIMEPOCALYPSE);
                }
            }
        }
    }

    public void tick(ServerLevel level, Runnable markDirty) {
        this.ticks++;

        if (this.ticks >= EVENT_TICKS) {
            end(level);
            markDirty.run();
        } else if (this.ticks % TimeHelper.SECONDS_1 == 0) {
            this.progressBar.setProgress(Mth.clamp(this.ticks / (float) EVENT_TICKS, 0, 1));
            this.updatePlayers(level);
            for (var player : this.progressBar.getPlayers()) {
                var pos = new BlockPos(
                        player.blockPosition().getX() + level.getRandom().nextInt(SPAWN_RANGE_DIAMETER) - SPAWN_RANGE_HALF,
                        SPAWN_HEIGHT,
                        player.blockPosition().getZ() + level.getRandom().nextInt(SPAWN_RANGE_DIAMETER) - SPAWN_RANGE_HALF);
                var size = this.getSlimeSize();
                var slime = (ASlime) SLIMES.getRandom(level.getRandom())
                        .orElse(WLEntities.SLIME_VERDANT.get())
                        .create(level, EntitySpawnReason.EVENT);
                slime.addTag(ASlime.TAG_SLIME_RAIN);
                slime.snapTo(pos.getX(), pos.getY(), pos.getZ());
                slime.finalizeSpawn(level, level.getCurrentDifficultyAt(pos), EntitySpawnReason.EVENT, null);
                slime.setSize(size, true);
                if (WLConfigs.SLIME_RAIN_DROP_SLIME_CROWN.get() && size > 1 && level.getRandom().nextInt(1000) < 8) {//0.8%
                    slime.swallowItem(new ItemStack(WLItems.SLIME_CROWN.get()));
                } else {
                    slime.tryToSwallowItem(WLConfigs.SLIME_RAIN_SWALLOWED_ITEMS_CHANCE_INCREASED.get());
                }
                level.addFreshEntity(slime);
            }

            markDirty.run();
        }
    }

    private int getSlimeSize() {
        if (this.ticks >= this.thirdPhaseTicks) {
            return 4;
        } else if (this.ticks >= this.secondPhaseTicks) {
            return 2;
        }
        return 1;
    }

    private void updatePlayers(ServerLevel level) {
        var set = Sets.newHashSet(this.progressBar.getPlayers());
        var list = level.getPlayers(p -> true);

        for (var player : list) {
            if (!set.contains(player)) {
                this.progressBar.addPlayer(player);
            }
        }

        for (var player : set) {
            if (!list.contains(player)) {
                this.progressBar.removePlayer(player);
            }
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void setLastEventDay(ServerLevel level) {
        this.lastEventDay = level.getOverworldClockTime() / TimeHelper.DAY;
    }

    public void tryToStartEvent(ServerLevel level, Runnable markDirty) {
        if (WLConfigs.SLIME_RAIN_ENABLE.get()) {
            if (!level.isRaining() && !level.isThundering()) {
                long today = level.getOverworldClockTime() / TimeHelper.DAY;
                long daysPassed = today - this.lastEventDay;
                long daysAfterMinimalTime = daysPassed - MIN_DAYS_BETWEEN_RAINS;
                if (daysAfterMinimalTime >= 0) {
                    var chance = level.getRandom().nextInt(100);
                    // 4% + 4% per day, max 90%
                    var maxChance = Mth.clamp((daysAfterMinimalTime + 1) * WLConfigs.SLIME_RAIN_CHANCE.get(), 0, 90);
                    if (WLConfigs.DEBUG_MODE.get()) {
                        LOGGER.info("Slime Rain chance {}, required range 0 - {}", chance, maxChance);
                    }
                    if (this.lastEventDay == -1 || // first time event
                            chance < maxChance) {
                        this.start(level, true);
                        markDirty.run();
                    }
                } else if (WLConfigs.DEBUG_MODE.get()) {
                    LOGGER.info("Can't start Slime Rain - minimal amount of days still not passed");
                    LOGGER.info("Today {}, daysPassed {}, daysAfterMinimalTime {}", today, daysPassed, daysAfterMinimalTime);
                }
            } else if (WLConfigs.DEBUG_MODE.get()) {
                LOGGER.info("Can't try to start Slime Rain - bad weather!");
            }
        }
    }

}
