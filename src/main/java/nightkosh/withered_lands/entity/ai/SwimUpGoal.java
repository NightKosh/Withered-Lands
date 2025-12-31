package nightkosh.withered_lands.entity.ai;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;
import nightkosh.withered_lands.entity.water.AWaterWalkingMob;

public class SwimUpGoal extends Goal {

    private final AWaterWalkingMob mob;
    private final double speedModifier;
    private final int seaLevel;
    private boolean stuck;

    public SwimUpGoal(AWaterWalkingMob mob, double speedModifier, int seaLevel) {
        this.mob = mob;
        this.speedModifier = speedModifier;
        this.seaLevel = seaLevel;
    }

    @Override
    public boolean canUse() {
        return !this.mob.level().isBrightOutside() &&
                this.mob.isInWater() &&
                this.mob.getY() < this.seaLevel - 2;
    }

    @Override
    public boolean canContinueToUse() {
        return this.canUse() && !this.stuck;
    }

    @Override
    public void tick() {
        if (this.mob.getY() < this.seaLevel - 1 && (this.mob.getNavigation().isDone() || this.mob.closeToNextPos())) {
            var vec3 = DefaultRandomPos.getPosTowards(
                    this.mob, 4, 8,
                    new Vec3(this.mob.getX(), this.seaLevel - 1, this.mob.getZ()),
                    Math.PI / 2);
            if (vec3 == null) {
                this.stuck = true;
                return;
            }

            this.mob.getNavigation().moveTo(vec3.x, vec3.y, vec3.z, this.speedModifier);
        }
    }

    @Override
    public void start() {
        this.mob.setSearchingForLand(true);
        this.stuck = false;
    }

    @Override
    public void stop() {
        this.mob.setSearchingForLand(false);
    }

}
