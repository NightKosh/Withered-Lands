package nightkosh.withered_lands.entity.slime;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.Difficulty;
import net.minecraft.world.attribute.EnvironmentAttributes;
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
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.event.EventHooks;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.helper.TimeHelper;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class FrozenSlime extends ASlime {

    private static final WeightedList<Item> ITEMS = WeightedList.<Item>builder()
            .add(Items.TORCH, 8)
            .add(Items.STICK, 6)
            .add(Items.SNOWBALL, 8)
            // animals
            .add(Items.BLUE_EGG, 2)
            .add(Items.FEATHER, 3)
            .add(Items.CHICKEN, 1)
            .add(Items.RABBIT, 1)
            .add(Items.RABBIT_HIDE, 2)
            // seeds and fruits
            .add(Items.SWEET_BERRIES, 6)
            .add(Items.PUMPKIN_SEEDS, 4)
            .add(Items.BEETROOT_SEEDS, 3)
            .add(Items.BEETROOT, 2)
            .add(Items.BROWN_MUSHROOM, 4)
            .add(Items.RED_MUSHROOM, 4)
            // saplings
            .add(Items.SPRUCE_SAPLING, 4)
            // other
            .add(Items.BONE, 3)
            .add(Items.ROTTEN_FLESH, 1)
            .add(Items.SALMON, 1)
            .build();

    public FrozenSlime(EntityType<? extends ASlime> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected WeightedList<Item> getSwallowedItemList() {
        return ITEMS;
    }

    @Override
    protected void applyEffect(LivingEntity entity) {
        super.applyEffect(entity);
        if (WLConfigs.FROZEN_SLIME_FREEZING_DEBUFF.get()) {
            entity.setTicksFrozen(entity.getTicksFrozen() + TimeHelper.SECONDS_10);
        }
    }

    @Override
    public void die(@Nonnull DamageSource damageSource) {
        super.die(damageSource);
        if (WLConfigs.FROZEN_SLIME_SNOW.get()) {
            placeBlockAtDeath(Blocks.POWDER_SNOW.defaultBlockState());
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.level() instanceof ServerLevel level) {
            if (level.environmentAttributes().getValue(EnvironmentAttributes.SNOW_GOLEM_MELTS, this.position())) {
                this.hurtServer(level, this.damageSources().onFire(), 1);
            }

            if (WLConfigs.FROZEN_SLIME_SPREAD_SNOW.get() && EventHooks.canEntityGrief(level, this)) {
                var blockstate = Blocks.SNOW.defaultBlockState();
                for (int i = 0; i < 4; i++) {
                    var blockpos = new BlockPos(
                            Mth.floor(this.getX() + (i % 2 * 2 - 1) * 0.25F),
                            Mth.floor(this.getY()),
                            Mth.floor(this.getZ() + (i / 2 % 2 * 2 - 1) * 0.25F));
                    if (this.level().getBlockState(blockpos).isAir() && blockstate.canSurvive(this.level(), blockpos)) {
                        this.level().setBlockAndUpdate(blockpos, blockstate);
                        this.level().gameEvent(GameEvent.BLOCK_PLACE, blockpos, GameEvent.Context.of(this, blockstate));
                    }
                }
            }
        }
    }

    @Nonnull
    @Override
    protected ParticleOptions getParticleType() {
        return ParticleTypes.SNOWFLAKE;
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
        return WLConfigs.FROZEN_SLIME_SPAWN.get() &&
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
                    return (ground == Blocks.SNOW_BLOCK || ground == Blocks.SNOW || ground == Blocks.POWDER_SNOW ||
                            ground == Blocks.ICE || ground == Blocks.BLUE_ICE || ground == Blocks.FROSTED_ICE || ground == Blocks.PACKED_ICE ||
                            ground == Blocks.PODZOL || ground == Blocks.GRASS_BLOCK || ground == Blocks.DIRT) &&
                            checkDensity(level, pos, FrozenSlime.class);
                } else if (pos.getY() < 50) {
                    // TODO additional checks to avoid spawn near buildings
                    return isUndergroundBlock(ground) && checkDensity(level, pos, FrozenSlime.class);
                }
            }
        }

        return false;
    }

}
