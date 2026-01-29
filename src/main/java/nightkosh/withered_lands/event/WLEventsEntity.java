package nightkosh.withered_lands.event;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.monster.skeleton.*;
import net.minecraft.world.entity.monster.zombie.Drowned;
import net.minecraft.world.entity.monster.zombie.Husk;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.monster.zombie.ZombifiedPiglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.player.CanPlayerSleepEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nightkosh.withered_lands.core.ModInfo;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.core.WLEntities;
import nightkosh.withered_lands.core.WLMobEffects;
import nightkosh.withered_lands.entity.WLSkeleton;
import nightkosh.withered_lands.entity.crawler.ASkullCrawler;
import nightkosh.withered_lands.helper.WLEnchantmentHelper;

import static nightkosh.withered_lands.WitheredLandsMod.LOGGER;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@EventBusSubscriber(modid = ModInfo.ID)
public class WLEventsEntity {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        var entity = event.getEntity();

        if (WLConfigs.SKELETON_WITH_SWORD_SPAWN.get() && entity instanceof Skeleton &&
                !(entity instanceof WLSkeleton) &&
                entity.getRandom().nextInt(5) == 0) {//20% chance
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

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onLivingHealEvent(LivingHealEvent event) {
        if (event.getEntity().hasEffect(WLMobEffects.BLEEDING)) {
            if (WLConfigs.DEBUG_MODE.get()) {
                LOGGER.info("LivingHealEvent event triggered. Going to cancel event due to bleeding effect");
            }
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onLivingTick(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof Player player && !player.level().isClientSide()) {
            if ((player.tickCount % 20) == 0) {
                if (WLConfigs.DEBUG_MODE.get()) {
                    LOGGER.info("EntityTickEvent.Post event triggered. Going to check player starvation curse.");
                }
                WLEnchantmentHelper.applyCurseEffect(player);
            }
        }
    }

    private static final int MIN_FOOD_TO_SLEEP = 18;

    @SubscribeEvent
    public static void onCanPlayerSleep(CanPlayerSleepEvent event) {
        var player = event.getEntity();
        var level = event.getLevel();
        if (!player.level().isClientSide() && event.getVanillaProblem() == null) {
            if (WLConfigs.DEBUG_MODE.get()) {
                LOGGER.info("CanPlayerSleepEvent event triggered for player {}.", player.getScoreboardName());
            }

            if (WLConfigs.TO_HUNGRY_TO_SLEEP.get() && player.getFoodData().getFoodLevel() < MIN_FOOD_TO_SLEEP) {
                denySleep(player, event, Component.translatable("message.withered_lands.to_hungry_to_sleep")
                        .withStyle(ChatFormatting.RED));
            } else if (WLConfigs.OPEN_SKY_SLEEP.get() && hasOpenSkyForBed(level, event.getPos())) {
                denySleep(player, event, Component.translatable("message.withered_lands.open_sky_sleep")
                        .withStyle(ChatFormatting.RED));
            }
        }
    }

    private static void denySleep(ServerPlayer player, CanPlayerSleepEvent event, Component msg) {
        event.setProblem(Player.BedSleepingProblem.OTHER_PROBLEM);
        player.displayClientMessage(msg, true);
    }

    private static boolean hasOpenSkyForBed(Level level, BlockPos bedPos) {
        var posAbove = bedPos.above();
        return level.canSeeSky(posAbove) &&
                level.canSeeSky(posAbove.north()) &&
                level.canSeeSky(posAbove.south()) &&
                level.canSeeSky(posAbove.west()) &&
                level.canSeeSky(posAbove.east());
    }

}
