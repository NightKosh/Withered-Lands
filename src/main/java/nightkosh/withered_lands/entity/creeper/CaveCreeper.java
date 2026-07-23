package nightkosh.withered_lands.entity.creeper;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.entity.AMonster;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CaveCreeper extends Creeper {

    public CaveCreeper(EntityType<? extends Creeper> entityType, Level level) {
        super(entityType, level);
    }

    @Nonnull
    @Override
    protected Component getTypeName() {
        return EntityTypes.CREEPER.getDescription();
    }

    public static boolean checkSpawnRules(
            EntityType<? extends Creeper> entityType, ServerLevelAccessor levelAccessor,
            EntitySpawnReason spawnReason, BlockPos blockPos, RandomSource random) {
        return WLConfigs.CREEPER_SPAWN.get() &&
                AMonster.checkCommonSpawnRules(levelAccessor, blockPos, random);
    }

}
