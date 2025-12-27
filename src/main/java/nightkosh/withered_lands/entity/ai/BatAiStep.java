package nightkosh.withered_lands.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import nightkosh.withered_lands.entity.bat.HostileBat;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BatAiStep {

    private static final TargetingConditions BAT_RESTING_TARGETING = TargetingConditions
            .forNonCombat()
            .range(4.0);

    public static void step(ServerLevel level, HostileBat bat) {
        var pos = bat.blockPosition();
        var upPos = pos.above();
        if (bat.isResting()) {
            boolean flag = bat.isSilent();
            if (level.getBlockState(upPos).isRedstoneConductor(level, pos)) {
                if (level.random.nextInt(200) == 0) {
                    bat.yHeadRot = level.random.nextInt(360);
                }

                if (bat.getTarget() != null || level.getNearestPlayer(BAT_RESTING_TARGETING, bat) != null) {
                    bat.setResting(false);
                    if (!flag) {
                        level.levelEvent(null, 1025, pos, 0);
                    }
                }
            } else {
                bat.setResting(false);
                if (!flag) {
                    level.levelEvent(null, 1025, pos, 0);
                }
            }
        } else {
            if (bat.getTargetPosition() != null &&
                    (!level.isEmptyBlock(bat.getTargetPosition()) || bat.getTargetPosition().getY() < level.getMinY())) {
                bat.setTargetPosition(null);
            }

            if (bat.getTarget() != null) {
                bat.setTargetPosition(bat.getTarget().blockPosition().above());
            } else if (bat.getTargetPosition() == null ||
                    level.random.nextInt(30) == 0 ||
                    bat.getTargetPosition().closerToCenterThan(bat.position(), 2)) {
                bat.setTargetPosition(BlockPos.containing(
                        bat.getX() + level.random.nextInt(7) - level.random.nextInt(7),
                        bat.getY() + level.random.nextInt(6) - 2,
                        bat.getZ() + level.random.nextInt(7) - level.random.nextInt(7)));
            }

            double d0 = bat.getTargetPosition().getX() + 0.5 - bat.getX();
            double d1 = bat.getTargetPosition().getY() + 0.1 - bat.getY();
            double d2 = bat.getTargetPosition().getZ() + 0.5 - bat.getZ();

            var delta = bat.getDeltaMovement();
            var newDelta = delta.add(
                    (Math.signum(d2) * 0.5 - delta.x) * 0.1,
                    (Math.signum(d0) * 0.7 - delta.y) * 0.1,
                    (Math.signum(d1) * 0.5 - delta.z) * 0.1);
            bat.setDeltaMovement(newDelta);

            float f = (float) (Mth.atan2(newDelta.z, newDelta.x) * 180 / Math.PI) - 90;
            float f1 = Mth.wrapDegrees(f - bat.getYRot());
            bat.zza = 0.5F;
            bat.setYRot(bat.getYRot() + f1);

            if (level.random.nextInt(100) == 0 && level.getBlockState(upPos).isRedstoneConductor(level, upPos)) {
                bat.setResting(true);
            }
        }
    }

}
