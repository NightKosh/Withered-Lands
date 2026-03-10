package nightkosh.withered_lands.core;

import net.neoforged.neoforge.common.ModConfigSpec;
import nightkosh.withered_lands.helper.TimeHelper;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WLConfigs {

    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    // mob cap
    public static ModConfigSpec.ConfigValue<Double> MOB_CAP_MULTIPLIER;

    // sleep
    public static ModConfigSpec.ConfigValue<Boolean> TO_HUNGRY_TO_SLEEP;
    public static ModConfigSpec.ConfigValue<Boolean> OPEN_SKY_SLEEP;

    // slimes
    public static ModConfigSpec.ConfigValue<Boolean> SLIMES_HUNGER_DEBUFF;
    public static ModConfigSpec.ConfigValue<Boolean> VERDANT_SLIME_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> SANDY_SLIME_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> SANDY_SLIME_SLOWNESS_DEBUFF;
    public static ModConfigSpec.ConfigValue<Boolean> SANDY_SLIME_SAND;
    public static ModConfigSpec.ConfigValue<Boolean> SANDY_SLIME_SPREAD_SAND;
    public static ModConfigSpec.ConfigValue<Boolean> FROZEN_SLIME_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> FROZEN_SLIME_FREEZING_DEBUFF;
    public static ModConfigSpec.ConfigValue<Boolean> FROZEN_SLIME_SNOW;
    public static ModConfigSpec.ConfigValue<Boolean> FROZEN_SLIME_SPREAD_SNOW;
    public static ModConfigSpec.ConfigValue<Boolean> MUD_SLIME_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> MUD_SLIME_NAUSEA_DEBUFF;
    public static ModConfigSpec.ConfigValue<Boolean> MUD_SLIME_MUD;
    public static ModConfigSpec.ConfigValue<Boolean> MUD_SLIME_SPREAD_MUD;
    public static ModConfigSpec.ConfigValue<Boolean> JUNGLE_SLIME_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> JUNGLE_SLIME_POISON_DEBUFF;
    public static ModConfigSpec.ConfigValue<Boolean> JUNGLE_SLIME_MOSS;
    public static ModConfigSpec.ConfigValue<Boolean> JUNGLE_SLIME_SPREAD_MOSS;
    public static ModConfigSpec.ConfigValue<Boolean> CAVE_SLIME_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> CAVE_SLIME_WEAKNESS_DEBUFF;
    public static ModConfigSpec.ConfigValue<Boolean> CAVE_SLIME_GRAVEL;
    public static ModConfigSpec.ConfigValue<Boolean> ABYSSAL_SLIME_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> ABYSSAL_SLIME_DARKNESS_DEBUFF;
    public static ModConfigSpec.ConfigValue<Boolean> TOXIC_SLUDGE_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> TOXIC_SLUDGE_BLIGHTWATER;
    public static ModConfigSpec.ConfigValue<Boolean> TOXIC_SLUDGE_RUST_DEBUFF;
    public static ModConfigSpec.ConfigValue<Boolean> TOXIC_SLUDGE_CORROSION;
    public static ModConfigSpec.ConfigValue<Boolean> MOLTEN_SLIME_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> MOLTEN_SLIME_FIRE_DEBUFF;
    public static ModConfigSpec.ConfigValue<Boolean> MOLTEN_SLIME_LAVA;
    public static ModConfigSpec.ConfigValue<Boolean> MOLTEN_SLIME_SPREAD_FIRE;
    public static ModConfigSpec.ConfigValue<Boolean> SLIME_ITEMS_CUSTOM_RENDERER;

    // bats
    public static ModConfigSpec.ConfigValue<Boolean> BATS_BLEEDING_DEBUFF;
    public static ModConfigSpec.ConfigValue<Boolean> CAVE_BAT_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> CAVE_BAT_NAUSEA_DEBUFF;
    public static ModConfigSpec.ConfigValue<Boolean> VAMPIRE_BAT_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> VAMPIRE_BAT_BLEEDING_DEBUFF;
    public static ModConfigSpec.ConfigValue<Boolean> ICE_BAT_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> ICE_BAT_FREEZING_DEBUFF;
    public static ModConfigSpec.ConfigValue<Boolean> FLYING_FOX_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> BLAZING_BAT_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> BLAZING_BAT_FIRE_DEBUFF;
    public static ModConfigSpec.ConfigValue<Boolean> WITHERED_BAT_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> WITHERED_BAT_WITHER_DEBUFF;
    public static ModConfigSpec.ConfigValue<Boolean> VOLATILE_BAT_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> VOLATILE_BAT_EXPLOSION;
    public static ModConfigSpec.ConfigValue<Boolean> CHORUS_BAT_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> CHORUS_BAT_TELEPORTATION;

    // breeze
    public static ModConfigSpec.ConfigValue<Boolean> THUNDERSTORM_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> BLIZZARD_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> SAND_DEVIL_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> DIRGE_GALE_SPAWN;

    // ghosts
    public static ModConfigSpec.ConfigValue<Boolean> HOLLOW_STALKER_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> HOLLOW_STALKER_EXTINGUISH_LIGHT;
    public static ModConfigSpec.ConfigValue<Boolean> LOST_SOUL_SPAWN;

    // spiders
    public static ModConfigSpec.ConfigValue<Boolean> CAVE_SPIDER_SPAWN;

    // creepers
    public static ModConfigSpec.ConfigValue<Boolean> CREEPER_SPAWN;

    // wolves
    public static ModConfigSpec.ConfigValue<Boolean> SKELETON_DOG_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> ZOMBIE_DOG_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> BARGHEST_SPAWN;

    // cats
    public static ModConfigSpec.ConfigValue<Boolean> SKELETON_CAT_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> ZOMBIE_CAT_SPAWN;

    // horses
    public static ModConfigSpec.ConfigValue<Boolean> SKELETON_HORSE_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> ZOMBIE_HORSE_SPAWN;

    // underwater mobs
    public static ModConfigSpec.ConfigValue<Boolean> DROWNED_SAILOR_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> PHANTOM_DIVER_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> SWAMP_THING_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> GIANT_FROG_SPAWN;

    // fish
    public static ModConfigSpec.ConfigValue<Boolean> MINNOW_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> PIRANHA_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> PIRANHA_BLEEDING_DEBUFF;
    public static ModConfigSpec.ConfigValue<Boolean> PIKE_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> JELLYFISH_HUNGER_DEBUFF;
    public static ModConfigSpec.ConfigValue<Boolean> WHITE_JELLYFISH_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> WHITE_JELLYFISH_SLOWNESS_DEBUFF;
    public static ModConfigSpec.ConfigValue<Boolean> BLUE_JELLYFISH_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> BLUE_JELLYFISH_FREEZING_DEBUFF;
    public static ModConfigSpec.ConfigValue<Boolean> GREEN_JELLYFISH_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> GREEN_JELLYFISH_POISON_DEBUFF;
    public static ModConfigSpec.ConfigValue<Boolean> YELLOW_JELLYFISH_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> YELLOW_JELLYFISH_NAUSEA_DEBUFF;
    public static ModConfigSpec.ConfigValue<Boolean> RED_JELLYFISH_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> RED_JELLYFISH_WITHER_DEBUFF;
    public static ModConfigSpec.ConfigValue<Boolean> PINK_JELLYFISH_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> PINK_JELLYFISH_WEAKNESS_DEBUFF;
    public static ModConfigSpec.ConfigValue<Boolean> PURPLE_JELLYFISH_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> PURPLE_JELLYFISH_BLINDNESS_DEBUFF;

    // desert
    public static ModConfigSpec.ConfigValue<Boolean> MUMMY_SPAWN;

    // snow
    public static ModConfigSpec.ConfigValue<Boolean> SNOWMAN_SPAWN;

    // giants
    public static ModConfigSpec.ConfigValue<Boolean> HILL_GIANT_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> FROZEN_GIANT_SPAWN;

    // other mobs
    public static ModConfigSpec.ConfigValue<Boolean> POSSESSED_ARMOR_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> MIMIC_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> SKELETON_WITH_SWORD_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> ILLUSIONER_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> KILLER_BUNNY_SPAWN;

    // other
    public static ModConfigSpec.ConfigValue<Boolean> SKULL_CRAWLERS_AT_MOBS_DEATH_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> ZOMBIE_PETS_ATTACK_PETS;
    public static ModConfigSpec.ConfigValue<Boolean> DISABLE_INFERNAL_MOBS;

    // events
    public static ModConfigSpec.ConfigValue<Boolean> SLIME_RAIN_ENABLE;
    public static ModConfigSpec.ConfigValue<Integer> SLIME_RAIN_DURATION;
    public static ModConfigSpec.ConfigValue<Integer> SLIME_RAIN_MIN_DAYS_BETWEEN_RAINS;
    public static ModConfigSpec.ConfigValue<Integer> SLIME_RAIN_CHANCE;
    public static ModConfigSpec.ConfigValue<Boolean> SLIME_RAIN_SWALLOWED_ITEMS_CHANCE_INCREASED;
    public static ModConfigSpec.ConfigValue<Boolean> SLIME_RAIN_DROP_SLIME_CROWN;
    public static ModConfigSpec.ConfigValue<Boolean> SLIME_RAIN_CRAFTABLE_CROWN;

    public static ModConfigSpec.ConfigValue<Boolean> DEBUG_MODE;

    static {
        BUILDER.push("Configs for Withered Lands Mod");

        // mob cap
        MOB_CAP_MULTIPLIER = BUILDER.comment("Changes the maximum number of monsters that can spawn. May impact performance, so adjust with care.")
                .defineInRange("Mob cap multiplier", 2D, 1, 10);

        // sleep
        TO_HUNGRY_TO_SLEEP = BUILDER.define("Can't sleep if hungry", true);
        OPEN_SKY_SLEEP = BUILDER.define("Can't sleep under the open sky", true);

        // slimes
        SLIMES_HUNGER_DEBUFF = BUILDER.define("Enable Slimes Hunger Debuff", true);
        VERDANT_SLIME_SPAWN = BUILDER.define("Verdant Slimes Spawn", true);
        SANDY_SLIME_SPAWN = BUILDER.define("Sandy Slimes Spawn", true);
        SANDY_SLIME_SLOWNESS_DEBUFF = BUILDER.define("Enable Sandy Slimes Slowness Debuff", true);
        SANDY_SLIME_SAND = BUILDER.define("Sandy Slimes Create Sand block at death", true);
        SANDY_SLIME_SPREAD_SAND = BUILDER.define("Sandy Slimes create Sand layers", true);
        FROZEN_SLIME_SPAWN = BUILDER.define("Frozen Slimes Spawn", true);
        FROZEN_SLIME_FREEZING_DEBUFF = BUILDER.define("Enable Frozen Slimes Freezing Debuff", true);
        FROZEN_SLIME_SNOW = BUILDER.define("Frozen Slimes Create Powder Snow block at death", true);
        FROZEN_SLIME_SPREAD_SNOW = BUILDER.define("Frozen Slimes create Snow layers", true);
        MUD_SLIME_SPAWN = BUILDER.define("Mud Slimes Spawn", true);
        MUD_SLIME_NAUSEA_DEBUFF = BUILDER.define("Enable Mud Slimes Nausea Debuff", true);
        MUD_SLIME_MUD = BUILDER.define("Mud Slimes Create Mud block at death", true);
        MUD_SLIME_SPREAD_MUD = BUILDER.define("Mud Slimes create Mud layers", true);
        JUNGLE_SLIME_SPAWN = BUILDER.define("Jungle Slimes Spawn", true);
        JUNGLE_SLIME_POISON_DEBUFF = BUILDER.define("Enable Jungle Slimes Poison Debuff", true);
        JUNGLE_SLIME_MOSS = BUILDER.define("Jungle Slimes Create Moss block at death", true);
        JUNGLE_SLIME_SPREAD_MOSS = BUILDER.define("Jungle Slimes create Moss carpets", true);
        CAVE_SLIME_SPAWN = BUILDER.define("Cave Slimes Spawn", true);
        CAVE_SLIME_WEAKNESS_DEBUFF = BUILDER.define("Enable Cave Slimes Weakness Debuff", true);
        CAVE_SLIME_GRAVEL = BUILDER.define("Cave Slimes Create Gravel block at death", true);
        ABYSSAL_SLIME_SPAWN = BUILDER.define("Abyssal Slimes Spawn", true);
        ABYSSAL_SLIME_DARKNESS_DEBUFF = BUILDER.define("Enable Abyssal Slimes Darkness Debuff", true);
        TOXIC_SLUDGE_SPAWN = BUILDER.define("Toxic Sludges Spawn", true);
        TOXIC_SLUDGE_BLIGHTWATER = BUILDER.define("Toxic Sludges Create Blightwater at death", true);
        TOXIC_SLUDGE_RUST_DEBUFF = BUILDER.define("Enable Toxic Sludges Rust Debuff", true);
        TOXIC_SLUDGE_CORROSION = BUILDER.define("Toxic Sludges Replace blocks", true);
        MOLTEN_SLIME_SPAWN = BUILDER.define("Molten Slimes Spawn", true);
        MOLTEN_SLIME_FIRE_DEBUFF = BUILDER.define("Enable Molten Slimes Fire Debuff", true);
        MOLTEN_SLIME_LAVA = BUILDER.define("Molten Slimes Create LAVA at death", true);
        MOLTEN_SLIME_SPREAD_FIRE = BUILDER.define("Molten Slimes ignites ground", true);
        SLIME_ITEMS_CUSTOM_RENDERER = BUILDER.comment("Switch to false in case you have any problems with item rendering inside slimes to switch to vanilla renderer")
                .define("Slime custom item renderer", true);

        // bats
        BATS_BLEEDING_DEBUFF = BUILDER.define("Enable Bats Bleeding Debuff", true);
        CAVE_BAT_SPAWN = BUILDER.define("Cave Bats Spawn", true);
        CAVE_BAT_NAUSEA_DEBUFF = BUILDER.define("Enable Cave Bats Nausea Debuff", true);
        VAMPIRE_BAT_SPAWN = BUILDER.define("Vampire Bats Spawn", true);
        VAMPIRE_BAT_BLEEDING_DEBUFF = BUILDER.define("Enable Vampire Bats Bleeding Debuff", true);
        ICE_BAT_SPAWN = BUILDER.define("Ice Bats Spawn", true);
        ICE_BAT_FREEZING_DEBUFF = BUILDER.define("Enable Ice Bats Freezing Debuff", true);
        FLYING_FOX_SPAWN = BUILDER.define("Flying Foxes Spawn", true);
        BLAZING_BAT_SPAWN = BUILDER.define("Blazing Bats Spawn", true);
        BLAZING_BAT_FIRE_DEBUFF = BUILDER.define("Enable Blazing Bats Fire Debuff", true);
        WITHERED_BAT_SPAWN = BUILDER.define("Withered Bats Spawn", true);
        WITHERED_BAT_WITHER_DEBUFF = BUILDER.define("Enable Withered Bats Withe Debuff", true);
        VOLATILE_BAT_SPAWN = BUILDER.define("Volatile Bats Spawn", true);
        VOLATILE_BAT_EXPLOSION = BUILDER.define("Enable Volatile Bats Explosion", true);
        CHORUS_BAT_SPAWN = BUILDER.define("Chorus Bats Spawn", true);
        CHORUS_BAT_TELEPORTATION = BUILDER.define("Enable Chorus Bats Teleportation", true);

        // breeze
        THUNDERSTORM_SPAWN = BUILDER.define("Thunderstorms Spawn", true);
        BLIZZARD_SPAWN = BUILDER.define("Blizzards Spawn", true);
        SAND_DEVIL_SPAWN = BUILDER.define("Sand Devils Spawn", true);
        DIRGE_GALE_SPAWN = BUILDER.define("Dirge Gales Spawn", true);

        // ghosts
        HOLLOW_STALKER_SPAWN = BUILDER.define("Hollow Stalkers Spawn", true);
        HOLLOW_STALKER_EXTINGUISH_LIGHT = BUILDER.define("Hollow Stalkers Extinguish Light", true);
        LOST_SOUL_SPAWN = BUILDER.define("Lost Souls Spawn", true);

        // spiders
        CAVE_SPIDER_SPAWN = BUILDER.define("Cave Spiders Spawn", true);

        // creepers
        CREEPER_SPAWN = BUILDER.define("Creepers Spawn", true);

        // wolves
        SKELETON_DOG_SPAWN = BUILDER.define("Skeleton Dogs Spawn", true);
        ZOMBIE_DOG_SPAWN = BUILDER.define("Zombie Dogs Spawn", true);
        BARGHEST_SPAWN = BUILDER.define("Barghests Spawn", true);

        // cats
        SKELETON_CAT_SPAWN = BUILDER.define("Skeleton Cats Spawn", true);
        ZOMBIE_CAT_SPAWN = BUILDER.define("Zombie Cats Spawn", true);

        // horses
        SKELETON_HORSE_SPAWN = BUILDER.define("Skeleton Horses Spawn", true);
        ZOMBIE_HORSE_SPAWN = BUILDER.define("Zombie Horses Spawn", true);

        // underwater mobs
        DROWNED_SAILOR_SPAWN = BUILDER.define("Drowned Sailors Spawn", true);
        PHANTOM_DIVER_SPAWN = BUILDER.define("Phantom Divers Spawn", true);
        SWAMP_THING_SPAWN = BUILDER.define("Swamp Things Spawn", true);
        GIANT_FROG_SPAWN = BUILDER.define("Giant Frogs Spawn", true);

        // fish
        MINNOW_SPAWN = BUILDER.define("Minnows Spawn", true);
        PIRANHA_SPAWN = BUILDER.define("Piranhas Spawn", true);
        PIRANHA_BLEEDING_DEBUFF = BUILDER.define("Enable Piranhas Bleeding Debuff", true);
        PIKE_SPAWN = BUILDER.define("Pikes Spawn", true);
        JELLYFISH_HUNGER_DEBUFF = BUILDER.define("Enable Jellyfishes Hunger Debuff", true);
        WHITE_JELLYFISH_SPAWN = BUILDER.define("White Jellyfishes Spawn", true);
        WHITE_JELLYFISH_SLOWNESS_DEBUFF = BUILDER.define("Enable White Jellyfishes Slowness Debuff", true);
        BLUE_JELLYFISH_SPAWN = BUILDER.define("Blue Jellyfishes Spawn", true);
        BLUE_JELLYFISH_FREEZING_DEBUFF = BUILDER.define("Enable Blue Jellyfishes Freezing Debuff", true);
        GREEN_JELLYFISH_SPAWN = BUILDER.define("Green Jellyfishes Spawn", true);
        GREEN_JELLYFISH_POISON_DEBUFF = BUILDER.define("Enable Green Jellyfishes Poison Debuff", true);
        YELLOW_JELLYFISH_SPAWN = BUILDER.define("Yellow Jellyfishes Spawn", true);
        YELLOW_JELLYFISH_NAUSEA_DEBUFF = BUILDER.define("Enable Yellow Jellyfishes Nausea Debuff", true);
        RED_JELLYFISH_SPAWN = BUILDER.define("Red Jellyfishes Spawn", true);
        RED_JELLYFISH_WITHER_DEBUFF = BUILDER.define("Enable Red Jellyfishes Wither Debuff", true);
        PINK_JELLYFISH_SPAWN = BUILDER.define("Pink Jellyfishes Spawn", true);
        PINK_JELLYFISH_WEAKNESS_DEBUFF = BUILDER.define("Enable Pink Jellyfishes Weakness Debuff", true);
        PURPLE_JELLYFISH_SPAWN = BUILDER.define("Purple Jellyfishes Spawn", true);
        PURPLE_JELLYFISH_BLINDNESS_DEBUFF = BUILDER.define("Enable Purple Jellyfishes Blindness Debuff", true);

        // desert
        MUMMY_SPAWN = BUILDER.define("Mummies Spawn", true);

        // snow
        SNOWMAN_SPAWN = BUILDER.define("Snowmans Spawn", true);

        // giants
        HILL_GIANT_SPAWN = BUILDER.define("Hill Giants Spawn", true);
        FROZEN_GIANT_SPAWN = BUILDER.define("Frozen Giants Spawn", true);

        // other mobs
        POSSESSED_ARMOR_SPAWN = BUILDER.define("Possessed Armors Spawn", true);
        MIMIC_SPAWN = BUILDER.define("Mimics Spawn", true);
        SKELETON_WITH_SWORD_SPAWN = BUILDER.define("Skeletons With Sword Spawn", true);
        ILLUSIONER_SPAWN = BUILDER.define("Illusioners Spawn", true);
        KILLER_BUNNY_SPAWN = BUILDER.define("Killer Bunnies Spawn", true);

        // other
        SKULL_CRAWLERS_AT_MOBS_DEATH_SPAWN = BUILDER.define("Should spawn skull crawlers at mobs death", true);
        ZOMBIE_PETS_ATTACK_PETS = BUILDER.define("Should zombie pets attack pets", true);

        // events
        SLIME_RAIN_ENABLE = BUILDER.define("Slime Rain event enable", true);
        SLIME_RAIN_DURATION = BUILDER.comment("Time counted as ingame ticks. 1 second = 20 ticks. By default, slime rain duration = 8 minutes.")
                .defineInRange("Slime Rain event duration", TimeHelper.MINS_8, TimeHelper.SECONDS_180, TimeHelper.MINS_30);
        SLIME_RAIN_MIN_DAYS_BETWEEN_RAINS = BUILDER.comment("Minimal amount of days between slime rains.")
                .define("Slime Rain - minimal amount of days between next slime rain", 7);
        SLIME_RAIN_CHANCE = BUILDER.comment("After the minimum number of days between events has passed, there is a 4% chance per day for the next Slime Rain to be triggered. Day 1 – 4%, Day 2 – 8%, Day 3 – 12%, etc. The maximum chance is capped at 90%.")
                .defineInRange("Slime Rain chance", 4, 1, 90);
        SLIME_RAIN_SWALLOWED_ITEMS_CHANCE_INCREASED = BUILDER.comment("By default, all medium and large slimes have a chance to contain a random slime-specific item. If enabled, this chance is tripled during Slime Rain.")
                .define("Slime Rain - increased slime items chance", true);
        SLIME_RAIN_DROP_SLIME_CROWN = BUILDER.comment("Gives a small chance for all medium and large slimes spawned during Slime Rain to contain the Crown of the Slime King.")
                .define("Slime Rain - slimes can drop Crown of the Slime King", true);
        SLIME_RAIN_CRAFTABLE_CROWN = BUILDER.define("Slime Rain - enable Crown of the Slime King crafting recipe", true);

        DISABLE_INFERNAL_MOBS = BUILDER.comment(
                        "By default some mobs from this mod should never be infernal. " +
                                "Set it to `false` to handle it on InfernalMobs mod side.")
                .define("Disable infernal mobs", true);

        DEBUG_MODE = BUILDER.comment("Enable additional dev logs")
                .define("Debug Mode", false);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

}
