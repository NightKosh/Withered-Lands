package nightkosh.withered_lands.event;

import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.rabbit.Rabbit;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import nightkosh.withered_lands.core.ModInfo;
import nightkosh.withered_lands.core.WLEntities;
import nightkosh.withered_lands.entity.*;
import nightkosh.withered_lands.entity.bat.*;
import nightkosh.withered_lands.entity.breeze.Blizzard;
import nightkosh.withered_lands.entity.breeze.DirgeGale;
import nightkosh.withered_lands.entity.breeze.SandDevil;
import nightkosh.withered_lands.entity.breeze.Thunderstorm;
import nightkosh.withered_lands.entity.cat.SkeletonCat;
import nightkosh.withered_lands.entity.cat.ZombieCat;
import nightkosh.withered_lands.entity.crawler.*;
import nightkosh.withered_lands.entity.creeper.CaveCreeper;
import nightkosh.withered_lands.entity.creeper.DeepslateCreeper;
import nightkosh.withered_lands.entity.creeper.DesertCreeper;
import nightkosh.withered_lands.entity.creeper.SnowyCreeper;
import nightkosh.withered_lands.entity.desert.Mummy;
import nightkosh.withered_lands.entity.ghost.HollowStalker;
import nightkosh.withered_lands.entity.ghost.LostSoul;
import nightkosh.withered_lands.entity.giant.FrozenGiant;
import nightkosh.withered_lands.entity.giant.HillGiant;
import nightkosh.withered_lands.entity.horse.SkeletonHorse;
import nightkosh.withered_lands.entity.horse.ZombieHorse;
import nightkosh.withered_lands.entity.slime.*;
import nightkosh.withered_lands.entity.snow.Snowman;
import nightkosh.withered_lands.entity.spider.WLCaveSpider;
import nightkosh.withered_lands.entity.water.DrownedSailor;
import nightkosh.withered_lands.entity.swamp.GiantFrog;
import nightkosh.withered_lands.entity.water.PhantomDiver;
import nightkosh.withered_lands.entity.swamp.SwampThing;
import nightkosh.withered_lands.entity.water.fish.Minnow;
import nightkosh.withered_lands.entity.water.fish.Pike;
import nightkosh.withered_lands.entity.water.fish.Piranha;
import nightkosh.withered_lands.entity.water.jellyfish.*;
import nightkosh.withered_lands.entity.wolf.Barghest;
import nightkosh.withered_lands.entity.wolf.SkeletonDog;
import nightkosh.withered_lands.entity.wolf.ZombieDog;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@EventBusSubscriber(modid = ModInfo.ID)
public class WLEventsEntityRegistration {

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        // slimes
        event.put(WLEntities.SLIME_VERDANT.get(), SlimeVerdant.createAttributeSupplier());
        event.put(WLEntities.SLIME_SANDY.get(), SlimeSandy.createAttributeSupplier());
        event.put(WLEntities.SLIME_FROZEN.get(), SlimeFrozen.createAttributeSupplier());
        event.put(WLEntities.SLIME_MUD.get(), SlimeMud.createAttributeSupplier());
        event.put(WLEntities.SLIME_JUNGLE.get(), SlimeJungle.createAttributeSupplier());
        event.put(WLEntities.SLIME_CAVE.get(), SlimeCave.createAttributeSupplier());
        event.put(WLEntities.SLIME_ABYSSAL.get(), SlimeAbyssal.createAttributeSupplier());
        event.put(WLEntities.SLIME_TOXIC_SLUDGE.get(), SlimeToxicSludge.createAttributeSupplier());
        event.put(WLEntities.SLIME_MOLTEN.get(), SlimeMolten.createAttributeSupplier());
        // bats
        event.put(WLEntities.BAT_CAVE.get(), BatCave.createAttributeSupplier());
        event.put(WLEntities.BAT_VAMPIRE.get(), BatVampire.createAttributeSupplier());
        event.put(WLEntities.BAT_ICE.get(), BatIce.createAttributeSupplier());
        event.put(WLEntities.BAT_FLYING_FOX.get(), BatFlyingFox.createAttributeSupplier());
        event.put(WLEntities.BAT_BLAZING.get(), BatBlazing.createAttributeSupplier());
        event.put(WLEntities.BAT_WITHERED.get(), BatWithered.createAttributeSupplier());
        event.put(WLEntities.BAT_VOLATILE.get(), BatVolatile.createAttributeSupplier());
        event.put(WLEntities.BAT_CHORUS.get(), BatChorus.createAttributeSupplier());
        // crawlers
        event.put(WLEntities.SKULL_CRAWLER_SKELETON.get(), SkullCrawlerSkeleton.createAttributeSupplier());
        event.put(WLEntities.SKULL_CRAWLER_STRAY.get(), SkullCrawlerStray.createAttributeSupplier());
        event.put(WLEntities.SKULL_CRAWLER_BOGGED.get(), SkullCrawlerBogged.createAttributeSupplier());
        event.put(WLEntities.SKULL_CRAWLER_PARCHED.get(), SkullCrawlerParched.createAttributeSupplier());
        event.put(WLEntities.SKULL_CRAWLER_WITHER.get(), SkullCrawlerWither.createAttributeSupplier());
        event.put(WLEntities.SKULL_CRAWLER_ZOMBIE.get(), SkullCrawlerZombie.createAttributeSupplier());
        event.put(WLEntities.SKULL_CRAWLER_HUSK.get(), SkullCrawlerHusk.createAttributeSupplier());
        event.put(WLEntities.SKULL_CRAWLER_DROWNED.get(), SkullCrawlerDrowned.createAttributeSupplier());
        event.put(WLEntities.SKULL_CRAWLER_PIGLIN.get(), SkullCrawlerPiglin.createAttributeSupplier());
        // breeze
        event.put(WLEntities.THUNDERSTORM.get(), Thunderstorm.createAttributeSupplier());
        event.put(WLEntities.BLIZZARD.get(), Blizzard.createAttributeSupplier());
        event.put(WLEntities.SAND_DEVIL.get(), SandDevil.createAttributeSupplier());
        event.put(WLEntities.DIRGE_GALE.get(), DirgeGale.createAttributeSupplier());
        // ghosts
        event.put(WLEntities.HOLLOW_STALKER.get(), HollowStalker.createAttributeSupplier());
        event.put(WLEntities.LOST_SOUL.get(), LostSoul.createAttributeSupplier());
        // spiders
        event.put(WLEntities.CAVE_SPIDER.get(), WLCaveSpider.createCaveSpider().build());
        // creepers
        event.put(WLEntities.DESERT_CREEPER.get(), Creeper.createAttributes().build());
        event.put(WLEntities.SNOWY_CREEPER.get(), Creeper.createAttributes().build());
        event.put(WLEntities.CAVE_CREEPER.get(), Creeper.createAttributes().build());
        event.put(WLEntities.DEEPSLATE_CREEPER.get(), Creeper.createAttributes().build());
        // wolves
        event.put(WLEntities.SKELETON_DOG.get(), SkeletonDog.createAttributeSupplier());
        event.put(WLEntities.ZOMBIE_DOG.get(), ZombieDog.createAttributeSupplier());
        event.put(WLEntities.BARGHEST.get(), Barghest.createAttributeSupplier());
        // cats
        event.put(WLEntities.SKELETON_CAT.get(), SkeletonCat.createAttributeSupplier());
        event.put(WLEntities.ZOMBIE_CAT.get(), ZombieCat.createAttributeSupplier());
        // horses
        event.put(WLEntities.SKELETON_HORSE.get(), SkeletonHorse.createAttributeSupplier());
        event.put(WLEntities.ZOMBIE_HORSE.get(), ZombieHorse.createAttributeSupplier());
        // underwater mobs
        event.put(WLEntities.DROWNED_SAILOR.get(), DrownedSailor.createAttributeSupplier());
        event.put(WLEntities.PHANTOM_DIVER.get(), PhantomDiver.createAttributeSupplier());
        // fishes
        event.put(WLEntities.MINNOW.get(), Minnow.createAttributeSupplier());
        event.put(WLEntities.PIRANHA.get(), Piranha.createAttributeSupplier());
        event.put(WLEntities.PIKE.get(), Pike.createAttributeSupplier());
        event.put(WLEntities.JELLYFISH_WHITE.get(), WhiteJellyfish.createAttributeSupplier());
        event.put(WLEntities.JELLYFISH_BLUE.get(), BlueJellyfish.createAttributeSupplier());
        event.put(WLEntities.JELLYFISH_GREEN.get(), GreenJellyfish.createAttributeSupplier());
        event.put(WLEntities.JELLYFISH_YELLOW.get(), YellowJellyfish.createAttributeSupplier());
        event.put(WLEntities.JELLYFISH_RED.get(), RedJellyfish.createAttributeSupplier());
        event.put(WLEntities.JELLYFISH_PINK.get(), PinkJellyfish.createAttributeSupplier());
        event.put(WLEntities.JELLYFISH_PURPLE.get(), PurpleJellyfish.createAttributeSupplier());
        // swamp
        event.put(WLEntities.GIANT_FROG.get(), GiantFrog.createAttributeSupplier());
        event.put(WLEntities.SWAMP_THING.get(), SwampThing.createAttributeSupplier());
        // desert
        event.put(WLEntities.MUMMY.get(), Mummy.createAttributeSupplier());
        // snow
        event.put(WLEntities.SNOWMAN.get(), Snowman.createAttributeSupplier());
        // giants
        event.put(WLEntities.HILL_GIANT.get(), HillGiant.createAttributeSupplier());
        event.put(WLEntities.FROZEN_GIANT.get(), FrozenGiant.createAttributeSupplier());
        // other
        event.put(WLEntities.POSSESSED_ARMOR.get(), PossessedArmor.createAttributeSupplier());
        event.put(WLEntities.MIMIC.get(), Mimic.createAttributeSupplier());
        event.put(WLEntities.SKELETON.get(), WLSkeleton.createAttributes().build());
        event.put(WLEntities.ILLUSIONER.get(), WLIllusioner.createAttributes().build());
        event.put(WLEntities.KILLER_BUNNY.get(), Rabbit.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacement(RegisterSpawnPlacementsEvent event) {
        // slimes
        event.register(WLEntities.SLIME_VERDANT.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                SlimeVerdant::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.SLIME_SANDY.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.WORLD_SURFACE,
                SlimeSandy::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.SLIME_FROZEN.get(),
                SpawnPlacementTypes.NO_RESTRICTIONS,
                Heightmap.Types.WORLD_SURFACE,
                SlimeFrozen::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.SLIME_MUD.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.WORLD_SURFACE,
                SlimeMud::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.SLIME_JUNGLE.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.WORLD_SURFACE,
                SlimeJungle::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.SLIME_CAVE.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                SlimeCave::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.SLIME_ABYSSAL.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                SlimeAbyssal::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.SLIME_TOXIC_SLUDGE.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                SlimeToxicSludge::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.SLIME_MOLTEN.get(),
                SpawnPlacementTypes.NO_RESTRICTIONS,
                Heightmap.Types.WORLD_SURFACE,
                SlimeMolten::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        // bats
        event.register(WLEntities.BAT_CAVE.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                BatCave::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.BAT_VAMPIRE.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                BatVampire::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.BAT_ICE.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                BatIce::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.BAT_FLYING_FOX.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                BatFlyingFox::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.BAT_BLAZING.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                BatBlazing::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.BAT_WITHERED.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                BatWithered::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.BAT_VOLATILE.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                BatVolatile::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.BAT_CHORUS.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                BatChorus::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        // crawlers
        event.register(WLEntities.SKULL_CRAWLER_SKELETON.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ASkullCrawler::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.SKULL_CRAWLER_STRAY.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ASkullCrawler::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.SKULL_CRAWLER_BOGGED.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ASkullCrawler::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.SKULL_CRAWLER_PARCHED.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ASkullCrawler::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.SKULL_CRAWLER_WITHER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ASkullCrawler::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.SKULL_CRAWLER_ZOMBIE.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ASkullCrawler::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.SKULL_CRAWLER_HUSK.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ASkullCrawler::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.SKULL_CRAWLER_DROWNED.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ASkullCrawler::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.SKULL_CRAWLER_PIGLIN.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ASkullCrawler::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        // breeze
        event.register(WLEntities.THUNDERSTORM.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Thunderstorm::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.BLIZZARD.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Blizzard::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.SAND_DEVIL.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                SandDevil::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.DIRGE_GALE.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                DirgeGale::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        // ghosts
        event.register(WLEntities.HOLLOW_STALKER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                HollowStalker::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.LOST_SOUL.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                LostSoul::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        // spiders
        event.register(WLEntities.CAVE_SPIDER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                WLCaveSpider::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        // creepers
        event.register(WLEntities.DESERT_CREEPER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                DesertCreeper::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.SNOWY_CREEPER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                SnowyCreeper::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.CAVE_CREEPER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                CaveCreeper::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.DEEPSLATE_CREEPER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                DeepslateCreeper::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        // wolves
        event.register(WLEntities.SKELETON_DOG.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                SkeletonDog::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.ZOMBIE_DOG.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ZombieDog::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.BARGHEST.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Barghest::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        // cats
        event.register(WLEntities.SKELETON_CAT.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                SkeletonCat::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.ZOMBIE_CAT.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ZombieCat::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        // horses
        event.register(WLEntities.SKELETON_HORSE.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                SkeletonHorse::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.ZOMBIE_HORSE.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ZombieHorse::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        // underwater mobs
        event.register(WLEntities.DROWNED_SAILOR.get(),
                SpawnPlacementTypes.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                DrownedSailor::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.PHANTOM_DIVER.get(),
                SpawnPlacementTypes.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                PhantomDiver::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        // fishes
        event.register(WLEntities.MINNOW.get(),
                SpawnPlacementTypes.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Minnow::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.PIRANHA.get(),
                SpawnPlacementTypes.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Piranha::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.PIKE.get(),
                SpawnPlacementTypes.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Pike::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.JELLYFISH_WHITE.get(),
                SpawnPlacementTypes.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                WhiteJellyfish::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.JELLYFISH_BLUE.get(),
                SpawnPlacementTypes.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                BlueJellyfish::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.JELLYFISH_GREEN.get(),
                SpawnPlacementTypes.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                GreenJellyfish::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.JELLYFISH_YELLOW.get(),
                SpawnPlacementTypes.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                YellowJellyfish::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.JELLYFISH_RED.get(),
                SpawnPlacementTypes.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                RedJellyfish::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.JELLYFISH_PINK.get(),
                SpawnPlacementTypes.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                PinkJellyfish::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.JELLYFISH_PURPLE.get(),
                SpawnPlacementTypes.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                PurpleJellyfish::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        // swamp
        event.register(WLEntities.GIANT_FROG.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.WORLD_SURFACE,
                GiantFrog::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.SWAMP_THING.get(),
                SpawnPlacementTypes.IN_WATER,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                SwampThing::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        // desert
        event.register(WLEntities.MUMMY.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Mummy::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        // snow
        event.register(WLEntities.SNOWMAN.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.WORLD_SURFACE,
                Snowman::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        // giants
        event.register(WLEntities.HILL_GIANT.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.WORLD_SURFACE,
                HillGiant::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.FROZEN_GIANT.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.WORLD_SURFACE,
                FrozenGiant::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        // other
        event.register(WLEntities.POSSESSED_ARMOR.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                PossessedArmor::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.MIMIC.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Mimic::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.SKELETON.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                WLSkeleton::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.ILLUSIONER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                WLIllusioner::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.KILLER_BUNNY.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                KillerBunny::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);
    }

}
