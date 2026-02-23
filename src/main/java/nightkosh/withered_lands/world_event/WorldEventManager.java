package nightkosh.withered_lands.world_event;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WorldEventManager extends SavedData {

    public static WorldEventManager INSTANCE = new WorldEventManager();

    private final SlimeRainEvent slimeRain;

    private WorldEventManager() {
        this.slimeRain = new SlimeRainEvent(false, 0, 0);
    }

    public void tick(ServerLevel level) {
        if (slimeRain.isActive()) {
            slimeRain.tick(level);
        }
    }

    public static void toggleSlimeRain(ServerLevel level, boolean state) {
        if (state) {
            INSTANCE.slimeRain.start(level);
        } else {
            INSTANCE.slimeRain.end(level);
        }
    }

}
