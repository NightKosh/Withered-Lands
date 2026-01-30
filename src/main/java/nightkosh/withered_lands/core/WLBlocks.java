package nightkosh.withered_lands.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import nightkosh.withered_lands.block.ExtinguishedTorchBlock;
import nightkosh.withered_lands.block.ExtinguishedWallTorchBlock;

import java.util.function.Supplier;

import static net.minecraft.resources.Identifier.fromNamespaceAndPath;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WLBlocks {

    public static final DeferredRegister<Block> BLOCKS_REGISTER =
            DeferredRegister.create(Registries.BLOCK, ModInfo.ID);

    public static final ResourceKey EXTINGUISHED_WALL_TORCH_RK = ResourceKey.create(
            Registries.BLOCK, fromNamespaceAndPath(ModInfo.ID, "extinguished_wall_torch"));
    public static final DeferredHolder<Block, Block> EXTINGUISHED_WALL_TORCH = registerBlock(
            "extinguished_wall_torch", () -> new ExtinguishedWallTorchBlock(EXTINGUISHED_WALL_TORCH_RK));

    public static final ResourceKey EXTINGUISHED_COPPER_WALL_TORCH_RK = ResourceKey.create(
            Registries.BLOCK, fromNamespaceAndPath(ModInfo.ID, "extinguished_copper_wall_torch"));
    public static final DeferredHolder<Block, Block> EXTINGUISHED_COPPER_WALL_TORCH = registerBlock(
            "extinguished_copper_wall_torch", () -> new ExtinguishedWallTorchBlock(EXTINGUISHED_COPPER_WALL_TORCH_RK));

    public static final ResourceKey EXTINGUISHED_SOUL_WALL_TORCH_RK = ResourceKey.create(
            Registries.BLOCK, fromNamespaceAndPath(ModInfo.ID, "extinguished_soul_wall_torch"));
    public static final DeferredHolder<Block, Block> EXTINGUISHED_SOUL_WALL_TORCH = registerBlock(
            "extinguished_soul_wall_torch", () -> new ExtinguishedWallTorchBlock(EXTINGUISHED_SOUL_WALL_TORCH_RK));

    public static final ResourceKey EXTINGUISHED_TORCH_RK = ResourceKey.create(
            Registries.BLOCK, fromNamespaceAndPath(ModInfo.ID, "extinguished_torch"));
    public static final DeferredHolder<Block, Block> EXTINGUISHED_TORCH = registerBlock(
            "extinguished_torch", () -> new ExtinguishedTorchBlock(EXTINGUISHED_TORCH_RK));

    public static final ResourceKey EXTINGUISHED_COPPER_TORCH_RK = ResourceKey.create(
            Registries.BLOCK, fromNamespaceAndPath(ModInfo.ID, "extinguished_copper_torch"));
    public static final DeferredHolder<Block, Block> EXTINGUISHED_COPPER_TORCH = registerBlock(
            "extinguished_copper_torch",
            () -> new ExtinguishedTorchBlock(EXTINGUISHED_COPPER_TORCH_RK));

    public static final ResourceKey EXTINGUISHED_SOUL_TORCH_RK = ResourceKey.create(
            Registries.BLOCK, fromNamespaceAndPath(ModInfo.ID, "extinguished_soul_torch"));
    public static final DeferredHolder<Block, Block> EXTINGUISHED_SOUL_TORCH = registerBlock(
            "extinguished_soul_torch",
            () -> new ExtinguishedTorchBlock(EXTINGUISHED_SOUL_TORCH_RK));

    private static <T extends Block> DeferredHolder<Block, T> registerBlock(
            String name, Supplier<T> block, Supplier<Item> itemBlock) {
        WLItems.ITEMS_REGISTER.register(name, itemBlock);
        return registerBlock(name, block);
    }

    private static <T extends Block> DeferredHolder<Block, T> registerBlock(
            String name, Supplier<T> block) {
        return BLOCKS_REGISTER.register(name, block);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS_REGISTER.register(eventBus);
    }

}
