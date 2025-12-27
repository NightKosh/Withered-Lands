package nightkosh.withered_lands.event;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import nightkosh.withered_lands.core.ModInfo;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.core.WLEntities;
import nightkosh.withered_lands.renderer.bat.*;

import static nightkosh.withered_lands.WitheredLandsMod.LOGGER;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@EventBusSubscriber(modid = ModInfo.ID, value = Dist.CLIENT)
public class WLEventsClient {

    @SubscribeEvent
    public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        if (WLConfigs.DEBUG_MODE.get()) {
            LOGGER.info("EntityRenderersEvent.RegisterRenderers event triggered");
        }
        // bats
        event.registerEntityRenderer(WLEntities.VAMPIRE_BAT.get(), VampireBatRenderer::new);
        event.registerEntityRenderer(WLEntities.BLAZING_BAT.get(), BlazingBatRenderer::new);
        event.registerEntityRenderer(WLEntities.WITHERED_BAT.get(), WitheredBatRenderer::new);
        event.registerEntityRenderer(WLEntities.VOLATILE_BAT.get(), VolatileBatRenderer::new);
        event.registerEntityRenderer(WLEntities.CHORUS_BAT.get(), ChorusBatRenderer::new);
    }

}
