package nightkosh.withered_lands.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CommandsList {

    public static final String NAME = "commands_list";

    private static int execute(CommandContext<CommandSourceStack> ctx) {
        var src = ctx.getSource();
        src.sendSuccess(() -> Component.literal("Withered Lands mod commands:")
                        .withStyle(ChatFormatting.GREEN),
                false);
        src.sendSuccess(() -> Component.literal("/wl ")
                        .append(CommandSlimeRain.NAME),
                false);

        return 1;
    }

    public static LiteralArgumentBuilder<CommandSourceStack> getCommand() {
        return Commands.literal(NAME)
                .executes(CommandsList::execute);
    }

}
