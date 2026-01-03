package nightkosh.withered_lands.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;
import nightkosh.withered_lands.core.ModInfo;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.core.WLItems;

import static nightkosh.withered_lands.WitheredLandsMod.LOGGER;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@EventBusSubscriber(modid = ModInfo.ID)
public class WLEventsOther {

    @SubscribeEvent
    public static void onFuelBurnTime(FurnaceFuelBurnTimeEvent event) {
        if (WLConfigs.DEBUG_MODE.get()) {
            LOGGER.info("FurnaceFuelBurnTimeEvent event triggered");
        }
        if (event.getItemStack().is(WLItems.SLIME_GEL.get())) {
            event.setBurnTime(100);
        }
    }

}
