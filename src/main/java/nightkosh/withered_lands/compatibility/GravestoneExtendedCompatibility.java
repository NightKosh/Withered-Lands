package nightkosh.withered_lands.compatibility;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.fml.ModList;

import static net.minecraft.resources.Identifier.fromNamespaceAndPath;
import static nightkosh.withered_lands.WitheredLandsMod.LOGGER;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GravestoneExtendedCompatibility {

    public static final String MOD_ID = "gravestone_extended";

    // blocks
    public static final Block PILE_OF_BONES = getBlock("pile_of_bones");
    public static final Block PILE_OF_BONES_SKULL_SKELETON_CRAWLER = getBlock("pile_of_bones_skull_skeleton_crawler");
    public static final Block PILE_OF_BONES_SKULL_STRAY_CRAWLER = getBlock("pile_of_bones_skull_stray_crawler");
    public static final Block PILE_OF_BONES_SKULL_BOGGED_CRAWLER = getBlock("pile_of_bones_skull_bogged_crawler");
    public static final Block PILE_OF_BONES_SKULL_PARCHED_CRAWLER = getBlock("pile_of_bones_skull_parched_crawler");
    public static final Block PILE_OF_BONES_SKULL_WITHER_CRAWLER = getBlock("pile_of_bones_skull_wither_crawler");
    public static final Block PILE_OF_BONES_SKULL_ZOMBIE_CRAWLER = getBlock("pile_of_bones_skull_zombie_crawler");
    public static final Block PILE_OF_BONES_SKULL_HUSK_CRAWLER = getBlock("pile_of_bones_skull_husk_crawler");
    public static final Block PILE_OF_BONES_SKULL_DROWNED_CRAWLER = getBlock("pile_of_bones_skull_drowned_crawler");
    public static final Block PILE_OF_BONES_SKULL_PIGLIN_CRAWLER = getBlock("pile_of_bones_skull_piglin_crawler");

    public static final Block BONE_BLOCK = getBlock("bone_block");
    public static final Block BONE_BLOCK_SKULL = getBlock("bone_block_skull");
    public static final Block BONE_BLOCK_SKELETON_CRAWLER = getBlock("bone_block_skeleton_crawler");
    public static final Block BONE_BLOCK_SKULL_SKELETON_CRAWLER = getBlock("bone_block_skull_skeleton_crawler");
    public static final Block BONE_BLOCK_STRAY_CRAWLER = getBlock("bone_block_stray_crawler");
    public static final Block BONE_BLOCK_SKULL_STRAY_CRAWLER = getBlock("bone_block_skull_stray_crawler");
    public static final Block BONE_BLOCK_BOGGED_CRAWLER = getBlock("bone_block_bogged_crawler");
    public static final Block BONE_BLOCK_SKULL_BOGGED_CRAWLER = getBlock("bone_block_skull_bogged_crawler");
    public static final Block BONE_BLOCK_PARCHED_CRAWLER = getBlock("bone_block_parched_crawler");
    public static final Block BONE_BLOCK_SKULL_PARCHED_CRAWLER = getBlock("bone_block_skull_parched_crawler");
    public static final Block BONE_BLOCK_WITHER_CRAWLER = getBlock("bone_block_wither_crawler");
    public static final Block BONE_BLOCK_SKULL_WITHER_CRAWLER = getBlock("bone_block_skull_wither_crawler");
    public static final Block BONE_BLOCK_ZOMBIE_CRAWLER = getBlock("bone_block_zombie_crawler");
    public static final Block BONE_BLOCK_SKULL_ZOMBIE_CRAWLER = getBlock("bone_block_skull_zombie_crawler");
    public static final Block BONE_BLOCK_HUSK_CRAWLER = getBlock("bone_block_husk_crawler");
    public static final Block BONE_BLOCK_SKULL_HUSK_CRAWLER = getBlock("bone_block_skull_husk_crawler");
    public static final Block BONE_BLOCK_DROWNED_CRAWLER = getBlock("bone_block_drowned_crawler");
    public static final Block BONE_BLOCK_SKULL_DROWNED_CRAWLER = getBlock("bone_block_skull_drowned_crawler");
    public static final Block BONE_BLOCK_PIGLIN_CRAWLER = getBlock("bone_block_piglin_crawler");
    public static final Block BONE_BLOCK_SKULL_PIGLIN_CRAWLER = getBlock("bone_block_skull_piglin_crawler");

    // effects
    public static final Identifier BONE_SKIN = fromNamespaceAndPath(MOD_ID, "bone_skin");

    private static Item getItem(String id) {
        var holder = BuiltInRegistries.ITEM.get(fromNamespaceAndPath(MOD_ID, id));
        return holder.isPresent() ?
                holder.get().value() :
                Items.NETHER_STAR;
    }

    private static Block getBlock(String id) {
        var holder = BuiltInRegistries.BLOCK.get(fromNamespaceAndPath(MOD_ID, id));
        return holder.isPresent() ?
                holder.get().value() :
                Blocks.CAKE;
    }

    public static Holder.Reference<MobEffect> getBoneSkinEffect() {
        var effect = BuiltInRegistries.MOB_EFFECT.get(BONE_SKIN);
        if (!effect.isPresent()) {
            LOGGER.info("Missing effect: " + BONE_SKIN);
            return null;
        }
        return effect.get();
    }

    public static boolean loaded() {
        return loaded(MOD_ID);
    }

    protected static boolean loaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

}
