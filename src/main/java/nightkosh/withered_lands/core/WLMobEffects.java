package nightkosh.withered_lands.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import nightkosh.withered_lands.mob_effect.CallOfTheAbyssEffect;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WLMobEffects {

    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, ModInfo.ID);

    public static final DeferredHolder<MobEffect, MobEffect> CALL_OF_THE_ABYSS =
            EFFECTS.register("call_of_the_abyss", CallOfTheAbyssEffect::new);


    public static void register(IEventBus eventBus) {
        EFFECTS.register(eventBus);
    }

}
