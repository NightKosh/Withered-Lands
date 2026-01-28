package nightkosh.withered_lands.entity.slime;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
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
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.EventHooks;
import nightkosh.withered_lands.core.WLConfigs;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ToxicSludge extends ASlime {

    public ToxicSludge(EntityType<? extends ASlime> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void applyEffect(LivingEntity entity) {
        //TODO
//        entity.addEffect(new MobEffectInstance(MobEffects.RUST, 100), this);//GSPotion.RUST, 100
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.level() instanceof ServerLevel level &&
                WLConfigs.TOXIC_SLUDGE_CORROSION.get() &&
                EventHooks.canEntityGrief(level, this) &&
                this.tickCount % 10 != 0) {
            var box = this.getBoundingBox();
            int minX = Mth.floor(box.minX - 0.1);
            int minY = Mth.floor(box.minY - 0.1);
            int minZ = Mth.floor(box.minZ - 0.1);
            int maxX = Mth.floor(box.maxX + 0.1);
            int maxY = Mth.floor(box.maxY + 0.1);
            int maxZ = Mth.floor(box.maxZ + 0.1);

            var pos = new BlockPos.MutableBlockPos();
            for (int x = minX; x <= maxX; x++) {
                for (int y = minY; y <= maxY; y++) {
                    for (int z = minZ; z <= maxZ; z++) {
                        pos.set(x, y, z);
                        tryToReplaceBlock(level, pos, level.getBlockState(pos));
                    }
                }
            }
        }
    }

    public static void tryToReplaceBlock(Level level, BlockPos pos, BlockState state) {
        if (state.is(Blocks.STONE) || state.is(Blocks.MOSSY_COBBLESTONE)) {
            level.setBlock(pos, Blocks.COBBLESTONE.defaultBlockState(), 3);
        } else if (state.is(Blocks.GRAVEL)) {
            level.setBlock(pos, Blocks.SAND.defaultBlockState(), 3);
        }
        if (state.is(Blocks.DEEPSLATE)) {
            level.setBlock(pos, Blocks.COBBLED_DEEPSLATE.defaultBlockState(), 3);
        }
        if (state.is(Blocks.STONE_BRICKS) || state.is(Blocks.MOSSY_STONE_BRICKS)) {
            level.setBlock(pos, Blocks.CRACKED_STONE_BRICKS.defaultBlockState(), 3);
        }
        if (state.is(Blocks.DEEPSLATE_BRICKS)) {
            level.setBlock(pos, Blocks.CRACKED_DEEPSLATE_BRICKS.defaultBlockState(), 3);
        }
        if (state.is(Blocks.DEEPSLATE_TILES)) {
            level.setBlock(pos, Blocks.CRACKED_DEEPSLATE_TILES.defaultBlockState(), 3);
        } else if (state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.PODZOL) || state.is(Blocks.MYCELIUM)) {
            level.setBlock(pos, Blocks.DIRT.defaultBlockState(), 3);
        }
    }

    //TODO
//    @Override
//    public void onDeath(DamageSource source) {
//        if (!this.world.isRemote && this.getSlimeSize() > 1) {
//            IBlockState state = world.getBlockState(this.getPosition());
//            if (state.getBlock().isReplaceable(this.world, this.getPosition())) {
//                world.setBlockState(this.getPosition(), GSBlock.TOXIC_WATER.getDefaultState());
//            }
//        }
//        super.onDeath(source);
//    }

    @Override
    protected int getDefaultSpawnSize() {
        return 4;
    }

    @Nonnull
    @Override
    protected SoundEvent getSquishSound() {
        return SoundEvents.LAVA_EXTINGUISH;
    }

    @Nonnull
    @Override
    protected ParticleOptions getParticleType() {
        return ParticleTypes.POOF;
    }

    public static AttributeSupplier createAttributeSupplier() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, 4)
                .add(Attributes.MAX_HEALTH, 30)
                .build();
    }

    public static boolean checkSpawnRules(
            EntityType<? extends ASlime> entityType, ServerLevelAccessor levelAccessor,
            EntitySpawnReason spawnReason, BlockPos blockPos, RandomSource random) {
        return WLConfigs.TOXIC_SLUDGE_SPAWN.get() &&
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
                if (!level.canSeeSky(pos) && pos.getY() < 15) {
                    var ground = level.getBlockState(pos.below()).getBlock();
                    // TODO additional checks to avoid spawn near buildings
                    return isUndergroundBlock(ground);
                }
            }
        }

        return false;
    }

}
