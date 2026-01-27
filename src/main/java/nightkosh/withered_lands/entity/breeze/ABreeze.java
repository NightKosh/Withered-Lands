package nightkosh.withered_lands.entity.breeze;

import com.mojang.serialization.Dynamic;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.debug.DebugBreezeInfo;
import net.minecraft.util.debug.DebugSubscriptions;
import net.minecraft.util.profiling.Profiler;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import nightkosh.withered_lands.entity.ai.breeze.BreezeAi;
import nightkosh.withered_lands.entity.projectile.AWindCharge;
import org.jspecify.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Optional;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class ABreeze extends Monster {
    private static final int SLIDE_PARTICLES_AMOUNT = 20;
    private static final int IDLE_PARTICLES_AMOUNT = 1;
    private static final int JUMP_TRAIL_PARTICLES_AMOUNT = 3;
    private static final int JUMP_TRAIL_DURATION_TICKS = 5;
    private static final int JUMP_CIRCLE_DISTANCE_Y = 10;
    private static final int WHIRL_SOUND_FREQUENCY_MIN = 1;
    private static final int WHIRL_SOUND_FREQUENCY_MAX = 80;

    private int jumpTrailStartedTick = 0;
    private int soundTick = 0;

    public AnimationState idle = new AnimationState();
    public AnimationState slide = new AnimationState();
    public AnimationState slideBack = new AnimationState();
    public AnimationState longJump = new AnimationState();
    public AnimationState shoot = new AnimationState();
    public AnimationState inhale = new AnimationState();

    private static final ProjectileDeflection PROJECTILE_DEFLECTION = (projectile, entity, randomSource) -> {
        entity.level().playSound(null, entity, SoundEvents.BREEZE_DEFLECT, entity.getSoundSource(), 1, 1);
        ProjectileDeflection.REVERSE.deflect(projectile, entity, randomSource);
    };

    public ABreeze(EntityType<? extends ABreeze> entityType, Level level) {
        super(entityType, level);
        this.setPathfindingMalus(PathType.DAMAGE_FIRE, -1);
        this.xpReward = 10;
    }

    @Override
    protected Brain<?> makeBrain(Dynamic<?> dynamic) {
        return BreezeAi.makeBrain(this, this.brainProvider().makeBrain(dynamic), false);
    }

    @Override
    public Brain<ABreeze> getBrain() {
        return (Brain<ABreeze>) super.getBrain();
    }

    @Override
    protected Brain.Provider<ABreeze> brainProvider() {
        return Brain.provider(BreezeAi.MEMORY_TYPES, BreezeAi.SENSOR_TYPES);
    }

    @Override
    public void onSyncedDataUpdated(@Nonnull EntityDataAccessor<?> dataAccessor) {
        if (this.level().isClientSide() && DATA_POSE.equals(dataAccessor)) {
            this.resetAnimations();
            switch (this.getPose()) {
                case SHOOTING:
                    this.shoot.startIfStopped(this.tickCount);
                    break;
                case INHALING:
                    this.inhale.startIfStopped(this.tickCount);
                    break;
                case SLIDING:
                    this.slide.startIfStopped(this.tickCount);
            }
        }

        super.onSyncedDataUpdated(dataAccessor);
    }

    private void resetAnimations() {
        this.shoot.stop();
        this.idle.stop();
        this.inhale.stop();
        this.longJump.stop();
    }

    @Override
    public void tick() {
        var pose = this.getPose();
        switch (pose) {
            case SHOOTING:
            case INHALING:
            case STANDING:
                this.resetJumpTrail().emitGroundParticles(IDLE_PARTICLES_AMOUNT + this.getRandom().nextInt(1));
                break;
            case SLIDING:
                this.emitGroundParticles(SLIDE_PARTICLES_AMOUNT);
                break;
            case LONG_JUMPING:
                this.longJump.startIfStopped(this.tickCount);
                this.emitJumpTrailParticles();
        }

        this.idle.startIfStopped(this.tickCount);
        if (pose != Pose.SLIDING && this.slide.isStarted()) {
            this.slideBack.start(this.tickCount);
            this.slide.stop();
        }

        this.soundTick = this.soundTick == 0 ?
                this.random.nextIntBetweenInclusive(WHIRL_SOUND_FREQUENCY_MIN, WHIRL_SOUND_FREQUENCY_MAX) :
                this.soundTick - 1;
        if (this.soundTick == 0) {
            this.playWhirlSound();
        }

        if (this.level().isClientSide()) {
            for (int i = 0; i < 2; i++) {
                addParticle(getParticle(),
                        this.getX() + 0.5 - this.random.nextDouble(),
                        this.getY() + 0.75 + this.random.nextDouble() * 0.5,
                        this.getZ() + 0.5 - this.random.nextDouble());
            }
        }

        super.tick();
    }

    public @Nonnull ABreeze resetJumpTrail() {
        this.jumpTrailStartedTick = 0;
        return this;
    }

    public void emitJumpTrailParticles() {
        if (++this.jumpTrailStartedTick <= JUMP_TRAIL_DURATION_TICKS) {
            var vec3 = this.position().add(this.getDeltaMovement())
                    .add(0, 0.1F, 0);

            for (int i = 0; i < JUMP_TRAIL_PARTICLES_AMOUNT; i++) {
                addParticle(getParticle(), vec3.x, vec3.y, vec3.z);
            }
        }
    }

    public void emitGroundParticles(int count) {
        if (!this.isPassenger()) {
            var vec3 = this.getBoundingBox().getCenter();
            var vec31 = new Vec3(vec3.x, this.position().y, vec3.z);
            for (int i = 0; i < count; i++) {
                addParticle(getParticle(), vec31.x, vec31.y, vec31.z);
            }
        }
    }

    @Override
    public void playAmbientSound() {
        if (this.getTarget() == null || !this.onGround()) {
            this.level().playLocalSound(this, this.getAmbientSound(), this.getSoundSource(), 1, 1);
        }
    }

    public void playWhirlSound() {
        this.level().playLocalSound(this,
                SoundEvents.BREEZE_WHIRL, this.getSoundSource(),
                0.8F + 0.2F * this.random.nextFloat(),
                0.7F + 0.4F * this.random.nextFloat());
    }

    @Nonnull
    @Override
    public ProjectileDeflection deflection(Projectile projectile) {
        if (projectile.getType() != EntityType.BREEZE_WIND_CHARGE &&
                projectile.getType() != EntityType.WIND_CHARGE) {
            return this.getType().is(EntityTypeTags.DEFLECTS_PROJECTILES) ? PROJECTILE_DEFLECTION : ProjectileDeflection.NONE;
        } else {
            return ProjectileDeflection.NONE;
        }
    }

    @Nonnull
    @Override
    public SoundSource getSoundSource() {
        return SoundSource.HOSTILE;
    }

    @Nonnull
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BREEZE_DEATH;
    }

    @Nonnull
    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSource) {
        return SoundEvents.BREEZE_HURT;
    }

    @Nonnull
    @Override
    protected SoundEvent getAmbientSound() {
        return this.onGround() ? SoundEvents.BREEZE_IDLE_GROUND : SoundEvents.BREEZE_IDLE_AIR;
    }

    @Nonnull
    public Optional<LivingEntity> getHurtBy() {
        return this.getBrain()
                .getMemory(MemoryModuleType.HURT_BY)
                .map(DamageSource::getEntity)
                .filter(entity -> entity instanceof LivingEntity)
                .map(entity -> (LivingEntity) entity);
    }

    public boolean withinInnerCircleRange(Vec3 pos) {
        return pos.closerThan(this.blockPosition().getCenter(), 4, JUMP_CIRCLE_DISTANCE_Y);
    }

    @Override
    protected void customServerAiStep(@Nonnull ServerLevel level) {
        var profilerfiller = Profiler.get();
        profilerfiller.push("breezeBrain");
        this.getBrain().tick(level, this);
        profilerfiller.popPush("breezeActivityUpdate");
        BreezeAi.updateActivity(this);
        profilerfiller.pop();
        super.customServerAiStep(level);
    }

    @Override
    public boolean canAttackType(@Nonnull EntityType<?> entityType) {
        return entityType == EntityType.PLAYER || entityType == EntityType.IRON_GOLEM;
    }

    @Override
    public int getMaxHeadYRot() {
        return 30;
    }

    @Override
    public int getHeadRotSpeed() {
        return 25;
    }

    public double getFiringYPosition() {
        return this.getY() + this.getBbHeight() / 2.0F + 0.3F;
    }

    @Override
    public boolean isInvulnerableTo(@Nonnull ServerLevel level, DamageSource damageSource) {
        return damageSource.getEntity() instanceof ABreeze || super.isInvulnerableTo(level, damageSource);
    }

    @Override
    public double getFluidJumpThreshold() {
        return this.getEyeHeight();
    }

    @Override
    public boolean causeFallDamage(double fallDistance, float damageMultiplier, @Nonnull DamageSource damageSource) {
        if (fallDistance > 3) {
            this.playSound(SoundEvents.BREEZE_LAND, 1, 1);
        }

        return super.causeFallDamage(fallDistance, damageMultiplier, damageSource);
    }

    @Nonnull
    @Override
    protected MovementEmission getMovementEmission() {
        return MovementEmission.EVENTS;
    }

    @Override
    public @Nullable LivingEntity getTarget() {
        return this.getTargetFromBrain();
    }

    @Override
    public void registerDebugValues(@Nonnull ServerLevel serverLevel, @Nonnull Registration registration) {
        super.registerDebugValues(serverLevel, registration);
        registration.register(
                DebugSubscriptions.BREEZES,
                () -> new DebugBreezeInfo(
                        this.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET)
                                .map(Entity::getId),
                        this.getBrain().getMemory(MemoryModuleType.BREEZE_JUMP_TARGET)));
    }

    protected void addParticle(@Nonnull ParticleOptions particle, double x, double y, double z) {
        this.level().addParticle(particle, x, y, z, 0, 0, 0);
    }

    public abstract AWindCharge getWindCharge(ABreeze breeze, Level level);

    protected abstract ParticleOptions getParticle();

    protected static boolean checkCommonSpawnRules(ServerLevelAccessor level, BlockPos pos) {
        return level.getDifficulty() != Difficulty.PEACEFUL &&
                (level.getLevel().isRaining() || level.getLevel().isThundering()) &&
                level.canSeeSky(pos) &&
                level.getBrightness(LightLayer.BLOCK, pos) == 0;
    }

}
