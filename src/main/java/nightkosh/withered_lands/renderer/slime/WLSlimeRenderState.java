package nightkosh.withered_lands.renderer.slime;

import net.minecraft.client.renderer.entity.state.SlimeRenderState;
import net.minecraft.world.item.ItemStack;
import nightkosh.withered_lands.entity.slime.ASlime;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WLSlimeRenderState extends SlimeRenderState {

    public ASlime slime;
    public ItemStack headEquipment = ItemStack.EMPTY;
    public ItemStack chestEquipment = ItemStack.EMPTY;
    public ItemStack legsEquipment = ItemStack.EMPTY;
    public ItemStack feetEquipment = ItemStack.EMPTY;

}
