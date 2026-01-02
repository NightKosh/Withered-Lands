package nightkosh.withered_lands.entity.slime;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class ASlime extends Slime {

    protected static final List<Item> MIDDLE_ITEMS = List.of(
            Items.BONE,
            Items.BONE,
            Items.ROTTEN_FLESH,
            Items.ROTTEN_FLESH,
            Items.ARROW,
            Items.SPIDER_EYE);

    public ASlime(EntityType<? extends ASlime> entityType, Level level) {
        super(entityType, level);
    }

    protected int getDefaultSpawnSize() {
        return 2;
    }

    @Override
    protected void dealDamage(@Nonnull LivingEntity entity) {
        if (this.level() instanceof ServerLevel serverlevel &&
                this.isAlive() &&
                this.isWithinMeleeAttackRange(entity) &&
                this.hasLineOfSight(entity)) {
            var damagesource = this.damageSources().mobAttack(this);
            if (entity.hurtServer(serverlevel, damagesource, this.getAttackDamage())) {
                this.playSound(SoundEvents.SLIME_ATTACK, 1, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1);
                EnchantmentHelper.doPostAttackEffects(serverlevel, entity, damagesource);
                applyEffect(entity);
            }
        }
    }

    protected void applyEffect(LivingEntity entity) {
        entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 150), this);
    }

    protected static boolean isUndergroundBlock(Block ground) {
        return ground == Blocks.STONE ||  ground == Blocks.COBBLESTONE ||
                ground == Blocks.GRANITE || ground == Blocks.ANDESITE || ground == Blocks.DIORITE ||
                ground == Blocks.BLACKSTONE || ground == Blocks.DEEPSLATE ||
                ground == Blocks.DIRT || ground == Blocks.GRAVEL ||
                ground == Blocks.SAND || ground == Blocks.RED_SAND;
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(
            @Nonnull ServerLevelAccessor level, @Nonnull DifficultyInstance difficulty,
            @Nonnull EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData) {
        groupData = super.finalizeSpawn(level, difficulty, spawnReason, groupData);
        this.setSize(getDefaultSpawnSize(), true);
        return groupData;
    }

    //TODO
//    @Nullable
//    @Override
//    public SpawnGroupData finalizeSpawn(
//            @Nonnull ServerLevelAccessor level, @Nonnull DifficultyInstance difficulty,
//            @Nonnull EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData) {
//        groupData = super.finalizeSpawn(level, difficulty, spawnReason, groupData);
//        livingdata = super.onInitialSpawn(difficulty, livingdata);
//
//        if (getSize() > 1) {
//            this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(MIDDLE_ITEMS[RAND.nextInt(MIDDLE_ITEMS.length)], 1 + RAND.nextInt(5)));
//            this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(MIDDLE_ITEMS[RAND.nextInt(MIDDLE_ITEMS.length)], 1 + RAND.nextInt(5)));
//            this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(MIDDLE_ITEMS[RAND.nextInt(MIDDLE_ITEMS.length)], 1 + RAND.nextInt(5)));
//            this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(MIDDLE_ITEMS[RAND.nextInt(MIDDLE_ITEMS.length)], 1 + RAND.nextInt(5)));
//            if (getSize() >= 2) {
//            } else {
//                this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(MIDDLE_ITEMS[RAND.nextInt(MIDDLE_ITEMS.length)], 1 + RAND.nextInt(5)));
//            }
//        }
//        return livingdata;
//    }
//
//    @Override
//    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
//        super.dropFewItems(wasRecentlyHit, lootingModifier);
//
//        this.entityDropItem(this.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND), 0);
//        this.entityDropItem(this.getItemStackFromSlot(EntityEquipmentSlot.HEAD), 0);
//        this.entityDropItem(this.getItemStackFromSlot(EntityEquipmentSlot.CHEST), 0);
//        this.entityDropItem(this.getItemStackFromSlot(EntityEquipmentSlot.LEGS), 0);
//        this.entityDropItem(this.getItemStackFromSlot(EntityEquipmentSlot.FEET), 0);
//    }
//
//    @Override
//    public boolean getCanSpawnHere() {
//        if (this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.posY <= 30 && this.isValidLightLevel() &&
//                MobsHelper.isDimensionAllowedForSpawn(this.world)) {
//            IBlockState state = this.world.getBlockState((new BlockPos(this)).down());
//            Block block = state.getBlock();
//            return state.canEntitySpawn(this) &&
//                    (block == Blocks.STONE || block == Blocks.COBBLESTONE || block == Blocks.DIRT || block == Blocks.GRAVEL);
//        } else {
//            return false;
//        }
//    }

}
