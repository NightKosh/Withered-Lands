package nightkosh.withered_lands.entity.ai.behavior;

import com.google.common.collect.ImmutableMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.entity.player.Player;
import nightkosh.withered_lands.entity.swamp.GiantFrog;

import javax.annotation.Nonnull;
import java.util.ArrayList;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GiantFrogShootTongue extends Behavior<GiantFrog> {

    public static final int TIME_OUT_DURATION = 100;
    public static final int CATCH_ANIMATION_DURATION = 6;
    public static final int TONGUE_ANIMATION_DURATION = 10;
    private static final float EATING_DISTANCE = 7;
    private static final float EATING_MOVEMENT_FACTOR = 0.75F;
    public static final int UNREACHABLE_TONGUE_TARGETS_COOLDOWN_DURATION = 100;
    public static final int MAX_UNREACHBLE_TONGUE_TARGETS_IN_MEMORY = 5;

    private int eatAnimationTimer;
    private int calculatePathCounter;
    private State state = State.DONE;

    public GiantFrogShootTongue() {
        super(
                ImmutableMap.of(
                        MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT,
                        MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED,
                        MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT
                ),
                TIME_OUT_DURATION
        );
    }

    @Override
    protected boolean checkExtraStartConditions(@Nonnull ServerLevel level, GiantFrog frog) {
        var entity = frog.getBrain()
                .getMemory(MemoryModuleType.ATTACK_TARGET)
                .get();
        boolean flag = this.canPathfindToTarget(frog, entity);
        if (!flag) {
            frog.getBrain().eraseMemory(MemoryModuleType.ATTACK_TARGET);
            this.addUnreachableTargetToMemory(frog, entity);
        }

        return flag && frog.getPose() != Pose.CROAKING && entity instanceof Player;
    }

    @Override
    protected boolean canStillUse(@Nonnull ServerLevel level, GiantFrog frog, long xz) {
        return frog.getBrain().hasMemoryValue(MemoryModuleType.ATTACK_TARGET) &&
                this.state != State.DONE;
    }

    @Override
    protected void start(@Nonnull ServerLevel level, GiantFrog frog, long xz) {
        var livingentity = frog.getBrain()
                .getMemory(MemoryModuleType.ATTACK_TARGET)
                .get();
        BehaviorUtils.lookAtEntity(frog, livingentity);
        frog.setTongueTarget(livingentity);
        frog.getBrain().setMemory(
                MemoryModuleType.WALK_TARGET,
                new WalkTarget(livingentity.position(), 2, 0));
        this.calculatePathCounter = 10;
        this.state = State.MOVE_TO_TARGET;
    }

    @Override
    protected void stop(@Nonnull ServerLevel level, GiantFrog frog, long xz) {
        frog.getBrain().eraseMemory(MemoryModuleType.ATTACK_TARGET);
        frog.eraseTongueTarget();
        frog.setPose(Pose.STANDING);
    }

    @Override
    protected void tick(@Nonnull ServerLevel level, GiantFrog frog, long p_218662_) {
        var livingentity = frog.getBrain()
                .getMemory(MemoryModuleType.ATTACK_TARGET)
                .get();
        frog.setTongueTarget(livingentity);
        switch (this.state) {
            case MOVE_TO_TARGET:
                if (livingentity.distanceTo(frog) < EATING_DISTANCE) {
                    level.playSound(null, frog, SoundEvents.FROG_TONGUE, SoundSource.NEUTRAL, 2, 1);
                    frog.setPose(Pose.USING_TONGUE);
                    livingentity.setDeltaMovement(livingentity
                            .position()
                            .vectorTo(frog.position())
                            .normalize()
                            .scale(EATING_MOVEMENT_FACTOR));
                    this.eatAnimationTimer = 0;
                    this.state = State.CATCH_ANIMATION;
                } else if (this.calculatePathCounter <= 0) {
                    frog.getBrain().setMemory(
                            MemoryModuleType.WALK_TARGET,
                            new WalkTarget(livingentity.position(), 2, 0));
                    this.calculatePathCounter = TONGUE_ANIMATION_DURATION;
                } else {
                    this.calculatePathCounter--;
                }
                break;
            case CATCH_ANIMATION:
                if (this.eatAnimationTimer++ >= CATCH_ANIMATION_DURATION) {
                    this.state = State.EAT_ANIMATION;
                    this.eatEntity(level, frog);
                }
                break;
            case EAT_ANIMATION:
                if (this.eatAnimationTimer >= TONGUE_ANIMATION_DURATION) {
                    this.state = State.DONE;
                } else {
                    this.eatAnimationTimer++;
                }
            case DONE:
        }
    }

    private void eatEntity(ServerLevel level, GiantFrog frog) {
        level.playSound(null, frog, SoundEvents.FROG_EAT, SoundSource.NEUTRAL, 2, 1);
        var optional = frog.getTongueTarget();
        if (optional.isPresent()) {
            var entity = optional.get();
            if (entity.isAlive()) {
                frog.doHurtTarget(level, entity);
                if (!entity.isAlive()) {
                    entity.remove(Entity.RemovalReason.KILLED);
                }
            }
        }
    }

    private boolean canPathfindToTarget(GiantFrog frog, LivingEntity target) {
        var path = frog.getNavigation().createPath(target, 0);
        return path != null && path.getDistToTarget() < EATING_DISTANCE;
    }

    private void addUnreachableTargetToMemory(GiantFrog frog, LivingEntity target) {
        var list = frog.getBrain()
                .getMemory(MemoryModuleType.UNREACHABLE_TONGUE_TARGETS)
                .orElseGet(ArrayList::new);
        boolean flag = !list.contains(target.getUUID());
        if (list.size() == MAX_UNREACHBLE_TONGUE_TARGETS_IN_MEMORY && flag) {
            list.remove(0);
        }

        if (flag) {
            list.add(target.getUUID());
        }

        frog.getBrain().setMemoryWithExpiry(
                MemoryModuleType.UNREACHABLE_TONGUE_TARGETS,
                list,
                UNREACHABLE_TONGUE_TARGETS_COOLDOWN_DURATION);
    }

    enum State {
        MOVE_TO_TARGET,
        CATCH_ANIMATION,
        EAT_ANIMATION,
        DONE
    }

}
