package nightkosh.withered_lands.entity.ai.breeze;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import nightkosh.withered_lands.entity.breeze.ABreeze;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BreezeUtil {

    public static Vec3 randomPointBehindTarget(LivingEntity target, RandomSource random) {
        return target.position()
                .add(Vec3.directionFromRotation(0, target.yHeadRot + 180 + (float) random.nextGaussian() * 90 / 2)
                        .scale(Mth.lerp(random.nextFloat(), 4, 8)));
    }

    public static boolean hasLineOfSight(ABreeze breeze, Vec3 pos) {
        var vec3 = new Vec3(breeze.getX(), breeze.getY(), breeze.getZ());
        return !(pos.distanceTo(vec3) > getMaxLineOfSightTestRange(breeze)) &&
                breeze.level().clip(new ClipContext(vec3, pos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, breeze))
                        .getType() == HitResult.Type.MISS;
    }

    public static void createLightning(
            @Nonnull ServerLevel level, double x, double y, double z) {
        var lightning = EntityType.LIGHTNING_BOLT.create(level, EntitySpawnReason.EVENT);
        if (lightning != null) {
            lightning.snapTo(x, y, z);
            level.addFreshEntity(lightning);
        }
    }

    private static double getMaxLineOfSightTestRange(ABreeze breeze) {
        return Math.max(50, breeze.getAttributeValue(Attributes.FOLLOW_RANGE));
    }

}
