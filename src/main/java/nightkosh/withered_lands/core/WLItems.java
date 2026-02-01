package nightkosh.withered_lands.core;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.StandingAndWallBlockItem;
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

    public static final DeferredHolder<Item, Item> TOXIC_SLUDGE_EGG = ITEMS_REGISTER.register(
            "toxic_sludge_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "toxic_sludge_spawn_egg")))
                    .spawnEgg(WLEntities.TOXIC_SLUDGE.get())));

    public static final DeferredHolder<Item, Item> MOLTEN_SLIME_EGG = ITEMS_REGISTER.register(
            "molten_slime_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "molten_slime_spawn_egg")))
                    .spawnEgg(WLEntities.MOLTEN_SLIME.get())));

    // bats
    public static final DeferredHolder<Item, Item> CAVE_BAT_EGG = ITEMS_REGISTER.register(
            "cave_bat_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "cave_bat_spawn_egg")))
                    .spawnEgg(WLEntities.CAVE_BAT.get())));

    public static final DeferredHolder<Item, Item> VAMPIRE_BAT_EGG = ITEMS_REGISTER.register(
            "vampire_bat_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "vampire_bat_spawn_egg")))
                    .spawnEgg(WLEntities.VAMPIRE_BAT.get())));

    public static final DeferredHolder<Item, Item> ICE_BAT_EGG = ITEMS_REGISTER.register(
            "ice_bat_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "ice_bat_spawn_egg")))
                    .spawnEgg(WLEntities.ICE_BAT.get())));

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

    // crawlers
    public static final DeferredHolder<Item, Item> SKELETON_SKULL_CRAWLER_EGG = ITEMS_REGISTER.register(
            "skeleton_skull_crawler_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "skeleton_skull_crawler_spawn_egg")))
                    .spawnEgg(WLEntities.SKELETON_SKULL_CRAWLER.get())));

    public static final DeferredHolder<Item, Item> STRAY_SKULL_CRAWLER_EGG = ITEMS_REGISTER.register(
            "stray_skull_crawler_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "stray_skull_crawler_spawn_egg")))
                    .spawnEgg(WLEntities.STRAY_SKULL_CRAWLER.get())));

    public static final DeferredHolder<Item, Item> BOGGED_SKULL_CRAWLER_EGG = ITEMS_REGISTER.register(
            "bogged_skull_crawler_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "bogged_skull_crawler_spawn_egg")))
                    .spawnEgg(WLEntities.BOGGED_SKULL_CRAWLER.get())));

    public static final DeferredHolder<Item, Item> PARCHED_SKULL_CRAWLER_EGG = ITEMS_REGISTER.register(
            "parched_skull_crawler_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "parched_skull_crawler_spawn_egg")))
                    .spawnEgg(WLEntities.PARCHED_SKULL_CRAWLER.get())));

    public static final DeferredHolder<Item, Item> WITHER_SKULL_CRAWLER_EGG = ITEMS_REGISTER.register(
            "wither_skull_crawler_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "wither_skull_crawler_spawn_egg")))
                    .spawnEgg(WLEntities.WITHER_SKULL_CRAWLER.get())));

    public static final DeferredHolder<Item, Item> ZOMBIE_SKULL_CRAWLER_EGG = ITEMS_REGISTER.register(
            "zombie_skull_crawler_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "zombie_skull_crawler_spawn_egg")))
                    .spawnEgg(WLEntities.ZOMBIE_SKULL_CRAWLER.get())));

    public static final DeferredHolder<Item, Item> HUSK_SKULL_CRAWLER_EGG = ITEMS_REGISTER.register(
            "husk_skull_crawler_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "husk_skull_crawler_spawn_egg")))
                    .spawnEgg(WLEntities.HUSK_SKULL_CRAWLER.get())));

    public static final DeferredHolder<Item, Item> DROWNED_SKULL_CRAWLER_EGG = ITEMS_REGISTER.register(
            "drowned_skull_crawler_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "drowned_skull_crawler_spawn_egg")))
                    .spawnEgg(WLEntities.DROWNED_SKULL_CRAWLER.get())));

    public static final DeferredHolder<Item, Item> PIGLIN_SKULL_CRAWLER_EGG = ITEMS_REGISTER.register(
            "piglin_skull_crawler_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "piglin_skull_crawler_spawn_egg")))
                    .spawnEgg(WLEntities.PIGLIN_SKULL_CRAWLER.get())));

    // breeze
    public static final DeferredHolder<Item, Item> THUNDERSTORM_EGG = ITEMS_REGISTER.register(
            "thunderstorm_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "thunderstorm_spawn_egg")))
                    .spawnEgg(WLEntities.THUNDERSTORM.get())));

    public static final DeferredHolder<Item, Item> BLIZZARD_EGG = ITEMS_REGISTER.register(
            "blizzard_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "blizzard_spawn_egg")))
                    .spawnEgg(WLEntities.BLIZZARD.get())));

    public static final DeferredHolder<Item, Item> SAND_DEVIL_EGG = ITEMS_REGISTER.register(
            "sand_devil_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "sand_devil_spawn_egg")))
                    .spawnEgg(WLEntities.SAND_DEVIL.get())));

    public static final DeferredHolder<Item, Item> DIRGE_GALE_EGG = ITEMS_REGISTER.register(
            "dirge_gale_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "dirge_gale_spawn_egg")))
                    .spawnEgg(WLEntities.DIRGE_GALE.get())));

    // ghosts
    public static final DeferredHolder<Item, Item> HOLLOW_STALKER_EGG = ITEMS_REGISTER.register(
            "hollow_stalker_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "hollow_stalker_spawn_egg")))
                    .spawnEgg(WLEntities.HOLLOW_STALKER.get())));

    // creepers
    public static final DeferredHolder<Item, Item> DESERT_CREEPER_EGG = ITEMS_REGISTER.register(
            "desert_creeper_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "desert_creeper_spawn_egg")))
                    .spawnEgg(WLEntities.DESERT_CREEPER.get())));

    public static final DeferredHolder<Item, Item> SNOWY_CREEPER_EGG = ITEMS_REGISTER.register(
            "snowy_creeper_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "snowy_creeper_spawn_egg")))
                    .spawnEgg(WLEntities.SNOWY_CREEPER.get())));

    public static final DeferredHolder<Item, Item> CAVE_CREEPER_EGG = ITEMS_REGISTER.register(
            "cave_creeper_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "cave_creeper_spawn_egg")))
                    .spawnEgg(WLEntities.CAVE_CREEPER.get())));

    public static final DeferredHolder<Item, Item> DEEPSLATE_CREEPER_EGG = ITEMS_REGISTER.register(
            "deepslate_creeper_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "deepslate_creeper_spawn_egg")))
                    .spawnEgg(WLEntities.DEEPSLATE_CREEPER.get())));

    // wolves
    public static final DeferredHolder<Item, Item> SKELETON_DOG_EGG = ITEMS_REGISTER.register(
            "skeleton_dog_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "skeleton_dog_spawn_egg")))
                    .spawnEgg(WLEntities.SKELETON_DOG.get())));

    public static final DeferredHolder<Item, Item> ZOMBIE_DOG_EGG = ITEMS_REGISTER.register(
            "zombie_dog_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "zombie_dog_spawn_egg")))
                    .spawnEgg(WLEntities.ZOMBIE_DOG.get())));

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

    public static final DeferredHolder<Item, Item> ZOMBIE_CAT_EGG = ITEMS_REGISTER.register(
            "zombie_cat_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "zombie_cat_spawn_egg")))
                    .spawnEgg(WLEntities.ZOMBIE_CAT.get())));

    // horses
    public static final DeferredHolder<Item, Item> SKELETON_HORSE_EGG = ITEMS_REGISTER.register(
            "skeleton_horse_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "skeleton_horse_spawn_egg")))
                    .spawnEgg(WLEntities.SKELETON_HORSE.get())));

    public static final DeferredHolder<Item, Item> ZOMBIE_HORSE_EGG = ITEMS_REGISTER.register(
            "zombie_horse_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "zombie_horse_spawn_egg")))
                    .spawnEgg(WLEntities.ZOMBIE_HORSE.get())));

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

    // swamp
    public static final DeferredHolder<Item, Item> SWAMP_THING_EGG = ITEMS_REGISTER.register(
            "swamp_thing_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "swamp_thing_spawn_egg")))
                    .spawnEgg(WLEntities.SWAMP_THING.get())));

    public static final DeferredHolder<Item, Item> GIANT_FROG_EGG = ITEMS_REGISTER.register(
            "giant_frog_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "giant_frog_spawn_egg")))
                    .spawnEgg(WLEntities.GIANT_FROG.get())));

    // fishes
    public static final DeferredHolder<Item, Item> MINNOW_EGG = ITEMS_REGISTER.register(
            "minnow_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "minnow_spawn_egg")))
                    .spawnEgg(WLEntities.MINNOW.get())));

    public static final DeferredHolder<Item, Item> PIRANHA_EGG = ITEMS_REGISTER.register(
            "piranha_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "piranha_spawn_egg")))
                    .spawnEgg(WLEntities.PIRANHA.get())));

    public static final DeferredHolder<Item, Item> PIKE_EGG = ITEMS_REGISTER.register(
            "pike_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "pike_spawn_egg")))
                    .spawnEgg(WLEntities.PIKE.get())));

    // desert
    public static final DeferredHolder<Item, Item> MUMMY_EGG = ITEMS_REGISTER.register(
            "mummy_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "mummy_spawn_egg")))
                    .spawnEgg(WLEntities.MUMMY.get())));

    // snow
    public static final DeferredHolder<Item, Item> SNOWMAN_EGG = ITEMS_REGISTER.register(
            "snowman_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "snowman_spawn_egg")))
                    .spawnEgg(WLEntities.SNOWMAN.get())));

    // giants
    public static final DeferredHolder<Item, Item> HILL_GIANT_EGG = ITEMS_REGISTER.register(
            "hill_giant_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "hill_giant_spawn_egg")))
                    .spawnEgg(WLEntities.HILL_GIANT.get())));

    public static final DeferredHolder<Item, Item> FROZEN_GIANT_EGG = ITEMS_REGISTER.register(
            "frozen_giant_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "frozen_giant_spawn_egg")))
                    .spawnEgg(WLEntities.FROZEN_GIANT.get())));

    // other
    public static final DeferredHolder<Item, Item> POSSESSED_ARMOR_EGG = ITEMS_REGISTER.register(
            "possessed_armor_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "possessed_armor_spawn_egg")))
                    .spawnEgg(WLEntities.POSSESSED_ARMOR.get())));

    public static final DeferredHolder<Item, Item> SKELETON_EGG = ITEMS_REGISTER.register(
            "skeleton_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "skeleton_spawn_egg")))
                    .spawnEgg(WLEntities.SKELETON.get())));

    public static final DeferredHolder<Item, Item> ILLUSIONER_EGG = ITEMS_REGISTER.register(
            "illusioner_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "illusioner_spawn_egg")))
                    .spawnEgg(WLEntities.ILLUSIONER.get())));

    public static final DeferredHolder<Item, Item> KILLER_BUNNY_EGG = ITEMS_REGISTER.register(
            "killer_bunny_spawn_egg",
            () -> new SpawnEggItem(new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "killer_bunny_spawn_egg")))
                    .spawnEgg(WLEntities.KILLER_BUNNY.get())));

    // items
    public static final DeferredHolder<Item, Item> SLIME_GEL = ITEMS_REGISTER.register(
            "slime_gel",
            () -> new SlimeGel(new Item.Properties()
                    .stacksTo(64)
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationModifier(0.2F)
                            .build())
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "slime_gel")))));

    public static final DeferredHolder<Item, Item> BAT_WING = ITEMS_REGISTER.register(
            "bat_wing",
            () -> new BatWing(new Item.Properties()
                    .stacksTo(64)
                    .food(new FoodProperties.Builder()
                            .nutrition(1)
                            .saturationModifier(0.2F)
                            .build())
                    .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, "bat_wing")))));

    public static final DeferredHolder<Item, Item> EXTINGUISHED_TORCH = ITEMS_REGISTER.register(
            "extinguished_torch",
            () -> new StandingAndWallBlockItem(
                    WLBlocks.EXTINGUISHED_TORCH.get(), WLBlocks.EXTINGUISHED_WALL_TORCH.get(), Direction.DOWN,
                    new Item.Properties()
                            .stacksTo(64)
                            .setId(WLBlocks.EXTINGUISHED_TORCH_RK)));

    public static final DeferredHolder<Item, Item> EXTINGUISHED_COPPER_TORCH = ITEMS_REGISTER.register(
            "extinguished_copper_torch",
            () -> new StandingAndWallBlockItem(
                    WLBlocks.EXTINGUISHED_COPPER_TORCH.get(), WLBlocks.EXTINGUISHED_COPPER_WALL_TORCH.get(), Direction.DOWN,
                    new Item.Properties()
                            .stacksTo(64)
                            .setId(WLBlocks.EXTINGUISHED_COPPER_TORCH_RK)));

    public static final DeferredHolder<Item, Item> EXTINGUISHED_SOUL_TORCH = ITEMS_REGISTER.register(
            "extinguished_soul_torch",
            () -> new StandingAndWallBlockItem(
                    WLBlocks.EXTINGUISHED_SOUL_TORCH.get(), WLBlocks.EXTINGUISHED_SOUL_WALL_TORCH.get(), Direction.DOWN,
                    new Item.Properties()
                            .stacksTo(64)
                            .setId(WLBlocks.EXTINGUISHED_SOUL_TORCH_RK)));

    public static void register(IEventBus eventBus) {
        ITEMS_REGISTER.register(eventBus);
    }

}
