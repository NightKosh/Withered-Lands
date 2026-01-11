package nightkosh.withered_lands.entity.swamp;

import com.mojang.serialization.Dynamic;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.util.profiling.Profiler;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.entity.ai.GiantFrogAi;
import nightkosh.withered_lands.entity.ai.look_control.FrogLC;
import nightkosh.withered_lands.entity.ai.navigation.GiantFrogPathNavigation;
import org.jspecify.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GiantFrog extends Monster {

    private static final EntityDataAccessor<OptionalInt> DATA_TONGUE_TARGET_ID = SynchedEntityData.defineId(
            GiantFrog.class, EntityDataSerializers.OPTIONAL_UNSIGNED_INT
    );

    public final AnimationState jumpAnimationState = new AnimationState();
    public final AnimationState croakAnimationState = new AnimationState();
    public final AnimationState tongueAnimationState = new AnimationState();
    public final AnimationState swimIdleAnimationState = new AnimationState();

    public GiantFrog(EntityType<? extends GiantFrog> entityType, Level level) {
        super(entityType, level);
        this.lookControl = new FrogLC(this);
        this.setPathfindingMalus(PathType.WATER, 4);
        this.setPathfindingMalus(PathType.TRAPDOOR, -1);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
    }

    @Nonnull
    @Override
    protected Brain.Provider<GiantFrog> brainProvider() {
        return Brain.provider(GiantFrogAi.MEMORY_TYPES, GiantFrogAi.SENSOR_TYPES);
    }

    @Nonnull
    @Override
    protected Brain<?> makeBrain(@Nonnull Dynamic<?> dynamic) {
        return GiantFrogAi.makeBrain(this.brainProvider().makeBrain(dynamic));
    }

    @Nonnull
    @Override
    public Brain<GiantFrog> getBrain() {
        return (Brain<GiantFrog>) super.getBrain();
    }

    @Override
    protected void defineSynchedData(@Nonnull SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_TONGUE_TARGET_ID, OptionalInt.empty());
    }

    public void eraseTongueTarget() {
        this.entityData.set(DATA_TONGUE_TARGET_ID, OptionalInt.empty());
    }

    public Optional<Entity> getTongueTarget() {
        return this.entityData.get(DATA_TONGUE_TARGET_ID).stream()
                .mapToObj(id -> this.level().getEntity(id))
                .filter(Objects::nonNull)
                .findFirst();
    }

    public void setTongueTarget(Entity tongueTarget) {
        this.entityData.set(DATA_TONGUE_TARGET_ID, OptionalInt.of(tongueTarget.getId()));
    }

    @Override
    public int getHeadRotSpeed() {
        return 35;
    }

    @Override
    public int getMaxHeadYRot() {
        return 5;
    }

    @Override
    protected void customServerAiStep(@Nonnull ServerLevel level) {
        ProfilerFiller profilerfiller = Profiler.get();
        profilerfiller.push("frogBrain");
        this.getBrain().tick(level, this);
        profilerfiller.pop();
        profilerfiller.push("frogActivityUpdate");
        GiantFrogAi.updateActivity(this);
        profilerfiller.pop();
        super.customServerAiStep(level);
    }

    @Override
    public void tick() {
        if (this.level().isClientSide()) {
            this.swimIdleAnimationState.animateWhen(
                    this.isInWater() && !this.walkAnimation.isMoving(),
                    this.tickCount);
        }

        super.tick();
    }

    @Override
    public void onSyncedDataUpdated(@Nonnull EntityDataAccessor<?> dataAccessor) {
        if (DATA_POSE.equals(dataAccessor)) {
            Pose pose = this.getPose();
            if (pose == Pose.LONG_JUMPING) {
                this.jumpAnimationState.start(this.tickCount);
            } else {
                this.jumpAnimationState.stop();
            }

            if (pose == Pose.CROAKING) {
                this.croakAnimationState.start(this.tickCount);
            } else {
                this.croakAnimationState.stop();
            }

            if (pose == Pose.USING_TONGUE) {
                this.tongueAnimationState.start(this.tickCount);
            } else {
                this.tongueAnimationState.stop();
            }
        }

        super.onSyncedDataUpdated(dataAccessor);
    }

    @Override
    protected void updateWalkAnimation(float partialTick) {
        float f;
        if (this.jumpAnimationState.isStarted()) {
            f = 0;
        } else {
            f = Math.min(partialTick * 25, 1);
        }

        this.walkAnimation.update(f, 0.4F, 1);
    }

    @Override
    public SpawnGroupData finalizeSpawn(
            ServerLevelAccessor level, @Nonnull DifficultyInstance difficulty,
            @Nonnull EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData) {
        GiantFrogAi.initMemories(this, level.getRandom());

        return super.finalizeSpawn(level, difficulty, spawnReason, groupData);
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    protected void travelInWater(@Nonnull Vec3 vec3, double x, boolean y, double z) {
        this.moveRelative(this.getSpeed(), vec3);
        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.9));
    }

    @Override
    protected PathNavigation createNavigation(@Nonnull Level level) {
        return new GiantFrogPathNavigation(this, level);
    }

    @Override
    public @Nullable LivingEntity getTarget() {
        return this.getTargetFromBrain();
    }

    @Nonnull
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.FROG_AMBIENT;
    }

    @Nonnull
    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSource) {
        return SoundEvents.FROG_HURT;
    }

    @Nonnull
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.FROG_DEATH;
    }

    @Override
    protected void playStepSound(@Nonnull BlockPos blockPos, @Nonnull BlockState state) {
        this.playSound(SoundEvents.FROG_STEP, 0.15F, 1);
    }

    public static AttributeSupplier createAttributeSupplier() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 1)
                .add(Attributes.FOLLOW_RANGE, 15)
                .add(Attributes.ATTACK_DAMAGE, 15)
                .add(Attributes.MAX_HEALTH, 40)
                .add(Attributes.STEP_HEIGHT, 1)
                .add(Attributes.ARMOR, 1)
                .build();
    }

    public static boolean checkSpawnRules(
            EntityType<? extends GiantFrog> entityType, ServerLevelAccessor levelAccessor,
            EntitySpawnReason spawnReason, BlockPos blockPos, RandomSource random) {
        return WLConfigs.GIANT_FROG_SPAWN.get() &&
                checkCommonSpawnRules(levelAccessor, spawnReason, blockPos);
    }

    public static boolean checkCommonSpawnRules(
            ServerLevelAccessor levelAccessor, EntitySpawnReason spawnReason, BlockPos pos) {
        return EntitySpawnReason.isSpawner(spawnReason) ||
                levelAccessor.getDifficulty() != Difficulty.PEACEFUL &&
                        levelAccessor.getBrightness(LightLayer.BLOCK, pos) == 0 &&
                        levelAccessor.canSeeSky(pos.above());
    }

}
