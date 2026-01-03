package nightkosh.withered_lands.event;

import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.monster.skeleton.*;
import net.minecraft.world.entity.monster.zombie.Drowned;
import net.minecraft.world.entity.monster.zombie.Husk;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.monster.zombie.ZombifiedPiglin;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;
import nightkosh.withered_lands.core.*;
import nightkosh.withered_lands.entity.bat.*;
import nightkosh.withered_lands.entity.cat.ZombieCat;
import nightkosh.withered_lands.entity.crawler.*;
import nightkosh.withered_lands.entity.desert.Mummy;
import nightkosh.withered_lands.entity.horse.SkeletonHorse;
import nightkosh.withered_lands.entity.horse.ZombieHorse;
import nightkosh.withered_lands.entity.slime.*;
import nightkosh.withered_lands.entity.water.DrownedSailor;
import nightkosh.withered_lands.entity.water.PhantomDiver;
import nightkosh.withered_lands.entity.water.SwampThing;
import nightkosh.withered_lands.entity.wolf.Barghest;
import nightkosh.withered_lands.entity.wolf.SkeletonDog;
import nightkosh.withered_lands.entity.cat.SkeletonCat;
import nightkosh.withered_lands.entity.wolf.ZombieDog;

import static nightkosh.withered_lands.WitheredLandsMod.LOGGER;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@EventBusSubscriber(modid = ModInfo.ID)
public class WLEventsEntity {

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
        event.put(WLEntities.VAMPIRE_BAT.get(), VampireBat.createAttributeSupplier());
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
        event.put(WLEntities.SWAMP_THING.get(), SwampThing.createAttributeSupplier());
        // desert
        event.put(WLEntities.MUMMY.get(), Mummy.createAttributeSupplier());
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
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                SandySlime::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.FROZEN_SLIME.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                FrozenSlime::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.MUD_SLIME.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                MudSlime::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        event.register(WLEntities.JUNGLE_SLIME.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
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
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                MoltenSlime::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);

        // bats
        event.register(WLEntities.VAMPIRE_BAT.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                VampireBat::checkSpawnRules,
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
    }

    @SubscribeEvent
    public static void onFuelBurnTime(FurnaceFuelBurnTimeEvent event) {
        if (WLConfigs.DEBUG_MODE.get()) {
            LOGGER.info("FurnaceFuelBurnTimeEvent event triggered");
        }
        if (event.getItemStack().is(WLItems.SLIME_GEL.get())) {
            event.setBurnTime(100);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLivingDeath(LivingDeathEvent event) {
        if (WLConfigs.DEBUG_MODE.get()) {
            LOGGER.info("LivingDeathEvent event triggered");
        }
        var entity = event.getEntity();
        var level = entity.level();
        if (!level.isClientSide()) {
            if (WLConfigs.SKULL_CRAWLERS_AT_MOBS_DEATH_SPAWN.get() && entity.getRandom().nextInt(10) == 0) {
                ASkullCrawler crawler = null;
                if (entity instanceof AbstractSkeleton skeleton) {
                    if (skeleton instanceof WitherSkeleton) {
                        crawler = WLEntities.WITHER_SKULL_CRAWLER.get()
                                .create(level, EntitySpawnReason.TRIGGERED);
                    } else if (skeleton instanceof Stray) {
                        crawler = WLEntities.STRAY_SKULL_CRAWLER.get()
                                .create(level, EntitySpawnReason.TRIGGERED);
                    } else if (skeleton instanceof Bogged) {
                        crawler = WLEntities.BOGGED_SKULL_CRAWLER.get()
                                .create(level, EntitySpawnReason.TRIGGERED);
                    } else if (skeleton instanceof Parched) {
                        crawler = WLEntities.PARCHED_SKULL_CRAWLER.get()
                                .create(level, EntitySpawnReason.TRIGGERED);
                    } else {
                        crawler = WLEntities.SKELETON_SKULL_CRAWLER.get()
                                .create(level, EntitySpawnReason.TRIGGERED);
                    }
                } else if (entity instanceof Zombie zombie) {
                    if (zombie instanceof Husk) {
                        crawler = WLEntities.HUSK_SKULL_CRAWLER.get()
                                .create(level, EntitySpawnReason.TRIGGERED);
                    } else if (entity instanceof Drowned) {
                        crawler = WLEntities.DROWNED_SKULL_CRAWLER.get()
                                .create(level, EntitySpawnReason.TRIGGERED);
                    } else if (entity instanceof ZombifiedPiglin) {
                        crawler = WLEntities.PIGLIN_SKULL_CRAWLER.get()
                                .create(level, EntitySpawnReason.TRIGGERED);
                    } else {
                        crawler = WLEntities.ZOMBIE_SKULL_CRAWLER.get()
                                .create(level, EntitySpawnReason.TRIGGERED);
                    }
                }
                if (crawler != null) {
                    if (WLConfigs.DEBUG_MODE.get()) {
                        LOGGER.info("Going to spawn skull crawler at mob death");
                    }
                    crawler.snapTo(entity.getX(), entity.getY() + 1.5, entity.getZ(),
                            entity.getYRot(), entity.getXRot());
                    level.addFreshEntity(crawler);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onLivingHealEvent(LivingHealEvent event) {
        if (event.getEntity().hasEffect(WLMobEffects.BLEEDING)) {
            if (WLConfigs.DEBUG_MODE.get()) {
                LOGGER.info("LivingHealEvent event triggered. Going to cancel event due to bleeding effect");
            }
            event.setCanceled(true);
        }
    }

}
