package nightkosh.withered_lands.sensor_type;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.NearestLivingEntitySensor;
import net.minecraft.world.entity.ai.sensing.Sensor;
import nightkosh.withered_lands.entity.breeze.ABreeze;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BreezeAttackTargetSensor extends NearestLivingEntitySensor<ABreeze> {

    @Override
    public Set<MemoryModuleType<?>> requires() {
        return ImmutableSet.copyOf(Iterables.concat(super.requires(), List.of(MemoryModuleType.NEAREST_ATTACKABLE)));
    }

    protected void doTick(ServerLevel level, ABreeze breeze) {
        super.doTick(level, breeze);

        breeze.getBrain()
                .getMemory(MemoryModuleType.NEAREST_LIVING_ENTITIES)
                .stream()
                .flatMap(Collection::stream)
                .filter(EntitySelector.NO_CREATIVE_OR_SPECTATOR)
                .filter(entity -> Sensor.isEntityAttackable(level, breeze, entity))
                .findFirst()
                .ifPresentOrElse(
                        entity -> breeze.getBrain().setMemory(MemoryModuleType.NEAREST_ATTACKABLE, entity),
                        () -> breeze.getBrain().eraseMemory(MemoryModuleType.NEAREST_ATTACKABLE));
    }

}
