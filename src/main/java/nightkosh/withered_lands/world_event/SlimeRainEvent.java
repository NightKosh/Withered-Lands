package nightkosh.withered_lands.world_event;

import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.core.WLEntities;
import nightkosh.withered_lands.entity.slime.ASlime;
import nightkosh.withered_lands.helper.TimeHelper;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SlimeRainEvent {

    private static final Component SLIME_RAIN_NAME = Component.translatable("event.withered_lands.slime_rain");
    private static final Component SLIME_RAIN_START = Component.translatable("message.withered_lands.slime_rain.start");
    private static final Component SLIME_RAIN_END = Component.translatable("message.withered_lands.slime_rain.end");

    private static final WeightedList<EntityType<?>> SLIMES = WeightedList.<EntityType<?>>builder()
            .add(WLEntities.VERDANT_SLIME.get(), 9)
            .add(WLEntities.SANDY_SLIME.get(), 4)
            .add(WLEntities.FROZEN_SLIME.get(), 4)
            .add(WLEntities.MUD_SLIME.get(), 4)
            .add(WLEntities.JUNGLE_SLIME.get(), 3)
            .add(WLEntities.CAVE_SLIME.get(), 2)
            .add(WLEntities.ABYSSAL_SLIME.get(), 2)
            .build();

    private static final int SPAWN_HEIGHT = 320;
    private static final int SPAWN_RANGE_DIAMETER = 100;
    private static final int SPAWN_RANGE_HALF = SPAWN_RANGE_DIAMETER / 2;
    private static final int EVENT_TICKS = WLConfigs.SLIME_RAIN_DURATION.get();

    public static final int MIN_DAYS_BETWEEN_RAINS = WLConfigs.SLIME_RAIN_MIN_DAYS_BETWEEN_RAINS.get();

    private final ServerBossEvent progressBar = new ServerBossEvent(
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

    protected void start(ServerLevel level) {
        if (!this.isActive) {
            var server = level.getServer();
            this.isActive = true;
            this.ticks = 0;
            this.setPhaseTicks();
            this.progressBar.setProgress(0);
            this.progressBar.setVisible(true);
            this.progressBar.setName(SLIME_RAIN_NAME);
            level.setWeatherParameters(0, EVENT_TICKS, true, false);
            server.getPlayerList().broadcastSystemMessage(SLIME_RAIN_START, false);
            this.setLastEventDay(level);
        }
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
                        player.blockPosition().getX() + level.random.nextInt(SPAWN_RANGE_DIAMETER) - SPAWN_RANGE_HALF,
                        SPAWN_HEIGHT,
                        player.blockPosition().getZ() + level.random.nextInt(SPAWN_RANGE_DIAMETER) - SPAWN_RANGE_HALF);
                var slime = (ASlime) SLIMES.getRandom(level.random)
                        .orElse(WLEntities.VERDANT_SLIME.get())
                        .create(level, EntitySpawnReason.EVENT);
                slime.addTag(ASlime.TAG_SLIME_RAIN);
                slime.snapTo(pos.getX(), pos.getY(), pos.getZ());
                slime.finalizeSpawn(level, level.getCurrentDifficultyAt(pos), EntitySpawnReason.EVENT, null);
                slime.setSize(this.getSlimeSize(), true);
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
        this.lastEventDay = level.getDayTime() / TimeHelper.DAY;
    }

    public void tryToStartEvent(ServerLevel level, Runnable markDirty) {
        if (WLConfigs.SLIME_RAIN_ENABLE.get() && !level.isRaining() && !level.isThundering()) {
            long today = level.getDayTime() / TimeHelper.DAY;
            long daysPassed = today - this.lastEventDay;
            long daysAfterMinimalTime = daysPassed - MIN_DAYS_BETWEEN_RAINS;
            if (daysAfterMinimalTime >= 0) {
                if (this.lastEventDay == -1 || // first time event
                        level.random.nextInt(100) < Mth.clamp(daysAfterMinimalTime * 8 + 4, 0, 90)) {// 4% + 8% per day, max 90%
                    this.start(level);
                    markDirty.run();
                }
            }
        }
    }

}
