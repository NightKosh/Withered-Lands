package nightkosh.withered_lands.entity.water;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import nightkosh.withered_lands.entity.ai.GoToBeachGoal;
import nightkosh.withered_lands.entity.ai.GoToWaterGoal;
import nightkosh.withered_lands.entity.ai.SwimUpGoal;
import nightkosh.withered_lands.entity.ai.WaterWalkingMoveControl;
import org.jspecify.annotations.Nullable;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class AWaterWalkingMob extends Monster {

    public boolean searchingForLand;

    public AWaterWalkingMob(EntityType<? extends AWaterWalkingMob> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new WaterWalkingMoveControl(this);
        this.setPathfindingMalus(PathType.WATER, 0);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(1, new GoToWaterGoal(this, 1));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1, false));
        this.goalSelector.addGoal(5, new GoToBeachGoal(this, 1));
        this.goalSelector.addGoal(6, new SwimUpGoal(this, 1, this.level().getSeaLevel()));
        this.goalSelector.addGoal(7, new RandomStrollGoal(this, 1));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false,
                (entity, level) -> this.okTarget(entity)));
    }

    @Nonnull
    @Override
    protected PathNavigation createNavigation(@Nonnull Level level) {
        return new AmphibiousPathNavigation(this, level);
    }

    @Override
    public boolean doHurtTarget(@Nonnull ServerLevel level, @Nonnull Entity entity) {
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

    public boolean closeToNextPos() {
        var path = this.getNavigation().getPath();
        if (path != null) {
            var blockpos = path.getTarget();
            if (blockpos != null) {
                return this.distanceToSqr(blockpos.getX(), blockpos.getY(), blockpos.getZ()) < 4;
            }
        }

        return false;
    }

    public void setSearchingForLand(boolean searchingForLand) {
        this.searchingForLand = searchingForLand;
    }

    protected static boolean isDeepEnoughToSpawn(LevelAccessor levelAccessor, BlockPos blockPos) {
        return blockPos.getY() < levelAccessor.getSeaLevel() - 1;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return this.isInWater() ?
                SoundEvents.DROWNED_AMBIENT_WATER :
                SoundEvents.DROWNED_AMBIENT;
    }

    @Nonnull
    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSource) {
        return this.isInWater() ?
                SoundEvents.DROWNED_HURT_WATER :
                SoundEvents.DROWNED_HURT;
    }

    @Nonnull
    @Override
    protected SoundEvent getDeathSound() {
        return this.isInWater() ?
                SoundEvents.DROWNED_DEATH_WATER :
                SoundEvents.DROWNED_DEATH;
    }

    @Override
    protected void playStepSound(@Nonnull BlockPos blockPos, @Nonnull BlockState state) {
        this.playSound(SoundEvents.DROWNED_STEP, 0.15F, 1.0F);
    }

    @Nonnull
    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.DROWNED_SWIM;
    }

    @Override
    public boolean checkSpawnObstruction(LevelReader levelReader) {
        return levelReader.isUnobstructed(this);
    }

    public boolean okTarget(@Nullable LivingEntity entity) {
        return entity != null &&
                (!this.level().isBrightOutside() || entity.isInWater());
    }

    @Override
    protected float getWaterSlowDown() {
        return 1;
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    public boolean wantsToSwim() {
        if (this.searchingForLand) {
            return true;
        } else {
            var livingentity = this.getTarget();
            return livingentity != null && livingentity.isInWater();
        }
    }

    @Override
    protected void travelInWater(@Nonnull Vec3 vec3, double p_479966_, boolean p_479754_, double p_480313_) {
        if (this.isUnderWater() && this.wantsToSwim()) {
            this.moveRelative(0.01F, vec3);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9));
        } else {
            super.travelInWater(vec3, p_479966_, p_479754_, p_480313_);
        }
    }

    @Override
    public void updateSwimming() {
        if (!this.level().isClientSide()) {
            this.setSwimming(this.isEffectiveAi() && this.isUnderWater() && this.wantsToSwim());
        }
    }

    @Override
    public boolean isVisuallySwimming() {
        return this.isSwimming() && !this.isPassenger();
    }

    public static boolean checkCommonSpawnRules(
            ServerLevelAccessor levelAccessor, EntitySpawnReason spawnReason,
            BlockPos blockPos, RandomSource random) {

        if (!levelAccessor.getFluidState(blockPos.below()).is(FluidTags.WATER) && !EntitySpawnReason.isSpawner(spawnReason)) {
            return false;
        } else {
            boolean flag = levelAccessor.getDifficulty() != Difficulty.PEACEFUL &&
                    (EntitySpawnReason.ignoresLightRequirements(spawnReason) || isDarkEnoughToSpawn(levelAccessor, blockPos, random)) &&
                    (EntitySpawnReason.isSpawner(spawnReason) || levelAccessor.getFluidState(blockPos).is(FluidTags.WATER));
            if (!flag || !EntitySpawnReason.isSpawner(spawnReason)) {
                return isDeepEnoughToSpawn(levelAccessor, blockPos) && flag;
            } else {
                return true;
            }
        }
    }

}
