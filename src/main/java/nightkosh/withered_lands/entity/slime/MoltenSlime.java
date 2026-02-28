package nightkosh.withered_lands.entity.slime;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import nightkosh.withered_lands.core.WLConfigs;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class MoltenSlime extends ASlime {

    private static final WeightedList<Item> ITEMS = WeightedList.<Item>builder()
            .add(Items.SOUL_TORCH, 10)
            .add(Items.SOUL_LANTERN, 4)
            .add(Items.ARROW, 4)
            .add(Items.STONE_SWORD, 1)
            .add(Items.STONE_PICKAXE, 1)
            // ores
            .add(Items.GOLD_NUGGET, 6)
            .add(Items.QUARTZ, 4)
            .add(Items.GLOWSTONE_DUST, 2)
            // seeds and fruits
            .add(Items.CRIMSON_FUNGUS, 3)
            .add(Items.WARPED_FUNGUS, 3)
            .add(Items.WEEPING_VINES, 3)
            .add(Items.TWISTING_VINES, 3)
            // other
            .add(Items.BONE, 5)
            .add(Items.BLAZE_POWDER, 1)
            .build();

    public MoltenSlime(EntityType<? extends ASlime> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected WeightedList<Item> getSwallowedItemList() {
        return ITEMS;
    }

    @Override
    protected int getSwallowedItemsChance() {
        return 60;
    }

    @Override
    protected void applyEffect(LivingEntity entity) {
        super.applyEffect(entity);
        entity.igniteForSeconds(3);
    }

    @Override
    public void die(@Nonnull DamageSource damageSource) {
        super.die(damageSource);
        if (WLConfigs.MOLTEN_SLIME_LAVA.get()) {
            placeBlockAtDeath(Blocks.LAVA.defaultBlockState());
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (WLConfigs.MOLTEN_SLIME_SPREAD_FIRE.get()) {
            this.spreadBlocks(Blocks.FIRE.defaultBlockState());
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (!level().isClientSide() && this.isInWater()) {
            this.hurt(this.damageSources().freeze(), 1);
        }
    }

    @Nonnull
    @Override
    protected ParticleOptions getParticleType() {
        return ParticleTypes.SMALL_FLAME;
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
        return WLConfigs.MOLTEN_SLIME_SPAWN.get() &&
                checkCommonSpawnRules(entityType, levelAccessor, spawnReason, blockPos, random);
    }

    protected static boolean checkCommonSpawnRules(
            EntityType<? extends ASlime> entityType, ServerLevelAccessor level,
            EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        if (level.getDifficulty() != Difficulty.PEACEFUL) {
            if (EntitySpawnReason.isSpawner(spawnReason)) {
                return checkMobSpawnRules(entityType, level, spawnReason, pos, random);
            }
            if (level instanceof Level l && l.dimension() != Level.NETHER) {
                // avoid additional checks out of the nether
                return true;
            } else {
                // Do not check light, otherwise it prevent slime spawn near ruined portals and in lava
                var ground = level.getBlockState(pos.below());
                return (ground.is(Blocks.NETHERRACK) || ground.is(Blocks.SOUL_SAND) || ground.is(Blocks.BASALT) ||
                        ground.is(Blocks.MAGMA_BLOCK) || ground.is(Blocks.CRIMSON_NYLIUM) || ground.is(Blocks.WARPED_NYLIUM) ||
                        ground.is(Blocks.OBSIDIAN) || ground.is(Blocks.GOLD_BLOCK) || ground.is(Blocks.LAVA) ||
                        ground.is(Blocks.GRASS_BLOCK) || ground.is(Blocks.PODZOL) || ground.is(Blocks.MYCELIUM) ||
                        ground.is(Blocks.DIRT) || ground.is(Blocks.MUD) || ground.is(Blocks.GRAVEL) ||
                        ground.is(Blocks.SAND)) &&
                        checkDensity(level, pos, FrozenSlime.class);
            }
        }

        return false;
    }

}
