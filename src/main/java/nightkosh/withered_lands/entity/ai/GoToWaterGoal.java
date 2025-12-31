package nightkosh.withered_lands.entity.ai;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.EnumSet;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GoToWaterGoal extends Goal {

    private final PathfinderMob mob;
    private final double speedModifier;
    private final Level level;
    private double wantedX;
    private double wantedY;
    private double wantedZ;

    public GoToWaterGoal(PathfinderMob mob, double speedModifier) {
        this.mob = mob;
        this.speedModifier = speedModifier;
        this.level = mob.level();
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (!this.level.isBrightOutside()) {
            return false;
        } else if (this.mob.isInWater()) {
            return false;
        } else {
            var vec3 = this.getWaterPos();
            if (vec3 == null) {
                return false;
            } else {
                this.wantedX = vec3.x;
                this.wantedY = vec3.y;
                this.wantedZ = vec3.z;
                return true;
            }
        }
    }

    @Override
    public boolean canContinueToUse() {
        return !this.mob.getNavigation().isDone();
    }

    @Override
    public void start() {
        this.mob.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.speedModifier);
    }

    private @Nullable Vec3 getWaterPos() {
        var random = this.mob.getRandom();
        var blockPos = this.mob.blockPosition();

        for (int i = 0; i < 10; i++) {
            var pos = blockPos.offset(
                    random.nextInt(20) - 10,
                    2 - random.nextInt(8),
                    random.nextInt(20) - 10);
            if (this.level.getBlockState(pos).is(Blocks.WATER)) {
                return Vec3.atBottomCenterOf(pos);
            }
        }

        return null;
    }

}
