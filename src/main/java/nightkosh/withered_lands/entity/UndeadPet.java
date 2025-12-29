package nightkosh.withered_lands.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class UndeadPet extends Monster {

    public UndeadPet(EntityType<? extends UndeadPet> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new LeapAtTargetGoal(this, 0.3F));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
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

//    @Override
//    protected void defineSynchedData(SynchedEntityData.Builder builder) {
//        super.defineSynchedData(builder);
//    }


//    /**
//     * Get this Entity's EnumCreatureAttribute
//     */
//    @Override
//    public EnumCreatureAttribute getCreatureAttribute() {
//        return EnumCreatureAttribute.UNDEAD;
//    }
//
//
//    @Override
//    public void readEntityFromNBT(NBTTagCompound nbt) {
//        super.readEntityFromNBT(nbt);
//        this.setMobType(EnumUndeadMobType.getById(nbt.getByte("Type")));
//    }
//
//    @Override
//    public void writeEntityToNBT(NBTTagCompound nbt) {
//        super.writeEntityToNBT(nbt);
//        nbt.setByte("Type", (byte) this.mobType.ordinal());
//    }

//    /**
//     * This method gets called when the entity kills another one.
//     */
//    @Override
//    public void onKillEntity(EntityLivingBase entityLiving) {
//        super.onKillEntity(entityLiving);
//
//        if (this.mobType == EnumUndeadMobType.ZOMBIE &&
//                this.getEntityWorld().getDifficulty() == EnumDifficulty.NORMAL || this.getEntityWorld().getDifficulty() == EnumDifficulty.HARD) {
//            spawnZombieMob(entityLiving);
//        }
//    }

//    protected void spawnZombieMob(EntityLivingBase entityLivingBase) {
//        if (entityLivingBase instanceof EntityLiving) {
//            EntityLiving entity = (EntityLiving) entityLivingBase;
//            EntityLiving zombie = null;
//            if (entity instanceof EntityVillager) {
//                EntityVillager villager = (EntityVillager) entityLivingBase;
//                EntityZombieVillager entityZombie = new EntityZombieVillager(this.getEntityWorld());
//                entityZombie.copyLocationAndAnglesFrom(entity);
//                this.getEntityWorld().removeEntity(entity);
//                entityZombie.onInitialSpawn(this.getEntityWorld().getDifficultyForLocation(new BlockPos(this)), null);
//
//                entityZombie.setProfession(villager.getProfession());
//                entityZombie.setChild(entityLivingBase.isChild());
//                entityZombie.setNoAI(villager.isAIDisabled());
//
//                if (villager.hasCustomName()) {
//                    entityZombie.setCustomNameTag(villager.getCustomNameTag());
//                    entityZombie.setAlwaysRenderNameTag(villager.getAlwaysRenderNameTag());
//                }
//
//                this.getEntityWorld().spawnEntity(entityZombie);
//                this.getEntityWorld().playEvent(null, 1026, new BlockPos(this), 0);
//                zombie = entityZombie;
//            } else if (entity instanceof EntityWolf) {
//                EntityZombieDog zombieDog = new EntityZombieDog(this.getEntityWorld(), false);
//                zombieDog.copyLocationAndAnglesFrom(entity);
//
//                this.getEntityWorld().removeEntity(entity);
//                this.getEntityWorld().spawnEntity(zombieDog);
//                this.getEntityWorld().playEvent(null, 1026, new BlockPos(this), 0);
//
//                zombie = zombieDog;
//            } else if (entity instanceof EntityOcelot) {
//                EntityZombieCat zombieCat = new EntityZombieCat(this.getEntityWorld(), false);
//                zombieCat.copyLocationAndAnglesFrom(entity);
//                if (((EntityOcelot) entity).isTamed()) {
//                    zombieCat.setSkin(((EntityOcelot) entity).getTameSkin());
//                } else {
//                    zombieCat.setSkin(0);
//                }
//                this.getEntityWorld().removeEntity(entity);
//
//                zombieCat.onInitialSpawn(this.getEntityWorld().getDifficultyForLocation(new BlockPos(this)), null);
//                this.getEntityWorld().spawnEntity(zombieCat);
//                this.getEntityWorld().playEvent(null, 1026, new BlockPos(this), 0);
//
//                zombie = zombieCat;
//            } else if (entity instanceof EntityHorse) {
//                EntityZombieHorse zombieHorse = new EntityZombieHorse(this.getEntityWorld());
//                zombieHorse.copyLocationAndAnglesFrom(entity);
//                zombieHorse.setGrowingAge(((EntityHorse) entity).getGrowingAge());
//
//                this.getEntityWorld().removeEntity(entity);
//                this.getEntityWorld().spawnEntity(zombieHorse);
//                this.getEntityWorld().playEvent(null, 1026, new BlockPos(this), 0);
//
//                zombie = zombieHorse;
//            }
//            if (zombie != null && entity.hasCustomName()) {
//                zombie.setCustomNameTag(entity.getCustomNameTag());
//            }
//        }
//    }

    protected static boolean checkCommonSpawnRules(ServerLevelAccessor level, BlockPos pos, RandomSource random) {
        return level.getDifficulty() != Difficulty.PEACEFUL &&
                isDarkEnoughToSpawn(level, pos, random);
    }

}
