package nightkosh.withered_lands.entity.horse;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.equine.AbstractHorse;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.monster.zombie.ZombieVillager;
import net.minecraft.world.entity.monster.zombie.ZombifiedPiglin;
import net.minecraft.world.entity.npc.villager.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.neoforged.neoforge.event.EventHooks;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.core.WLEntities;
import nightkosh.withered_lands.entity.wolf.ZombieDog;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ZombieHorse extends AUndeadHorse {

    public ZombieHorse(EntityType<? extends AUndeadHorse> entityType, Level level) {
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
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this)
                .setAlertOthers(Zombie.class, ZombifiedPiglin.class, ZombieVillager.class, ZombieDog.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));

        if (WLConfigs.ZOMBIE_PETS_ATTACK_PETS.get()) {
            this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, AbstractHorse.class, true));
        }
    }

    @Override
    public boolean killedEntity(@Nonnull ServerLevel level, @Nonnull LivingEntity entity, @Nonnull DamageSource damageSource) {
        boolean flag = super.killedEntity(level, entity, damageSource);
        if ((level.getDifficulty() == Difficulty.NORMAL || level.getDifficulty() == Difficulty.HARD) &&
                entity instanceof AbstractHorse horse && EventHooks.canLivingConvert(entity, WLEntities.ZOMBIE_HORSE.get(), (timer) -> {
        })) {
            if (level.getDifficulty() != Difficulty.HARD && this.random.nextBoolean()) {
                return flag;
            }

            if (this.convertToZombie(level, horse, WLEntities.ZOMBIE_HORSE.get())) {
                flag = false;
            }
        }

        return flag;
    }

    @Override
    protected Monster getJockey(@Nonnull ServerLevelAccessor level) {
        return EntityType.ZOMBIE.create(this.level(), EntitySpawnReason.JOCKEY);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        super.getAmbientSound();
        return SoundEvents.ZOMBIE_HORSE_AMBIENT;
    }

    @Nonnull
    @Override
    protected SoundEvent getDeathSound() {
        super.getDeathSound();
        return SoundEvents.ZOMBIE_HORSE_DEATH;
    }

    @Override
    @Nonnull
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSource) {
        super.getHurtSound(damageSource);
        return SoundEvents.ZOMBIE_HORSE_HURT;
    }

    public static AttributeSupplier createAttributeSupplier() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 25)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 5)
                .build();
    }

    public static boolean checkSpawnRules(
            EntityType<? extends AUndeadHorse> entityType, ServerLevelAccessor level,
            EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        return WLConfigs.ZOMBIE_HORSE_SPAWN.get() &&
                checkCommonSpawnRules(level, pos, random);
    }

}
