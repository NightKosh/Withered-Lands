package nightkosh.withered_lands.entity.slime;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.event.EventHooks;
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
public class SandySlime extends ASlime {

    private static final BlockParticleOption PARTICLE = new BlockParticleOption(
            ParticleTypes.BLOCK, Blocks.SAND.defaultBlockState());

    public SandySlime(EntityType<? extends ASlime> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void applyEffect(LivingEntity entity) {
        super.applyEffect(entity);
        entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, TimeHelper.SECONDS_15), this);
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
        if (this.level() instanceof ServerLevel level && EventHooks.canEntityGrief(level, this)) {
            var blockstate = WLBlocks.SAND_LAYER.get().defaultBlockState();
            for (int i = 0; i < 4; i++) {
                var blockpos = new BlockPos(
                        Mth.floor(this.getX() + (i % 2 * 2 - 1) * 0.25F),
                        Mth.floor(this.getY()),
                        Mth.floor(this.getZ() + (i / 2 % 2 * 2 - 1) * 0.25F));
                if (this.level().getBlockState(blockpos).isAir() &&
                        blockstate.canSurvive(this.level(), blockpos) &&
                        this.level().getBlockState(this.blockPosition().below()).isSolidRender()) {
                    this.level().setBlockAndUpdate(blockpos, blockstate);
                    this.level().gameEvent(GameEvent.BLOCK_PLACE, blockpos, GameEvent.Context.of(this, blockstate));
                }
            }
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
                    return ground == Blocks.SAND || ground == Blocks.RED_SAND;
                } else if (pos.getY() < 50) {
                    // TODO additional checks to avoid spawn near buildings
                    return isUndergroundBlock(ground);
                }
            }
        }

        return false;
    }

}
