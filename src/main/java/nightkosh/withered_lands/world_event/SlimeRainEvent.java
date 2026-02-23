package nightkosh.withered_lands.world_event;

import com.google.common.collect.Sets;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.saveddata.SavedData;
import nightkosh.withered_lands.core.WLEntities;
import nightkosh.withered_lands.entity.slime.ASlime;
import nightkosh.withered_lands.helper.TimeHelper;

import java.util.List;

public class SlimeRainEvent extends SavedData {

    private static final Component SLIME_RAIN_NAME = Component.translatable("event.withered_lands.slime_rain");
    private static final Component SLIME_RAIN_START = Component.translatable("message.withered_lands.slime_rain.start");
    private static final Component SLIME_RAIN_END = Component.translatable("message.withered_lands.slime_rain.end");
    private static final List<EntityType> SLIMES = List.of(
            WLEntities.VERDANT_SLIME.get(), WLEntities.VERDANT_SLIME.get(), WLEntities.VERDANT_SLIME.get(),
            WLEntities.VERDANT_SLIME.get(), WLEntities.VERDANT_SLIME.get(), WLEntities.VERDANT_SLIME.get(),
            WLEntities.VERDANT_SLIME.get(), WLEntities.VERDANT_SLIME.get(), WLEntities.VERDANT_SLIME.get(),
            WLEntities.SANDY_SLIME.get(), WLEntities.SANDY_SLIME.get(),
            WLEntities.SANDY_SLIME.get(), WLEntities.SANDY_SLIME.get(),
            WLEntities.FROZEN_SLIME.get(), WLEntities.FROZEN_SLIME.get(),
            WLEntities.FROZEN_SLIME.get(), WLEntities.FROZEN_SLIME.get(),
            WLEntities.MUD_SLIME.get(), WLEntities.MUD_SLIME.get(),
            WLEntities.MUD_SLIME.get(), WLEntities.MUD_SLIME.get(),
            WLEntities.JUNGLE_SLIME.get(), WLEntities.JUNGLE_SLIME.get(), WLEntities.JUNGLE_SLIME.get(),
            WLEntities.CAVE_SLIME.get(), WLEntities.CAVE_SLIME.get(),
            WLEntities.ABYSSAL_SLIME.get(), WLEntities.ABYSSAL_SLIME.get(),
            WLEntities.TOXIC_SLUDGE.get(),
            WLEntities.MOLTEN_SLIME.get());

    private static final int SPAWN_HEIGHT = 320;
    private static final int SPAWN_RANGE = 80;
    private static final int SPAWN_RANGE_HALF = SPAWN_RANGE / 2;
    private static final int EVENT_TICKS = TimeHelper.SECONDS_180;

    private final ServerBossEvent progressBar = new ServerBossEvent(
            SLIME_RAIN_NAME,
            BossEvent.BossBarColor.GREEN,
            BossEvent.BossBarOverlay.NOTCHED_10);


    private boolean isActive;
    private final int lastEventTime;
    private int ticks;

    public SlimeRainEvent(boolean isActive, int lastEventTime, int ticks) {
        this.isActive = isActive;
        this.lastEventTime = lastEventTime;
        this.ticks = ticks;

        this.setDirty();
    }

    protected void start(ServerLevel level) {
        if (!this.isActive) {
            var server = level.getServer();
            this.isActive = true;
            this.progressBar.setProgress(0);
            this.progressBar.setVisible(true);
            this.progressBar.setName(SLIME_RAIN_NAME);
            level.setWeatherParameters(0, EVENT_TICKS, true, false);
            server.getPlayerList().broadcastSystemMessage(SLIME_RAIN_START, false);
        }
    }

    protected void end(ServerLevel level) {
        if (this.isActive) {
            var server = level.getServer();
            this.isActive = false;
            this.progressBar.setVisible(false);
            this.progressBar.removeAllPlayers();
            server.getPlayerList().broadcastSystemMessage(SLIME_RAIN_END, false);
        }
    }

    public void tick(ServerLevel level) {
        this.ticks++;

        if (this.ticks % TimeHelper.SECONDS_5 == 0) {
            this.progressBar.setProgress(Mth.clamp(this.ticks / (float) EVENT_TICKS, 0, 1));
            this.updatePlayers(level);
            for (var player : this.progressBar.getPlayers()) {
                for (int i = 0; i < 4; i++) {
                    var pos = new BlockPos(
                            player.blockPosition().getX() + level.random.nextInt(SPAWN_RANGE) - SPAWN_RANGE_HALF,
                            SPAWN_HEIGHT,
                            player.blockPosition().getZ() + level.random.nextInt(SPAWN_RANGE) - SPAWN_RANGE_HALF);
                    var slime = (ASlime) SLIMES.get(level.random.nextInt(SLIMES.size())).create(level, EntitySpawnReason.EVENT);
                    slime.addTag(ASlime.TAG_SLIME_RAIN);
                    slime.snapTo(pos.getX(), pos.getY(), pos.getZ());
                    slime.finalizeSpawn(level, level.getCurrentDifficultyAt(pos), EntitySpawnReason.EVENT, null);
                    level.addFreshEntity(slime);
                }
            }
        }

        if (this.ticks >= EVENT_TICKS) {
            end(level);
        }
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

}
