package nightkosh.withered_lands.entity.horse;

import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import nightkosh.withered_lands.entity.AUndeadPet;
import org.jspecify.annotations.Nullable;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class AUndeadHorse extends AUndeadPet {

    public AUndeadHorse(EntityType<? extends AUndeadHorse> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1, false));
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(
            @Nonnull ServerLevelAccessor level, @Nonnull DifficultyInstance difficulty,
            @Nonnull EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData) {
        var jockey = getJockey(level);
        if (jockey != null) {
            jockey.snapTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0);
            jockey.finalizeSpawn(level, difficulty, spawnReason, null);
            jockey.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SPEAR));
            jockey.startRiding(this, false, false);
        }

        return super.finalizeSpawn(level, difficulty, spawnReason, groupData);
    }

    protected abstract Monster getJockey(@Nonnull ServerLevelAccessor level);

}
