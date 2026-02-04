package nightkosh.withered_lands.event;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.CanPlayerSleepEvent;
import nightkosh.withered_lands.core.ModInfo;
import nightkosh.withered_lands.core.WLConfigs;

import static nightkosh.withered_lands.WitheredLandsMod.LOGGER;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@EventBusSubscriber(modid = ModInfo.ID)
public class WLEventsSleep {

    private static final int MIN_FOOD_TO_SLEEP = 18;

    @SubscribeEvent
    public static void onCanPlayerSleep(CanPlayerSleepEvent event) {
        var player = event.getEntity();
        var level = event.getLevel();
        if (!player.level().isClientSide() && event.getVanillaProblem() == null) {
            if (WLConfigs.DEBUG_MODE.get()) {
                LOGGER.info("CanPlayerSleepEvent event triggered for player {}.", player.getScoreboardName());
            }

            if (WLConfigs.TO_HUNGRY_TO_SLEEP.get() && player.getFoodData().getFoodLevel() < MIN_FOOD_TO_SLEEP) {
                denySleep(player, event, Component.translatable("message.withered_lands.to_hungry_to_sleep")
                        .withStyle(ChatFormatting.RED));
            } else if (WLConfigs.OPEN_SKY_SLEEP.get() && hasOpenSkyForBed(level, event.getPos())) {
                denySleep(player, event, Component.translatable("message.withered_lands.open_sky_sleep")
                        .withStyle(ChatFormatting.RED));
            }
        }
    }

    private static void denySleep(ServerPlayer player, CanPlayerSleepEvent event, Component msg) {
        event.setProblem(Player.BedSleepingProblem.OTHER_PROBLEM);
        player.displayClientMessage(msg, true);
    }

    private static boolean hasOpenSkyForBed(Level level, BlockPos bedPos) {
        var posAbove = bedPos.above();
        return level.canSeeSky(posAbove) &&
                level.canSeeSky(posAbove.north()) &&
                level.canSeeSky(posAbove.south()) &&
                level.canSeeSky(posAbove.west()) &&
                level.canSeeSky(posAbove.east());
    }

}
