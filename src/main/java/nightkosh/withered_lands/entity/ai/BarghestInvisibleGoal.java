package nightkosh.withered_lands.entity.ai;

import net.minecraft.world.entity.ai.goal.Goal;
import nightkosh.withered_lands.entity.wolf.Barghest;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BarghestInvisibleGoal extends Goal {

    protected Barghest mob;

    public BarghestInvisibleGoal(Barghest mob) {
        this.mob = mob;
    }

    @Override
    public boolean canUse() {
        return this.mob.getTarget() == null && this.mob.getLastHurtByMob() == null;
    }

    @Override
    public boolean canContinueToUse() {
        return this.canUse();
    }

    @Override
    public void start() {
        this.mob.setBarghestInvisible(true);
    }

    @Override
    public void stop() {
        this.mob.setBarghestInvisible(false);
    }

}
