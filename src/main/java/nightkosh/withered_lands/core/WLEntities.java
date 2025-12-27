package nightkosh.withered_lands.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import nightkosh.withered_lands.entity.bat.*;

import static net.minecraft.resources.Identifier.fromNamespaceAndPath;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WLEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES_REGISTER =
            DeferredRegister.create(Registries.ENTITY_TYPE, ModInfo.ID);

    // bats
    public static final DeferredHolder<EntityType<?>, EntityType<VampireBat>> VAMPIRE_BAT =
            ENTITY_TYPES_REGISTER.register("vampire_bat",
                    () -> EntityType.Builder.of(VampireBat::new, MobCategory.MONSTER)
                            .sized(0.5F, 0.9F)
                            .eyeHeight(0.45F)
                            .notInPeaceful()
                            .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    fromNamespaceAndPath(ModInfo.ID, "vampire_bat"))));

    public static final DeferredHolder<EntityType<?>, EntityType<BlazingBat>> BLAZING_BAT =
            ENTITY_TYPES_REGISTER.register("blazing_bat",
                    () -> EntityType.Builder.of(BlazingBat::new, MobCategory.MONSTER)
                            .sized(0.5F, 0.9F)
                            .eyeHeight(0.45F)
                            .fireImmune()
                            .notInPeaceful()
                            .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    fromNamespaceAndPath(ModInfo.ID, "blazing_bat"))));

    public static final DeferredHolder<EntityType<?>, EntityType<WitheredBat>> WITHERED_BAT =
            ENTITY_TYPES_REGISTER.register("withered_bat",
                    () -> EntityType.Builder.of(WitheredBat::new, MobCategory.MONSTER)
                            .sized(0.5F, 0.9F)
                            .eyeHeight(0.45F)
                            .fireImmune()
                            .notInPeaceful()
                            .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    fromNamespaceAndPath(ModInfo.ID, "withered_bat"))));

    public static final DeferredHolder<EntityType<?>, EntityType<VolatileBat>> VOLATILE_BAT =
            ENTITY_TYPES_REGISTER.register("volatile_bat",
                    () -> EntityType.Builder.of(VolatileBat::new, MobCategory.MONSTER)
                            .sized(0.5F, 0.9F)
                            .eyeHeight(0.45F)
                            .fireImmune()
                            .notInPeaceful()
                            .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    fromNamespaceAndPath(ModInfo.ID, "volatile_bat"))));

    public static final DeferredHolder<EntityType<?>, EntityType<ChorusBat>> CHORUS_BAT =
            ENTITY_TYPES_REGISTER.register("chorus_bat",
                    () -> EntityType.Builder.of(ChorusBat::new, MobCategory.MONSTER)
                            .sized(0.5F, 0.9F)
                            .eyeHeight(0.45F)
                            .notInPeaceful()
                            .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    fromNamespaceAndPath(ModInfo.ID, "chorus_bat"))));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES_REGISTER.register(eventBus);
    }

}
