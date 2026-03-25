package nightkosh.withered_lands.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WLTabs {

    public static final DeferredRegister<CreativeModeTab> WL_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModInfo.ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> WITHERED_LANDS =
            WL_TAB.register("withered_lands", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(WLItems.WITHER_SKULL_CRAWLER_EGG))
                    .title(Component.translatable("itemGroup." + ModInfo.ID))
                    .displayItems((parameters, output) -> {

                        output.accept(WLItems.SLIME_GEL.get());
                        output.accept(WLItems.SLIME_GEL_CHUNK.get());
                        output.accept(WLItems.SLIME_SOUP.get());
                        output.accept(WLItems.SLIME_CROWN.get());
                        output.accept(WLItems.BAT_WING.get());
                        output.accept(WLItems.ETHEREAL_DUST.get());
                        output.accept(WLItems.ETHEREAL_ICE_CREAM.get());

                        // skulls
                        output.accept(WLItems.SKULL_HUSK.get());
                        output.accept(WLItems.SKULL_DROWNED.get());
                        output.accept(WLItems.SKULL_STRAY.get());
                        output.accept(WLItems.SKULL_BOGGED.get());
                        output.accept(WLItems.SKULL_PARCHED.get());
                        output.accept(WLItems.SKULL_PIGLIN.get());

                        // torch
                        output.accept(WLItems.EXTINGUISHED_TORCH.get());
                        output.accept(WLItems.EXTINGUISHED_COPPER_TORCH.get());
                        output.accept(WLItems.EXTINGUISHED_SOUL_TORCH.get());

                        // sand
                        output.accept(WLItems.LAYER_SAND.get());
                        output.accept(WLItems.LAYER_MUD.get());
                        output.accept(WLItems.LAYER_MOSS.get());

                        // ---------- eggs ----------

                        // slimes
                        output.accept(WLItems.SLIME_VERDANT_EGG.get());
                        output.accept(WLItems.SLIME_SANDY_EGG.get());
                        output.accept(WLItems.SLIME_FROZEN_EGG.get());
                        output.accept(WLItems.SLIME_MUD_EGG.get());
                        output.accept(WLItems.SLIME_JUNGLE_EGG.get());
                        output.accept(WLItems.SLIME_CAVE_EGG.get());
                        output.accept(WLItems.SLIME_ABYSSAL_EGG.get());
                        output.accept(WLItems.SLIME_TOXIC_SLUDGE_EGG.get());
                        output.accept(WLItems.SLIME_MOLTEN_EGG.get());
                        // bats
                        output.accept(WLItems.BAT_CAVE_EGG.get());
                        output.accept(WLItems.BAT_VAMPIRE_EGG.get());
                        output.accept(WLItems.BAT_ICE_EGG.get());
                        output.accept(WLItems.BAT_FLYING_FOX_EGG.get());
                        output.accept(WLItems.BAT_BLAZING_EGG.get());
                        output.accept(WLItems.BAT_WITHERED_EGG.get());
                        output.accept(WLItems.BAT_VOLATILE_EGG.get());
                        output.accept(WLItems.BAT_CHORUS_EGG.get());
                        // crawlers
                        output.accept(WLItems.SKELETON_SKULL_CRAWLER_EGG.get());
                        output.accept(WLItems.STRAY_SKULL_CRAWLER_EGG.get());
                        output.accept(WLItems.BOGGED_SKULL_CRAWLER_EGG.get());
                        output.accept(WLItems.PARCHED_SKULL_CRAWLER_EGG.get());
                        output.accept(WLItems.WITHER_SKULL_CRAWLER_EGG.get());
                        output.accept(WLItems.ZOMBIE_SKULL_CRAWLER_EGG.get());
                        output.accept(WLItems.HUSK_SKULL_CRAWLER_EGG.get());
                        output.accept(WLItems.DROWNED_SKULL_CRAWLER_EGG.get());
                        output.accept(WLItems.PIGLIN_SKULL_CRAWLER_EGG.get());
                        // breeze
                        output.accept(WLItems.THUNDERSTORM_EGG.get());
                        output.accept(WLItems.BLIZZARD_EGG.get());
                        output.accept(WLItems.SAND_DEVIL_EGG.get());
                        output.accept(WLItems.DIRGE_GALE_EGG.get());
                        // ghosts
                        output.accept(WLItems.HOLLOW_STALKER_EGG.get());
                        output.accept(WLItems.LOST_SOUL_EGG.get());
                        // creepers
                        output.accept(WLItems.DESERT_CREEPER_EGG.get());
                        output.accept(WLItems.SNOWY_CREEPER_EGG.get());
                        output.accept(WLItems.CAVE_CREEPER_EGG.get());
                        output.accept(WLItems.DEEPSLATE_CREEPER_EGG.get());
                        // wolves
                        output.accept(WLItems.SKELETON_DOG_EGG.get());
                        output.accept(WLItems.ZOMBIE_DOG_EGG.get());
                        output.accept(WLItems.BARGHEST_EGG.get());
                        // cats
                        output.accept(WLItems.SKELETON_CAT_EGG.get());
                        output.accept(WLItems.ZOMBIE_CAT_EGG.get());
                        // horses
                        output.accept(WLItems.SKELETON_HORSE_EGG.get());
                        output.accept(WLItems.ZOMBIE_HORSE_EGG.get());
                        // underwater mobs
                        output.accept(WLItems.DROWNED_SAILOR_EGG.get());
                        output.accept(WLItems.PHANTOM_DIVER_EGG.get());
                        // swamp
                        output.accept(WLItems.SWAMP_THING_EGG.get());
                        output.accept(WLItems.GIANT_FROG_EGG.get());
                        // desert
                        output.accept(WLItems.MUMMY_EGG.get());
                        // snow
                        output.accept(WLItems.SNOWMAN_EGG.get());
                        // giants
                        output.accept(WLItems.HILL_GIANT_EGG.get());
                        output.accept(WLItems.FROZEN_GIANT_EGG.get());
                        // other
                        output.accept(WLItems.POSSESSED_ARMOR_EGG.get());
                        output.accept(WLItems.MIMIC_EGG.get());
                        output.accept(WLItems.SKELETON_EGG.get());
                        output.accept(WLItems.ILLUSIONER_EGG.get());
                        output.accept(WLItems.KILLER_BUNNY_EGG.get());
                        // fishes
                        output.accept(WLItems.MINNOW_EGG.get());
                        output.accept(WLItems.PIRANHA_EGG.get());
                        output.accept(WLItems.PIKE_EGG.get());
                        output.accept(WLItems.JELLYFISH_WHITE_EGG.get());
                        output.accept(WLItems.JELLYFISH_BLUE_EGG.get());
                        output.accept(WLItems.JELLYFISH_GREEN_EGG.get());
                        output.accept(WLItems.JELLYFISH_YELLOW_EGG.get());
                        output.accept(WLItems.JELLYFISH_RED_EGG.get());
                        output.accept(WLItems.JELLYFISH_PINK_EGG.get());
                        output.accept(WLItems.JELLYFISH_PURPLE_EGG.get());
                    })
                    .build()
            );

    public static void register(IEventBus modEventBus) {
        WL_TAB.register(modEventBus);
    }

}
