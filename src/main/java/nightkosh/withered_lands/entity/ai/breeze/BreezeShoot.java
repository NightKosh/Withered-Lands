package nightkosh.withered_lands.entity.ai.breeze;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableMap;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import nightkosh.withered_lands.entity.breeze.ABreeze;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BreezeShoot extends Behavior<ABreeze> {

    protected static final int ATTACK_RANGE_MAX_SQRT = 256;
    protected static final int UNCERTAINTY_BASE = 5;
    protected static final int UNCERTAINTY_MULTIPLIER = 4;
    protected static final float PROJECTILE_MOVEMENT_SCALE = 0.7F;
    protected static final int SHOOT_INITIAL_DELAY_TICKS = 15;
    protected static final int SHOOT_RECOVER_DELAY_TICKS = 4;

    @VisibleForTesting
    public BreezeShoot() {
        super(
                ImmutableMap.of(
                        MemoryModuleType.ATTACK_TARGET,
                        MemoryStatus.VALUE_PRESENT,
                        MemoryModuleType.BREEZE_SHOOT_COOLDOWN,
                        MemoryStatus.VALUE_ABSENT,
                        MemoryModuleType.BREEZE_SHOOT_CHARGING,
                        MemoryStatus.VALUE_ABSENT,
                        MemoryModuleType.BREEZE_SHOOT_RECOVERING,
                        MemoryStatus.VALUE_ABSENT,
                        MemoryModuleType.BREEZE_SHOOT,
                        MemoryStatus.VALUE_PRESENT,
                        MemoryModuleType.WALK_TARGET,
                        MemoryStatus.VALUE_ABSENT,
                        MemoryModuleType.BREEZE_JUMP_TARGET,
                        MemoryStatus.VALUE_ABSENT
                ),
                SHOOT_INITIAL_DELAY_TICKS + 1 + SHOOT_RECOVER_DELAY_TICKS
        );
    }

    @Override
    protected boolean checkExtraStartConditions(@Nonnull ServerLevel level, ABreeze breeze) {
        return breeze.getPose() == Pose.STANDING && breeze.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET)
                .map(range -> isTargetWithinRange(breeze, range))
                .map(aBoolean -> {
                    if (!aBoolean) {
                        breeze.getBrain().eraseMemory(MemoryModuleType.BREEZE_SHOOT);
                    }

                    return aBoolean;
                }).orElse(false);
    }

    @Override
    protected boolean canStillUse(@Nonnull ServerLevel level, ABreeze breeze, long gameTime) {
        return breeze.getBrain().hasMemoryValue(MemoryModuleType.ATTACK_TARGET) &&
                breeze.getBrain().hasMemoryValue(MemoryModuleType.BREEZE_SHOOT);
    }

    @Override
    protected void start(@Nonnull ServerLevel level, ABreeze breeze, long gameTime) {
        breeze.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET)
                .ifPresent(entity -> breeze.setPose(Pose.SHOOTING));
        breeze.getBrain().setMemoryWithExpiry(
                MemoryModuleType.BREEZE_SHOOT_CHARGING,
                Unit.INSTANCE,
                SHOOT_INITIAL_DELAY_TICKS);
        breeze.playSound(SoundEvents.BREEZE_INHALE, 1, 1);
    }

    @Override
    protected void stop(@Nonnull ServerLevel level, ABreeze breeze, long gameTime) {
        if (breeze.getPose() == Pose.SHOOTING) {
            breeze.setPose(Pose.STANDING);
        }

        breeze.getBrain().setMemoryWithExpiry(
                MemoryModuleType.BREEZE_SHOOT_COOLDOWN,
                Unit.INSTANCE,
                getShootCooldownTicks());
        breeze.getBrain().eraseMemory(MemoryModuleType.BREEZE_SHOOT);
    }

    @Override
    protected void tick(@Nonnull ServerLevel level, ABreeze breeze, long gameTime) {
        var brain = breeze.getBrain();
        var target = brain.getMemory(MemoryModuleType.ATTACK_TARGET).orElse(null);
        if (target != null) {
            breeze.lookAt(EntityAnchorArgument.Anchor.EYES, target.position());
            if (!brain.getMemory(MemoryModuleType.BREEZE_SHOOT_CHARGING).isPresent() &&
                    !brain.getMemory(MemoryModuleType.BREEZE_SHOOT_RECOVERING).isPresent()) {
                brain.setMemoryWithExpiry(
                        MemoryModuleType.BREEZE_SHOOT_RECOVERING,
                        Unit.INSTANCE,
                        SHOOT_RECOVER_DELAY_TICKS);
                attack(level, breeze, target);
            }
        }
    }

    protected void attack(@Nonnull ServerLevel level, ABreeze breeze, LivingEntity target) {
        Projectile.spawnProjectileUsingShoot(
                breeze.getWindCharge(breeze, level),
                level, ItemStack.EMPTY,
                target.getX() - breeze.getX(),
                target.getY(target.isPassenger() ? 0.8 : 0.3) - breeze.getFiringYPosition(),
                target.getZ() - breeze.getZ(),
                PROJECTILE_MOVEMENT_SCALE,
                UNCERTAINTY_BASE - level.getDifficulty().getId() * UNCERTAINTY_MULTIPLIER);
        breeze.playSound(SoundEvents.BREEZE_SHOOT, 1.5F, 1);
    }

    protected int getShootCooldownTicks() {
        return 10;
    }

    protected static boolean isTargetWithinRange(ABreeze breeze, LivingEntity target) {
        return breeze.position().distanceToSqr(target.position()) < ATTACK_RANGE_MAX_SQRT;
    }

}
