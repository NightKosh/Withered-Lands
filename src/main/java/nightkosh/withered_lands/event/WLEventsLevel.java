package nightkosh.withered_lands.event;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import nightkosh.withered_lands.core.ModInfo;
import nightkosh.withered_lands.world_event.WorldEventManager;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@EventBusSubscriber(modid = ModInfo.ID)
public class WLEventsLevel {

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post e) {
        if (e.getLevel() instanceof ServerLevel level &&
                level.dimension().equals(Level.OVERWORLD)) {
            WorldEventManager.INSTANCE.tick(level);
//            WorldEventManager.get(level).tick(level);
        }
    }

}
