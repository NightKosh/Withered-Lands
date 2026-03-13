package nightkosh.withered_lands.core;

import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import static net.minecraft.resources.Identifier.fromNamespaceAndPath;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WLAdvancements {

    public static final Identifier ROOT = fromNamespaceAndPath(ModInfo.ID, "root");

    public static final Identifier SLIME_SEASON = fromNamespaceAndPath(ModInfo.ID, "slime_season");
    public static final Identifier SEVEN_SOUP = fromNamespaceAndPath(ModInfo.ID, "seven_soup");
    public static final Identifier RUSTED_AWAY = fromNamespaceAndPath(ModInfo.ID, "rusted_away");
    public static final Identifier MOLTEN_MESS = fromNamespaceAndPath(ModInfo.ID, "molten_mess");
    public static final Identifier SLIME_EXTERMINATOR = fromNamespaceAndPath(ModInfo.ID, "slime_exterminator");
    public static final Identifier SLIMEPOCALYPSE = fromNamespaceAndPath(ModInfo.ID, "slimepocalypse");

    public static final Identifier NOT_SO_HARMLESS = fromNamespaceAndPath(ModInfo.ID, "not_so_harmless");
    public static final Identifier KAMIKAZE = fromNamespaceAndPath(ModInfo.ID, "kamikaze");
    public static final Identifier CHAOTIC_BLINK = fromNamespaceAndPath(ModInfo.ID, "chaotic_blink");
    public static final Identifier MASTER_OF_THE_NIGHT = fromNamespaceAndPath(ModInfo.ID, "master_of_the_night");

    public static final Identifier RESTLESS_BONES = fromNamespaceAndPath(ModInfo.ID, "restless_bones");
    public static final Identifier SKULL_HUNTER = fromNamespaceAndPath(ModInfo.ID, "skull_hunter");
    public static final Identifier PROOF_OF_THE_HUNT = fromNamespaceAndPath(ModInfo.ID, "proof_of_the_hunt");

    public static final Identifier THATS_NOT_A_COD = fromNamespaceAndPath(ModInfo.ID, "thats_not_a_cod");
    public static final Identifier STINGING_CATCH = fromNamespaceAndPath(ModInfo.ID, "stinging_catch");
    public static final Identifier JELLYFISH_JAM = fromNamespaceAndPath(ModInfo.ID, "jellyfish_jam");
    public static final Identifier GET_OUT_OF_MY_SWAMP = fromNamespaceAndPath(ModInfo.ID, "get_out_of_my_swamp");
    public static final Identifier CALL_OF_THE_ABYSS = fromNamespaceAndPath(ModInfo.ID, "call_of_the_abyss");

    public static final Identifier SOMETHINGS_WRONG = fromNamespaceAndPath(ModInfo.ID, "somethings_wrong");
    public static final Identifier BRING_OUT_THE_HOLY_HAND_GRENADE = fromNamespaceAndPath(ModInfo.ID, "bring_out_the_holy_hand_grenade");
    public static final Identifier SMOKE_AND_MIRRORS = fromNamespaceAndPath(ModInfo.ID, "smoke_and_mirrors");
    public static final Identifier FRIENDLY_FIRE = fromNamespaceAndPath(ModInfo.ID, "friendly_fire");
    public static final Identifier THE_WIND_CHANGES = fromNamespaceAndPath(ModInfo.ID, "the_wind_changes");
    public static final Identifier WHISPERS_OF_THE_DEAD = fromNamespaceAndPath(ModInfo.ID, "whispers_of_the_dead");
    public static final Identifier EYE_OF_THE_STORM = fromNamespaceAndPath(ModInfo.ID, "eye_of_the_storm");

    public static final Identifier SUPERNATURAL = fromNamespaceAndPath(ModInfo.ID, "supernatural");
    public static final Identifier A_CURSED_REWARD = fromNamespaceAndPath(ModInfo.ID, "a_cursed_reward");
    public static final Identifier PET_SEMATARY = fromNamespaceAndPath(ModInfo.ID, "pet_sematary");
    public static final Identifier EYES_IN_THE_DARK = fromNamespaceAndPath(ModInfo.ID, "eyes_in_the_dark");
    public static final Identifier WHOS_A_GOOD_BOY = fromNamespaceAndPath(ModInfo.ID, "whos_a_good_boy");
    public static final Identifier ITS_A_TRAP = fromNamespaceAndPath(ModInfo.ID, "its_a_trap");
    public static final Identifier TRUST_ISSUES = fromNamespaceAndPath(ModInfo.ID, "trust_issues");
    public static final Identifier LINGERING_SHADOW = fromNamespaceAndPath(ModInfo.ID, "lingering_shadow");
    public static final Identifier NO_MORE_SHADOWS = fromNamespaceAndPath(ModInfo.ID, "no_more_shadows");

    public static void giveAdvancement(Player player, Level level, Identifier advancement) {
        if (player instanceof ServerPlayer serverPlayer) {
            var adv = level.getServer().getAdvancements().get(advancement);
            if (adv != null) {
                var playerAdv = serverPlayer.getAdvancements();
                if (!playerAdv.getOrStartProgress(adv).isDone()) {
                    playerAdv.award(adv, "triggered");
                }
            }
        }
    }

}
