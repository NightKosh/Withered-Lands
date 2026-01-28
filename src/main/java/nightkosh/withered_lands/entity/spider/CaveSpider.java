package nightkosh.withered_lands.entity.spider;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
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
public class CaveSpider extends net.minecraft.world.entity.monster.spider.CaveSpider {

    public CaveSpider(EntityType<? extends CaveSpider> entityType, Level level) {
        super(entityType, level);
    }

    @Nonnull
    @Override
    protected Component getTypeName() {
        return EntityType.CAVE_SPIDER.getDescription();
    }

    public static boolean checkSpawnRules(
            EntityType<? extends CaveSpider> entityType, ServerLevelAccessor levelAccessor,
            EntitySpawnReason spawnReason, BlockPos blockPos, RandomSource random) {
        return WLConfigs.CAVE_SPIDER_SPAWN.get() &&
                !levelAccessor.canSeeSky(blockPos) &&
                blockPos.getY() < 40 &&
                AMonster.checkCommonSpawnRules(levelAccessor, blockPos, random);
    }

}
