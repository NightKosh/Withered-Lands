package nightkosh.withered_lands.entity.crawler;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import nightkosh.withered_lands.compatibility.GravestoneExtendedCompatibility;
import nightkosh.withered_lands.entity.AMonster;
import nightkosh.withered_lands.entity.ai.goal.HideInBonesGoal;
import nightkosh.withered_lands.entity.ai.goal.HideInPilesOfBonesGoal;
import nightkosh.withered_lands.entity.ai.goal.SummonSkullCrawlersGoal;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class ASkullCrawler extends AMonster {

    private static final EntityDataAccessor<Byte> CLIMBING = SynchedEntityData.defineId(ASkullCrawler.class, EntityDataSerializers.BYTE);

    private SummonSkullCrawlersGoal summonGoal;
    public HideInBonesGoal hideInBonesGoal;

    public ASkullCrawler(EntityType<? extends ASkullCrawler> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(1, new FloatGoal(this));
        if (GravestoneExtendedCompatibility.loaded()) {
            this.goalSelector.addGoal(3, this.summonGoal = new SummonSkullCrawlersGoal(this));
            this.goalSelector.addGoal(5, this.hideInBonesGoal = new HideInBonesGoal(this));
            this.goalSelector.addGoal(6, new HideInPilesOfBonesGoal(this));
        }
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1, false));

        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
    }

    @Override
    protected void playStepSound(@Nonnull BlockPos pos, @Nonnull BlockState block) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1);
    }

    @Override
    public boolean hurtServer(@Nonnull ServerLevel serverLevel, @Nonnull DamageSource damageSource, float xz) {
        if (this.isInvulnerableTo(serverLevel, damageSource)) {
            return false;
        } else {
            if ((damageSource.getEntity() != null || damageSource.is(DamageTypeTags.ALWAYS_TRIGGERS_SILVERFISH)) &&
                    this.summonGoal != null) {
                this.summonGoal.resetSummonColdown();
            }

            return super.hurtServer(serverLevel, damageSource, xz);
        }
    }

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

    public abstract Block getPilesOfBones();

    public abstract Block getBoneSkullBlock();

    public static boolean checkSpawnRules(
            EntityType<? extends ASkullCrawler> entityType, ServerLevelAccessor level,
            EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        return checkCommonSpawnRules(level, pos, random);
    }

}
