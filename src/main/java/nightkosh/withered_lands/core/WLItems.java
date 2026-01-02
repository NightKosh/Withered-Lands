package nightkosh.withered_lands.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import nightkosh.withered_lands.item.BatWing;
import nightkosh.withered_lands.item.SlimeGel;

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

    // slimes
    public static final DeferredHolder<Item, Item> SLIME_GEL = ITEMS_REGISTER.register(
            "slime_gel",
            () -> new SlimeGel(new Item.Properties()
                    .stacksTo(64)
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationModifier(0.2F)
                            .build())
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "slime_gel")))));

    public static final DeferredHolder<Item, Item> VERDANT_SLIME_EGG = ITEMS_REGISTER.register(
            "verdant_slime_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "verdant_slime_spawn_egg")))
                    .spawnEgg(WLEntities.VERDANT_SLIME.get())));

    public static final DeferredHolder<Item, Item> SANDY_SLIME_EGG = ITEMS_REGISTER.register(
            "sandy_slime_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "sandy_slime_spawn_egg")))
                    .spawnEgg(WLEntities.SANDY_SLIME.get())));

    public static final DeferredHolder<Item, Item> FROZEN_SLIME_EGG = ITEMS_REGISTER.register(
            "frozen_slime_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "frozen_slime_spawn_egg")))
                    .spawnEgg(WLEntities.FROZEN_SLIME.get())));

    public static final DeferredHolder<Item, Item> MUD_SLIME_EGG = ITEMS_REGISTER.register(
            "mud_slime_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "mud_slime_spawn_egg")))
                    .spawnEgg(WLEntities.MUD_SLIME.get())));

    public static final DeferredHolder<Item, Item> JUNGLE_SLIME_EGG = ITEMS_REGISTER.register(
            "jungle_slime_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "jungle_slime_spawn_egg")))
                    .spawnEgg(WLEntities.JUNGLE_SLIME.get())));

    public static final DeferredHolder<Item, Item> CAVE_SLIME_EGG = ITEMS_REGISTER.register(
            "cave_slime_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "cave_slime_spawn_egg")))
                    .spawnEgg(WLEntities.CAVE_SLIME.get())));

    public static final DeferredHolder<Item, Item> ABYSSAL_SLIME_EGG = ITEMS_REGISTER.register(
            "abyssal_slime_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "abyssal_slime_spawn_egg")))
                    .spawnEgg(WLEntities.ABYSSAL_SLIME.get())));

    public static final DeferredHolder<Item, Item> MOLTEN_SLIME_EGG = ITEMS_REGISTER.register(
            "molten_slime_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "molten_slime_spawn_egg")))
                    .spawnEgg(WLEntities.MOLTEN_SLIME.get())));

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

    public static final DeferredHolder<Item, Item> FLYING_FOX_EGG = ITEMS_REGISTER.register(
            "flying_fox_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "flying_fox_spawn_egg")))
                    .spawnEgg(WLEntities.FLYING_FOX.get())));

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
    public static final DeferredHolder<Item, Item> SKELETON_DOG_EGG = ITEMS_REGISTER.register(
            "skeleton_dog_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "skeleton_dog_spawn_egg")))
                    .spawnEgg(WLEntities.SKELETON_DOG.get())));

    public static final DeferredHolder<Item, Item> BARGHEST_EGG = ITEMS_REGISTER.register(
            "barghest_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "barghest_spawn_egg")))
                    .spawnEgg(WLEntities.BARGHEST.get())));

    // cats
    public static final DeferredHolder<Item, Item> SKELETON_CAT_EGG = ITEMS_REGISTER.register(
            "skeleton_cat_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "skeleton_cat_spawn_egg")))
                    .spawnEgg(WLEntities.SKELETON_CAT.get())));

    // underwater mobs
    public static final DeferredHolder<Item, Item> DROWNED_SAILOR_EGG = ITEMS_REGISTER.register(
            "drowned_sailor_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "drowned_sailor_spawn_egg")))
                    .spawnEgg(WLEntities.DROWNED_SAILOR.get())));

    public static final DeferredHolder<Item, Item> PHANTOM_DIVER_EGG = ITEMS_REGISTER.register(
            "phantom_diver_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "phantom_diver_spawn_egg")))
                    .spawnEgg(WLEntities.PHANTOM_DIVER.get())));

    public static final DeferredHolder<Item, Item> SWAMP_THING_EGG = ITEMS_REGISTER.register(
            "swamp_thing_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "swamp_thing_spawn_egg")))
                    .spawnEgg(WLEntities.SWAMP_THING.get())));

    // desert
    public static final DeferredHolder<Item, Item> MUMMY_EGG = ITEMS_REGISTER.register(
            "mummy_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "mummy_spawn_egg")))
                    .spawnEgg(WLEntities.MUMMY.get())));

    public static void register(IEventBus eventBus) {
        ITEMS_REGISTER.register(eventBus);
    }

}
