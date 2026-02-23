package nightkosh.withered_lands.entity.ai.goal;

import net.minecraft.world.entity.ai.goal.Goal;
import nightkosh.withered_lands.entity.wolf.Barghest;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BarghestInvisibleGoal extends Goal {

    protected Barghest barghest;

    public BarghestInvisibleGoal(Barghest mob) {
        this.barghest = mob;
    }

    @Override
    public boolean canUse() {
        return this.barghest.getLastHurtByMob() == null;
    }

    @Override
    public boolean canContinueToUse() {
        return this.canUse();
    }

    @Override
    public void start() {
        this.barghest.setBarghestInvisible(true);
    }

    @Override
    public void stop() {
        this.barghest.setBarghestInvisible(false);
    }

}
