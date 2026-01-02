package nightkosh.withered_lands.entity.slime;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import nightkosh.withered_lands.core.WLConfigs;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class MudSlime extends ASlime {

    public MudSlime(EntityType<? extends ASlime> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void applyEffect(LivingEntity entity) {
        super.applyEffect(entity);
        entity.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 150), this);
    }

    public static AttributeSupplier createAttributeSupplier() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, 2)
                .add(Attributes.MAX_HEALTH, 16)
                .build();
    }

    public static boolean checkSpawnRules(
            EntityType<? extends ASlime> entityType, ServerLevelAccessor levelAccessor,
            EntitySpawnReason spawnReason, BlockPos blockPos, RandomSource random) {
        return WLConfigs.MUD_SLIME_SPAWN.get() &&
                checkCommonSpawnRules(entityType, levelAccessor, spawnReason, blockPos, random);
    }

    protected static boolean checkCommonSpawnRules(
            EntityType<? extends ASlime> entityType, LevelAccessor level,
            EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        if (level.getDifficulty() != Difficulty.PEACEFUL) {
            if (EntitySpawnReason.isSpawner(spawnReason)) {
                return checkMobSpawnRules(entityType, level, spawnReason, pos, random);
            }

            if (level.getBrightness(LightLayer.BLOCK, pos) == 0) {
                var ground = level.getBlockState(pos.below()).getBlock();
                if (level.canSeeSky(pos)) {
                    // TODO additional checks to avoid spawn near buildings
                    return ground == Blocks.GRASS_BLOCK || ground == Blocks.DIRT || ground == Blocks.MUD;
                } else if (pos.getY() < 50) {
                    // TODO additional checks to avoid spawn near buildings
                    return isUndergroundBlock(ground);
                }
            }
        }

        return false;
    }

}
