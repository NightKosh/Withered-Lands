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

    // slimes
    public static ModConfigSpec.ConfigValue<Boolean> VERDANT_SLIME_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> SANDY_SLIME_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> FROZEN_SLIME_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> MUD_SLIME_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> JUNGLE_SLIME_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> CAVE_SLIME_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> ABYSSAL_SLIME_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> MOLTEN_SLIME_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> MOLTEN_SLIME_LAVA;
    // bats
    public static ModConfigSpec.ConfigValue<Boolean> VAMPIRE_BAT_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> FLYING_FOX_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> BLAZING_BAT_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> WITHERED_BAT_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> VOLATILE_BAT_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> CHORUS_BAT_SPAWN;

    // wolves
    public static ModConfigSpec.ConfigValue<Boolean> SKELETON_DOG;
    public static ModConfigSpec.ConfigValue<Boolean> ZOMBIE_DOG;
    public static ModConfigSpec.ConfigValue<Boolean> BARGHEST_SPAWN;

    // cats
    public static ModConfigSpec.ConfigValue<Boolean> SKELETON_CAT;
    public static ModConfigSpec.ConfigValue<Boolean> ZOMBIE_CAT;

    // horses
    public static ModConfigSpec.ConfigValue<Boolean> SKELETON_HORSE;
    public static ModConfigSpec.ConfigValue<Boolean> ZOMBIE_HORSE;

    // underwater mobs
    public static ModConfigSpec.ConfigValue<Boolean> DROWNED_SAILOR;
    public static ModConfigSpec.ConfigValue<Boolean> PHANTOM_DIVER;
    public static ModConfigSpec.ConfigValue<Boolean> SWAMP_THING;

    // desert
    public static ModConfigSpec.ConfigValue<Boolean> MUMMY;

    // other
    public static ModConfigSpec.ConfigValue<Boolean> ZOMBIE_PETS_ATTACK_PETS;
    public static ModConfigSpec.ConfigValue<Boolean> DEBUG_MODE;

    static {
        BUILDER.push("Configs for Withered Lands Mod");

        // slimes
        VERDANT_SLIME_SPAWN = BUILDER.define("Verdant Slimes Spawn", true);
        SANDY_SLIME_SPAWN = BUILDER.define("Sandy Slimes Spawn", true);
        FROZEN_SLIME_SPAWN = BUILDER.define("Frozen Slimes Spawn", true);
        MUD_SLIME_SPAWN = BUILDER.define("Mud Slimes Spawn", true);
        JUNGLE_SLIME_SPAWN = BUILDER.define("Jungle Slimes Spawn", true);
        CAVE_SLIME_SPAWN = BUILDER.define("Cave Slimes Spawn", true);
        ABYSSAL_SLIME_SPAWN = BUILDER.define("Abyssal Slimes Spawn", true);
        MOLTEN_SLIME_SPAWN = BUILDER.define("Molten Slimes Spawn", true);
        MOLTEN_SLIME_LAVA = BUILDER.define("Molten Slimes Create LAVA", true);
        // bats
        VAMPIRE_BAT_SPAWN = BUILDER.define("Vampire Bats Spawn", true);
        FLYING_FOX_SPAWN = BUILDER.define("Flying Foxes Spawn", true);
        BLAZING_BAT_SPAWN = BUILDER.define("Blazing Bats Spawn", true);
        WITHERED_BAT_SPAWN = BUILDER.define("Withered Bats Spawn", true);
        VOLATILE_BAT_SPAWN = BUILDER.define("Volatile Bats Spawn", true);
        CHORUS_BAT_SPAWN = BUILDER.define("Chorus Bats Spawn", true);

        // wolves
        SKELETON_DOG = BUILDER.define("Skeleton Dogs Spawn", true);
        ZOMBIE_DOG = BUILDER.define("Zombie Dogs Spawn", true);
        BARGHEST_SPAWN = BUILDER.define("Barghests Spawn", true);

        // cats
        SKELETON_CAT = BUILDER.define("Skeleton Cats Spawn", true);
        ZOMBIE_CAT = BUILDER.define("Zombie Cats Spawn", true);

        // horses
        SKELETON_HORSE = BUILDER.define("Skeleton Horses Spawn", true);
        ZOMBIE_HORSE = BUILDER.define("Zombie Horses Spawn", true);

        // underwater mobs
        DROWNED_SAILOR = BUILDER.define("Drowned Sailors Spawn", true);
        PHANTOM_DIVER = BUILDER.define("Phantom Divers Spawn", true);
        SWAMP_THING = BUILDER.define("Swamp Things Spawn", true);

        // desert
        MUMMY = BUILDER.define("Mummies Spawn", true);

        // other
        ZOMBIE_PETS_ATTACK_PETS = BUILDER.define("Should zombie pets attack pets", true);

        DEBUG_MODE = BUILDER.comment("Enable additional dev logs")
                .define("Debug Mode", false);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

}
