package nightkosh.withered_lands.event;

import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import nightkosh.withered_lands.core.ModInfo;
import nightkosh.withered_lands.core.WLEntities;
import nightkosh.withered_lands.entity.PossessedArmor;
import nightkosh.withered_lands.entity.bat.*;
import nightkosh.withered_lands.entity.breeze.Blizzard;
import nightkosh.withered_lands.entity.breeze.DirgeGale;
import nightkosh.withered_lands.entity.breeze.SandDevil;
import nightkosh.withered_lands.entity.breeze.Thunderstorm;
import nightkosh.withered_lands.entity.cat.SkeletonCat;
import nightkosh.withered_lands.entity.cat.ZombieCat;
import nightkosh.withered_lands.entity.crawler.*;
import nightkosh.withered_lands.entity.desert.Mummy;
import nightkosh.withered_lands.entity.giant.FrozenGiant;
import nightkosh.withered_lands.entity.giant.HillGiant;
import nightkosh.withered_lands.entity.horse.SkeletonHorse;
import nightkosh.withered_lands.entity.horse.ZombieHorse;
import nightkosh.withered_lands.entity.slime.*;
import nightkosh.withered_lands.entity.snow.Snowman;
import nightkosh.withered_lands.entity.water.DrownedSailor;
import nightkosh.withered_lands.entity.swamp.GiantFrog;
import nightkosh.withered_lands.entity.water.PhantomDiver;
import nightkosh.withered_lands.entity.swamp.SwampThing;
import nightkosh.withered_lands.entity.water.fish.Minnow;
import nightkosh.withered_lands.entity.water.fish.Pike;
import nightkosh.withered_lands.entity.water.fish.Piranha;
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
        event.put(WLEntities.VERDANT_SLIME.get(), VerdantSlime.createAttributeSupplier());
        event.put(WLEntities.SANDY_SLIME.get(), SandySlime.createAttributeSupplier());
        event.put(WLEntities.FROZEN_SLIME.get(), FrozenSlime.createAttributeSupplier());
        event.put(WLEntities.MUD_SLIME.get(), MudSlime.createAttributeSupplier());
        event.put(WLEntities.JUNGLE_SLIME.get(), JungleSlime.createAttributeSupplier());
        event.put(WLEntities.CAVE_SLIME.get(), CaveSlime.createAttributeSupplier());
        event.put(WLEntities.ABYSSAL_SLIME.get(), AbyssalSlime.createAttributeSupplier());
        event.put(WLEntities.MOLTEN_SLIME.get(), MoltenSlime.createAttributeSupplier());
        // bats
        event.put(WLEntities.CAVE_BAT.get(), CaveBat.createAttributeSupplier());
        event.put(WLEntities.VAMPIRE_BAT.get(), VampireBat.createAttributeSupplier());
        event.put(WLEntities.ICE_BAT.get(), IceBat.createAttributeSupplier());
        event.put(WLEntities.FLYING_FOX.get(), FlyingFox.createAttributeSupplier());
        event.put(WLEntities.BLAZING_BAT.get(), BlazingBat.createAttributeSupplier());
        event.put(WLEntities.WITHERED_BAT.get(), WitheredBat.createAttributeSupplier());
        event.put(WLEntities.VOLATILE_BAT.get(), VolatileBat.createAttributeSupplier());
        event.put(WLEntities.CHORUS_BAT.get(), ChorusBat.createAttributeSupplier());
        // crawlers
        event.put(WLEntities.SKELETON_SKULL_CRAWLER.get(), SkeletonSkullCrawler.createAttributeSupplier());
        event.put(WLEntities.STRAY_SKULL_CRAWLER.get(), StraySkullCrawler.createAttributeSupplier());
        event.put(WLEntities.BOGGED_SKULL_CRAWLER.get(), BoggedSkullCrawler.createAttributeSupplier());
        event.put(WLEntities.PARCHED_SKULL_CRAWLER.get(), ParchedSkullCrawler.createAttributeSupplier());
        event.put(WLEntities.WITHER_SKULL_CRAWLER.get(), WitherSkullCrawler.createAttributeSupplier());
        event.put(WLEntities.ZOMBIE_SKULL_CRAWLER.get(), ZombieSkullCrawler.createAttributeSupplier());
        event.put(WLEntities.HUSK_SKULL_CRAWLER.get(), HuskSkullCrawler.createAttributeSupplier());
        event.put(WLEntities.DROWNED_SKULL_CRAWLER.get(), DrownedSkullCrawler.createAttributeSupplier());
        event.put(WLEntities.PIGLIN_SKULL_CRAWLER.get(), PiglinSkullCrawler.createAttributeSupplier());
        // breeze
        event.put(WLEntities.THUNDERSTORM.get(), Thunderstorm.createAttributeSupplier());
        event.put(WLEntities.BLIZZARD.get(), Blizzard.createAttributeSupplier());
        event.put(WLEntities.SAND_DEVIL.get(), SandDevil.createAttributeSupplier());
        event.put(WLEntities.DIRGE_GALE.get(), DirgeGale.createAttributeSupplier());
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
    }

    @SubscribeEvent
    public static void registerSpawnPlacement(RegisterSpawnPlacementsEvent event) {
        // slimes
        event.register(WLEntities.VERDANT_SLIME.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                VerdantSlime::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.SANDY_SLIME.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.WORLD_SURFACE,
                SandySlime::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.FROZEN_SLIME.get(),
                SpawnPlacementTypes.NO_RESTRICTIONS,
                Heightmap.Types.WORLD_SURFACE,
                FrozenSlime::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.MUD_SLIME.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.WORLD_SURFACE,
                MudSlime::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.JUNGLE_SLIME.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.WORLD_SURFACE,
                JungleSlime::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.CAVE_SLIME.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                CaveSlime::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.ABYSSAL_SLIME.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                AbyssalSlime::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.MOLTEN_SLIME.get(),
                SpawnPlacementTypes.NO_RESTRICTIONS,
                Heightmap.Types.WORLD_SURFACE,
                MoltenSlime::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        // bats
        event.register(WLEntities.CAVE_BAT.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                CaveBat::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.VAMPIRE_BAT.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                VampireBat::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.ICE_BAT.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                IceBat::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.FLYING_FOX.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FlyingFox::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.BLAZING_BAT.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                BlazingBat::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.WITHERED_BAT.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                WitheredBat::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.VOLATILE_BAT.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                VolatileBat::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.CHORUS_BAT.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ChorusBat::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        // crawlers
        event.register(WLEntities.SKELETON_SKULL_CRAWLER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ASkullCrawler::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.STRAY_SKULL_CRAWLER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ASkullCrawler::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.BOGGED_SKULL_CRAWLER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ASkullCrawler::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.PARCHED_SKULL_CRAWLER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ASkullCrawler::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.WITHER_SKULL_CRAWLER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ASkullCrawler::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.ZOMBIE_SKULL_CRAWLER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ASkullCrawler::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.HUSK_SKULL_CRAWLER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ASkullCrawler::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.DROWNED_SKULL_CRAWLER.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                ASkullCrawler::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.PIGLIN_SKULL_CRAWLER.get(),
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
    }

}
