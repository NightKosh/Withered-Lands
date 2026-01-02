package nightkosh.withered_lands.entity.cat;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.feline.Cat;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.monster.zombie.ZombieVillager;
import net.minecraft.world.entity.monster.zombie.ZombifiedPiglin;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.EventHooks;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.core.WLEntities;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ZombieCat extends AUndeadCat {

    public ZombieCat(EntityType<? extends ZombieCat> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1, false));
        this.goalSelector.addGoal(4, new MoveTowardsRestrictionGoal(this, 1));
        this.goalSelector.addGoal(5, new MoveThroughVillageGoal(this, 1, true, 4, () -> false));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new OcelotAttackGoal(this));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this)
                .setAlertOthers(Zombie.class, ZombifiedPiglin.class, ZombieVillager.class, ZombieCat.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));

        if (WLConfigs.ZOMBIE_PETS_ATTACK_PETS.get()) {
            this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Cat.class, true));
        }
    }

    @Override
    public boolean killedEntity(@Nonnull ServerLevel level, @Nonnull LivingEntity entity, @Nonnull DamageSource damageSource) {
        boolean flag = super.killedEntity(level, entity, damageSource);
        if ((level.getDifficulty() == Difficulty.NORMAL || level.getDifficulty() == Difficulty.HARD) &&
                entity instanceof Cat cat && EventHooks.canLivingConvert(entity, WLEntities.ZOMBIE_CAT.get(), (timer) -> {
        })) {
            if (level.getDifficulty() != Difficulty.HARD && this.random.nextBoolean()) {
                return flag;
            }

            if (this.convertToZombie(level, cat)) {
                flag = false;
            }
        }

        return flag;
    }

    public boolean convertToZombie(ServerLevel level, Cat pet) {
        var zombie = pet.convertTo(
                WLEntities.ZOMBIE_CAT.get(),
                ConversionParams.single(pet, true, true),
                outcome -> {
                    outcome.finalizeSpawn(
                            level,
                            level.getCurrentDifficultyAt(outcome.blockPosition()),
                            EntitySpawnReason.CONVERSION,
                            new Zombie.ZombieGroupData(false, true)
                    );
                    EventHooks.onLivingConvert(pet, outcome);
                    if (!this.isSilent()) {
                        level.levelEvent(null, 1026, this.blockPosition(), 0);
                    }
                }
        );
        return zombie != null;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.CAT_STRAY_AMBIENT;
    }

    @Nonnull
    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSource) {
        return SoundEvents.CAT_HURT;
    }

    @Nonnull
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.CAT_DEATH;
    }

    @Override
    protected void playStepSound(@Nonnull BlockPos pos, @Nonnull BlockState state) {

    }

    public static AttributeSupplier createAttributeSupplier() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 1)
                .build();
    }

    public static boolean checkSpawnRules(
            EntityType<? extends ZombieCat> entityType, ServerLevelAccessor level,
            EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        return WLConfigs.ZOMBIE_CAT.get() &&
                checkCommonSpawnRules(level, pos, random);
    }

}
