package nightkosh.withered_lands.entity.bat;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import nightkosh.withered_lands.core.WLConfigs;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class VampireBat extends HostileBat {

    public VampireBat(EntityType<? extends HostileBat> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void applyEffect(LivingEntity entity) {
        //TODO
//        entity.addPotionEffect(new PotionEffect(GSPotion.BLEEDING, 600));
    }

    public static boolean checkSpawnRules(
            EntityType<? extends HostileBat> entityType, ServerLevelAccessor level,
            EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        return WLConfigs.VAMPIRE_BAT_SPAWN.get() && checkCommonSpawnRules(level, pos, random);
    }

}
