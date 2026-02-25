package nightkosh.withered_lands.entity.slime;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import nightkosh.withered_lands.helper.TimeHelper;
import org.jspecify.annotations.Nullable;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class ASlime extends Slime {

    public static final String TAG_SLIME_RAIN = "slime_rain";

    public ASlime(EntityType<? extends ASlime> entityType, Level level) {
        super(entityType, level);
    }

    protected abstract WeightedList<Item> getSwallowedItemList();

    protected ItemStack chooseSwallowedItem() {
        return this.random.nextInt(100) < getSwallowedItemsChance() ?
                new ItemStack(getSwallowedItemList().getRandom(this.random).get()) :
                ItemStack.EMPTY;
    }

    protected int getSwallowedItemsChance() {
        return 15;
    }

    protected int getDefaultSpawnSize() {
        return 2;
    }

    @Override
    protected boolean isDealsDamage() {
        return this.isEffectiveAi();
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
        entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, TimeHelper.SECONDS_25), this);
    }

    protected void placeBlockAtDeath(BlockState stateToPlace) {
        if (!this.level().isClientSide() && this.getSize() > 1) {
            var pos = this.blockPosition();
            var state = this.level().getBlockState(pos);
            var below = pos.below();

            if ((state.isAir() || state.canBeReplaced() || state.is(Blocks.MOSS_CARPET)) &&
                    this.level().getBlockState(below).isSolidRender()) {
                this.level().setBlock(pos, stateToPlace, 3);
            }
        }
    }

    protected static boolean isUndergroundBlock(Block ground) {
        return ground == Blocks.STONE || ground == Blocks.COBBLESTONE ||
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

        if (this.getSize() > 1) {
            var item = chooseSwallowedItem();
            if (!item.isEmpty()) {
                this.setItemSlot(EquipmentSlot.HEAD, item);
            }
        }
        return groupData;
    }

    @Override
    protected void dropCustomDeathLoot(@Nonnull ServerLevel level, @Nonnull DamageSource source, boolean recentlyHit) {
        var stack = this.getItemBySlot(EquipmentSlot.HEAD);
        if (!stack.isEmpty()) {
            if (stack.isDamageableItem()) {
                stack.setDamageValue(stack.getMaxDamage() - 10);
            }
            this.spawnAtLocation(level, stack.copy());
            this.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
        }

        super.dropCustomDeathLoot(level, source, recentlyHit);
    }

    @Override
    public void checkDespawn() {
        if (this.tickCount >= TimeHelper.SECONDS_8 || !this.getTags().contains(TAG_SLIME_RAIN)) {
            super.checkDespawn();
        }
    }

    protected static boolean checkDensity(ServerLevelAccessor level, BlockPos pos, Class clazz) {
        return level.getEntitiesOfClass(clazz, new AABB(pos).inflate(100)).size() <= 25;
    }

}
