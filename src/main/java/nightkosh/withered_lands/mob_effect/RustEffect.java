package nightkosh.withered_lands.mob_effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import nightkosh.withered_lands.compatibility.GravestoneExtendedCompatibility;

import javax.annotation.Nonnull;

import static nightkosh.withered_lands.compatibility.GravestoneExtendedCompatibility.getBoneSkinEffect;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RustEffect extends MobEffect {

    public RustEffect() {
        super(MobEffectCategory.HARMFUL, 0x839f30);
    }

    @Override
    public boolean applyEffectTick(@Nonnull ServerLevel level, @Nonnull LivingEntity entity, int amplifier) {
        if (!level.isClientSide()) {
            if (entity.tickCount % 20 == 0 &&
                    !(GravestoneExtendedCompatibility.loaded() &&
                            getBoneSkinEffect() != null &&
                            entity.hasEffect(getBoneSkinEffect()))) {
                damageItem(entity, EquipmentSlot.MAINHAND);
                damageItem(entity, EquipmentSlot.OFFHAND);
                damageItem(entity, EquipmentSlot.HEAD);
                damageItem(entity, EquipmentSlot.CHEST);
                damageItem(entity, EquipmentSlot.LEGS);
                damageItem(entity, EquipmentSlot.FEET);
            }
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    private void damageItem(LivingEntity entity, EquipmentSlot slot) {
        var stack = entity.getItemBySlot(slot);
        if (!stack.isEmpty() && stack.isDamageableItem() && !stack.nextDamageWillBreak()) {
            stack.hurtAndBreak(1, entity, slot);
        }
    }

}
