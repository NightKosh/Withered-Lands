package nightkosh.withered_lands.event;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.skeleton.*;
import net.minecraft.world.entity.monster.zombie.Drowned;
import net.minecraft.world.entity.monster.zombie.Husk;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.monster.zombie.ZombifiedPiglin;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import nightkosh.withered_lands.core.ModInfo;
import nightkosh.withered_lands.core.WLAdvancements;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.core.WLEntities;
import nightkosh.withered_lands.entity.Mimic;
import nightkosh.withered_lands.entity.WLSkeleton;
import nightkosh.withered_lands.entity.crawler.ASkullCrawler;

import static net.minecraft.resources.Identifier.withDefaultNamespace;
import static nightkosh.withered_lands.WitheredLandsMod.LOGGER;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@EventBusSubscriber(modid = ModInfo.ID)
public class WLEventsSpawn {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        var entity = event.getEntity();

        if (WLConfigs.SKELETON_WITH_SWORD_SPAWN.get() && entity instanceof Skeleton) {
            if (!(entity instanceof WLSkeleton) && entity.getRandom().nextInt(5) == 0) {//20% chance
                var level = event.getLevel();
                if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
                    if (WLConfigs.DEBUG_MODE.get()) {
                        LOGGER.info("EntityJoinLevelEvent triggered with Skeleton entity, going to replace it with custom one");
                    }
                    var newSkeleton = WLEntities.SKELETON.get().create(level, EntitySpawnReason.TRIGGERED);
                    newSkeleton.snapTo(entity.getX(), entity.getY(), entity.getZ(),
                            entity.getYRot(), entity.getXRot());
                    newSkeleton.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(entity.blockPosition()),
                            EntitySpawnReason.TRIGGERED, null);

                    entity.discard();

                    level.addFreshEntity(newSkeleton);

                    event.setCanceled(true);
                }
            }
        } else if (WLConfigs.CREEPER_SPAWN.get() && entity instanceof Creeper && entity.getClass().equals(Creeper.class)) {
            var level = event.getLevel();
            if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
                if (WLConfigs.DEBUG_MODE.get()) {
                    LOGGER.info("EntityJoinLevelEvent triggered with Creeper entity, going to replace it with custom one");
                }
                Creeper newCreeper = null;
                var pos = entity.blockPosition();
                boolean canSeeSky = level.canSeeSky(pos);
                if (pos.getY() <= 0 && !canSeeSky) {
                    if (WLConfigs.DEBUG_MODE.get()) {
                        LOGGER.info("Going to replace creeper with deepslate one");
                    }
                    newCreeper = WLEntities.DEEPSLATE_CREEPER.get().create(level, EntitySpawnReason.TRIGGERED);
                } else if (pos.getY() <= 55 && !canSeeSky) {
                    if (WLConfigs.DEBUG_MODE.get()) {
                        LOGGER.info("Going to replace creeper with cave one");
                    }
                    newCreeper = WLEntities.CAVE_CREEPER.get().create(level, EntitySpawnReason.TRIGGERED);
                } else {
                    var biome = level.getBiome(pos);
                    if (biome.is(Tags.Biomes.IS_SNOWY) || biome.is(Tags.Biomes.IS_ICY)) {
                        if (WLConfigs.DEBUG_MODE.get()) {
                            LOGGER.info("Going to replace creeper with snowy one");
                        }
                        newCreeper = WLEntities.SNOWY_CREEPER.get().create(level, EntitySpawnReason.TRIGGERED);
                    } else if (biome.is(Tags.Biomes.IS_DESERT) || biome.is(Tags.Biomes.IS_BADLANDS)) {
                        if (WLConfigs.DEBUG_MODE.get()) {
                            LOGGER.info("Going to replace creeper with desert one");
                        }
                        newCreeper = WLEntities.DESERT_CREEPER.get().create(level, EntitySpawnReason.TRIGGERED);
                    }
                }
                if (newCreeper != null) {
                    newCreeper.snapTo(entity.getX(), entity.getY(), entity.getZ(),
                            entity.getYRot(), entity.getXRot());
                    newCreeper.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(pos),
                            EntitySpawnReason.TRIGGERED, null);

                    entity.discard();

                    level.addFreshEntity(newCreeper);
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLivingDeath(LivingDeathEvent event) {
        if (WLConfigs.DEBUG_MODE.get()) {
            LOGGER.info("LivingDeathEvent event triggered");
        }
        var entity = event.getEntity();
        var level = entity.level();
        if (!level.isClientSide()) {
            if (WLConfigs.SKULL_CRAWLERS_AT_MOBS_DEATH_SPAWN.get() && entity.getRandom().nextInt(10) == 0) {
                ASkullCrawler crawler = null;
                if (entity instanceof AbstractSkeleton skeleton) {
                    if (skeleton instanceof WitherSkeleton) {
                        crawler = WLEntities.SKULL_CRAWLER_WITHER.get()
                                .create(level, EntitySpawnReason.TRIGGERED);
                    } else if (skeleton instanceof Stray) {
                        crawler = WLEntities.SKULL_CRAWLER_STRAY.get()
                                .create(level, EntitySpawnReason.TRIGGERED);
                    } else if (skeleton instanceof Bogged) {
                        crawler = WLEntities.SKULL_CRAWLER_BOGGED.get()
                                .create(level, EntitySpawnReason.TRIGGERED);
                    } else if (skeleton instanceof Parched) {
                        crawler = WLEntities.SKULL_CRAWLER_PARCHED.get()
                                .create(level, EntitySpawnReason.TRIGGERED);
                    } else {
                        crawler = WLEntities.SKULL_CRAWLER_SKELETON.get()
                                .create(level, EntitySpawnReason.TRIGGERED);
                    }
                } else if (entity instanceof Zombie zombie) {
                    if (zombie instanceof Husk) {
                        crawler = WLEntities.SKULL_CRAWLER_HUSK.get()
                                .create(level, EntitySpawnReason.TRIGGERED);
                    } else if (entity instanceof Drowned) {
                        crawler = WLEntities.SKULL_CRAWLER_DROWNED.get()
                                .create(level, EntitySpawnReason.TRIGGERED);
                    } else if (entity instanceof ZombifiedPiglin) {
                        crawler = WLEntities.SKULL_CRAWLER_PIGLIN.get()
                                .create(level, EntitySpawnReason.TRIGGERED);
                    } else {
                        crawler = WLEntities.SKULL_CRAWLER_ZOMBIE.get()
                                .create(level, EntitySpawnReason.TRIGGERED);
                    }
                }
                if (crawler != null) {
                    if (WLConfigs.DEBUG_MODE.get()) {
                        LOGGER.info("Going to spawn skull crawler at mob death");
                    }
                    crawler.snapTo(entity.getX(), entity.getY() + 1.5, entity.getZ(),
                            entity.getYRot(), entity.getXRot());
                    level.addFreshEntity(crawler);
                }
            }
        }
    }

    private static final ResourceKey<LootTable> SIMPLE_DUNGEON_CHEST =
            ResourceKey.create(
                    Registries.LOOT_TABLE,
                    withDefaultNamespace("chests/simple_dungeon"));

    private static final ResourceKey<LootTable> DESERT_PYRAMID_CHEST =
            ResourceKey.create(
                    Registries.LOOT_TABLE,
                    withDefaultNamespace("chests/desert_pyramid"));

    private static final ResourceKey<LootTable> SHIPWRECK_TREASURE_CHEST =
            ResourceKey.create(
                    Registries.LOOT_TABLE,
                    withDefaultNamespace("chests/shipwreck_treasure"));

    private static final ResourceKey<LootTable> NETHER_BRIDGE_CHEST =
            ResourceKey.create(
                    Registries.LOOT_TABLE,
                    withDefaultNamespace("chests/nether_bridge"));

    private static final ResourceKey<LootTable> STRONGHOLD_CORRIDOR_CHEST =
            ResourceKey.create(
                    Registries.LOOT_TABLE,
                    withDefaultNamespace("chests/stronghold_corridor"));

    private static final ResourceKey<LootTable> STRONGHOLD_CROSSING_CHEST =
            ResourceKey.create(
                    Registries.LOOT_TABLE,
                    withDefaultNamespace("chests/stronghold_crossing"));

    private static final ResourceKey<LootTable> STRONGHOLD_LIBRARY_CHEST =
            ResourceKey.create(
                    Registries.LOOT_TABLE,
                    withDefaultNamespace("chests/stronghold_library"));

    private static final ResourceKey<LootTable> BASTION_BRIDGE_CHEST =
            ResourceKey.create(
                    Registries.LOOT_TABLE,
                    withDefaultNamespace("chests/bastion_bridge"));

    private static final ResourceKey<LootTable> BASTION_TREASURE_CHEST =
            ResourceKey.create(
                    Registries.LOOT_TABLE,
                    withDefaultNamespace("chests/bastion_treasure"));

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!event.getLevel().isClientSide() && WLConfigs.MIMIC_SPAWN.get()) {
            var level = (ServerLevel) event.getLevel();
            var pos = event.getPos();
            if (level.getBlockEntity(pos) instanceof ChestBlockEntity chest) {
                var lootTable = chest.getLootTable();
                if (lootTable != null) {
                    if (lootTable.equals(SIMPLE_DUNGEON_CHEST)) {
                        replaceChestByMimic(3, "simple_dungeon", event, level, pos, Mimic.Type.SIMPLE_DUNGEON);//33%
                    } else if (lootTable.equals(NETHER_BRIDGE_CHEST)) {
                        replaceChestByMimic(5, "nether_bridge", event, level, pos, Mimic.Type.NETHER_BRIDGE);//20%
                    } else if (lootTable.equals(DESERT_PYRAMID_CHEST)) {
                        replaceChestByMimic(5, "desert_pyramid", event, level, pos, Mimic.Type.DESERT_PYRAMID);//20%
                    } else if (lootTable.equals(SHIPWRECK_TREASURE_CHEST)) {
                        replaceChestByMimic(7, "shipwreck_treasure", event, level, pos, Mimic.Type.SHIPWRECK_TREASURE);//15%
                    } else if (lootTable.equals(STRONGHOLD_CORRIDOR_CHEST)) {
                        replaceChestByMimic(8, "stronghold_corridor", event, level, pos, Mimic.Type.STRONGHOLD_CORRIDOR);//12.5%
                    } else if (lootTable.equals(STRONGHOLD_CROSSING_CHEST)) {
                        replaceChestByMimic(8, "stronghold_crossing", event, level, pos, Mimic.Type.STRONGHOLD_CROSSING);//12.5%
                    } else if (lootTable.equals(STRONGHOLD_LIBRARY_CHEST)) {
                        replaceChestByMimic(5, "stronghold_library", event, level, pos, Mimic.Type.STRONGHOLD_LIBRARY);//20%
                    } else if (lootTable.equals(BASTION_BRIDGE_CHEST)) {
                        replaceChestByMimic(20, "bastion_bridge", event, level, pos, Mimic.Type.BASTION_BRIDGE);//5%
                    } else if (lootTable.equals(BASTION_TREASURE_CHEST)) {
                        replaceChestByMimic(10, "bastion_treasure", event, level, pos, Mimic.Type.BASTION_TREASURE);//10%
                    }
                }
            }
        }
    }

    private static void replaceChestByMimic(
            int chance, String chestNameLog, PlayerInteractEvent.RightClickBlock event,
            ServerLevel level, BlockPos pos, Mimic.Type type) {
        if (level.getRandom().nextInt(chance) == 0) {
            if (WLConfigs.DEBUG_MODE.get()) {
                LOGGER.info("RightClickBlock event triggered for {} chest.", chestNameLog);
                LOGGER.info("Going to replace chest by Mimic!");
            }
            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.FAIL);

            Mimic.replaceChestByMimic(level, pos, event.getEntity(), type);

            WLAdvancements.giveAdvancement(event.getEntity(), level, WLAdvancements.ITS_A_TRAP);
        }
    }

}
