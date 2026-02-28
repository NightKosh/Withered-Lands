package nightkosh.withered_lands.world_event;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.helper.TimeHelper;

import static nightkosh.withered_lands.WitheredLandsMod.LOGGER;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WorldEventManager extends SavedData {

    public static final SavedDataType<WorldEventManager> ID = new SavedDataType<>(
            "withered_lands/world_events",
            WorldEventManager::new,
            RecordCodecBuilder.create(inst -> inst.group(
                            SlimeRainEvent.CODEC
                                    .fieldOf("slime_rain")
                                    .forGetter(m -> m.slimeRain))
                    .apply(inst, WorldEventManager::new)));

    private final SlimeRainEvent slimeRain;
    private long timeToCheckDay = 0;
    private long timeToStarSlimeRainMin = -1;
    private long timeToStarSlimeRainMax = -1;

    private WorldEventManager() {
        this.slimeRain = new SlimeRainEvent(false, 0, -1);
    }

    private WorldEventManager(SlimeRainEvent slimeRain) {
        this.slimeRain = slimeRain;
    }

    public static WorldEventManager get(ServerLevel anyLevel) {
        var overworld = anyLevel.getServer().getLevel(Level.OVERWORLD);
        return overworld == null ?
                new WorldEventManager() :
                overworld.getDataStorage().computeIfAbsent(ID);
    }

    public void tick(ServerLevel level) {
        long time = level.getDayTime();
        if (time >= this.timeToCheckDay) {
            long dayTime = time % TimeHelper.DAY;
            var globalDayTimeStart = time - dayTime;
            this.timeToStarSlimeRainMin = globalDayTimeStart + TimeHelper.SECONDS_30;
            this.timeToStarSlimeRainMax = globalDayTimeStart + TimeHelper.SECONDS_180;
            this.timeToCheckDay = globalDayTimeStart + TimeHelper.DAY;
            if (WLConfigs.DEBUG_MODE.get()) {
                LOGGER.info("WorldEventManager check current day.");
                LOGGER.info("Global time {}, day time {}, Slime Rain potential start time {}, next day check time {} ",
                        time, dayTime, this.timeToStarSlimeRainMin, this.timeToCheckDay);
            }
        }

        if (this.slimeRain.isActive()) {
            this.slimeRain.tick(level, this::setDirty);
        } else if (time >= this.timeToStarSlimeRainMin && time <= this.timeToStarSlimeRainMax) {
            this.slimeRain.tryToStartEvent(level, this::setDirty);
            this.timeToStarSlimeRainMin = -1;
            this.timeToStarSlimeRainMax = -1;
        }
    }

    public boolean toggleSlimeRain(ServerLevel level, boolean state, boolean changeLastDayCounter) {
        boolean iventActivated = false;
        if (state) {
            iventActivated = slimeRain.start(level, changeLastDayCounter);
        } else {
            slimeRain.end(level);
        }
        this.setDirty();
        return iventActivated;
    }

}
