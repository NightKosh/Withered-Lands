package nightkosh.withered_lands.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import nightkosh.withered_lands.entity.bat.*;
import nightkosh.withered_lands.entity.cat.SkeletonCat;
import nightkosh.withered_lands.entity.cat.ZombieCat;
import nightkosh.withered_lands.entity.desert.Mummy;
import nightkosh.withered_lands.entity.horse.SkeletonHorse;
import nightkosh.withered_lands.entity.horse.ZombieHorse;
import nightkosh.withered_lands.entity.slime.*;
import nightkosh.withered_lands.entity.water.DrownedSailor;
import nightkosh.withered_lands.entity.water.PhantomDiver;
import nightkosh.withered_lands.entity.water.SwampThing;
import nightkosh.withered_lands.entity.wolf.Barghest;
import nightkosh.withered_lands.entity.wolf.SkeletonDog;
import nightkosh.withered_lands.entity.wolf.ZombieDog;

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

    // slimes
    public static final DeferredHolder<EntityType<?>, EntityType<VerdantSlime>> VERDANT_SLIME =
            ENTITY_TYPES_REGISTER.register("verdant_slime",
                    () -> EntityType.Builder.of(VerdantSlime::new, MobCategory.MONSTER)
                            .sized(0.52F, 0.52F)
                            .spawnDimensionsScale(4.0F)
                            .eyeHeight(0.325F)
                            .notInPeaceful()
                            .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    fromNamespaceAndPath(ModInfo.ID, "verdant_slime"))));

    public static final DeferredHolder<EntityType<?>, EntityType<SandySlime>> SANDY_SLIME =
            ENTITY_TYPES_REGISTER.register("sandy_slime",
                    () -> EntityType.Builder.of(SandySlime::new, MobCategory.MONSTER)
                            .sized(0.52F, 0.52F)
                            .spawnDimensionsScale(4.0F)
                            .eyeHeight(0.325F)
                            .notInPeaceful()
                            .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    fromNamespaceAndPath(ModInfo.ID, "sandy_slime"))));

    public static final DeferredHolder<EntityType<?>, EntityType<FrozenSlime>> FROZEN_SLIME =
            ENTITY_TYPES_REGISTER.register("frozen_slime",
                    () -> EntityType.Builder.of(FrozenSlime::new, MobCategory.MONSTER)
                            .sized(0.52F, 0.52F)
                            .spawnDimensionsScale(4.0F)
                            .eyeHeight(0.325F)
                            .notInPeaceful()
                            .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    fromNamespaceAndPath(ModInfo.ID, "frozen_slime"))));

    public static final DeferredHolder<EntityType<?>, EntityType<MudSlime>> MUD_SLIME =
            ENTITY_TYPES_REGISTER.register("mud_slime",
                    () -> EntityType.Builder.of(MudSlime::new, MobCategory.MONSTER)
                            .sized(0.52F, 0.52F)
                            .spawnDimensionsScale(4.0F)
                            .eyeHeight(0.325F)
                            .notInPeaceful()
                            .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    fromNamespaceAndPath(ModInfo.ID, "mud_slime"))));

    public static final DeferredHolder<EntityType<?>, EntityType<JungleSlime>> JUNGLE_SLIME =
            ENTITY_TYPES_REGISTER.register("jungle_slime",
                    () -> EntityType.Builder.of(JungleSlime::new, MobCategory.MONSTER)
                            .sized(0.52F, 0.52F)
                            .spawnDimensionsScale(4.0F)
                            .eyeHeight(0.325F)
                            .notInPeaceful()
                            .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    fromNamespaceAndPath(ModInfo.ID, "jungle_slime"))));

    public static final DeferredHolder<EntityType<?>, EntityType<CaveSlime>> CAVE_SLIME =
            ENTITY_TYPES_REGISTER.register("cave_slime",
                    () -> EntityType.Builder.of(CaveSlime::new, MobCategory.MONSTER)
                            .sized(0.52F, 0.52F)
                            .spawnDimensionsScale(4.0F)
                            .eyeHeight(0.325F)
                            .notInPeaceful()
                            .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    fromNamespaceAndPath(ModInfo.ID, "cave_slime"))));

    public static final DeferredHolder<EntityType<?>, EntityType<AbyssalSlime>> ABYSSAL_SLIME =
            ENTITY_TYPES_REGISTER.register("abyssal_slime",
                    () -> EntityType.Builder.of(AbyssalSlime::new, MobCategory.MONSTER)
                            .sized(0.52F, 0.52F)
                            .spawnDimensionsScale(4.0F)
                            .eyeHeight(0.325F)
                            .notInPeaceful()
                            .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    fromNamespaceAndPath(ModInfo.ID, "abyssal_slime"))));

    public static final DeferredHolder<EntityType<?>, EntityType<MoltenSlime>> MOLTEN_SLIME =
            ENTITY_TYPES_REGISTER.register("molten_slime",
                    () -> EntityType.Builder.of(MoltenSlime::new, MobCategory.MONSTER)
                            .sized(0.52F, 0.52F)
                            .spawnDimensionsScale(4.0F)
                            .eyeHeight(0.325F)
                            .notInPeaceful()
                            .fireImmune()
                            .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    fromNamespaceAndPath(ModInfo.ID, "molten_slime"))));

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

    public static final DeferredHolder<EntityType<?>, EntityType<FlyingFox>> FLYING_FOX =
            ENTITY_TYPES_REGISTER.register("flying_fox",
                    () -> EntityType.Builder.of(FlyingFox::new, MobCategory.MONSTER)
                            .sized(1F, 1.8F)
                            .eyeHeight(0.9F)
                            .notInPeaceful()
                            .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    fromNamespaceAndPath(ModInfo.ID, "flying_fox"))));

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

    // wolves
    public static final DeferredHolder<EntityType<?>, EntityType<SkeletonDog>> SKELETON_DOG =
            ENTITY_TYPES_REGISTER.register("skeleton_dog",
                    () -> EntityType.Builder.of(SkeletonDog::new, MobCategory.MONSTER)
                            .sized(0.6F, 0.85F)
                            .eyeHeight(0.68F)
                            .notInPeaceful()
                            .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    fromNamespaceAndPath(ModInfo.ID, "skeleton_dog"))));

    public static final DeferredHolder<EntityType<?>, EntityType<ZombieDog>> ZOMBIE_DOG =
            ENTITY_TYPES_REGISTER.register("zombie_dog",
                    () -> EntityType.Builder.of(ZombieDog::new, MobCategory.MONSTER)
                            .sized(0.6F, 0.85F)
                            .eyeHeight(0.68F)
                            .notInPeaceful()
                            .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    fromNamespaceAndPath(ModInfo.ID, "zombie_dog"))));

    public static final DeferredHolder<EntityType<?>, EntityType<Barghest>> BARGHEST =
            ENTITY_TYPES_REGISTER.register("barghest",
                    () -> EntityType.Builder.of(Barghest::new, MobCategory.MONSTER)
                            .sized(1.2F, 1.7F)
                            .eyeHeight(1.36F)
                            .notInPeaceful()
                            .immuneTo(Blocks.WITHER_ROSE)
                            .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    fromNamespaceAndPath(ModInfo.ID, "barghest"))));

    // cats
    public static final DeferredHolder<EntityType<?>, EntityType<SkeletonCat>> SKELETON_CAT =
            ENTITY_TYPES_REGISTER.register("skeleton_cat",
                    () -> EntityType.Builder.of(SkeletonCat::new, MobCategory.MONSTER)
                            .sized(0.6F, 0.7F)
                            .eyeHeight(0.35F)
                            .notInPeaceful()
                            .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    fromNamespaceAndPath(ModInfo.ID, "skeleton_cat"))));

    public static final DeferredHolder<EntityType<?>, EntityType<ZombieCat>> ZOMBIE_CAT =
            ENTITY_TYPES_REGISTER.register("zombie_cat",
                    () -> EntityType.Builder.of(ZombieCat::new, MobCategory.MONSTER)
                            .sized(0.6F, 0.7F)
                            .eyeHeight(0.35F)
                            .notInPeaceful()
                            .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    fromNamespaceAndPath(ModInfo.ID, "zombie_cat"))));

    // horses
    public static final DeferredHolder<EntityType<?>, EntityType<SkeletonHorse>> SKELETON_HORSE =
            ENTITY_TYPES_REGISTER.register("skeleton_horse",
                    () -> EntityType.Builder.of(SkeletonHorse::new, MobCategory.MONSTER)
                            .passengerAttachments(1.31875F)
                            .sized(1.3964844F, 1.6F)
                            .eyeHeight(1.52F)
                            .notInPeaceful()
                            .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    fromNamespaceAndPath(ModInfo.ID, "skeleton_horse"))));

    public static final DeferredHolder<EntityType<?>, EntityType<ZombieHorse>> ZOMBIE_HORSE =
            ENTITY_TYPES_REGISTER.register("zombie_horse",
                    () -> EntityType.Builder.of(ZombieHorse::new, MobCategory.MONSTER)
                            .passengerAttachments(1.31875F)
                            .sized(1.3964844F, 1.6F)
                            .eyeHeight(1.52F)
                            .notInPeaceful()
                            .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    fromNamespaceAndPath(ModInfo.ID, "zombie_horse"))));

    // underwater mobs
    public static final DeferredHolder<EntityType<?>, EntityType<DrownedSailor>> DROWNED_SAILOR =
            ENTITY_TYPES_REGISTER.register("drowned_sailor",
                    () -> EntityType.Builder.of(DrownedSailor::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.95F)
                            .eyeHeight(1.74F)
                            .notInPeaceful()
                            .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    fromNamespaceAndPath(ModInfo.ID, "drowned_sailor"))));

    public static final DeferredHolder<EntityType<?>, EntityType<PhantomDiver>> PHANTOM_DIVER =
            ENTITY_TYPES_REGISTER.register("phantom_diver",
                    () -> EntityType.Builder.of(PhantomDiver::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.95F)
                            .eyeHeight(1.74F)
                            .notInPeaceful()
                            .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    fromNamespaceAndPath(ModInfo.ID, "phantom_diver"))));

    public static final DeferredHolder<EntityType<?>, EntityType<SwampThing>> SWAMP_THING =
            ENTITY_TYPES_REGISTER.register("swamp_thing",
                    () -> EntityType.Builder.of(SwampThing::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.95F)
                            .eyeHeight(1.74F)
                            .notInPeaceful()
                            .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    fromNamespaceAndPath(ModInfo.ID, "swamp_thing"))));

    // desert MUMMY
    public static final DeferredHolder<EntityType<?>, EntityType<Mummy>> MUMMY =
            ENTITY_TYPES_REGISTER.register("mummy",
                    () -> EntityType.Builder.of(Mummy::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.95F)
                            .eyeHeight(1.74F)
                            .notInPeaceful()
                            .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    fromNamespaceAndPath(ModInfo.ID, "mummy"))));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES_REGISTER.register(eventBus);
    }

}
