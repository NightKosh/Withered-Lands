package nightkosh.withered_lands.entity.crawler;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class ASkullCrawler extends Monster {

    private static final EntityDataAccessor<Byte> CLIMBING = SynchedEntityData.defineId(ASkullCrawler.class, EntityDataSerializers.BYTE);

    //TODO
//    private AISummonSkullCrawler summonAI;
//    private AIHideInBones hideInBonesAI;


    public ASkullCrawler(EntityType<? extends ASkullCrawler> entityType, Level level) {
        super(entityType, level);

    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(1, new FloatGoal(this));
//        this.goalSelector.addGoal(3, new AISummonSkullCrawler(this));//TODO
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1, false));
//        this.goalSelector.addGoal(5, this.hideInBonesAI = new AIHideInBones(this));//TODO
//        this.goalSelector.addGoal(6, new AIHideInPilesOfBones(this));//TODO

        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
    }

    @Override
    public boolean doHurtTarget(@Nonnull ServerLevel level, @Nonnull net.minecraft.world.entity.Entity entity) {
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
    protected void playStepSound(@Nonnull BlockPos pos, @Nonnull BlockState block) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1);
    }

    //TODO
//    @Override
//    public boolean hurtServer(ServerLevel serverLevel, DamageSource damageSource, float p_376305_) {
//        if (this.isInvulnerableTo(serverLevel, damageSource)) {
//            return false;
//        } else {
//            if ((source instanceof EntityDamageSource || source == DamageSource.MAGIC)) {
//                summonAI.resetSummonColdown();
//            }
//            if ((damageSource.getEntity() != null || damageSource.is(DamageTypeTags.ALWAYS_TRIGGERS_SILVERFISH)) && this.friendsGoal != null) {
//                this.summonAI.notifyHurt();
//            }
//
//            return super.hurtServer(serverLevel, damageSource, p_376305_);
//        }
//    }

    @Nonnull
    @Override
    protected PathNavigation createNavigation(@Nonnull Level level) {
        return new WallClimberNavigation(this, level);
    }

    @Override
    protected void defineSynchedData(@Nonnull SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(CLIMBING, (byte) 0);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide()) {
            this.setClimbing(this.horizontalCollision);
        }
    }

    @Override
    public boolean onClimbable() {
        return this.isClimbing();
    }

    @Override
    public void makeStuckInBlock(BlockState state, @Nonnull Vec3 vec3) {
        if (!state.is(Blocks.COBWEB)) {
            super.makeStuckInBlock(state, vec3);
        }
    }

    public boolean isClimbing() {
        return (this.entityData.get(CLIMBING) & 1) != 0;
    }

    public void setClimbing(boolean climbing) {
        byte b0 = this.entityData.get(CLIMBING);
        if (climbing) {
            b0 = (byte) (b0 | 1);
        } else {
            b0 = (byte) (b0 & -2);
        }

        this.entityData.set(CLIMBING, b0);
    }

    public boolean canHideInBones() {
        return false;
    }

    //TODO
//    public AIHideInBones getHideInBonesAI() {
//        return hideInBonesAI;
//    }

    public static boolean checkSpawnRules(
            EntityType<? extends ASkullCrawler> entityType, ServerLevelAccessor level,
            EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        return checkCommonSpawnRules(level, pos, random);
    }

    protected static boolean checkCommonSpawnRules(ServerLevelAccessor level, BlockPos pos, RandomSource random) {
        return level.getDifficulty() != Difficulty.PEACEFUL &&
                isDarkEnoughToSpawn(level, pos, random);
    }

}
