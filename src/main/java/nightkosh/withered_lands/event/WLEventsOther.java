package nightkosh.withered_lands.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;
import nightkosh.withered_lands.core.ModInfo;
import nightkosh.withered_lands.core.WLCommands;
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
        } else if (event.getItemStack().is(WLItems.SLIME_GEL_CHUNK.get())) {
            event.setBurnTime(1600);
        }
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        if (WLConfigs.DEBUG_MODE.get()) {
            LOGGER.info("RegisterCommandsEvent triggered");
        }

        var dispatcher = event.getDispatcher();
        var node = dispatcher.register(WLCommands.root());
        dispatcher.register(WLCommands.getAlias().redirect(node));
    }

}
