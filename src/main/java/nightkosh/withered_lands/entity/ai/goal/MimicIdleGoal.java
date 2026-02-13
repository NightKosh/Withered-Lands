package nightkosh.withered_lands.entity.ai.goal;

import net.minecraft.world.entity.ai.goal.Goal;
import nightkosh.withered_lands.entity.Mimic;
import nightkosh.withered_lands.entity.ai.move_control.JumpingMoveControl;
import nightkosh.withered_lands.helper.TimeHelper;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class MimicIdleGoal extends Goal {

    private final Mimic mimic;
    private int ticks = 0;

    public MimicIdleGoal(Mimic mimic) {
        this.mimic = mimic;
    }

    @Override
    public boolean canUse() {
        return !this.mimic.isHiding() && this.mimic.getTarget() == null;
    }

    @Override
    public boolean canContinueToUse() {
        return canUse() && this.ticks <= TimeHelper.SECONDS_5;
    }

    @Override
    public void tick() {
        this.ticks++;
    }

    @Override
    public void start() {
        super.start();
        this.ticks = 0;

        this.mimic.setIdle(true);
        if (this.mimic.getMoveControl() instanceof JumpingMoveControl moveControl) {
            moveControl.setWantedIdle();
        }
    }

    @Override
    public void stop() {
        super.stop();

        this.mimic.setIdle(false);
    }

}
