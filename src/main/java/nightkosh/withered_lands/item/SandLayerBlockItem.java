package nightkosh.withered_lands.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import javax.annotation.Nonnull;

public class SandLayerBlockItem extends BlockItem {

    public SandLayerBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Nonnull
    @Override
    public Component getName(@Nonnull ItemStack stack) {
        return Blocks.SAND.getName();
    }

}
