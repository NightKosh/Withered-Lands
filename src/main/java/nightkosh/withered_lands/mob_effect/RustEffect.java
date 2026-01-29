package nightkosh.withered_lands.mob_effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
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
                            getBoneSkinEffect() != null && entity.hasEffect(getBoneSkinEffect()))) {
                damageItem(entity.getItemBySlot(EquipmentSlot.MAINHAND));
                damageItem(entity.getItemBySlot(EquipmentSlot.OFFHAND));
                damageItem(entity.getItemBySlot(EquipmentSlot.HEAD));
                damageItem(entity.getItemBySlot(EquipmentSlot.CHEST));
                damageItem(entity.getItemBySlot(EquipmentSlot.LEGS));
                damageItem(entity.getItemBySlot(EquipmentSlot.FEET));
            }
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    private void damageItem(ItemStack stack) {
        if (!stack.isEmpty() && stack.isDamageableItem()) {
            stack.setDamageValue(stack.getDamageValue() + 1);
        }
    }

}
