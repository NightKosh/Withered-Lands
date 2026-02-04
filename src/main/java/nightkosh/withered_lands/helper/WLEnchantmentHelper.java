package nightkosh.withered_lands.helper;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import nightkosh.withered_lands.core.WLEnchantments;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WLEnchantmentHelper {

    public static Holder<Enchantment> getEnchantmentHolder(Level level, ResourceKey<Enchantment> key) {
        return level.registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT)
                .getOrThrow(key);
    }

    public static void applyCurseEffect(Player player) {
        float damage = 0;
        int duration = 0;
        var ench = getEnchantmentHolder(player.level(), WLEnchantments.CURSE_OF_STARVATION);
        for (var slot : EquipmentSlot.values()) {
            var item = player.getItemBySlot(slot);
            if (!item.isEmpty()) {
                if (EnchantmentHelper.getItemEnchantmentLevel(ench, item) > 0) {
                    damage += 0.5F;
                    duration += TimeHelper.SECONDS_10;
                }
            }
        }
        if (duration > 0) {
            player.addEffect(new MobEffectInstance(MobEffects.HUNGER, duration, 0));
            if (player.level() instanceof ServerLevel server && player.getRandom().nextInt(5) == 0) {
                player.hurtServer(server, server.damageSources().magic(), damage);
            }
        }
    }

}
