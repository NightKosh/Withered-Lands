package nightkosh.withered_lands.entity.bat;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import nightkosh.withered_lands.entity.ai.BatAiStep;

import javax.annotation.Nullable;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class HostileBat extends Monster {

    public static final float TICKS_PER_FLAP = 10;

    private static final EntityDataAccessor<Byte> HANGING_FLAG = SynchedEntityData.defineId(HostileBat.class, EntityDataSerializers.BYTE);
    public final AnimationState flyAnimationState = new AnimationState();
    public final AnimationState restAnimationState = new AnimationState();
    private @Nullable BlockPos targetPosition;

    public HostileBat(EntityType<? extends HostileBat> entityType, Level level) {
        super(entityType, level);
        if (!level.isClientSide()) {
            this.setResting(true);
        }
    }

    BatAiStep fg ;
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1, false));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.addBehaviourGoals();
    }

    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    protected static boolean checkCommonSpawnRules(ServerLevelAccessor level, BlockPos pos, RandomSource random) {
        return level.getDifficulty() != Difficulty.PEACEFUL &&
                isDarkEnoughToSpawn(level, pos, random);
    }

    @Override
    protected void customServerAiStep(ServerLevel level) {
        super.customServerAiStep(level);
        BatAiStep.step(level, this);
    }

    @Override
    public boolean doHurtTarget(ServerLevel level, Entity entity) {
        if (!super.doHurtTarget(level, entity)) {
            return false;
        } else {
            if (entity instanceof LivingEntity living) {
                applyEffect(living);
            }

            return true;
        }
    }

    protected void applyEffect(LivingEntity entity) {
    }

    @Override
    public boolean isFlapping() {
        return !this.isResting() && this.tickCount % TICKS_PER_FLAP == 0;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(HANGING_FLAG, (byte) 0);
    }

    @Override
    protected float getSoundVolume() {
        return 0.1F;
    }

    @Override
    public float getVoicePitch() {
        return super.getVoicePitch() * 0.95F;
    }

    @Nullable
    @Override
    public SoundEvent getAmbientSound() {
        return this.isResting() && this.random.nextInt(4) != 0 ? null : SoundEvents.BAT_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.BAT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BAT_DEATH;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    public static AttributeSupplier createAttributeSupplier() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6)
                .add(Attributes.FOLLOW_RANGE, 30)
                .add(Attributes.ATTACK_DAMAGE, 0.5)
                .build();
    }

    public boolean isResting() {
        return (this.entityData.get(HANGING_FLAG) & 1) != 0;
    }

    public void setResting(boolean isResting) {
        byte b0 = this.entityData.get(HANGING_FLAG);
        if (isResting) {
            this.entityData.set(HANGING_FLAG, (byte) (b0 | 1));
        } else {
            this.entityData.set(HANGING_FLAG, (byte) (b0 & -2));
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isResting()) {
            this.setDeltaMovement(Vec3.ZERO);
            this.setPosRaw(this.getX(), Mth.floor(this.getY()) + 1 - this.getBbHeight(), this.getZ());
        } else {
            this.setDeltaMovement(this.getDeltaMovement().multiply(1, 0.6, 1));
        }

        this.setupAnimationStates();
    }

    @Override
    protected Entity.MovementEmission getMovementEmission() {
        return Entity.MovementEmission.EVENTS;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
    }

    @Override
    public boolean isIgnoringBlockTriggers() {
        return true;
    }

    @Override
    public boolean hurtServer(ServerLevel serverLevel, DamageSource damageSource, float xz) {
        if (this.isInvulnerableTo(serverLevel, damageSource)) {
            return false;
        } else {
            if (this.isResting()) {
                this.setResting(false);
            }

            return super.hurtServer(serverLevel, damageSource, xz);
        }
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        this.entityData.set(HANGING_FLAG, input.getByteOr("BatFlags", (byte) 0));
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putByte("BatFlags", this.entityData.get(HANGING_FLAG));
    }

    public static boolean checkBatSpawnRules(
            EntityType<Bat> entityType, LevelAccessor level, EntitySpawnReason spawnReason, BlockPos pos, RandomSource randomSource
    ) {
        if (pos.getY() >= level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE, pos).getY()) {
            return false;
        } else if (randomSource.nextBoolean()) {
            return false;
        } else if (level.getMaxLocalRawBrightness(pos) > randomSource.nextInt(4)) {
            return false;
        } else {
            return level.getBlockState(pos.below()).is(BlockTags.BATS_SPAWNABLE_ON) && checkMobSpawnRules(entityType, level, spawnReason, pos, randomSource);
        }
    }

    private void setupAnimationStates() {
        if (this.isResting()) {
            this.flyAnimationState.stop();
            this.restAnimationState.startIfStopped(this.tickCount);
        } else {
            this.restAnimationState.stop();
            this.flyAnimationState.startIfStopped(this.tickCount);
        }
    }

    public BlockPos getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(BlockPos pos) {
        this.targetPosition = pos;
    }

}