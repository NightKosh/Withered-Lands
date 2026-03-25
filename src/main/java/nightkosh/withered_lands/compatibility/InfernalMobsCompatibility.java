package nightkosh.withered_lands.compatibility;

import net.neoforged.fml.ModList;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.entity.KillerBunny;
import nightkosh.withered_lands.entity.Mimic;
import nightkosh.withered_lands.entity.bat.*;
import nightkosh.withered_lands.entity.cat.SkeletonCat;
import nightkosh.withered_lands.entity.cat.ZombieCat;
import nightkosh.withered_lands.entity.crawler.*;
import nightkosh.withered_lands.entity.ghost.HollowStalker;
import nightkosh.withered_lands.entity.ghost.LostSoul;
import nightkosh.withered_lands.entity.slime.*;
import nightkosh.withered_lands.entity.water.fish.Minnow;
import nightkosh.withered_lands.entity.water.fish.Pike;
import nightkosh.withered_lands.entity.water.fish.Piranha;
import nightkosh.withered_lands.entity.wolf.SkeletonDog;
import nightkosh.withered_lands.entity.wolf.ZombieDog;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import static nightkosh.withered_lands.WitheredLandsMod.LOGGER;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class InfernalMobsCompatibility {

    public static final String MOD_ID = "infernalmobs";

    public static void disableInfernalMobs() {
        if (WLConfigs.DISABLE_INFERNAL_MOBS.get()) {
            try {
                var clazz = Class.forName("atomicstryker.infernalmobs.common.InfernalMobsCore");
                var instanceMethod = clazz.getDeclaredMethod("instance");
                var result = instanceMethod.invoke(null);

                var field = clazz.getDeclaredField("classesAllowedMap");
                field.setAccessible(true);

                var classesAllowedMap = (HashMap<String, Boolean>) field.get(result);


                classesAllowedMap.put(SlimeVerdant.class.getSimpleName(), false);
                classesAllowedMap.put(SlimeSandy.class.getSimpleName(), false);
                classesAllowedMap.put(SlimeFrozen.class.getSimpleName(), false);
                classesAllowedMap.put(SlimeMud.class.getSimpleName(), false);
                classesAllowedMap.put(SlimeJungle.class.getSimpleName(), false);
                classesAllowedMap.put(SlimeCave.class.getSimpleName(), false);
                classesAllowedMap.put(SlimeAbyssal.class.getSimpleName(), false);
                classesAllowedMap.put(SlimeToxicSludge.class.getSimpleName(), false);
                classesAllowedMap.put(SlimeMolten.class.getSimpleName(), false);

                classesAllowedMap.put(BatCave.class.getSimpleName(), false);
                classesAllowedMap.put(BatVampire.class.getSimpleName(), false);
                classesAllowedMap.put(BatIce.class.getSimpleName(), false);
                classesAllowedMap.put(BatFlyingFox.class.getSimpleName(), false);
                classesAllowedMap.put(BatBlazing.class.getSimpleName(), false);
                classesAllowedMap.put(BatWithered.class.getSimpleName(), false);
                classesAllowedMap.put(BatVolatile.class.getSimpleName(), false);
                classesAllowedMap.put(BatChorus.class.getSimpleName(), false);

                classesAllowedMap.put(SkeletonSkullCrawler.class.getSimpleName(), false);
                classesAllowedMap.put(StraySkullCrawler.class.getSimpleName(), false);
                classesAllowedMap.put(BoggedSkullCrawler.class.getSimpleName(), false);
                classesAllowedMap.put(ParchedSkullCrawler.class.getSimpleName(), false);
                classesAllowedMap.put(WitherSkullCrawler.class.getSimpleName(), false);
                classesAllowedMap.put(ZombieSkullCrawler.class.getSimpleName(), false);
                classesAllowedMap.put(HuskSkullCrawler.class.getSimpleName(), false);
                classesAllowedMap.put(DrownedSkullCrawler.class.getSimpleName(), false);
                classesAllowedMap.put(PiglinSkullCrawler.class.getSimpleName(), false);

                classesAllowedMap.put(HollowStalker.class.getSimpleName(), false);
                classesAllowedMap.put(LostSoul.class.getSimpleName(), false);

                classesAllowedMap.put(Mimic.class.getSimpleName(), false);

                classesAllowedMap.put(SkeletonCat.class.getSimpleName(), false);
                classesAllowedMap.put(ZombieCat.class.getSimpleName(), false);
                classesAllowedMap.put(SkeletonDog.class.getSimpleName(), false);
                classesAllowedMap.put(ZombieDog.class.getSimpleName(), false);

                classesAllowedMap.put(KillerBunny.class.getSimpleName(), false);

                classesAllowedMap.put(Minnow.class.getSimpleName(), false);
                classesAllowedMap.put(Piranha.class.getSimpleName(), false);
                classesAllowedMap.put(Pike.class.getSimpleName(), false);

                if (WLConfigs.DEBUG_MODE.get()) {
                    LOGGER.info("InfernalMobs compatibility module successfully applied");
                }
            } catch (ClassNotFoundException e) {
                LOGGER.error("InfernalMobs compatibility error! Can't find InfernalMobsCore class", e);
            } catch (NoSuchMethodException e) {
                LOGGER.error("InfernalMobs compatibility error! Can't find instance method", e);
            } catch (InvocationTargetException e) {
                LOGGER.error("InfernalMobs compatibility error! Can't invoke instance method", e);
            } catch (IllegalAccessException e) {
                LOGGER.error("InfernalMobs compatibility error! Can't change classesAllowedMap field access modifier", e);
            } catch (NoSuchFieldException e) {
                LOGGER.error("InfernalMobs compatibility error! Can't find classesAllowedMap field", e);
            }
        }
    }

    public static boolean loaded() {
        return loaded(MOD_ID);
    }

    protected static boolean loaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

}
