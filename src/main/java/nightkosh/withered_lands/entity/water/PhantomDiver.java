package nightkosh.withered_lands.entity.water;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
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
public class PhantomDiver extends EntityDrowned {

    public PhantomDiver(EntityType<? extends PhantomDiver> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(
            @Nonnull ServerLevelAccessor level, @Nonnull DifficultyInstance difficulty,
            @Nonnull EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData) {
        groupData = super.finalizeSpawn(level, difficulty, spawnReason, groupData);
        this.populateDefaultEquipmentSlots(level.getRandom(), difficulty);
        return groupData;
    }

    @Override
    protected void populateDefaultEquipmentSlots(@Nonnull RandomSource randomSource, @Nonnull DifficultyInstance difficulty) {
        if (randomSource.nextInt(10) == 0) {
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.TRIDENT));
        }
    }

    public static boolean checkSpawnRules(
            EntityType<? extends PhantomDiver> entityType, ServerLevelAccessor levelAccessor,
            EntitySpawnReason spawnReason, BlockPos blockPos, RandomSource random) {
        return WLConfigs.PHANTOM_DIVER.get() &&
                checkCommonSpawnRules(levelAccessor, spawnReason, blockPos, random);
    }

    public static AttributeSupplier createAttributeSupplier() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 3)
                .add(Attributes.FOLLOW_RANGE, 35)
                .add(Attributes.STEP_HEIGHT, 1)
                .add(Attributes.MAX_HEALTH, 30)
                .add(Attributes.ARMOR, 4)
                .build();
    }

}
