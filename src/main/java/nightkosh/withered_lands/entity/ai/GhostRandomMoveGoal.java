package nightkosh.withered_lands.entity.ai;

import net.minecraft.world.entity.ai.goal.Goal;
import nightkosh.withered_lands.entity.ghost.AGhost;

import java.util.EnumSet;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GhostRandomMoveGoal extends Goal {

    private final AGhost ghost;

    public GhostRandomMoveGoal(AGhost ghost) {
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        this.ghost = ghost;
    }

    @Override
    public boolean canUse() {
        return !ghost.getMoveControl().hasWanted() && ghost.getRandom().nextInt(reducedTickDelay(7)) == 0;
    }

    @Override
    public boolean canContinueToUse() {
        return false;
    }

    @Override
    public void tick() {
        for (int i = 0; i < 3; i++) {
            var pos = ghost.blockPosition().offset(
                    ghost.getRandom().nextInt(15) - 7,
                    ghost.getRandom().nextInt(11) - 5,
                    ghost.getRandom().nextInt(15) - 7);
            if (ghost.level().isEmptyBlock(pos)) {
                ghost.getMoveControl().setWantedPosition(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 0.25);
                if (ghost.getTarget() == null) {
                    ghost.getLookControl().setLookAt(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 180, 20);
                }
                break;
            }
        }
    }

}
