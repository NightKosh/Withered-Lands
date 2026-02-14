package nightkosh.withered_lands.entity.ai.move_control;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.phys.Vec3;
import nightkosh.withered_lands.entity.ghost.AGhost;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GhostMoveControl extends MoveControl {

    private final AGhost ghost;

    public GhostMoveControl(AGhost ghost) {
        super(ghost);
        this.ghost = ghost;
    }

    @Override
    public void tick() {
        if (this.operation == MoveControl.Operation.MOVE_TO) {
            var vec3 = new Vec3(this.wantedX - ghost.getX(), this.wantedY - ghost.getY(), this.wantedZ - ghost.getZ());
            double d0 = vec3.length();
            if (d0 < ghost.getBoundingBox().getSize()) {
                this.operation = MoveControl.Operation.WAIT;
                ghost.setDeltaMovement(ghost.getDeltaMovement().scale(0.5));
            } else {
                ghost.setDeltaMovement(ghost.getDeltaMovement().add(vec3.scale(this.speedModifier * 0.05 / d0)));
                if (ghost.getTarget() == null) {
                    var vec31 = ghost.getDeltaMovement();
                    ghost.setYRot(-((float) Mth.atan2(vec31.x, vec31.z)) * (180 / (float) Math.PI));
                } else {
                    double d2 = ghost.getTarget().getX() - ghost.getX();
                    double d1 = ghost.getTarget().getZ() - ghost.getZ();
                    ghost.setYRot(-((float) Mth.atan2(d2, d1)) * (180 / (float) Math.PI));
                }
                ghost.yBodyRot = ghost.getYRot();
            }
        }
    }

}
