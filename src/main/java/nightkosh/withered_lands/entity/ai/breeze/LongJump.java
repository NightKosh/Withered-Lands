package nightkosh.withered_lands.entity.ai.breeze;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Unit;
import net.minecraft.util.Util;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.LongJumpUtil;
import net.minecraft.world.entity.ai.behavior.Swim;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import nightkosh.withered_lands.entity.breeze.ABreeze;
import org.jspecify.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Optional;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class LongJump extends Behavior<ABreeze> {

    protected static final int REQUIRED_AIR_BLOCKS_ABOVE = 4;
    protected static final int INHALING_DURATION_TICKS = 10;
    protected static final float MAX_JUMP_VELOCITY_MULTIPLIER = 0.058F;
    protected static final ObjectArrayList<Integer> ALLOWED_ANGLES = new ObjectArrayList<>(
            Lists.newArrayList(40, 55, 60, 75, 80));

    @VisibleForTesting
    public LongJump() {
        super(Map.of(MemoryModuleType.ATTACK_TARGET,
                        MemoryStatus.VALUE_PRESENT,
                        MemoryModuleType.BREEZE_JUMP_COOLDOWN,
                        MemoryStatus.VALUE_ABSENT,
                        MemoryModuleType.BREEZE_JUMP_INHALING,
                        MemoryStatus.REGISTERED,
                        MemoryModuleType.BREEZE_JUMP_TARGET,
                        MemoryStatus.REGISTERED,
                        MemoryModuleType.BREEZE_SHOOT,
                        MemoryStatus.VALUE_ABSENT,
                        MemoryModuleType.WALK_TARGET,
                        MemoryStatus.VALUE_ABSENT,
                        MemoryModuleType.BREEZE_LEAVING_WATER,
                        MemoryStatus.REGISTERED),
                200);
    }

    public boolean canRun(ServerLevel level, ABreeze breeze) {
        if (!breeze.onGround() && !breeze.isInWater()) {
            return false;
        } else if (Swim.shouldSwim(breeze)) {
            return false;
        } else if (breeze.getBrain().checkMemory(MemoryModuleType.BREEZE_JUMP_TARGET, MemoryStatus.VALUE_PRESENT)) {
            return true;
        } else {
            var livingEntity = breeze.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).orElse(null);
            if (livingEntity == null) {
                return false;
            } else if (outOfAggroRange(breeze, livingEntity)) {
                breeze.getBrain().eraseMemory(MemoryModuleType.ATTACK_TARGET);
                return false;
            } else if (tooCloseForJump(breeze, livingEntity)) {
                return false;
            } else if (!canJumpFromCurrentPosition(level, breeze)) {
                return false;
            } else {
                var blockpos = snapToSurface(breeze, BreezeUtil.randomPointBehindTarget(livingEntity, breeze.getRandom()));
                if (blockpos == null) {
                    return false;
                } else {
                    var blockstate = level.getBlockState(blockpos.below());
                    if (breeze.getType().isBlockDangerous(blockstate)) {
                        return false;
                    } else if (!BreezeUtil.hasLineOfSight(breeze, Vec3.atCenterOf(blockpos)) &&
                            !BreezeUtil.hasLineOfSight(breeze, Vec3.atCenterOf(blockpos.above(REQUIRED_AIR_BLOCKS_ABOVE)))) {
                        return false;
                    } else {
                        return setNewJumpPos(level, breeze, blockpos);
                    }
                }
            }
        }
    }

    protected boolean setNewJumpPos(ServerLevel level, ABreeze breeze, BlockPos pos) {
        breeze.getBrain().setMemory(MemoryModuleType.BREEZE_JUMP_TARGET, pos);
        return true;
    }

    @Override
    protected boolean checkExtraStartConditions(@Nonnull ServerLevel level, ABreeze breeze) {
        return canRun(level, breeze);
    }

    @Override
    protected boolean canStillUse(@Nonnull ServerLevel level, ABreeze breeze, long gameTime) {
        return breeze.getPose() != Pose.STANDING && !breeze.getBrain().hasMemoryValue(MemoryModuleType.BREEZE_JUMP_COOLDOWN);
    }

    @Override
    protected void start(@Nonnull ServerLevel level, ABreeze breeze, long gameTime) {
        if (breeze.getBrain().checkMemory(MemoryModuleType.BREEZE_JUMP_INHALING, MemoryStatus.VALUE_ABSENT)) {
            breeze.getBrain().setMemoryWithExpiry(MemoryModuleType.BREEZE_JUMP_INHALING, Unit.INSTANCE, INHALING_DURATION_TICKS);
        }

        breeze.setPose(Pose.INHALING);
        level.playSound(null, breeze, SoundEvents.BREEZE_CHARGE, SoundSource.HOSTILE, 1, 1);
        breeze.getBrain()
                .getMemory(MemoryModuleType.BREEZE_JUMP_TARGET)
                .ifPresent(pos -> breeze.lookAt(EntityAnchorArgument.Anchor.EYES, Vec3.atCenterOf(pos)));
    }

    @Override
    protected void tick(@Nonnull ServerLevel level, ABreeze breeze, long gameTime) {
        if (!breeze.isInWater() && breeze.getBrain().checkMemory(MemoryModuleType.BREEZE_LEAVING_WATER, MemoryStatus.VALUE_PRESENT)) {
            breeze.getBrain().eraseMemory(MemoryModuleType.BREEZE_LEAVING_WATER);
        }

        if (isFinishedInhaling(breeze)) {
            doJump(level, breeze);
        } else if (isFinishedJumping(breeze)) {
            breeze.playSound(SoundEvents.BREEZE_LAND, 1, 1);
            breeze.setPose(Pose.STANDING);
            breeze.setDiscardFriction(false);
            breeze.getBrain().setMemoryWithExpiry(
                    MemoryModuleType.BREEZE_JUMP_COOLDOWN,
                    Unit.INSTANCE,
                    breeze.getBrain().hasMemoryValue(MemoryModuleType.HURT_BY) ? 2 : 10);
            breeze.getBrain().setMemoryWithExpiry(MemoryModuleType.BREEZE_SHOOT, Unit.INSTANCE, 100);
        }
    }

    protected void doJump(@Nonnull ServerLevel level, ABreeze breeze) {
        var vec3 = breeze.getBrain()
                .getMemory(MemoryModuleType.BREEZE_JUMP_TARGET)
                .flatMap(pos -> calculateOptimalJumpVector(breeze, breeze.getRandom(), Vec3.atBottomCenterOf(pos)))
                .orElse(null);
        if (vec3 == null) {
            breeze.setPose(Pose.STANDING);
            return;
        }

        if (breeze.isInWater()) {
            breeze.getBrain().setMemory(MemoryModuleType.BREEZE_LEAVING_WATER, Unit.INSTANCE);
        }

        breeze.playSound(SoundEvents.BREEZE_JUMP, 1, 1);
        breeze.setPose(Pose.LONG_JUMPING);
        breeze.setYRot(breeze.yBodyRot);
        breeze.setDiscardFriction(true);
        breeze.setDeltaMovement(vec3);
    }

    @Override
    protected void stop(@Nonnull ServerLevel level, ABreeze breeze, long gameTime) {
        if (breeze.getPose() == Pose.LONG_JUMPING || breeze.getPose() == Pose.INHALING) {
            breeze.setPose(Pose.STANDING);
        }

        breeze.getBrain().eraseMemory(MemoryModuleType.BREEZE_JUMP_TARGET);
        breeze.getBrain().eraseMemory(MemoryModuleType.BREEZE_JUMP_INHALING);
        breeze.getBrain().eraseMemory(MemoryModuleType.BREEZE_LEAVING_WATER);
    }

    protected static boolean isFinishedInhaling(ABreeze breeze) {
        return breeze.getPose() == Pose.INHALING && breeze.getBrain()
                .getMemory(MemoryModuleType.BREEZE_JUMP_INHALING).isEmpty();
    }

    protected static boolean isFinishedJumping(ABreeze breeze) {
        return breeze.getPose() == Pose.LONG_JUMPING &&
                (breeze.onGround() || (breeze.isInWater() && breeze.getBrain()
                        .checkMemory(MemoryModuleType.BREEZE_LEAVING_WATER, MemoryStatus.VALUE_ABSENT)));
    }

    protected static @Nullable BlockPos snapToSurface(LivingEntity owner, Vec3 targetPos) {
        var clipcontext = new ClipContext(
                targetPos,
                targetPos.relative(Direction.DOWN, 10),
                ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE,
                owner);
        var hitResult = owner.level().clip(clipcontext);
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            return BlockPos.containing(hitResult.getLocation()).above();
        } else {
            var newHitResult = owner.level().clip(
                    new ClipContext(
                            targetPos,
                            targetPos.relative(Direction.UP, 10),
                            ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE,
                            owner));
            return newHitResult.getType() == HitResult.Type.BLOCK ?
                    BlockPos.containing(newHitResult.getLocation()).above() :
                    null;
        }
    }

    protected static boolean outOfAggroRange(ABreeze breeze, LivingEntity target) {
        return !target.closerThan(breeze, breeze.getAttributeValue(Attributes.FOLLOW_RANGE));
    }

    protected static boolean tooCloseForJump(ABreeze breeze, LivingEntity target) {
        return target.distanceTo(breeze) - REQUIRED_AIR_BLOCKS_ABOVE <= 0;
    }

    protected static boolean canJumpFromCurrentPosition(ServerLevel level, ABreeze breeze) {
        var pos = breeze.blockPosition();
        if (level.getBlockState(pos).is(Blocks.HONEY_BLOCK)) {
            return false;
        } else {
            for (int i = 1; i <= REQUIRED_AIR_BLOCKS_ABOVE; i++) {
                var newPos = pos.relative(Direction.UP, i);
                if (!level.getBlockState(newPos).isAir() && !level.getFluidState(newPos).is(FluidTags.WATER)) {
                    return false;
                }
            }

            return true;
        }
    }

    protected static Optional<Vec3> calculateOptimalJumpVector(ABreeze breeze, RandomSource random, Vec3 target) {
        for (int i : Util.shuffledCopy(ALLOWED_ANGLES, random)) {
            var optionalVec3 = LongJumpUtil.calculateJumpVectorForAngle(
                    breeze, target,
                    MAX_JUMP_VELOCITY_MULTIPLIER * (float) breeze.getAttributeValue(Attributes.FOLLOW_RANGE),
                    i, false);
            if (optionalVec3.isPresent()) {
                if (breeze.hasEffect(MobEffects.JUMP_BOOST)) {
                    return optionalVec3.map(vec3 -> vec3.add(
                            0,
                            optionalVec3.get().normalize().y * breeze.getJumpBoostPower(),
                            0));
                }

                return optionalVec3;
            }
        }

        return Optional.empty();
    }

}
