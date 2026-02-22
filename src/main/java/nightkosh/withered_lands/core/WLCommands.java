package nightkosh.withered_lands.core;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import nightkosh.withered_lands.command.CommandSlimeRain;
import nightkosh.withered_lands.command.CommandsList;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WLCommands {

    public static final String NAME = "withered_lands";
    public static final String ALIAS = "wl";

    public static LiteralArgumentBuilder<CommandSourceStack> root() {
        return Commands.literal(NAME)
                .then(CommandsList.getCommand())
                .then(CommandSlimeRain.getCommand());
    }

    public static LiteralArgumentBuilder<CommandSourceStack> getAlias() {
        return Commands.literal(ALIAS);
    }

}
