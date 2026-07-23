package nightkosh.withered_lands.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.rabbit.Rabbit;
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
public class KillerBunny extends Rabbit {

    public KillerBunny(EntityType<? extends Rabbit> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(
            @Nonnull ServerLevelAccessor levelAccessor, @Nonnull DifficultyInstance difficulty,
            @Nonnull EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData) {
        var result = super.finalizeSpawn(levelAccessor, difficulty, spawnReason, groupData);

        this.setVariant(Variant.EVIL);
        return result;
    }

    @Nonnull
    @Override
    protected Component getTypeName() {
        return EntityTypes.RABBIT.getDescription();
    }

    public static boolean checkSpawnRules(
            EntityType<? extends KillerBunny> entityType, ServerLevelAccessor levelAccessor,
            EntitySpawnReason spawnReason, BlockPos blockPos, RandomSource random) {
        return WLConfigs.KILLER_BUNNY_SPAWN.get() &&
                levelAccessor.canSeeSky(blockPos) &&
                AMonster.checkCommonSpawnRules(levelAccessor, blockPos, random);
    }

}
