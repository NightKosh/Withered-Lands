package nightkosh.withered_lands.entity.slime;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
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
public class SlimeVerdant extends ASlime {

    private static final WeightedList<Item> ITEMS = WeightedList.<Item>builder()
            .add(Items.TORCH, 12)
            .add(Items.STICK, 8)
            // flowers
            .add(Items.POPPY, 2)
            .add(Items.DANDELION, 2)
            .add(Items.ALLIUM, 1)
            .add(Items.AZURE_BLUET, 1)
            .add(Items.RED_TULIP, 1)
            .add(Items.ORANGE_TULIP, 1)
            .add(Items.WHITE_TULIP, 1)
            .add(Items.PINK_TULIP, 1)
            .add(Items.OXEYE_DAISY, 1)
            .add(Items.CORNFLOWER, 1)
            .add(Items.LILY_OF_THE_VALLEY, 1)
            // chicken
            .add(Items.EGG, 2)
            .add(Items.FEATHER, 3)
            .add(Items.CHICKEN, 1)
            // bee
            .add(Items.HONEYCOMB, 1)
            // seeds and fruits
            .add(Items.WHEAT_SEEDS, 8)
            .add(Items.WHEAT, 5)
            .add(Items.BEETROOT_SEEDS, 7)
            .add(Items.BEETROOT, 4)
            .add(Items.CARROT, 3)
            .add(Items.POTATO, 3)
            .add(Items.POISONOUS_POTATO, 1)
            .add(Items.APPLE, 3)
            // saplings
            .add(Items.BIRCH_SAPLING, 3)
            .add(Items.OAK_SAPLING, 3)
            .add(Items.DARK_OAK_SAPLING, 2)
            .add(Items.CHERRY_SAPLING, 1)
            .build();

    public SlimeVerdant(EntityType<? extends ASlime> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected WeightedList<Item> getSwallowedItemList() {
        return ITEMS;
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
        return WLConfigs.VERDANT_SLIME_SPAWN.get() &&
                checkCommonSpawnRules(entityType, levelAccessor, spawnReason, blockPos, random);
    }

    protected static boolean checkCommonSpawnRules(
            EntityType<? extends ASlime> entityType, ServerLevelAccessor level,
            EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        if (level.getDifficulty() != Difficulty.PEACEFUL) {
            if (EntitySpawnReason.isSpawner(spawnReason)) {
                return checkMobSpawnRules(entityType, level, spawnReason, pos, random);
            }

            if (level.canSeeSky(pos) &&
                    level.getBrightness(LightLayer.BLOCK, pos) == 0 &&
                    level.getBrightness(LightLayer.SKY, pos) > 0) {
                var ground = level.getBlockState(pos.below()).getBlock();
                // TODO additional checks to avoid spawn near buildings
                return (ground == Blocks.GRASS_BLOCK || ground == Blocks.PODZOL || ground == Blocks.MYCELIUM ||
                        ground == Blocks.DIRT || ground == Blocks.MUD || ground == Blocks.GRAVEL || ground == Blocks.SAND) &&
                        checkDensity(level, pos, SlimeVerdant.class);
            }
        }

        return false;
    }

}
