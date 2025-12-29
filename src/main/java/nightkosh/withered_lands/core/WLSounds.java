package nightkosh.withered_lands.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.minecraft.resources.Identifier.fromNamespaceAndPath;
import static net.minecraft.resources.Identifier.parse;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WLSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS_REGISTER =
            DeferredRegister.create(Registries.SOUND_EVENT, ModInfo.ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> BARGHEST_GROWL =
            registerSoundEvent("barghest_growl", "barghest.growl");

    public static final DeferredHolder<SoundEvent, SoundEvent> BARGHEST_HURT =
            registerSoundEvent("barghest_hurt", parse("entity.wolf_angry.hurt"));

    public static final DeferredHolder<SoundEvent, SoundEvent> BARGHEST_DEATH =
            registerSoundEvent("barghest_death", parse("entity.wolf_big.death"));

    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvent(String name, String path) {
        return registerSoundEvent(name, fromNamespaceAndPath(ModInfo.ID, path));
    }

    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvent(String name, Identifier identifier) {
        return SOUND_EVENTS_REGISTER.register(name, () -> SoundEvent.createVariableRangeEvent(identifier));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS_REGISTER.register(eventBus);
    }

}
