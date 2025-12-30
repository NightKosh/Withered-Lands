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
                    .icon(() -> new ItemStack(WLItems.VAMPIRE_BAT_EGG))
                    .title(Component.translatable("itemGroup." + ModInfo.ID))
                    .displayItems((parameters, output) -> {

                        output.accept(WLItems.BAT_WING.get());

                        // ---------- eggs ----------

                        // bats
                        output.accept(WLItems.VAMPIRE_BAT_EGG.get());
                        output.accept(WLItems.BLAZING_BAT_EGG.get());
                        output.accept(WLItems.WITHERED_BAT_EGG.get());
                        output.accept(WLItems.VOLATILE_BAT_EGG.get());
                        output.accept(WLItems.CHORUS_BAT_EGG.get());
                        // wolves
                        output.accept(WLItems.SKELETON_DOG_EGG.get());
                        output.accept(WLItems.BARGHEST_EGG.get());
                        // cats
                        output.accept(WLItems.SKELETON_CAT_EGG.get());
                    })
                    .build()
            );

    public static void register(IEventBus modEventBus) {
        WL_TAB.register(modEventBus);
    }

}
