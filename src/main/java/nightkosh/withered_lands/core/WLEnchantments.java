package nightkosh.withered_lands.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

import static net.minecraft.resources.Identifier.fromNamespaceAndPath;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WLEnchantments {

    public static final ResourceKey<Enchantment> CURSE_OF_STARVATION =
            ResourceKey.create(Registries.ENCHANTMENT, fromNamespaceAndPath(ModInfo.ID, "curse_of_starvation"));

}
