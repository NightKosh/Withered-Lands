package nightkosh.withered_lands.entity.ai.goal;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import java.util.EnumSet;

public class KeepUnderwaterSwimmingGoal extends Goal {

    private final PathfinderMob mob;
    private final Level level;
    private double wantedX;
    private double wantedY;
    private double wantedZ;
    private final double speedModifier;

    public KeepUnderwaterSwimmingGoal(PathfinderMob mob, double speedModifier) {
        this.mob = mob;
        this.level = mob.level();
        this.speedModifier = speedModifier;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        var pos = this.mob.blockPosition();
        var below = pos.below();
        if (!this.level.getBlockState(pos.above()).is(Blocks.WATER) &&
                level.getBlockState(below).is(Blocks.WATER)) {
            this.wantedX = below.getX();
            this.wantedY = below.getY();
            this.wantedZ = below.getZ();
            return true;
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        return !this.mob.getNavigation().isDone();
    }

    @Override
    public void start() {
        this.mob.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.speedModifier);
    }

}
