package nightkosh.withered_lands.entity.slime;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
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
public class SlimeSandy extends ASlime {

    private static final WeightedList<Item> ITEMS = WeightedList.<Item>builder()
            .add(Items.TORCH, 8)
            .add(Items.STICK, 6)
            // animals
            .add(Items.BROWN_EGG, 2)
            .add(Items.FEATHER, 3)
            .add(Items.CHICKEN, 1)
            .add(Items.RABBIT, 1)
            .add(Items.RABBIT_HIDE, 2)
            .add(Items.ARMADILLO_SCUTE, 1)
            // seeds and fruits
            .add(Items.CACTUS, 4)
            .add(Items.CACTUS_FLOWER, 1)
            .add(Items.DEAD_BUSH, 1)
            // saplings
            .add(Items.ACACIA_SAPLING, 3)
            .add(Items.SUGAR_CANE, 2)
            // other
            .add(Items.GOLD_NUGGET, 1)
            .add(Items.BONE, 3)
            .add(Items.ROTTEN_FLESH, 1)
            .build();

    private static final BlockParticleOption PARTICLE = new BlockParticleOption(
            ParticleTypes.BLOCK, Blocks.SAND.defaultBlockState());

    public SlimeSandy(EntityType<? extends ASlime> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected WeightedList<Item> getSwallowedItemList() {
        return ITEMS;
    }

    @Override
    protected void applyEffect(LivingEntity entity) {
        super.applyEffect(entity);
        if (WLConfigs.SANDY_SLIME_SLOWNESS_DEBUFF.get()) {
            entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, TimeHelper.SECONDS_5), this);
        }
    }

    @Override
    public void die(@Nonnull DamageSource damageSource) {
        super.die(damageSource);
        if (WLConfigs.SANDY_SLIME_SAND.get()) {
            placeBlockAtDeath(Blocks.SAND.defaultBlockState());
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (WLConfigs.SANDY_SLIME_SPREAD_SAND.get()) {
            this.spreadBlocks(WLBlocks.LAYER_SAND.get().defaultBlockState());
        }
    }

    @Nonnull
    @Override
    protected ParticleOptions getParticleType() {
        return PARTICLE;
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
        return WLConfigs.SANDY_SLIME_SPAWN.get() &&
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
                if (level.canSeeSky(pos)) {
                    // TODO additional checks to avoid spawn near buildings
                    return (ground == Blocks.SAND || ground == Blocks.RED_SAND) &&
                            checkDensity(level, pos, SlimeSandy.class);
                } else if (pos.getY() < 50) {
                    // TODO additional checks to avoid spawn near buildings
                    return isUndergroundBlock(ground) && checkDensity(level, pos, SlimeSandy.class);
                }
            }
        }

        return false;
    }

}
