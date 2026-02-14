package nightkosh.withered_lands.event;

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
import net.minecraft.world.level.block.Blocks;
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
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.core.WLEntities;
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
                        crawler = WLEntities.WITHER_SKULL_CRAWLER.get()
                                .create(level, EntitySpawnReason.TRIGGERED);
                    } else if (skeleton instanceof Stray) {
                        crawler = WLEntities.STRAY_SKULL_CRAWLER.get()
                                .create(level, EntitySpawnReason.TRIGGERED);
                    } else if (skeleton instanceof Bogged) {
                        crawler = WLEntities.BOGGED_SKULL_CRAWLER.get()
                                .create(level, EntitySpawnReason.TRIGGERED);
                    } else if (skeleton instanceof Parched) {
                        crawler = WLEntities.PARCHED_SKULL_CRAWLER.get()
                                .create(level, EntitySpawnReason.TRIGGERED);
                    } else {
                        crawler = WLEntities.SKELETON_SKULL_CRAWLER.get()
                                .create(level, EntitySpawnReason.TRIGGERED);
                    }
                } else if (entity instanceof Zombie zombie) {
                    if (zombie instanceof Husk) {
                        crawler = WLEntities.HUSK_SKULL_CRAWLER.get()
                                .create(level, EntitySpawnReason.TRIGGERED);
                    } else if (entity instanceof Drowned) {
                        crawler = WLEntities.DROWNED_SKULL_CRAWLER.get()
                                .create(level, EntitySpawnReason.TRIGGERED);
                    } else if (entity instanceof ZombifiedPiglin) {
                        crawler = WLEntities.PIGLIN_SKULL_CRAWLER.get()
                                .create(level, EntitySpawnReason.TRIGGERED);
                    } else {
                        crawler = WLEntities.ZOMBIE_SKULL_CRAWLER.get()
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

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!event.getLevel().isClientSide() && WLConfigs.MIMIC_SPAWN.get()) {
            var level = (ServerLevel) event.getLevel();
            var pos = event.getPos();
            if (level.getBlockEntity(pos) instanceof ChestBlockEntity chest) {
                var lootTable = chest.getLootTable();
                if (lootTable != null) {
                    if (lootTable.equals(SIMPLE_DUNGEON_CHEST) && level.getRandom().nextInt(3) == 0) {
                        if (WLConfigs.DEBUG_MODE.get()) {
                            LOGGER.info("RightClickBlock event triggered for simple_dungeon chest.");
                            LOGGER.info("Going to replace chest by Mimic!");
                        }
                        event.setCanceled(true);
                        event.setCancellationResult(InteractionResult.FAIL);

                        level.removeBlockEntity(pos);
                        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);

                        var mimic = WLEntities.MIMIC.get().create(level, EntitySpawnReason.TRIGGERED);
                        if (mimic != null) {
                            mimic.setPosRaw(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                            mimic.lookAt(event.getEntity(), 360, 360);

                            level.addFreshEntity(mimic);
                        }
                    }
                }
            }
        }
    }

}
