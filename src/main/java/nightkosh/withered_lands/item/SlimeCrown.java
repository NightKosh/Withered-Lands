package nightkosh.withered_lands.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.world_event.WorldEventManager;

import javax.annotation.Nonnull;

import static nightkosh.withered_lands.WitheredLandsMod.LOGGER;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SlimeCrown extends Item {

    public SlimeCrown(Properties properties) {
        super(properties);
    }

    @Nonnull
    @Override
    public InteractionResult use(Level level, @Nonnull Player player, @Nonnull InteractionHand hand) {
        if (!level.isClientSide() && level instanceof ServerLevel server) {
            if (WorldEventManager.get(server)
                    .toggleSlimeRain(server, true, true)) {
                LOGGER.info("Player {} activated Slime Rain by SlimeCrown", player.getScoreboardName());
                player.getItemInHand(hand).consume(1, player);
                return InteractionResult.SUCCESS;
            } else if (WLConfigs.DEBUG_MODE.get()) {
                LOGGER.info("Player {} tried to activate Slime Rain by SlimeCrown, but failed", player.getScoreboardName());
            }
        }
        return InteractionResult.FAIL;
    }

    @Override
    public boolean isFoil(@Nonnull ItemStack stack) {
        return true;
    }

}
