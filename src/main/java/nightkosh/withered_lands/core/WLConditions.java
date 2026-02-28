package nightkosh.withered_lands.core;

import com.mojang.serialization.MapCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import nightkosh.withered_lands.condition.SlimeCrownEnabledCondition;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WLConditions {

    public static final DeferredRegister<MapCodec<? extends ICondition>> CONDITIONS =
            DeferredRegister.create(NeoForgeRegistries.CONDITION_SERIALIZERS.key(), ModInfo.ID);

    public static final DeferredHolder<MapCodec<? extends ICondition>, MapCodec<? extends ICondition>> SLIME_CROWN_RECIPE_ENABLED =
            CONDITIONS.register("slime_crown_recipe_enabled", () -> SlimeCrownEnabledCondition.CODEC);

    public static void register(IEventBus eventBus) {
        CONDITIONS.register(eventBus);
    }

}
