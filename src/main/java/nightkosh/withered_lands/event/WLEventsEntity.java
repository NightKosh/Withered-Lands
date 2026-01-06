package nightkosh.withered_lands.event;

import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.monster.skeleton.*;
import net.minecraft.world.entity.monster.zombie.Drowned;
import net.minecraft.world.entity.monster.zombie.Husk;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.monster.zombie.ZombifiedPiglin;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nightkosh.withered_lands.core.ModInfo;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.core.WLEntities;
import nightkosh.withered_lands.core.WLMobEffects;
import nightkosh.withered_lands.enchantment.WLEnchantmentHelper;
import nightkosh.withered_lands.entity.crawler.ASkullCrawler;

import static nightkosh.withered_lands.WitheredLandsMod.LOGGER;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@EventBusSubscriber(modid = ModInfo.ID)
public class WLEventsEntity {

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

}
