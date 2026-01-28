package nightkosh.withered_lands.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import nightkosh.withered_lands.core.WLConfigs;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Illusioner extends net.minecraft.world.entity.monster.illager.Illusioner {

    public Illusioner(EntityType<? extends Illusioner> entityType, Level level) {
        super(entityType, level);
    }

    @Nonnull
    @Override
    protected Component getTypeName() {
        return EntityType.ILLUSIONER.getDescription();
    }

    public static boolean checkSpawnRules(
            EntityType<? extends Illusioner> entityType, ServerLevelAccessor levelAccessor,
            EntitySpawnReason spawnReason, BlockPos blockPos, RandomSource random) {
        return WLConfigs.ILLUSIONER_SPAWN.get() &&
                AMonster.checkCommonSpawnRules(levelAccessor, blockPos, random);
    }

}
