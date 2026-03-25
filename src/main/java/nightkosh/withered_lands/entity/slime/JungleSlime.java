package nightkosh.withered_lands.entity.slime;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
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
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import nightkosh.withered_lands.core.WLBlocks;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.helper.TimeHelper;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class JungleSlime extends ASlime {

    private static final WeightedList<Item> ITEMS = WeightedList.<Item>builder()
            .add(Items.TORCH, 6)
            .add(Items.STICK, 5)
            // chicken
            .add(Items.BROWN_EGG, 2)
            .add(Items.FEATHER, 3)
            .add(Items.CHICKEN, 1)
            // bee
            .add(Items.HONEYCOMB, 1)
            // seeds and fruits
            .add(Items.MELON_SEEDS, 2)
            .add(Items.MELON_SLICE, 2)
            .add(Items.COCOA_BEANS, 3)
            // saplings
            .add(Items.JUNGLE_SAPLING, 2)
            .add(Items.BAMBOO, 2)
            .add(Items.SUGAR_CANE, 4)
            .add(Items.VINE, 5)
            // other
            .add(Items.TROPICAL_FISH, 1)
            .add(Items.BONE, 3)
            .add(Items.ROTTEN_FLESH, 2)
            .add(Items.SPIDER_EYE, 2)
            .add(Items.STRING, 3)
            .build();

    public JungleSlime(EntityType<? extends ASlime> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected WeightedList<Item> getSwallowedItemList() {
        return ITEMS;
    }

    @Override
    public void die(@Nonnull DamageSource damageSource) {
        super.die(damageSource);
        if (WLConfigs.JUNGLE_SLIME_MOSS.get()) {
            placeBlockAtDeath(Blocks.MOSS_BLOCK.defaultBlockState());
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (WLConfigs.JUNGLE_SLIME_SPREAD_MOSS.get()) {
            this.spreadBlocks(WLBlocks.LAYER_MOSS.get().defaultBlockState());
        }
    }

    @Override
    protected void applyEffect(LivingEntity entity) {
        super.applyEffect(entity);
        if (WLConfigs.JUNGLE_SLIME_POISON_DEBUFF.get()) {
            entity.addEffect(new MobEffectInstance(MobEffects.POISON, TimeHelper.SECONDS_5), this);
        }
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
        return WLConfigs.JUNGLE_SLIME_SPAWN.get() &&
                checkCommonSpawnRules(entityType, levelAccessor, spawnReason, blockPos, random);
    }

    protected static boolean checkCommonSpawnRules(
            EntityType<? extends ASlime> entityType, ServerLevelAccessor level,
            EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        if (level.getDifficulty() != Difficulty.PEACEFUL) {
            if (EntitySpawnReason.isSpawner(spawnReason)) {
                return checkMobSpawnRules(entityType, level, spawnReason, pos, random);
            }

            if (level.getBrightness(LightLayer.BLOCK, pos) == 0) {
                var ground = level.getBlockState(pos.below()).getBlock();
                if (level.canSeeSky(pos)) {
                    // TODO additional checks to avoid spawn near buildings
                    return (ground == Blocks.GRASS_BLOCK || ground == Blocks.DIRT || ground == Blocks.MUD ||
                            ground == Blocks.PODZOL || ground == Blocks.JUNGLE_LEAVES) &&
                            checkDensity(level, pos, JungleSlime.class);
                } else if (pos.getY() < 50) {
                    // TODO additional checks to avoid spawn near buildings
                    return isUndergroundBlock(ground) && checkDensity(level, pos, JungleSlime.class);
                }
            }
        }

        return false;
    }

}
