package nightkosh.withered_lands.event;

import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nightkosh.withered_lands.core.ModInfo;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.core.WLMobEffects;
import nightkosh.withered_lands.helper.WLEnchantmentHelper;

import static nightkosh.withered_lands.WitheredLandsMod.LOGGER;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@EventBusSubscriber(modid = ModInfo.ID)
public class WLEventsEntity {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onLivingHealEvent(LivingHealEvent event) {
        if (event.getEntity().hasEffect(WLMobEffects.BLEEDING)) {
            if (WLConfigs.DEBUG_MODE.get()) {
                LOGGER.info("LivingHealEvent event triggered. Going to cancel event due to bleeding effect");
            }
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onLivingTick(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof Player player && !player.level().isClientSide()) {
            if ((player.tickCount % 20) == 0) {
                if (WLConfigs.DEBUG_MODE.get()) {
                    LOGGER.info("EntityTickEvent.Post event triggered. Going to check player starvation curse.");
                }
                WLEnchantmentHelper.applyCurseEffect(player);
            }
        }
    }

}
