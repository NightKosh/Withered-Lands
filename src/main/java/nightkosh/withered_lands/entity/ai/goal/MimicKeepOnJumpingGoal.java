package nightkosh.withered_lands.entity.ai.goal;

import net.minecraft.world.entity.ai.goal.Goal;
import nightkosh.withered_lands.entity.Mimic;
import nightkosh.withered_lands.entity.ai.move_control.JumpingMoveControl;

import java.util.EnumSet;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class MimicKeepOnJumpingGoal extends Goal {

    private final Mimic mimic;

    public MimicKeepOnJumpingGoal(Mimic mimic) {
        this.mimic = mimic;
        this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return !this.mimic.isHiding() && !this.mimic.isIdle() && !this.mimic.isPassenger();
    }

    @Override
    public void tick() {
        if (this.mimic.getMoveControl() instanceof JumpingMoveControl moveControl) {
            moveControl.setWantedMovement();
        }
    }

}
