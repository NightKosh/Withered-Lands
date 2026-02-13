package nightkosh.withered_lands.entity.ai.goal;

import net.minecraft.world.entity.ai.goal.Goal;
import nightkosh.withered_lands.entity.Mimic;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class HideAsChestGoal extends Goal {

    private final Mimic mimic;

    public HideAsChestGoal(Mimic mimic) {
        this.mimic = mimic;
    }

    @Override
    public boolean canUse() {
        return this.mimic.getTarget() == null && this.mimic.getLastHurtByMob() == null && this.mimic.canHide();
    }

    @Override
    public void start() {
        this.mimic.setHiding(true);
        var pos = this.mimic.blockPosition();
        this.mimic.setXRot(0);
        this.mimic.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
    }

    @Override
    public void stop() {
        this.mimic.setHiding(false);
        // should not hide anymore
        this.mimic.setCanHide(false);
    }

}
