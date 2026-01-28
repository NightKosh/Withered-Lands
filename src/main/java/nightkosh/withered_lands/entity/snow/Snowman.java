package nightkosh.withered_lands.entity.snow;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.event.EventHooks;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.entity.AMonster;
import nightkosh.withered_lands.entity.projectile.FrozenSnowball;
import nightkosh.withered_lands.helper.TimeHelper;
import org.jspecify.annotations.Nullable;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Snowman extends AMonster implements RangedAttackMob {

    public Snowman(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1, 20, 15));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.level() instanceof ServerLevel level) {
            if (level.environmentAttributes().getValue(EnvironmentAttributes.SNOW_GOLEM_MELTS, this.position())) {
                this.hurtServer(level, this.damageSources().onFire(), 1);
            }

            if (!EventHooks.canEntityGrief(level, this)) {
                return;
            }

            var blockstate = Blocks.SNOW.defaultBlockState();
            for (int i = 0; i < 4; i++) {
                var blockpos = new BlockPos(
                        Mth.floor(this.getX() + (i % 2 * 2 - 1) * 0.25F),
                        Mth.floor(this.getY()),
                        Mth.floor(this.getZ() + (i / 2 % 2 * 2 - 1) * 0.25F));
                if (this.level().getBlockState(blockpos).isAir() && blockstate.canSurvive(this.level(), blockpos)) {
                    this.level().setBlockAndUpdate(blockpos, blockstate);
                    this.level().gameEvent(GameEvent.BLOCK_PLACE, blockpos, GameEvent.Context.of(this, blockstate));
                }
            }
        }
    }

    @Override
    public void performRangedAttack(LivingEntity entity, float velocity) {
        double x = entity.getX() - this.getX();
        double y = entity.getEyeY() - 1.1F;
        double z = entity.getZ() - this.getZ();
        double d = Math.sqrt(x * x + z * z) * 0.2F;
        if (this.level() instanceof ServerLevel level) {
            Projectile.spawnProjectile(
                    new FrozenSnowball(level, this),
                    level,
                    new ItemStack(Items.SNOWBALL),
                    snowball -> snowball.shoot(x, y + d - snowball.getY(), z, 1.6F, 12)
            );
        }

        this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
    }

    @Override
    public boolean isSensitiveToWater() {
        return true;
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return SoundEvents.SNOW_GOLEM_AMBIENT;
    }

    @Nonnull
    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSource) {
        return SoundEvents.SNOW_GOLEM_HURT;
    }

    @Nonnull
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SNOW_GOLEM_DEATH;
    }

    @Override
    protected void applyEffect(LivingEntity entity) {
        entity.setTicksFrozen(entity.getTicksFrozen() + TimeHelper.SECONDS_10);
    }

    public static AttributeSupplier createAttributeSupplier() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.FOLLOW_RANGE, 25)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, 1)
                .build();
    }

    public static boolean checkSpawnRules(
            EntityType<? extends Snowman> entityType, ServerLevelAccessor levelAccessor,
            EntitySpawnReason spawnReason, BlockPos blockPos, RandomSource random) {
        return WLConfigs.SNOWMAN_SPAWN.get() &&
                levelAccessor.canSeeSky(blockPos.above()) &&
                checkCommonSpawnRules(levelAccessor, blockPos, random);
    }

}
