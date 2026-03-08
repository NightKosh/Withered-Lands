package nightkosh.withered_lands.entity.slime;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.core.WLItems;
import nightkosh.withered_lands.helper.TimeHelper;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class AbyssalSlime extends ASlime {

    private static final WeightedList<Item> ITEMS = WeightedList.<Item>builder()
            .add(Items.TORCH, 8)
            .add(Items.COPPER_LANTERN.oxidized(), 4)
            .add(Items.ARROW, 4)
            .add(Items.STONE_SWORD, 1)
            .add(Items.STONE_PICKAXE, 1)
            .add(Items.BOW, 1)
            // ores
            .add(Items.FLINT, 6)
            .add(Items.COAL, 4)
            .add(Items.RAW_IRON, 3)
            .add(Items.RAW_GOLD, 2)
            .add(Items.LAPIS_LAZULI, 4)
            .add(Items.REDSTONE, 3)
            .add(Items.AMETHYST_SHARD, 1)
            // seeds and fruits
            .add(Items.GLOW_BERRIES, 3)
            .add(Items.BROWN_MUSHROOM, 5)
            .add(Items.RED_MUSHROOM, 5)
            // other
            .add(Items.BONE, 5)
            .add(Items.ROTTEN_FLESH, 2)
            .add(Items.SPIDER_EYE, 2)
            .add(Items.STRING, 4)
            .add(WLItems.BAT_WING.get(), 5)
            .build();

    public AbyssalSlime(EntityType<? extends ASlime> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected WeightedList<Item> getSwallowedItemList() {
        return ITEMS;
    }

    @Override
    protected int getSwallowedItemsChance() {
        return 25;
    }

    @Override
    protected int getDefaultSpawnSize() {
        return 4;
    }

    @Override
    protected void applyEffect(LivingEntity entity) {
        super.applyEffect(entity);
        if (WLConfigs.ABYSSAL_SLIME_DARKNESS_DEBUFF.get()) {
            entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, TimeHelper.SECONDS_30), this);
        }
    }

    public static AttributeSupplier createAttributeSupplier() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, 4)
                .add(Attributes.MAX_HEALTH, 25)
                .build();
    }

    public static boolean checkSpawnRules(
            EntityType<? extends ASlime> entityType, ServerLevelAccessor levelAccessor,
            EntitySpawnReason spawnReason, BlockPos blockPos, RandomSource random) {
        return WLConfigs.ABYSSAL_SLIME_SPAWN.get() &&
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
                if (!level.canSeeSky(pos) && pos.getY() < 25) {
                    var ground = level.getBlockState(pos.below()).getBlock();
                    // TODO additional checks to avoid spawn near buildings
                    return isUndergroundBlock(ground);
                }
            }
        }

        return false;
    }

    @Nonnull
    @Override
    protected ParticleOptions getParticleType() {
        return ParticleTypes.LARGE_SMOKE;
    }

}
