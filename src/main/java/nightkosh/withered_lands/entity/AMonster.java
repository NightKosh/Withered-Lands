package nightkosh.withered_lands.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class AMonster extends Monster {

    protected AMonster(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
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

    public static boolean checkCommonSpawnRules(ServerLevelAccessor level, BlockPos pos, RandomSource random) {
        return level.getDifficulty() != Difficulty.PEACEFUL &&
                isDarkEnoughToSpawn(level, pos, random);
    }

}
