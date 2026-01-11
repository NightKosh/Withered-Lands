package nightkosh.withered_lands.sensor_type;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.NearestVisibleLivingEntitySensor;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nonnull;
import java.util.ArrayList;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GiantFrogAttackableSensor extends NearestVisibleLivingEntitySensor {

    public static final float TARGET_DETECTION_DISTANCE = 15;

    @Override
    protected boolean isMatchingEntity(@Nonnull ServerLevel level, @Nonnull LivingEntity frog, @Nonnull LivingEntity target) {
        return target instanceof Player &&
                !frog.getBrain().hasMemoryValue(MemoryModuleType.HAS_HUNTING_COOLDOWN) &&
                Sensor.isEntityAttackable(level, frog, target) &&
                !this.isUnreachableAttackTarget(frog, target) &&
                target.closerThan(frog, TARGET_DETECTION_DISTANCE);
    }

    private boolean isUnreachableAttackTarget(LivingEntity attacker, LivingEntity target) {
        return attacker.getBrain()
                .getMemory(MemoryModuleType.UNREACHABLE_TONGUE_TARGETS)
                .orElseGet(ArrayList::new)
                .contains(target.getUUID());
    }

    @Nonnull
    @Override
    protected MemoryModuleType getMemory() {
        return MemoryModuleType.NEAREST_ATTACKABLE;
    }

}
