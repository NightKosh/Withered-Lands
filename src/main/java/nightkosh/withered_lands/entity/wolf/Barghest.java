package nightkosh.withered_lands.entity.wolf;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.core.WLSounds;
import nightkosh.withered_lands.entity.ai.BarghestInvisibleGoal;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Barghest extends UndeadDog {

    private static final EntityDataAccessor<Boolean> INVISIBLE_ID = SynchedEntityData.defineId(Barghest.class, EntityDataSerializers.BOOLEAN);

    public Barghest(EntityType<? extends Barghest> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BarghestInvisibleGoal(this));

        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1, false));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(INVISIBLE_ID, true);
    }

    public static AttributeSupplier createAttributeSupplier() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 80)
                .add(Attributes.FOLLOW_RANGE, 20)
                .add(Attributes.MOVEMENT_SPEED, 0.45)
                .add(Attributes.ATTACK_DAMAGE, 5)
                .build();
    }

    @Override
    public void tick() {
        if (this.level() instanceof ServerLevel server) {
            int count = this.isBarghestInvisible() ? 5 : 10;
            server.sendParticles(ParticleTypes.SMOKE,
                    this.getX() + 1.5 - this.random.nextDouble() * 3,
                    this.getY() + 0.25 + this.random.nextDouble() * 1.5,
                    this.getZ() + 1.5 - this.random.nextDouble() * 3,
                    count,
                    0, 0, 0,
                    0);
        }

        super.tick();
    }


    @Override
    protected void applyEffect(LivingEntity entity) {
        entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200), this);
    }

    public void setBarghestInvisible(boolean isInvisible) {
        this.entityData.set(INVISIBLE_ID, isInvisible);
    }

    public boolean isBarghestInvisible() {
        return this.entityData.get(INVISIBLE_ID);
    }

    @Override
    protected void playStepSound(@Nonnull BlockPos pos, @Nonnull BlockState blockIn) {
        this.playSound(SoundEvents.WOLF_STEP, 0.15F, 1);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return WLSounds.BARGHEST_GROWL.get();
    }

    @Nonnull
    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSource) {
        return WLSounds.BARGHEST_HURT.get();
    }

    @Nonnull
    @Override
    protected SoundEvent getDeathSound() {
        return WLSounds.BARGHEST_DEATH.get();
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    public static boolean checkSpawnRules(
            EntityType<? extends Barghest> entityType, ServerLevelAccessor level,
            EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        return WLConfigs.BARGHEST_SPAWN.get() &&
                checkCommonSpawnRules(level, pos, random) &&
                (level.canSeeSky(pos.above()) || level.getLevel().dimension() != Level.OVERWORLD);
    }

}
