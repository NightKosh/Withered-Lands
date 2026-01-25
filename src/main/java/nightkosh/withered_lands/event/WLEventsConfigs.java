package nightkosh.withered_lands.event;

import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import nightkosh.withered_lands.core.ModInfo;
import nightkosh.withered_lands.core.WLConfigs;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@EventBusSubscriber(modid = ModInfo.ID)
public class WLEventsConfigs {

    private static final int DEFAULT_CAP = 70;

    @SubscribeEvent
    public static void onConfigsLoading(ModConfigEvent.Loading event) {
        if (event.getConfig().getSpec() == WLConfigs.SPEC) {
            MobCategory.MONSTER.max = (int) (DEFAULT_CAP * WLConfigs.MOB_CAP_MULTIPLIER.get());
        }
    }

    @SubscribeEvent
    public static void onConfigsLoading(ModConfigEvent.Reloading event) {
        if (event.getConfig().getSpec() == WLConfigs.SPEC) {
            MobCategory.MONSTER.max = (int) (DEFAULT_CAP * WLConfigs.MOB_CAP_MULTIPLIER.get());
        }
    }

}
