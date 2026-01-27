package nightkosh.withered_lands.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import nightkosh.withered_lands.entity.breeze.ABreeze;
import nightkosh.withered_lands.entity.swamp.GiantFrog;
import nightkosh.withered_lands.sensor_type.BreezeAttackTargetSensor;
import nightkosh.withered_lands.sensor_type.GiantFrogAttackableSensor;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WLSensorTypes {

    public static final DeferredRegister<SensorType<?>> SENSORS =
            DeferredRegister.create(Registries.SENSOR_TYPE, ModInfo.ID);

    public static final DeferredHolder<SensorType<?>, SensorType<? extends Sensor<? super GiantFrog>>> GIANT_FROG =
            SENSORS.register("giant_frog",
                    () -> new SensorType<>(GiantFrogAttackableSensor::new));

    public static final DeferredHolder<SensorType<?>, SensorType<? extends Sensor<? super ABreeze>>> BREEZE_TARGETS =
            SENSORS.register("breeze_attack_target_sensor",
                    () -> new SensorType<>(BreezeAttackTargetSensor::new));

    public static void register(IEventBus modEventBus) {
        SENSORS.register(modEventBus);
    }

}
