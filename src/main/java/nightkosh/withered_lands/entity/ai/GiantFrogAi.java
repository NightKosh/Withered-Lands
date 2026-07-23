package nightkosh.withered_lands.entity.ai;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.ActivityData;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.PathfindingContext;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import nightkosh.withered_lands.core.WLSensorTypes;
import nightkosh.withered_lands.entity.ai.behavior.GiantFrogCroak;
import nightkosh.withered_lands.entity.ai.behavior.GiantFrogShootTongue;
import nightkosh.withered_lands.entity.swamp.GiantFrog;

import java.util.List;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GiantFrogAi {

    public static final ImmutableList<SensorType<? extends Sensor<? super GiantFrog>>> SENSOR_TYPES = ImmutableList.of(
            SensorType.NEAREST_LIVING_ENTITIES,
            SensorType.HURT_BY,
            WLSensorTypes.GIANT_FROG.get(),
            SensorType.IS_IN_WATER
    );

    public static List<ActivityData<GiantFrog>> getActivities() {
        return List.of(initCoreActivity(), initIdleActivity(), initSwimActivity(), initTongueActivity(), initJumpActivity());
    }

    public static final ImmutableList<MemoryModuleType<?>> MEMORY_TYPES = ImmutableList.of(
            MemoryModuleType.LOOK_TARGET,
            MemoryModuleType.NEAREST_LIVING_ENTITIES,
            MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES,
            MemoryModuleType.WALK_TARGET,
            MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
            MemoryModuleType.PATH,
            MemoryModuleType.LONG_JUMP_COOLDOWN_TICKS,
            MemoryModuleType.LONG_JUMP_MID_JUMP,
            MemoryModuleType.ATTACK_TARGET,
            MemoryModuleType.HURT_BY,
            MemoryModuleType.HURT_BY_ENTITY,
            MemoryModuleType.NEAREST_ATTACKABLE,
            MemoryModuleType.IS_IN_WATER,
            MemoryModuleType.UNREACHABLE_TONGUE_TARGETS
    );

    private static final float SPEED_MULTIPLIER_IN_WATER = 0.75F;
    private static final UniformInt TIME_BETWEEN_LONG_JUMPS = UniformInt.of(100, 140);
    private static final int MAX_LONG_JUMP_HEIGHT = 4;
    private static final int MAX_LONG_JUMP_WIDTH = 8;
    private static final float MAX_JUMP_VELOCITY_MULTIPLIER = 3.57F;

    public static void initMemories(GiantFrog frog, RandomSource random) {
        frog.getBrain().setMemory(MemoryModuleType.LONG_JUMP_COOLDOWN_TICKS, TIME_BETWEEN_LONG_JUMPS.sample(random));
    }

    private static ActivityData<GiantFrog> initCoreActivity() {
        return ActivityData.create(
                Activity.CORE,
                0,
                ImmutableList.of(
                        new LookAtTargetSink(45, 90),
                        new MoveToTargetSink(),
                        new CountDownCooldownTicks(MemoryModuleType.LONG_JUMP_COOLDOWN_TICKS))
        );
    }

    private static ActivityData<GiantFrog> initIdleActivity() {
        return ActivityData.create(
                Activity.IDLE,
                ImmutableList.of(
                        Pair.of(0, SetEntityLookTargetSometimes.create(EntityTypes.PLAYER, 6, UniformInt.of(30, 60))),
                        Pair.of(2, StartAttacking.create(
                                (level, frog) -> canAttack(frog),
                                (level, frog) -> frog.getBrain()
                                        .getMemory(MemoryModuleType.NEAREST_ATTACKABLE))),
                        Pair.of(3, TryFindLand.create(6, 1)),
                        Pair.of(4, new RunOne<>(
                                ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT),
                                ImmutableList.of(
                                        Pair.of(RandomStroll.stroll(1), 1),
                                        Pair.of(SetWalkTargetFromLookTarget.create(1, 3), 1),
                                        Pair.of(new GiantFrogCroak(), 3),
                                        Pair.of(BehaviorBuilder.triggerIf(Entity::onGround), 2))))),
                ImmutableSet.of(
                        Pair.of(MemoryModuleType.LONG_JUMP_MID_JUMP, MemoryStatus.VALUE_ABSENT),
                        Pair.of(MemoryModuleType.IS_IN_WATER, MemoryStatus.VALUE_ABSENT))
        );
    }

    private static ActivityData<GiantFrog> initSwimActivity() {
        return ActivityData.create(
                Activity.SWIM,
                ImmutableList.of(
                        Pair.of(0, SetEntityLookTargetSometimes.create(EntityTypes.PLAYER, 6, UniformInt.of(30, 60))),
                        Pair.of(2, StartAttacking.create(
                                (level, frog) -> canAttack(frog),
                                (level, frog) -> frog.getBrain()
                                        .getMemory(MemoryModuleType.NEAREST_ATTACKABLE))),
                        Pair.of(3, TryFindLand.create(8, 1.5F)),
                        Pair.of(5, new GateBehavior<>(
                                ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT),
                                ImmutableSet.of(),
                                GateBehavior.OrderPolicy.ORDERED,
                                GateBehavior.RunningPolicy.TRY_ALL,
                                ImmutableList.of(
                                        Pair.of(RandomStroll.swim(SPEED_MULTIPLIER_IN_WATER), 1),
                                        Pair.of(RandomStroll.stroll(1, true), 1),
                                        Pair.of(SetWalkTargetFromLookTarget.create(1, 3), 1),
                                        Pair.of(BehaviorBuilder.triggerIf(Entity::isInWater), 5))))),
                ImmutableSet.of(
                        Pair.of(MemoryModuleType.LONG_JUMP_MID_JUMP, MemoryStatus.VALUE_ABSENT),
                        Pair.of(MemoryModuleType.IS_IN_WATER, MemoryStatus.VALUE_PRESENT))
        );
    }

    private static ActivityData<GiantFrog> initJumpActivity() {
        return ActivityData.create(
                Activity.LONG_JUMP,
                ImmutableList.of(
                        Pair.of(0, new LongJumpMidJump(TIME_BETWEEN_LONG_JUMPS, SoundEvents.FROG_STEP)),
                        Pair.of(1, new LongJumpToPreferredBlock<>(
                                TIME_BETWEEN_LONG_JUMPS,
                                MAX_LONG_JUMP_HEIGHT,
                                MAX_LONG_JUMP_WIDTH,
                                MAX_JUMP_VELOCITY_MULTIPLIER,
                                frog -> SoundEvents.FROG_LONG_JUMP,
                                BlockTags.FROG_PREFER_JUMP_TO,
                                0.5F,
                                GiantFrogAi::isAcceptableLandingSpot))),
                ImmutableSet.of(
                        Pair.of(MemoryModuleType.LONG_JUMP_COOLDOWN_TICKS, MemoryStatus.VALUE_ABSENT),
                        Pair.of(MemoryModuleType.IS_IN_WATER, MemoryStatus.VALUE_ABSENT)
                )
        );
    }

    private static ActivityData<GiantFrog> initTongueActivity() {
        return ActivityData.create(
                Activity.TONGUE,
                0,
                ImmutableList.of(
                        StopAttackingIfTargetInvalid.create(),
                        new GiantFrogShootTongue()),
                MemoryModuleType.ATTACK_TARGET
        );
    }

    private static <E extends Mob> boolean isAcceptableLandingSpot(E mob, BlockPos pos) {
        var level = mob.level();
        var blockpos = pos.below();
        if (level.getFluidState(pos).isEmpty() && level.getFluidState(blockpos).isEmpty() && level.getFluidState(pos.above()).isEmpty()) {
            var blockstate = level.getBlockState(pos);
            var blockstate1 = level.getBlockState(blockpos);
            if (!blockstate.is(BlockTags.FROG_PREFER_JUMP_TO) && !blockstate1.is(BlockTags.FROG_PREFER_JUMP_TO)) {
                var pathfindingcontext = new PathfindingContext(mob.level(), mob);
                var pathtype = WalkNodeEvaluator.getPathTypeStatic(pathfindingcontext, pos.mutable());
                var pathtype1 = WalkNodeEvaluator.getPathTypeStatic(pathfindingcontext, blockpos.mutable());
                return pathtype == PathType.TRAPDOOR || (blockstate.isAir() && pathtype1 == PathType.TRAPDOOR) || LongJumpToRandomPos.defaultAcceptableLandingSpot(mob, pos);
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    private static boolean canAttack(GiantFrog frog) {
        return true;
    }

    public static void updateActivity(GiantFrog frog) {
        frog.getBrain()
                .setActiveActivityToFirstValid(ImmutableList.of(
                        Activity.TONGUE,
                        Activity.LONG_JUMP,
                        Activity.SWIM,
                        Activity.IDLE));
    }

}
