package nightkosh.withered_lands.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import nightkosh.withered_lands.core.WLConfigs;
import org.jspecify.annotations.Nullable;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WLSkeleton extends Skeleton {

    public WLSkeleton(EntityType<? extends WLSkeleton> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(
            @Nonnull ServerLevelAccessor level, @Nonnull DifficultyInstance difficulty,
            @Nonnull EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData) {
        groupData = super.finalizeSpawn(level, difficulty, spawnReason, groupData);

        this.setItemSlot(EquipmentSlot.MAINHAND, getHandItem(Items.STONE_SWORD));
        this.setItemSlot(EquipmentSlot.OFFHAND, getHandItem(Items.SHIELD));

        return groupData;
    }

    @Override
    public boolean hurtServer(@Nonnull ServerLevel level, @Nonnull DamageSource source, float amount) {
        if (source.getDirectEntity() instanceof AbstractArrow arrow &&
                this.getOffhandItem().is(Items.SHIELD) &&
                isInFrontOfMe(arrow)) {
            this.level().broadcastEntityEvent(this, (byte) 29);
            this.playSound(SoundEvents.SHIELD_BLOCK.value(), 1, 1);
            // cancel damage
            return false;
        } else {
            return super.hurtServer(level, source, amount);
        }
    }

    private boolean isInFrontOfMe(AbstractArrow arrow) {
        var look = this.getViewVector(1).normalize();
        var toProjectile = arrow.position().subtract(this.position()).normalize();

        return look.dot(toProjectile) > 0;
    }

    private ItemStack getHandItem(Item item) {
        var stack = new ItemStack(item);
        stack.setDamageValue(stack.getMaxDamage() - 30);
        return stack;
    }

    @Nonnull
    @Override
    protected Component getTypeName() {
        return EntityType.SKELETON.getDescription();
    }

    public static boolean checkSpawnRules(
            EntityType<? extends WLSkeleton> entityType, ServerLevelAccessor levelAccessor,
            EntitySpawnReason spawnReason, BlockPos blockPos, RandomSource random) {
        return WLConfigs.SKELETON_WITH_SWORD_SPAWN.get() &&
                AMonster.checkCommonSpawnRules(levelAccessor, blockPos, random);
    }

}
