package nightkosh.withered_lands.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import nightkosh.withered_lands.block.*;

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

    public static final ResourceKey SLIME_GEL_CHUNK_RK = ResourceKey.create(
            Registries.BLOCK, fromNamespaceAndPath(ModInfo.ID, "slime_gel_chunk"));
    public static final DeferredHolder<Block, Block> SLIME_GEL_CHUNK = registerBlock(
            "slime_gel_chunk", () -> new Block(BlockBehaviour.Properties.of()
                    .sound(SoundType.SLIME_BLOCK)
                    .mapColor(MapColor.GRASS)
                    .friction(0.8F)
                    .noOcclusion()
                    .setId(SLIME_GEL_CHUNK_RK)));

    public static final ResourceKey SKULL_HUSK_RK = ResourceKey.create(
            Registries.BLOCK, fromNamespaceAndPath(ModInfo.ID, "skull_husk"));
    public static final DeferredHolder<Block, Block> SKULL_HUSK = registerBlock(
            "skull_husk", () -> new Skull(SKULL_HUSK_RK));

    public static final ResourceKey SKULL_DROWNED_RK = ResourceKey.create(
            Registries.BLOCK, fromNamespaceAndPath(ModInfo.ID, "skull_drowned"));
    public static final DeferredHolder<Block, Block> SKULL_DROWNED = registerBlock(
            "skull_drowned", () -> new Skull(SKULL_DROWNED_RK));

    public static final ResourceKey SKULL_STRAY_RK = ResourceKey.create(
            Registries.BLOCK, fromNamespaceAndPath(ModInfo.ID, "skull_stray"));
    public static final DeferredHolder<Block, Block> SKULL_STRAY = registerBlock(
            "skull_stray", () -> new Skull(SKULL_STRAY_RK));

    public static final ResourceKey SKULL_BOGGED_RK = ResourceKey.create(
            Registries.BLOCK, fromNamespaceAndPath(ModInfo.ID, "skull_bogged"));
    public static final DeferredHolder<Block, Block> SKULL_BOGGED = registerBlock(
            "skull_bogged", () -> new Skull(SKULL_BOGGED_RK));

    public static final ResourceKey SKULL_PARCHED_RK = ResourceKey.create(
            Registries.BLOCK, fromNamespaceAndPath(ModInfo.ID, "skull_parched"));
    public static final DeferredHolder<Block, Block> SKULL_PARCHED = registerBlock(
            "skull_parched", () -> new Skull(SKULL_PARCHED_RK));

    public static final ResourceKey SKULL_PIGLIN_RK = ResourceKey.create(
            Registries.BLOCK, fromNamespaceAndPath(ModInfo.ID, "skull_piglin"));
    public static final DeferredHolder<Block, Block> SKULL_PIGLIN = registerBlock(
            "skull_piglin", () -> new Skull(SKULL_PIGLIN_RK));

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

    public static final ResourceKey LAYER_SAND_RK = ResourceKey.create(
            Registries.BLOCK, fromNamespaceAndPath(ModInfo.ID, "layer_sand"));
    public static final DeferredHolder<Block, Block> LAYER_SAND = registerBlock(
            "layer_sand",
            () -> new SandLayerBlock(LAYER_SAND_RK));

    public static final ResourceKey LAYER_MUD_RK = ResourceKey.create(
            Registries.BLOCK, fromNamespaceAndPath(ModInfo.ID, "layer_mud"));
    public static final DeferredHolder<Block, Block> LAYER_MUD = registerBlock(
            "layer_mud",
            () -> new MudLayerBlock(LAYER_MUD_RK));

    public static final ResourceKey LAYER_MOSS_RK = ResourceKey.create(
            Registries.BLOCK, fromNamespaceAndPath(ModInfo.ID, "layer_moss"));
    public static final DeferredHolder<Block, Block> LAYER_MOSS = registerBlock(
            "layer_moss",
            () -> new MossLayerBlock(LAYER_MOSS_RK));

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
