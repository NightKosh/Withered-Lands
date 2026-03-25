package nightkosh.withered_lands.entity.slime;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
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
public class SlimeMud extends ASlime {

    private static final WeightedList<Item> ITEMS = WeightedList.<Item>builder()
            .add(Items.TORCH, 6)
            .add(Items.STICK, 5)
            .add(Items.CLAY_BALL, 8)
            // flowers
            .add(Items.BLUE_ORCHID, 3)
            // chicken
            .add(Items.EGG, 2)
            .add(Items.FEATHER, 3)
            .add(Items.CHICKEN, 1)
            // bee
            .add(Items.HONEYCOMB, 1)
            // seeds and fruits
            .add(Items.CARROT, 2)
            .add(Items.POTATO, 2)
            .add(Items.POISONOUS_POTATO, 1)
            .add(Items.BROWN_MUSHROOM, 4)
            .add(Items.RED_MUSHROOM, 4)
            // saplings
            .add(Items.OAK_SAPLING, 2)
            .add(Items.MANGROVE_PROPAGULE, 2)
            .add(Items.SUGAR_CANE, 7)
            .add(Items.LILY_PAD, 5)
            .add(Items.VINE, 6)
            // other
            .add(Items.BONE, 3)
            .add(Items.ROTTEN_FLESH, 2)
            .add(Items.SPIDER_EYE, 3)
            .add(Items.STRING, 5)
            .build();

    public SlimeMud(EntityType<? extends ASlime> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected WeightedList<Item> getSwallowedItemList() {
        return ITEMS;
    }

    @Override
    protected void applyEffect(LivingEntity entity) {
        super.applyEffect(entity);
        if (WLConfigs.MUD_SLIME_NAUSEA_DEBUFF.get()) {
            entity.addEffect(new MobEffectInstance(MobEffects.NAUSEA, TimeHelper.SECONDS_10), this);
        }
    }

    @Override
    public void die(@Nonnull DamageSource damageSource) {
        super.die(damageSource);
        if (WLConfigs.MUD_SLIME_MUD.get()) {
            placeBlockAtDeath(Blocks.MUD.defaultBlockState());
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.level() instanceof ServerLevel level && EventHooks.canEntityGrief(level, this)) {
            var blockstate = Blocks.MUD.defaultBlockState();
            var blockpos = this.blockPosition().below();
            if (this.level().getBlockState(blockpos).is(Blocks.DIRT)) {
                this.level().setBlockAndUpdate(blockpos, blockstate);
                this.level().gameEvent(GameEvent.BLOCK_PLACE, blockpos, GameEvent.Context.of(this, blockstate));
            }
        }
        if (WLConfigs.MUD_SLIME_SPREAD_MUD.get()) {
            this.spreadBlocks(WLBlocks.LAYER_MUD.get().defaultBlockState());
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
        return WLConfigs.MUD_SLIME_SPAWN.get() &&
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
                    return (ground == Blocks.GRASS_BLOCK || ground == Blocks.DIRT || ground == Blocks.MUD) &&
                            checkDensity(level, pos, SlimeMud.class);
                } else if (pos.getY() < 50) {
                    // TODO additional checks to avoid spawn near buildings
                    return isUndergroundBlock(ground) && checkDensity(level, pos, SlimeMud.class);
                }
            }
        }

        return false;
    }

}
