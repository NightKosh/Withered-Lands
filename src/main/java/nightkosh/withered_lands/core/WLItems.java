package nightkosh.withered_lands.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import nightkosh.withered_lands.item.BatWing;

import static net.minecraft.resources.Identifier.fromNamespaceAndPath;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WLItems {

    public static final DeferredRegister<Item> ITEMS_REGISTER =
            DeferredRegister.create(Registries.ITEM, ModInfo.ID);

    // bats
    public static final DeferredHolder<Item, Item> BAT_WING = ITEMS_REGISTER.register(
            "bat_wing",
            () -> new BatWing(new Item.Properties()
                    .stacksTo(64)
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "bat_wing")))));

    public static final DeferredHolder<Item, Item> VAMPIRE_BAT_EGG = ITEMS_REGISTER.register(
            "vampire_bat_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "vampire_bat_spawn_egg")))
                    .spawnEgg(WLEntities.VAMPIRE_BAT.get())));

    public static final DeferredHolder<Item, Item> BLAZING_BAT_EGG = ITEMS_REGISTER.register(
            "blazing_bat_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "blazing_bat_spawn_egg")))
                    .spawnEgg(WLEntities.BLAZING_BAT.get())));

    public static final DeferredHolder<Item, Item> WITHERED_BAT_EGG = ITEMS_REGISTER.register(
            "withered_bat_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "withered_bat_spawn_egg")))
                    .spawnEgg(WLEntities.WITHERED_BAT.get())));

    public static final DeferredHolder<Item, Item> VOLATILE_BAT_EGG = ITEMS_REGISTER.register(
            "volatile_bat_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "volatile_bat_spawn_egg")))
                    .spawnEgg(WLEntities.VOLATILE_BAT.get())));

    public static final DeferredHolder<Item, Item> CHORUS_BAT_EGG = ITEMS_REGISTER.register(
            "chorus_bat_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "chorus_bat_spawn_egg")))
                    .spawnEgg(WLEntities.CHORUS_BAT.get())));

    // wolves
    public static final DeferredHolder<Item, Item> BARGHEST_EGG = ITEMS_REGISTER.register(
            "barghest_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "barghest_spawn_egg")))
                    .spawnEgg(WLEntities.BARGHEST.get())));

    public static void register(IEventBus eventBus) {
        ITEMS_REGISTER.register(eventBus);
    }

}
