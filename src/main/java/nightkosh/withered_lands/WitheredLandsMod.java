package nightkosh.withered_lands;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import nightkosh.withered_lands.core.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@Mod(ModInfo.ID)
public class WitheredLandsMod {

    public static WitheredLandsMod INSTANCE;

    public static final Logger LOGGER = LogManager.getLogger(ModInfo.ID);

    public WitheredLandsMod(IEventBus eventBus, ModContainer container) {
        INSTANCE = this;

        container.registerConfig(ModConfig.Type.COMMON, WLConfigs.SPEC, ModInfo.ID + ".toml");

        WLEntities.register(eventBus);
        WLItems.register(eventBus);
        WLTabs.register(eventBus);
    }

}
