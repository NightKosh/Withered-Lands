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
                        output.accept(WLItems.BAT_WING.get());

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

                        // ---------- eggs ----------

                        // slimes
                        output.accept(WLItems.VERDANT_SLIME_EGG.get());
                        output.accept(WLItems.SANDY_SLIME_EGG.get());
                        output.accept(WLItems.FROZEN_SLIME_EGG.get());
                        output.accept(WLItems.MUD_SLIME_EGG.get());
                        output.accept(WLItems.JUNGLE_SLIME_EGG.get());
                        output.accept(WLItems.CAVE_SLIME_EGG.get());
                        output.accept(WLItems.ABYSSAL_SLIME_EGG.get());
                        output.accept(WLItems.TOXIC_SLUDGE_EGG.get());
                        output.accept(WLItems.MOLTEN_SLIME_EGG.get());
                        // bats
                        output.accept(WLItems.CAVE_BAT_EGG.get());
                        output.accept(WLItems.VAMPIRE_BAT_EGG.get());
                        output.accept(WLItems.ICE_BAT_EGG.get());
                        output.accept(WLItems.FLYING_FOX_EGG.get());
                        output.accept(WLItems.BLAZING_BAT_EGG.get());
                        output.accept(WLItems.WITHERED_BAT_EGG.get());
                        output.accept(WLItems.VOLATILE_BAT_EGG.get());
                        output.accept(WLItems.CHORUS_BAT_EGG.get());
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
                        output.accept(WLItems.SKELETON_EGG.get());
                        output.accept(WLItems.ILLUSIONER_EGG.get());
                        output.accept(WLItems.KILLER_BUNNY_EGG.get());
                        // fishes
                        output.accept(WLItems.MINNOW_EGG.get());
                        output.accept(WLItems.PIRANHA_EGG.get());
                        output.accept(WLItems.PIKE_EGG.get());
                    })
                    .build()
            );

    public static void register(IEventBus modEventBus) {
        WL_TAB.register(modEventBus);
    }

}
