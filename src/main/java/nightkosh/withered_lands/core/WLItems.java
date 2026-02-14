package nightkosh.withered_lands.core;

import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
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
    public static final DeferredHolder<Item, Item> VERDANT_SLIME_EGG =
            registerEgg(WLEntities.VERDANT_SLIME, "verdant_slime_spawn_egg");

    public static final DeferredHolder<Item, Item> SANDY_SLIME_EGG =
            registerEgg(WLEntities.SANDY_SLIME, "sandy_slime_spawn_egg");

    public static final DeferredHolder<Item, Item> FROZEN_SLIME_EGG =
            registerEgg(WLEntities.FROZEN_SLIME, "frozen_slime_spawn_egg");

    public static final DeferredHolder<Item, Item> MUD_SLIME_EGG =
            registerEgg(WLEntities.MUD_SLIME, "mud_slime_spawn_egg");

    public static final DeferredHolder<Item, Item> JUNGLE_SLIME_EGG =
            registerEgg(WLEntities.JUNGLE_SLIME, "jungle_slime_spawn_egg");

    public static final DeferredHolder<Item, Item> CAVE_SLIME_EGG =
            registerEgg(WLEntities.CAVE_SLIME, "cave_slime_spawn_egg");

    public static final DeferredHolder<Item, Item> ABYSSAL_SLIME_EGG =
            registerEgg(WLEntities.ABYSSAL_SLIME, "abyssal_slime_spawn_egg");

    public static final DeferredHolder<Item, Item> TOXIC_SLUDGE_EGG =
            registerEgg(WLEntities.TOXIC_SLUDGE, "toxic_sludge_spawn_egg");

    public static final DeferredHolder<Item, Item> MOLTEN_SLIME_EGG =
            registerEgg(WLEntities.MOLTEN_SLIME, "molten_slime_spawn_egg");

    // bats
    public static final DeferredHolder<Item, Item> CAVE_BAT_EGG =
            registerEgg(WLEntities.CAVE_BAT, "cave_bat_spawn_egg");

    public static final DeferredHolder<Item, Item> VAMPIRE_BAT_EGG =
            registerEgg(WLEntities.VAMPIRE_BAT, "vampire_bat_spawn_egg");

    public static final DeferredHolder<Item, Item> ICE_BAT_EGG =
            registerEgg(WLEntities.ICE_BAT, "ice_bat_spawn_egg");

    public static final DeferredHolder<Item, Item> FLYING_FOX_EGG =
            registerEgg(WLEntities.FLYING_FOX, "flying_fox_spawn_egg");

    public static final DeferredHolder<Item, Item> BLAZING_BAT_EGG =
            registerEgg(WLEntities.BLAZING_BAT, "blazing_bat_spawn_egg");

    public static final DeferredHolder<Item, Item> WITHERED_BAT_EGG =
            registerEgg(WLEntities.WITHERED_BAT, "withered_bat_spawn_egg");

    public static final DeferredHolder<Item, Item> VOLATILE_BAT_EGG =
            registerEgg(WLEntities.VOLATILE_BAT, "volatile_bat_spawn_egg");

    public static final DeferredHolder<Item, Item> CHORUS_BAT_EGG =
            registerEgg(WLEntities.CHORUS_BAT, "chorus_bat_spawn_egg");

    // crawlers
    public static final DeferredHolder<Item, Item> SKELETON_SKULL_CRAWLER_EGG =
            registerEgg(WLEntities.SKELETON_SKULL_CRAWLER, "skeleton_skull_crawler_spawn_egg");

    public static final DeferredHolder<Item, Item> STRAY_SKULL_CRAWLER_EGG =
            registerEgg(WLEntities.STRAY_SKULL_CRAWLER, "stray_skull_crawler_spawn_egg");

    public static final DeferredHolder<Item, Item> BOGGED_SKULL_CRAWLER_EGG =
            registerEgg(WLEntities.BOGGED_SKULL_CRAWLER, "bogged_skull_crawler_spawn_egg");

    public static final DeferredHolder<Item, Item> PARCHED_SKULL_CRAWLER_EGG =
            registerEgg(WLEntities.PARCHED_SKULL_CRAWLER, "parched_skull_crawler_spawn_egg");

    public static final DeferredHolder<Item, Item> WITHER_SKULL_CRAWLER_EGG =
            registerEgg(WLEntities.WITHER_SKULL_CRAWLER, "wither_skull_crawler_spawn_egg");

    public static final DeferredHolder<Item, Item> ZOMBIE_SKULL_CRAWLER_EGG =
            registerEgg(WLEntities.ZOMBIE_SKULL_CRAWLER, "zombie_skull_crawler_spawn_egg");

    public static final DeferredHolder<Item, Item> HUSK_SKULL_CRAWLER_EGG =
            registerEgg(WLEntities.HUSK_SKULL_CRAWLER, "husk_skull_crawler_spawn_egg");

    public static final DeferredHolder<Item, Item> DROWNED_SKULL_CRAWLER_EGG =
            registerEgg(WLEntities.DROWNED_SKULL_CRAWLER, "drowned_skull_crawler_spawn_egg");

    public static final DeferredHolder<Item, Item> PIGLIN_SKULL_CRAWLER_EGG =
            registerEgg(WLEntities.PIGLIN_SKULL_CRAWLER, "piglin_skull_crawler_spawn_egg");

    // breeze
    public static final DeferredHolder<Item, Item> THUNDERSTORM_EGG =
            registerEgg(WLEntities.THUNDERSTORM, "thunderstorm_spawn_egg");

    public static final DeferredHolder<Item, Item> BLIZZARD_EGG =
            registerEgg(WLEntities.BLIZZARD, "blizzard_spawn_egg");

    public static final DeferredHolder<Item, Item> SAND_DEVIL_EGG =
            registerEgg(WLEntities.SAND_DEVIL, "sand_devil_spawn_egg");

    public static final DeferredHolder<Item, Item> DIRGE_GALE_EGG =
            registerEgg(WLEntities.DIRGE_GALE, "dirge_gale_spawn_egg");

    // ghosts
    public static final DeferredHolder<Item, Item> HOLLOW_STALKER_EGG =
            registerEgg(WLEntities.HOLLOW_STALKER, "hollow_stalker_spawn_egg");

    public static final DeferredHolder<Item, Item> LOST_SOUL_EGG =
            registerEgg(WLEntities.LOST_SOUL, "lost_soul_spawn_egg");

    // creepers
    public static final DeferredHolder<Item, Item> DESERT_CREEPER_EGG =
            registerEgg(WLEntities.DESERT_CREEPER, "desert_creeper_spawn_egg");

    public static final DeferredHolder<Item, Item> SNOWY_CREEPER_EGG =
            registerEgg(WLEntities.SNOWY_CREEPER, "snowy_creeper_spawn_egg");

    public static final DeferredHolder<Item, Item> CAVE_CREEPER_EGG =
            registerEgg(WLEntities.CAVE_CREEPER, "cave_creeper_spawn_egg");

    public static final DeferredHolder<Item, Item> DEEPSLATE_CREEPER_EGG =
            registerEgg(WLEntities.DEEPSLATE_CREEPER, "deepslate_creeper_spawn_egg");

    // wolves
    public static final DeferredHolder<Item, Item> SKELETON_DOG_EGG =
            registerEgg(WLEntities.SKELETON_DOG, "skeleton_dog_spawn_egg");

    public static final DeferredHolder<Item, Item> ZOMBIE_DOG_EGG =
            registerEgg(WLEntities.ZOMBIE_DOG, "zombie_dog_spawn_egg");

    public static final DeferredHolder<Item, Item> BARGHEST_EGG =
            registerEgg(WLEntities.BARGHEST, "barghest_spawn_egg");

    // cats
    public static final DeferredHolder<Item, Item> SKELETON_CAT_EGG =
            registerEgg(WLEntities.SKELETON_CAT, "skeleton_cat_spawn_egg");

    public static final DeferredHolder<Item, Item> ZOMBIE_CAT_EGG =
            registerEgg(WLEntities.ZOMBIE_CAT, "zombie_cat_spawn_egg");

    // horses
    public static final DeferredHolder<Item, Item> SKELETON_HORSE_EGG =
            registerEgg(WLEntities.SKELETON_HORSE, "skeleton_horse_spawn_egg");

    public static final DeferredHolder<Item, Item> ZOMBIE_HORSE_EGG =
            registerEgg(WLEntities.ZOMBIE_HORSE, "zombie_horse_spawn_egg");

    // underwater mobs
    public static final DeferredHolder<Item, Item> DROWNED_SAILOR_EGG =
            registerEgg(WLEntities.DROWNED_SAILOR, "drowned_sailor_spawn_egg");

    public static final DeferredHolder<Item, Item> PHANTOM_DIVER_EGG =
            registerEgg(WLEntities.PHANTOM_DIVER, "phantom_diver_spawn_egg");

    // swamp
    public static final DeferredHolder<Item, Item> SWAMP_THING_EGG =
            registerEgg(WLEntities.SWAMP_THING, "swamp_thing_spawn_egg");

    public static final DeferredHolder<Item, Item> GIANT_FROG_EGG =
            registerEgg(WLEntities.GIANT_FROG, "giant_frog_spawn_egg");

    // fishes
    public static final DeferredHolder<Item, Item> MINNOW_EGG =
            registerEgg(WLEntities.MINNOW, "minnow_spawn_egg");

    public static final DeferredHolder<Item, Item> PIRANHA_EGG =
            registerEgg(WLEntities.PIRANHA, "piranha_spawn_egg");

    public static final DeferredHolder<Item, Item> PIKE_EGG =
            registerEgg(WLEntities.PIKE, "pike_spawn_egg");

    // desert
    public static final DeferredHolder<Item, Item> MUMMY_EGG =
            registerEgg(WLEntities.MUMMY, "mummy_spawn_egg");

    // snow
    public static final DeferredHolder<Item, Item> SNOWMAN_EGG =
            registerEgg(WLEntities.SNOWMAN, "snowman_spawn_egg");

    // giants
    public static final DeferredHolder<Item, Item> HILL_GIANT_EGG =
            registerEgg(WLEntities.HILL_GIANT, "hill_giant_spawn_egg");

    public static final DeferredHolder<Item, Item> FROZEN_GIANT_EGG =
            registerEgg(WLEntities.FROZEN_GIANT, "frozen_giant_spawn_egg");

    // other
    public static final DeferredHolder<Item, Item> POSSESSED_ARMOR_EGG =
            registerEgg(WLEntities.POSSESSED_ARMOR, "possessed_armor_spawn_egg");

    public static final DeferredHolder<Item, Item> MIMIC_EGG =
            registerEgg(WLEntities.MIMIC, "mimic_spawn_egg");

    public static final DeferredHolder<Item, Item> SKELETON_EGG =
            registerEgg(WLEntities.SKELETON, "skeleton_spawn_egg");

    public static final DeferredHolder<Item, Item> ILLUSIONER_EGG =
            registerEgg(WLEntities.ILLUSIONER, "illusioner_spawn_egg");

    public static final DeferredHolder<Item, Item> KILLER_BUNNY_EGG =
            registerEgg(WLEntities.KILLER_BUNNY, "killer_bunny_spawn_egg");

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

    // skulls
    public static final DeferredHolder<Item, Item> SKULL_HUSK = ITEMS_REGISTER.register(
            "skull_husk",
            () -> new BlockItem(
                    WLBlocks.SKULL_HUSK.get(),
                    new Item.Properties()
                            .stacksTo(64)
                            .equippable(EquipmentSlot.HEAD)
                            .setId(WLBlocks.SKULL_HUSK_RK)));

    public static final DeferredHolder<Item, Item> SKULL_DROWNED = ITEMS_REGISTER.register(
            "skull_drowned",
            () -> new BlockItem(
                    WLBlocks.SKULL_DROWNED.get(),
                    new Item.Properties()
                            .stacksTo(64)
                            .equippable(EquipmentSlot.HEAD)
                            .setId(WLBlocks.SKULL_DROWNED_RK)));

    public static final DeferredHolder<Item, Item> SKULL_STRAY = ITEMS_REGISTER.register(
            "skull_stray",
            () -> new BlockItem(
                    WLBlocks.SKULL_STRAY.get(),
                    new Item.Properties()
                            .stacksTo(64)
                            .equippable(EquipmentSlot.HEAD)
                            .setId(WLBlocks.SKULL_STRAY_RK)));

    public static final DeferredHolder<Item, Item> SKULL_BOGGED = ITEMS_REGISTER.register(
            "skull_bogged",
            () -> new BlockItem(
                    WLBlocks.SKULL_BOGGED.get(),
                    new Item.Properties()
                            .stacksTo(64)
                            .equippable(EquipmentSlot.HEAD)
                            .setId(WLBlocks.SKULL_BOGGED_RK)));

    public static final DeferredHolder<Item, Item> SKULL_PARCHED = ITEMS_REGISTER.register(
            "skull_parched",
            () -> new BlockItem(
                    WLBlocks.SKULL_PARCHED.get(),
                    new Item.Properties()
                            .stacksTo(64)
                            .equippable(EquipmentSlot.HEAD)
                            .setId(WLBlocks.SKULL_PARCHED_RK)));

    public static final DeferredHolder<Item, Item> SKULL_PIGLIN = ITEMS_REGISTER.register(
            "skull_piglin",
            () -> new BlockItem(
                    WLBlocks.SKULL_PIGLIN.get(),
                    new Item.Properties()
                            .stacksTo(64)
                            .equippable(EquipmentSlot.HEAD)
                            .setId(WLBlocks.SKULL_PIGLIN_RK)));

    // torch
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

    private static DeferredHolder<Item, Item> registerEgg(DeferredHolder entityHolder, String id) {
        return ITEMS_REGISTER.register(
                id, () -> new SpawnEggItem(new Item.Properties()
                        .setId(ResourceKey.create(Registries.ITEM, fromNamespaceAndPath(ModInfo.ID, id)))
                        .spawnEgg(((DeferredHolder<EntityType<?>, EntityType<? extends LivingEntity>>) entityHolder).get())));
    }

    public static void register(IEventBus eventBus) {
        ITEMS_REGISTER.register(eventBus);
    }


}
