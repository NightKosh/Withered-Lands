package nightkosh.withered_lands.entity.giant;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import nightkosh.withered_lands.core.WLConfigs;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class HillGiant extends AGiant {

    public HillGiant(EntityType<? extends AGiant> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier createAttributeSupplier() {
        return Monster.createMonsterAttributes()
                .add(Attributes.ARMOR, 5)
                .add(Attributes.STEP_HEIGHT, 4)
                .add(Attributes.MAX_HEALTH, 100)
                .add(Attributes.FOLLOW_RANGE, 50)
                .add(Attributes.ATTACK_DAMAGE, 20)
                .add(Attributes.MOVEMENT_SPEED, 0.23F)
                .build();
    }

    public static boolean checkSpawnRules(
            EntityType<? extends AGiant> entityType, ServerLevelAccessor levelAccessor,
            EntitySpawnReason spawnReason, BlockPos blockPos, RandomSource random) {
        return WLConfigs.HILL_GIANT_SPAWN.get() &&
                checkCommonSpawnRules(levelAccessor, blockPos, random);
    }

}
