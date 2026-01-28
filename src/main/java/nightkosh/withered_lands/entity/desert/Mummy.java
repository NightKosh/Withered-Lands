package nightkosh.withered_lands.entity.desert;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.zombie.ZombifiedPiglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.core.WLSounds;
import nightkosh.withered_lands.entity.AMonster;
import nightkosh.withered_lands.helper.TimeHelper;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Mummy extends AMonster {

    public Mummy(EntityType<? extends Mummy> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1, false));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers(ZombifiedPiglin.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    protected void applyEffect(LivingEntity entity) {
        entity.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.WEAKNESS, TimeHelper.SECONDS_15), this);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return WLSounds.MUMMY_AMBIENT.get();
    }

    @Nonnull
    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSource) {
        return SoundEvents.HUSK_HURT;
    }

    @Nonnull
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.HUSK_DEATH;
    }

    @Override
    protected void playStepSound(@Nonnull BlockPos blockPos, @Nonnull BlockState state) {
        this.playSound(WLSounds.MUMMY_STEP.get(), 0.15F, 1);
    }

    public static AttributeSupplier createAttributeSupplier() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 4)
                .add(Attributes.FOLLOW_RANGE, 35)
                .add(Attributes.MAX_HEALTH, 30)
                .add(Attributes.ARMOR, 4)
                .build();
    }

    public static boolean checkSpawnRules(
            EntityType<? extends Mummy> entityType, ServerLevelAccessor levelAccessor,
            EntitySpawnReason spawnReason, BlockPos blockPos, RandomSource random) {
        return WLConfigs.MUMMY_SPAWN.get() &&
                checkCommonSpawnRules(levelAccessor, blockPos, random);
    }

}
