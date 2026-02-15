package nightkosh.withered_lands.entity.water.jellyfish;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.helper.TimeHelper;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlueJellyfish extends AJellyfish {

    public BlueJellyfish(EntityType<? extends AJellyfish> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void applyEffect(LivingEntity entity) {
        super.applyEffect(entity);
        entity.setTicksFrozen(entity.getTicksFrozen() + TimeHelper.SECONDS_15);
    }

    public static AttributeSupplier createAttributeSupplier() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 8)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, 0.4)
                .build();
    }

    public static boolean checkSpawnRules(
            EntityType<? extends AJellyfish> entityType, ServerLevelAccessor levelAccessor,
            EntitySpawnReason spawnReason, BlockPos blockPos, RandomSource random) {
        return WLConfigs.BLUE_JELLYFISH_SPAWN.get() &&
                checkCommonSpawnRules(levelAccessor, blockPos, random, BlueJellyfish.class);
    }

}
