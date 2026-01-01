package nightkosh.withered_lands.event;

import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import nightkosh.withered_lands.core.ModInfo;
import nightkosh.withered_lands.core.WLEntities;
import nightkosh.withered_lands.entity.bat.*;
import nightkosh.withered_lands.entity.desert.Mummy;
import nightkosh.withered_lands.entity.water.DrownedSailor;
import nightkosh.withered_lands.entity.water.PhantomDiver;
import nightkosh.withered_lands.entity.water.SwampThing;
import nightkosh.withered_lands.entity.wolf.Barghest;
import nightkosh.withered_lands.entity.wolf.SkeletonDog;
import nightkosh.withered_lands.entity.cat.SkeletonCat;

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
        // bats
        event.put(WLEntities.VAMPIRE_BAT.get(), VampireBat.createAttributeSupplier());
        event.put(WLEntities.FLYING_FOX.get(), FlyingFox.createAttributeSupplier());
        event.put(WLEntities.BLAZING_BAT.get(), BlazingBat.createAttributeSupplier());
        event.put(WLEntities.WITHERED_BAT.get(), WitheredBat.createAttributeSupplier());
        event.put(WLEntities.VOLATILE_BAT.get(), VolatileBat.createAttributeSupplier());
        event.put(WLEntities.CHORUS_BAT.get(), ChorusBat.createAttributeSupplier());
        // wolves
        event.put(WLEntities.SKELETON_DOG.get(), SkeletonDog.createAttributeSupplier());
        event.put(WLEntities.BARGHEST.get(), Barghest.createAttributeSupplier());
        // cats
        event.put(WLEntities.SKELETON_CAT.get(), SkeletonCat.createAttributeSupplier());
        // underwater mobs
        event.put(WLEntities.DROWNED_SAILOR.get(), DrownedSailor.createAttributeSupplier());
        event.put(WLEntities.PHANTOM_DIVER.get(), PhantomDiver.createAttributeSupplier());
        event.put(WLEntities.SWAMP_THING.get(), SwampThing.createAttributeSupplier());
        // desert
        event.put(WLEntities.MUMMY.get(), Mummy.createAttributeSupplier());
    }

    @SubscribeEvent
    public static void registerSpawnPlacement(RegisterSpawnPlacementsEvent event) {
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

        // wolves
        event.register(WLEntities.SKELETON_DOG.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                SkeletonDog::checkSpawnRules,
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

}
