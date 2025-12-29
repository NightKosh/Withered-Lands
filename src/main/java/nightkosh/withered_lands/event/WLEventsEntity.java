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
import nightkosh.withered_lands.entity.wolf.Barghest;


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
        event.put(WLEntities.BLAZING_BAT.get(), BlazingBat.createAttributeSupplier());
        event.put(WLEntities.WITHERED_BAT.get(), WitheredBat.createAttributeSupplier());
        event.put(WLEntities.VOLATILE_BAT.get(), VolatileBat.createAttributeSupplier());
        event.put(WLEntities.CHORUS_BAT.get(), ChorusBat.createAttributeSupplier());
        // wolves
        event.put(WLEntities.BARGHEST.get(), Barghest.createAttributeSupplier());
    }

    @SubscribeEvent
    public static void registerSpawnPlacement(RegisterSpawnPlacementsEvent event) {
        // bats
        event.register(WLEntities.VAMPIRE_BAT.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                VampireBat::checkSpawnRules,
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
        event.register(WLEntities.BARGHEST.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Barghest::checkSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.OR);
    }

}
