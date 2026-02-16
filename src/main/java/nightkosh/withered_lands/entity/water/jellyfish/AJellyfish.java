package nightkosh.withered_lands.entity.water.jellyfish;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import nightkosh.withered_lands.entity.ai.goal.AttackIfInWaterGoal;
import nightkosh.withered_lands.entity.ai.goal.KeepUnderwaterSwimmingGoal;
import nightkosh.withered_lands.entity.water.fish.AHostileFish;
import nightkosh.withered_lands.helper.TimeHelper;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class AJellyfish extends AHostileFish {

    public final AnimationState moveLegsAnimation = new AnimationState();
    public final AnimationState inflateAnimation = new AnimationState();

    public AJellyfish(EntityType<? extends AJellyfish> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new KeepUnderwaterSwimmingGoal(this, 1));
        super.registerGoals();
    }

    @Override
    protected boolean followBoat() {
        return false;
    }

    @Override
    protected void registerTargetGoals() {
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new AttackIfInWaterGoal(this, Player.class, false));
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.isInWater()) {
            // keep underwater
            double surfaceY = findWaterSurface(this.blockPosition());
            if (surfaceY != Double.NEGATIVE_INFINITY) {
                double top = this.getY() + this.getBbHeight();
                double maxTop = surfaceY - 0.15;

                if (top > maxTop) {
                    var dm = this.getDeltaMovement();
                    double pushDown = -0.06 - (top - maxTop) * 0.15;
                    this.setDeltaMovement(dm.x, Math.min(dm.y, pushDown), dm.z);
                }
            }
        }
    }

    protected double findWaterSurface(BlockPos start) {
        var pos = start.mutable();
        int y = start.getY();

        if (this.level().getFluidState(pos).is(FluidTags.WATER)) {
            int maxScan = 5;
            for (int i = 0; i < maxScan; i++) {
                pos.setY(y + 1);
                if (!this.level().getFluidState(pos).is(FluidTags.WATER)) {
                    return y + 1;
                }
                y++;
            }
        }
        return Double.NEGATIVE_INFINITY;
    }

    @Override
    public void tick() {
        super.tick();
        this.moveLegsAnimation.startIfStopped(this.tickCount);

        if (this.isInWater()) {
            this.inflateAnimation.startIfStopped(this.tickCount);
        } else {
            this.inflateAnimation.stop();
        }
    }

    @Override
    protected void applyEffect(LivingEntity entity) {
        entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, TimeHelper.SECONDS_25), this);
    }

    @Override
    protected SoundEvent getFlopSound() {
        return null;
    }

}
