package nightkosh.withered_lands.event;

import net.minecraft.client.renderer.entity.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import nightkosh.withered_lands.core.ModInfo;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.core.WLEntities;
import nightkosh.withered_lands.renderer.MimicRenderer;
import nightkosh.withered_lands.renderer.PossessedArmorRenderer;
import nightkosh.withered_lands.renderer.WLSkeletonRenderer;
import nightkosh.withered_lands.renderer.bat.*;
import nightkosh.withered_lands.renderer.breeze.BlizzardRenderer;
import nightkosh.withered_lands.renderer.breeze.DirgeGaleRenderer;
import nightkosh.withered_lands.renderer.breeze.SandDevilRenderer;
import nightkosh.withered_lands.renderer.breeze.ThunderstormRenderer;
import nightkosh.withered_lands.renderer.cat.SkeletonCatRenderer;
import nightkosh.withered_lands.renderer.cat.ZombieCatRenderer;
import nightkosh.withered_lands.renderer.crawler.*;
import nightkosh.withered_lands.renderer.creeper.CaveCreeperRenderer;
import nightkosh.withered_lands.renderer.creeper.DeepslateCreeperRenderer;
import nightkosh.withered_lands.renderer.creeper.DesertCreeperRenderer;
import nightkosh.withered_lands.renderer.creeper.SnowyCreeperRenderer;
import nightkosh.withered_lands.renderer.desert.MummyRenderer;
import nightkosh.withered_lands.renderer.ghost.HollowStalkerRenderer;
import nightkosh.withered_lands.renderer.ghost.LostSoulRenderer;
import nightkosh.withered_lands.renderer.giant.FrozenGiantRenderer;
import nightkosh.withered_lands.renderer.giant.HillGiantRenderer;
import nightkosh.withered_lands.renderer.horse.SkeletonHorseRenderer;
import nightkosh.withered_lands.renderer.horse.ZombieHorseRenderer;
import nightkosh.withered_lands.renderer.model.*;
import nightkosh.withered_lands.renderer.model.jellyfish.*;
import nightkosh.withered_lands.renderer.slime.*;
import nightkosh.withered_lands.renderer.snow.SnowmanRenderer;
import nightkosh.withered_lands.renderer.water.DrownedSailorRenderer;
import nightkosh.withered_lands.renderer.swamp.GiantFrogRenderer;
import nightkosh.withered_lands.renderer.water.PhantomDiverRenderer;
import nightkosh.withered_lands.renderer.swamp.SwampThingRenderer;
import nightkosh.withered_lands.renderer.water.fish.MinnowRenderer;
import nightkosh.withered_lands.renderer.water.fish.PikeRenderer;
import nightkosh.withered_lands.renderer.water.fish.PiranhaRenderer;
import nightkosh.withered_lands.renderer.water.jellyfish.*;
import nightkosh.withered_lands.renderer.wolf.BarghestRenderer;
import nightkosh.withered_lands.renderer.wolf.SkeletonDogRenderer;
import nightkosh.withered_lands.renderer.wolf.ZombieDogRenderer;

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
        // slimes
        event.registerEntityRenderer(WLEntities.SLIME_VERDANT.get(), SlimeVerdantRenderer::new);
        event.registerEntityRenderer(WLEntities.SLIME_SANDY.get(), SlimeSandyRenderer::new);
        event.registerEntityRenderer(WLEntities.SLIME_FROZEN.get(), SlimeFrozenRenderer::new);
        event.registerEntityRenderer(WLEntities.SLIME_MUD.get(), SlimeMudRenderer::new);
        event.registerEntityRenderer(WLEntities.SLIME_JUNGLE.get(), SlimeJungleRenderer::new);
        event.registerEntityRenderer(WLEntities.SLIME_CAVE.get(), SlimeCaveRenderer::new);
        event.registerEntityRenderer(WLEntities.SLIME_ABYSSAL.get(), SlimeAbyssalRenderer::new);
        event.registerEntityRenderer(WLEntities.SLIME_TOXIC_SLUDGE.get(), SlimeToxicSludgeRenderer::new);
        event.registerEntityRenderer(WLEntities.SLIME_MOLTEN.get(), SlimeMoltenRenderer::new);
        // bats
        event.registerEntityRenderer(WLEntities.CAVE_BAT.get(), CaveBatRenderer::new);
        event.registerEntityRenderer(WLEntities.VAMPIRE_BAT.get(), VampireBatRenderer::new);
        event.registerEntityRenderer(WLEntities.ICE_BAT.get(), IceBatRenderer::new);
        event.registerEntityRenderer(WLEntities.FLYING_FOX.get(), FlyingFoxRenderer::new);
        event.registerEntityRenderer(WLEntities.BLAZING_BAT.get(), BlazingBatRenderer::new);
        event.registerEntityRenderer(WLEntities.WITHERED_BAT.get(), WitheredBatRenderer::new);
        event.registerEntityRenderer(WLEntities.VOLATILE_BAT.get(), VolatileBatRenderer::new);
        event.registerEntityRenderer(WLEntities.CHORUS_BAT.get(), ChorusBatRenderer::new);
        // crawlers
        event.registerEntityRenderer(WLEntities.SKELETON_SKULL_CRAWLER.get(), SkeletonSkullCrawlerRenderer::new);
        event.registerEntityRenderer(WLEntities.STRAY_SKULL_CRAWLER.get(), StraySkullCrawlerRenderer::new);
        event.registerEntityRenderer(WLEntities.BOGGED_SKULL_CRAWLER.get(), BoggedSkullCrawlerRenderer::new);
        event.registerEntityRenderer(WLEntities.PARCHED_SKULL_CRAWLER.get(), ParchedSkullCrawlerRenderer::new);
        event.registerEntityRenderer(WLEntities.WITHER_SKULL_CRAWLER.get(), WitherSkullCrawlerRenderer::new);
        event.registerEntityRenderer(WLEntities.ZOMBIE_SKULL_CRAWLER.get(), ZombieSkullCrawlerRenderer::new);
        event.registerEntityRenderer(WLEntities.HUSK_SKULL_CRAWLER.get(), HuskSkullCrawlerRenderer::new);
        event.registerEntityRenderer(WLEntities.DROWNED_SKULL_CRAWLER.get(), DrownedSkullCrawlerRenderer::new);
        event.registerEntityRenderer(WLEntities.PIGLIN_SKULL_CRAWLER.get(), PiglinSkullCrawlerRenderer::new);
        // breeze
        event.registerEntityRenderer(WLEntities.THUNDERSTORM.get(), ThunderstormRenderer::new);
        event.registerEntityRenderer(WLEntities.BLIZZARD.get(), BlizzardRenderer::new);
        event.registerEntityRenderer(WLEntities.SAND_DEVIL.get(), SandDevilRenderer::new);
        event.registerEntityRenderer(WLEntities.DIRGE_GALE.get(), DirgeGaleRenderer::new);
        // ghosts
        event.registerEntityRenderer(WLEntities.HOLLOW_STALKER.get(), HollowStalkerRenderer::new);
        event.registerEntityRenderer(WLEntities.LOST_SOUL.get(), LostSoulRenderer::new);
        // spiders
        event.registerEntityRenderer(WLEntities.CAVE_SPIDER.get(), CaveSpiderRenderer::new);
        // creepers
        event.registerEntityRenderer(WLEntities.DESERT_CREEPER.get(), DesertCreeperRenderer::new);
        event.registerEntityRenderer(WLEntities.SNOWY_CREEPER.get(), SnowyCreeperRenderer::new);
        event.registerEntityRenderer(WLEntities.CAVE_CREEPER.get(), CaveCreeperRenderer::new);
        event.registerEntityRenderer(WLEntities.DEEPSLATE_CREEPER.get(), DeepslateCreeperRenderer::new);
        // wolves
        event.registerEntityRenderer(WLEntities.SKELETON_DOG.get(), SkeletonDogRenderer::new);
        event.registerEntityRenderer(WLEntities.ZOMBIE_DOG.get(), ZombieDogRenderer::new);
        event.registerEntityRenderer(WLEntities.BARGHEST.get(), BarghestRenderer::new);
        // cats
        event.registerEntityRenderer(WLEntities.SKELETON_CAT.get(), SkeletonCatRenderer::new);
        event.registerEntityRenderer(WLEntities.ZOMBIE_CAT.get(), ZombieCatRenderer::new);
        // horses
        event.registerEntityRenderer(WLEntities.SKELETON_HORSE.get(), SkeletonHorseRenderer::new);
        event.registerEntityRenderer(WLEntities.ZOMBIE_HORSE.get(), ZombieHorseRenderer::new);
        // underwater mobs
        event.registerEntityRenderer(WLEntities.DROWNED_SAILOR.get(), DrownedSailorRenderer::new);
        event.registerEntityRenderer(WLEntities.PHANTOM_DIVER.get(), PhantomDiverRenderer::new);
        event.registerEntityRenderer(WLEntities.SWAMP_THING.get(), SwampThingRenderer::new);
        event.registerEntityRenderer(WLEntities.GIANT_FROG.get(), GiantFrogRenderer::new);
        // fishes
        event.registerEntityRenderer(WLEntities.MINNOW.get(), MinnowRenderer::new);
        event.registerEntityRenderer(WLEntities.PIRANHA.get(), PiranhaRenderer::new);
        event.registerEntityRenderer(WLEntities.PIKE.get(), PikeRenderer::new);
        event.registerEntityRenderer(WLEntities.JELLYFISH_WHITE.get(), WhiteJellyfishRenderer::new);
        event.registerEntityRenderer(WLEntities.JELLYFISH_BLUE.get(), BlueJellyfishRenderer::new);
        event.registerEntityRenderer(WLEntities.JELLYFISH_GREEN.get(), GreenJellyfishRenderer::new);
        event.registerEntityRenderer(WLEntities.JELLYFISH_YELLOW.get(), YellowJellyfishRenderer::new);
        event.registerEntityRenderer(WLEntities.JELLYFISH_RED.get(), RedJellyfishRenderer::new);
        event.registerEntityRenderer(WLEntities.JELLYFISH_PINK.get(), PinkJellyfishRenderer::new);
        event.registerEntityRenderer(WLEntities.JELLYFISH_PURPLE.get(), PurpleJellyfishRenderer::new);
        // desert
        event.registerEntityRenderer(WLEntities.MUMMY.get(), MummyRenderer::new);
        // snow
        event.registerEntityRenderer(WLEntities.SNOWMAN.get(), SnowmanRenderer::new);
        // giant
        event.registerEntityRenderer(WLEntities.HILL_GIANT.get(), HillGiantRenderer::new);
        event.registerEntityRenderer(WLEntities.FROZEN_GIANT.get(), FrozenGiantRenderer::new);
        // other
        event.registerEntityRenderer(WLEntities.POSSESSED_ARMOR.get(), PossessedArmorRenderer::new);
        event.registerEntityRenderer(WLEntities.MIMIC.get(), MimicRenderer::new);
        event.registerEntityRenderer(WLEntities.SKELETON.get(), WLSkeletonRenderer::new);
        event.registerEntityRenderer(WLEntities.ILLUSIONER.get(), IllusionerRenderer::new);
        event.registerEntityRenderer(WLEntities.KILLER_BUNNY.get(), RabbitRenderer::new);

        // projectiles
        event.registerEntityRenderer(WLEntities.FROZEN_SNOWBALL.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(WLEntities.BLIZZARD_WIND_CHARGE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(WLEntities.SAND_DEVIL_WIND_CHARGE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(WLEntities.DIRGE_GALE_WIND_CHARGE.get(), ThrownItemRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        if (WLConfigs.DEBUG_MODE.get()) {
            LOGGER.info("EntityRenderersEvent.RegisterLayerDefinitions event triggered");
        }
        event.registerLayerDefinition(PhantomDiverModel.LAYER, PhantomDiverModel::createBodyLayer);
        event.registerLayerDefinition(WLSkeletonModel.LAYER, WLSkeletonModel::createBodyLayer);
        event.registerLayerDefinition(DirgeGaleModel.LAYER, DirgeGaleModel::createBodyLayer);
        event.registerLayerDefinition(HollowStalkerModel.LAYER, HollowStalkerModel::createBodyLayer);
        event.registerLayerDefinition(LostSoulModel.LAYER, LostSoulModel::createBodyLayer);
        event.registerLayerDefinition(SkullCrawlerModel.LAYER, SkullCrawlerModel::createBodyLayer);
        event.registerLayerDefinition(MimicModel.LAYER, MimicModel::createBodyLayer);
        event.registerLayerDefinition(JellyfishWhiteModel.LAYER, JellyfishWhiteModel::createBodyLayer);
        event.registerLayerDefinition(JellyfishBlueModel.LAYER, JellyfishBlueModel::createBodyLayer);
        event.registerLayerDefinition(JellyfishGreenModel.LAYER, JellyfishGreenModel::createBodyLayer);
        event.registerLayerDefinition(JellyfishYellowModel.LAYER, JellyfishYellowModel::createBodyLayer);
        event.registerLayerDefinition(JellyfishRedModel.LAYER, JellyfishRedModel::createBodyLayer);
        event.registerLayerDefinition(JellyfishPinkModel.LAYER, JellyfishPinkModel::createBodyLayer);
        event.registerLayerDefinition(JellyfishPurpleModel.LAYER, JellyfishPurpleModel::createBodyLayer);
    }

}
