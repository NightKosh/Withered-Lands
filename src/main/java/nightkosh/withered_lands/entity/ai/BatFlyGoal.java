package nightkosh.withered_lands.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ai.goal.Goal;
import nightkosh.withered_lands.entity.bat.HostileBat;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BatFlyGoal extends Goal {

    private final HostileBat bat;
    private final RandomSource random;

    public BatFlyGoal(HostileBat bat) {
        this.bat = bat;
        random = bat.getRandom();
    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return true;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public void tick() {
        var level = bat.level();
        var pos = bat.blockPosition();
        var upPos = pos.above();
        if (bat.isResting()) {
            if (level.getBlockState(upPos).isRedstoneConductor(level, pos)) {
                if (this.random.nextInt(200) == 0) {
                    bat.yHeadRot = level.random.nextInt(360);
                }

                if (bat.getTarget() != null) {
                    bat.setResting(false);
                    level.levelEvent(null, 1025, pos, 0);
                }
            } else {
                bat.setResting(false);
                level.levelEvent(null, 1025, pos, 0);
            }
        } else {
            if (bat.getTargetPosition() != null &&
                    (!level.isEmptyBlock(bat.getTargetPosition()) || bat.getTargetPosition().getY() < level.getMinY())) {
                bat.setTargetPosition(null);
            }

            if (bat.getTarget() != null) {
                bat.setTargetPosition(bat.getTarget().blockPosition().above());
            } else if (bat.getTargetPosition() == null ||
                    this.random.nextInt(30) == 0 ||
                    bat.getTargetPosition().closerToCenterThan(bat.position(), 2)) {
                bat.setTargetPosition(BlockPos.containing(
                        bat.getX() + this.random.nextInt(7) - this.random.nextInt(7),
                        bat.getY() + this.random.nextInt(6) - 2,
                        bat.getZ() + this.random.nextInt(7) - this.random.nextInt(7)));
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

            if (this.random.nextInt(100) == 0 && level.getBlockState(upPos).isRedstoneConductor(level, upPos)) {
                bat.setResting(true);
            }
        }

        super.tick();
    }

}
