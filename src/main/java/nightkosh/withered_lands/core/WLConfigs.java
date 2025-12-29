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

    // bats
    public static ModConfigSpec.ConfigValue<Boolean> VAMPIRE_BAT_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> BLAZING_BAT_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> WITHERED_BAT_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> VOLATILE_BAT_SPAWN;
    public static ModConfigSpec.ConfigValue<Boolean> CHORUS_BAT_SPAWN;

    // wolves
    public static ModConfigSpec.ConfigValue<Boolean> BARGHEST_SPAWN;

    // other
    public static ModConfigSpec.ConfigValue<Boolean> DEBUG_MODE;

    static {
        BUILDER.push("Configs for Withered Lands Mod");

        // bats
        VAMPIRE_BAT_SPAWN = BUILDER.define("Vampire Bats Spawn", true);
        BLAZING_BAT_SPAWN = BUILDER.define("Blazing Bats Spawn", true);
        WITHERED_BAT_SPAWN = BUILDER.define("Withered Bats Spawn", true);
        VOLATILE_BAT_SPAWN = BUILDER.define("Volatile Bats Spawn", true);
        CHORUS_BAT_SPAWN = BUILDER.define("Chorus Bats Spawn", true);

        // wolves
        BARGHEST_SPAWN = BUILDER.define("Barghests Spawn", true);

        DEBUG_MODE = BUILDER.comment("Enable additional dev logs")
                .define("Debug Mode", false);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

}
