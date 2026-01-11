package nightkosh.withered_lands.entity.ai.look_control;

import net.minecraft.world.entity.ai.control.LookControl;
import nightkosh.withered_lands.entity.swamp.GiantFrog;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class FrogLC extends LookControl {

    public FrogLC(GiantFrog frog) {
        super(frog);
    }

    @Override
    protected boolean resetXRotOnTick() {
        return ((GiantFrog) mob).getTongueTarget().isEmpty();
    }

}
