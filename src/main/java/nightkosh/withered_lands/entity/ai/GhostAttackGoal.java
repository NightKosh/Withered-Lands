package nightkosh.withered_lands.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import nightkosh.withered_lands.core.WLSounds;
import nightkosh.withered_lands.entity.ghost.AGhost;

import java.util.EnumSet;

public class GhostAttackGoal extends Goal {

    protected final AGhost ghost;

    public GhostAttackGoal(AGhost ghost) {
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        this.ghost = ghost;
    }

    @Override
    public boolean canUse() {
        var target = ghost.getTarget();
        return target != null &&
                target.isAlive() &&
                !ghost.getMoveControl().hasWanted() &&
                ghost.getRandom().nextInt(reducedTickDelay(7)) == 0 &&
                ghost.distanceToSqr(target) > 4;
    }

    @Override
    public boolean canContinueToUse() {
        return ghost.getMoveControl().hasWanted() && ghost.isCharging() &&
                ghost.getTarget() != null && ghost.getTarget().isAlive();
    }

    @Override
    public void start() {
        LivingEntity livingentity = ghost.getTarget();
        if (livingentity != null) {
            Vec3 vec3 = livingentity.getEyePosition();
            ghost.getMoveControl().setWantedPosition(vec3.x, vec3.y, vec3.z, 1);
        }

        ghost.setIsCharging(true);
        ghost.playSound(WLSounds.HOLLOW_STALKER_ATTACK.get(), 1, 1);
    }

    @Override
    public void stop() {
        ghost.setIsCharging(false);
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        var target = ghost.getTarget();
        if (target != null) {
            if (ghost.getBoundingBox().intersects(target.getBoundingBox())) {
                ghost.doHurtTarget(getServerLevel(ghost.level()), target);
                ghost.setIsCharging(false);
            } else {
                if (ghost.distanceToSqr(target) < 9) {
                    var vec3 = target.getEyePosition();
                    ghost.getMoveControl().setWantedPosition(vec3.x, vec3.y, vec3.z, 1);
                }
            }
        }
    }

}
