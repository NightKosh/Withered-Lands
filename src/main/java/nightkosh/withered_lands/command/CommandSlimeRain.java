package nightkosh.withered_lands.command;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.permissions.Permissions;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CommandSlimeRain {

    public static final String NAME = "commands_list";

    private static int execute(CommandContext<CommandSourceStack> ctx, boolean state) {
        var src = ctx.getSource();

        return 1;
    }

    public static LiteralArgumentBuilder<CommandSourceStack> getCommand() {
        return Commands.literal(NAME)
                .requires(src -> src.permissions().hasPermission(Permissions.COMMANDS_ADMIN))
                .executes(ctx -> CommandSlimeRain.execute(ctx, true))
                .then(Commands.argument("state", BoolArgumentType.bool())
                        .executes(ctx ->
                                execute(ctx, BoolArgumentType.getBool(ctx, "state"))));
    }

}
