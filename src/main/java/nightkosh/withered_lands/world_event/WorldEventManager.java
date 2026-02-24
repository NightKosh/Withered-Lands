package nightkosh.withered_lands.world_event;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

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
                    .apply(inst, WorldEventManager::new))
    );

    private final SlimeRainEvent slimeRain;

    private WorldEventManager() {
        this.slimeRain = new SlimeRainEvent(false, 0);
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
        if (slimeRain.isActive()) {
            slimeRain.tick(level, this::setDirty);
        }
    }

    public void toggleSlimeRain(ServerLevel level, boolean state) {
        if (state) {
            slimeRain.start(level);
        } else {
            slimeRain.end(level);
        }
        this.setDirty();
    }

}
