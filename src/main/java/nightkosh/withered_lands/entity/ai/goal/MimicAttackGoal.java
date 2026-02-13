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
public class MimicAttackGoal extends Goal {

    private final Mimic mimic;
    private int growTiredTimer;

    public MimicAttackGoal(Mimic mimic) {
        this.mimic = mimic;
        this.setFlags(EnumSet.of(Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        var target = this.mimic.getTarget();
        if (target == null) {
            return false;
        } else {
            return this.mimic.canAttack(target) && this.mimic.getMoveControl() instanceof JumpingMoveControl;
        }
    }

    @Override
    public void start() {
        this.growTiredTimer = reducedTickDelay(300);
        super.start();
    }

    @Override
    public boolean canContinueToUse() {
        var target = this.mimic.getTarget();
        if (target == null) {
            return false;
        } else {
            return this.mimic.canAttack(target) && --this.growTiredTimer > 0;
        }
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        var target = this.mimic.getTarget();
        if (target != null) {
            this.mimic.lookAt(target, 10, 10);
        }

        if (this.mimic.getMoveControl() instanceof JumpingMoveControl moveControl) {
            moveControl.setDirection(this.mimic.getYRot(), this.mimic.isEffectiveAi());
        }
    }

}
