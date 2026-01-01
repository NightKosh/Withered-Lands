package nightkosh.withered_lands.event;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.LootTableLoadEvent;
import nightkosh.withered_lands.core.ModInfo;
import nightkosh.withered_lands.core.WLConfigs;

import static net.minecraft.resources.Identifier.fromNamespaceAndPath;
import static nightkosh.withered_lands.WitheredLandsMod.LOGGER;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@EventBusSubscriber(modid = ModInfo.ID)
public class WLLootInjector {

    @SubscribeEvent
    public static void inject(LootTableLoadEvent event) {
        if (event.getName().toString().equals("minecraft:entities/bat")) {
            if (WLConfigs.DEBUG_MODE.get()) {
                LOGGER.info("LootTableLoadEvent event triggered. Going to inject additional bat loot.");
            }

            event.getTable().addPool(
                    LootPool.lootPool()
                            .name("withered_lands_bat_inject")
                            .add(NestedLootTable.lootTableReference(
                                            ResourceKey.create(
                                                    Registries.LOOT_TABLE,
                                                    fromNamespaceAndPath(ModInfo.ID, "inject/bat")))
                                    .setWeight(1))
                            .build());
        }
    }

}
