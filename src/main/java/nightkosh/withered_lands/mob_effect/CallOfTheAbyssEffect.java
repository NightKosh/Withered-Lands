package nightkosh.withered_lands.mob_effect;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CallOfTheAbyssEffect extends MobEffect {

    public CallOfTheAbyssEffect() {
        super(MobEffectCategory.HARMFUL, 0x2D6FFF);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(@Nonnull ServerLevel level, LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide()) {
            if (!entity.isEyeInFluid(FluidTags.WATER) ||
                    level.getBlockState(BlockPos.containing(entity.getX(), entity.getEyeY(), entity.getZ()))
                            .is(Blocks.BUBBLE_COLUMN)) {

                if (!entity.canBreatheUnderwater()
                        && !MobEffectUtil.hasWaterBreathing(entity)
                        && (!(entity instanceof Player) || !((Player) entity).getAbilities().invulnerable)) {
                    entity.setAirSupply(decreaseAirSupply(entity, entity.getAirSupply()) - 4);
                    if (shouldTakeDrowningDamage(entity)) {
                        entity.setAirSupply(0);
                        level.broadcastEntityEvent(entity, (byte) 67);
                        entity.hurtServer(level, entity.damageSources().drown(), 2);
                    }
                }
            }
        }

        return true;
    }

    // almost the same as LivingEntity.decreaseAirSupply
    private int decreaseAirSupply(LivingEntity entity, int currentAir) {
        if (entity instanceof IronGolem) {
            return currentAir;
        } else {

            var attributeinstance = entity.getAttribute(Attributes.OXYGEN_BONUS);
            double d0;
            if (attributeinstance != null) {
                d0 = attributeinstance.getValue();
            } else {
                d0 = 0;
            }

            return d0 > 0 && entity.getRandom().nextDouble() >= 1 / (d0 + 1) ?
                    currentAir :
                    currentAir - 1;
        }
    }

    private boolean shouldTakeDrowningDamage(LivingEntity entity) {
        return entity.getAirSupply() <= -20;
    }

}
