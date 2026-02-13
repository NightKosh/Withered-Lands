package nightkosh.withered_lands.entity.ai.goal;

import net.minecraft.world.effect.MobEffects;
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
public class MimicRandomDirectionGoal extends Goal {

    private final Mimic mimic;
    private float chosenDegrees;
    private int nextRandomizeTime;

    public MimicRandomDirectionGoal(Mimic mimic) {
        this.mimic = mimic;
        this.setFlags(EnumSet.of(Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        return !this.mimic.isHiding() &&
                this.mimic.getTarget() == null &&
                (this.mimic.onGround() || this.mimic.isInWater() || this.mimic.isInLava() || this.mimic.hasEffect(MobEffects.LEVITATION)) &&
                this.mimic.getMoveControl() instanceof JumpingMoveControl;
    }

    @Override
    public void tick() {
        if (canUse()) {
            if (--this.nextRandomizeTime <= 0) {
                this.nextRandomizeTime = this.adjustedTickDelay(40 + this.mimic.getRandom().nextInt(60));
                this.chosenDegrees = this.mimic.getRandom().nextInt(360);
            }

            if (this.mimic.getMoveControl() instanceof JumpingMoveControl moveControl) {
                moveControl.setDirection(this.chosenDegrees, false);
            }
        }
    }

}
