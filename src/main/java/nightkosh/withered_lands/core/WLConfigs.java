package nightkosh.withered_lands.core;

import net.neoforged.neoforge.common.ModConfigSpec;

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
    public static ModConfigSpec.ConfigValue<Boolean> VERDANT_SLIME_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> SANDY_SLIME_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> FROZEN_SLIME_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> FROZEN_SLIME_SNOW;
    public static ModConfigSpec.ConfigValue<Boolean> MUD_SLIME_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> JUNGLE_SLIME_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> CAVE_SLIME_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> ABYSSAL_SLIME_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> TOXIC_SLUDGE_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> TOXIC_SLUDGE_CORROSION;
    public static ModConfigSpec.ConfigValue<Boolean> MOLTEN_SLIME_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> MOLTEN_SLIME_LAVA;

    // bats
    public static ModConfigSpec.ConfigValue<Boolean> CAVE_BAT_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> VAMPIRE_BAT_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> ICE_BAT_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> FLYING_FOX_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> BLAZING_BAT_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> WITHERED_BAT_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> VOLATILE_BAT_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> CHORUS_BAT_SPAWN;

    // breeze
    public static ModConfigSpec.ConfigValue<Boolean> THUNDERSTORM_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> BLIZZARD_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> SAND_DEVIL_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> DIRGE_GALE_SPAWN;

    // spiders
    public static ModConfigSpec.ConfigValue<Boolean> CAVE_SPIDER_SPAWN;

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
    public static ModConfigSpec.ConfigValue<Boolean> PIKE_SPAWN;

    // desert
    public static ModConfigSpec.ConfigValue<Boolean> MUMMY_SPAWN;

    // snow
    public static ModConfigSpec.ConfigValue<Boolean> SNOWMAN_SPAWN;

    // giants
    public static ModConfigSpec.ConfigValue<Boolean> HILL_GIANT_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> FROZEN_GIANT_SPAWN;

    // other mobs
    public static ModConfigSpec.ConfigValue<Boolean> POSSESSED_ARMOR_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> ILLUSIONER_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> KILLER_BUNNY_SPAWN;

    // other
    public static ModConfigSpec.ConfigValue<Boolean> SKULL_CRAWLERS_AT_MOBS_DEATH_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> ZOMBIE_PETS_ATTACK_PETS;
    public static ModConfigSpec.ConfigValue<Boolean> DISABLE_INFERNAL_MOBS;
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
        VERDANT_SLIME_SPAWN = BUILDER.define("Verdant Slimes Spawn", true);
        SANDY_SLIME_SPAWN = BUILDER.define("Sandy Slimes Spawn", true);
        FROZEN_SLIME_SPAWN = BUILDER.define("Frozen Slimes Spawn", true);
        FROZEN_SLIME_SNOW = BUILDER.define("Frozen Slimes Create Powder Snow block at death", true);
        MUD_SLIME_SPAWN = BUILDER.define("Mud Slimes Spawn", true);
        JUNGLE_SLIME_SPAWN = BUILDER.define("Jungle Slimes Spawn", true);
        CAVE_SLIME_SPAWN = BUILDER.define("Cave Slimes Spawn", true);
        ABYSSAL_SLIME_SPAWN = BUILDER.define("Abyssal Slimes Spawn", true);
        TOXIC_SLUDGE_SPAWN = BUILDER.define("Toxic Sludges Spawn", true);
        TOXIC_SLUDGE_CORROSION = BUILDER.define("Toxic Sludges Replace blocks", true);
        MOLTEN_SLIME_SPAWN = BUILDER.define("Molten Slimes Spawn", true);
        MOLTEN_SLIME_LAVA = BUILDER.define("Molten Slimes Create LAVA at death", true);

        // bats
        CAVE_BAT_SPAWN = BUILDER.define("Cave Bats Spawn", true);
        VAMPIRE_BAT_SPAWN = BUILDER.define("Vampire Bats Spawn", true);
        ICE_BAT_SPAWN = BUILDER.define("Ice Bats Spawn", true);
        FLYING_FOX_SPAWN = BUILDER.define("Flying Foxes Spawn", true);
        BLAZING_BAT_SPAWN = BUILDER.define("Blazing Bats Spawn", true);
        WITHERED_BAT_SPAWN = BUILDER.define("Withered Bats Spawn", true);
        VOLATILE_BAT_SPAWN = BUILDER.define("Volatile Bats Spawn", true);
        CHORUS_BAT_SPAWN = BUILDER.define("Chorus Bats Spawn", true);

        // breeze
        THUNDERSTORM_SPAWN = BUILDER.define("Thunderstorms Spawn", true);
        BLIZZARD_SPAWN = BUILDER.define("Blizzards Spawn", true);
        SAND_DEVIL_SPAWN = BUILDER.define("Sand Devils Spawn", true);
        DIRGE_GALE_SPAWN = BUILDER.define("Dirge Gales Spawn", true);

        // spiders
        CAVE_SPIDER_SPAWN = BUILDER.define("Cave Spiders Spawn", true);

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
        PIKE_SPAWN = BUILDER.define("Pikes Spawn", true);

        // desert
        MUMMY_SPAWN = BUILDER.define("Mummies Spawn", true);

        // snow
        SNOWMAN_SPAWN = BUILDER.define("Snowmans Spawn", true);

        // giants
        HILL_GIANT_SPAWN = BUILDER.define("Hill Giants Spawn", true);
        FROZEN_GIANT_SPAWN = BUILDER.define("Frozen Giants Spawn", true);

        // other mobs
        POSSESSED_ARMOR_SPAWN = BUILDER.define("Possessed Armors Spawn", true);
        ILLUSIONER_SPAWN = BUILDER.define("Illusioners Spawn", true);
        KILLER_BUNNY_SPAWN = BUILDER.define("Killer Bunnies Spawn", true);

        // other
        SKULL_CRAWLERS_AT_MOBS_DEATH_SPAWN = BUILDER.define("Should spawn skull crawlers at mobs death", true);
        ZOMBIE_PETS_ATTACK_PETS = BUILDER.define("Should zombie pets attack pets", true);

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
