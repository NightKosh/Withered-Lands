package nightkosh.withered_lands.entity.water.jellyfish;

import net.minecraft.sounds.SoundEvent;
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
            if (this.level().getFluidState(this.blockPosition().above()).isEmpty()) {
                this.setDeltaMovement(this.getDeltaMovement().add(0, -0.05, 0));
            }
        }
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
