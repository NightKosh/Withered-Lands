package nightkosh.withered_lands.entity.ai.breeze;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;
import nightkosh.withered_lands.entity.breeze.ABreeze;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Slide extends Behavior<ABreeze> {

    public Slide() {
        super(
                Map.of(
                        MemoryModuleType.ATTACK_TARGET,
                        MemoryStatus.VALUE_PRESENT,
                        MemoryModuleType.WALK_TARGET,
                        MemoryStatus.VALUE_ABSENT,
                        MemoryModuleType.BREEZE_JUMP_COOLDOWN,
                        MemoryStatus.VALUE_ABSENT,
                        MemoryModuleType.BREEZE_SHOOT,
                        MemoryStatus.VALUE_ABSENT
                )
        );
    }

    @Override
    protected boolean checkExtraStartConditions(@Nonnull ServerLevel level, ABreeze breeze) {
        return breeze.onGround() && !breeze.isInWater() && breeze.getPose() == Pose.STANDING;
    }

    @Override
    protected void start(@Nonnull ServerLevel level, ABreeze breeze, long gameTime) {
        var livingEntity = breeze.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).orElse(null);
        if (livingEntity != null) {
            boolean flag = breeze.withinInnerCircleRange(livingEntity.position());
            Vec3 vec3 = null;
            if (flag) {
                var vec31 = DefaultRandomPos.getPosAway(breeze, 5, 5, livingEntity.position());
                if (vec31 != null &&
                        BreezeUtil.hasLineOfSight(breeze, vec31) &&
                        livingEntity.distanceToSqr(vec31.x, vec31.y, vec31.z) > livingEntity.distanceToSqr(breeze)) {
                    vec3 = vec31;
                }
            }

            if (vec3 == null) {
                vec3 = breeze.getRandom().nextBoolean() ?
                        BreezeUtil.randomPointBehindTarget(livingEntity, breeze.getRandom()) :
                        randomPointInMiddleCircle(breeze, livingEntity);
            }

            breeze.getBrain().setMemory(
                    MemoryModuleType.WALK_TARGET,
                    new WalkTarget(BlockPos.containing(vec3), 0.6F, 1));
        }
    }

    private static Vec3 randomPointInMiddleCircle(ABreeze breeze, LivingEntity target) {
        var vec3 = target.position().subtract(breeze.position());
        double d0 = vec3.length() - Mth.lerp(breeze.getRandom().nextDouble(), 8.0, 4.0);
        return breeze.position().add(vec3.normalize().multiply(d0, d0, d0));
    }

}
