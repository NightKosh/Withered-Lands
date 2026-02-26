package nightkosh.withered_lands.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class MossLayerBlockItem extends BlockItem {

    public MossLayerBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Nonnull
    @Override
    public Component getName(@Nonnull ItemStack stack) {
        return Blocks.MOSS_CARPET.getName();
    }

}
