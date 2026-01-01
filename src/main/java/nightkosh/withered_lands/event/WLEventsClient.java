package nightkosh.withered_lands.event;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import nightkosh.withered_lands.core.ModInfo;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.core.WLEntities;
import nightkosh.withered_lands.renderer.bat.*;
import nightkosh.withered_lands.renderer.cat.SkeletonCatRenderer;
import nightkosh.withered_lands.renderer.model.PhantomDiverModel;
import nightkosh.withered_lands.renderer.water.PhantomDiverRenderer;
import nightkosh.withered_lands.renderer.wolf.BarghestRenderer;
import nightkosh.withered_lands.renderer.wolf.SkeletonDogRenderer;

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
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        if (WLConfigs.DEBUG_MODE.get()) {
            LOGGER.info("EntityRenderersEvent.RegisterRenderers event triggered");
        }
        // bats
        event.registerEntityRenderer(WLEntities.VAMPIRE_BAT.get(), VampireBatRenderer::new);
        event.registerEntityRenderer(WLEntities.FLYING_FOX.get(), FlyingFoxRenderer::new);
        event.registerEntityRenderer(WLEntities.BLAZING_BAT.get(), BlazingBatRenderer::new);
        event.registerEntityRenderer(WLEntities.WITHERED_BAT.get(), WitheredBatRenderer::new);
        event.registerEntityRenderer(WLEntities.VOLATILE_BAT.get(), VolatileBatRenderer::new);
        event.registerEntityRenderer(WLEntities.CHORUS_BAT.get(), ChorusBatRenderer::new);
        // wolves
        event.registerEntityRenderer(WLEntities.SKELETON_DOG.get(), SkeletonDogRenderer::new);
        event.registerEntityRenderer(WLEntities.BARGHEST.get(), BarghestRenderer::new);
        // cat
        event.registerEntityRenderer(WLEntities.SKELETON_CAT.get(), SkeletonCatRenderer::new);
        // underwater mobs
        event.registerEntityRenderer(WLEntities.PHANTOM_DIVER.get(), PhantomDiverRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        if (WLConfigs.DEBUG_MODE.get()) {
            LOGGER.info("EntityRenderersEvent.RegisterLayerDefinitions event triggered");
        }
        // underwater mobs
        event.registerLayerDefinition(PhantomDiverModel.LAYER, PhantomDiverModel::createBodyLayer);
    }

}
