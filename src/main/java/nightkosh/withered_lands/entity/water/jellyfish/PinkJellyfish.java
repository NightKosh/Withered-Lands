package nightkosh.withered_lands.entity.water.jellyfish;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
public class PinkJellyfish extends AJellyfish {

    public PinkJellyfish(EntityType<? extends AJellyfish> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void applyEffect(LivingEntity entity) {
        super.applyEffect(entity);
        entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, TimeHelper.SECONDS_10), this);
    }

    public static AttributeSupplier createAttributeSupplier() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 4)
                .add(Attributes.MOVEMENT_SPEED, 0.1)
                .add(Attributes.ATTACK_DAMAGE, 0.2)
                .build();
    }

    public static boolean checkSpawnRules(
            EntityType<? extends AJellyfish> entityType, ServerLevelAccessor levelAccessor,
            EntitySpawnReason spawnReason, BlockPos blockPos, RandomSource random) {
        return WLConfigs.PINK_JELLYFISH_SPAWN.get() &&
                checkCommonSpawnRules(levelAccessor, blockPos, random, PinkJellyfish.class);
    }

}
