package nightkosh.withered_lands.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import nightkosh.withered_lands.compatibility.InfernalMobsCompatibility;
import nightkosh.withered_lands.core.ModInfo;
import nightkosh.withered_lands.core.WLConfigs;

import static nightkosh.withered_lands.WitheredLandsMod.LOGGER;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@EventBusSubscriber(modid = ModInfo.ID)
public class WLEventsLoad {

    @SubscribeEvent
    public static void onFMLLoadCompleteEvent(FMLLoadCompleteEvent event) {
        if (WLConfigs.DEBUG_MODE.get()) {
            LOGGER.info("FMLLoadCompleteEvent event triggered");
        }
        if (InfernalMobsCompatibility.loaded()) {
            InfernalMobsCompatibility.disableInfernalMobs();
        }
    }

    @SubscribeEvent
    public static void onServerAboutToStartEvent(ServerAboutToStartEvent event) {
        if (WLConfigs.DEBUG_MODE.get()) {
            LOGGER.info("ServerAboutToStartEvent event triggered");
        }
        if (InfernalMobsCompatibility.loaded()) {
            InfernalMobsCompatibility.disableInfernalMobs();
        }
    }

}
