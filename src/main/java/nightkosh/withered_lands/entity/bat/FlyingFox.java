package nightkosh.withered_lands.entity.bat;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import nightkosh.withered_lands.core.WLConfigs;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class FlyingFox extends HostileBat {

    public FlyingFox(EntityType<? extends HostileBat> entityType, Level level) {
        super(entityType, level);
    }


    public static AttributeSupplier createAttributeSupplier() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 12)
                .add(Attributes.FOLLOW_RANGE, 30)
                .add(Attributes.ATTACK_DAMAGE, 3)
                .build();
    }

    public static boolean checkSpawnRules(
            EntityType<? extends HostileBat> entityType, ServerLevelAccessor level,
            EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        return WLConfigs.VAMPIRE_BAT_SPAWN.get() && checkCommonSpawnRules(level, pos, random);
    }

}
