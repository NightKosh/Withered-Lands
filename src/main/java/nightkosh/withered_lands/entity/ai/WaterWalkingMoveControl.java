package nightkosh.withered_lands.entity.ai;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import nightkosh.withered_lands.entity.water.AWaterWalkingMob;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WaterWalkingMoveControl extends MoveControl {

    public WaterWalkingMoveControl(AWaterWalkingMob mob) {
        super(mob);
    }

    @Override
    public void tick() {
        var entity = (AWaterWalkingMob) this.mob;
        var target = entity.getTarget();
        if (entity.wantsToSwim() && entity.isInWater()) {
            if (target != null && target.getY() > entity.getY() || entity.searchingForLand) {
                entity.setDeltaMovement(entity.getDeltaMovement().add(0, 0.002, 0));
            }

            if (this.operation != MoveControl.Operation.MOVE_TO || entity.getNavigation().isDone()) {
                entity.setSpeed(0);
                return;
            }

            double x = this.wantedX - entity.getX();
            double y = this.wantedY - entity.getY();
            double z = this.wantedZ - entity.getZ();

            float f = (float) (Mth.atan2(z, x) * 180 / Math.PI) - 90;
            entity.setYRot(this.rotlerp(entity.getYRot(), f, 90));
            entity.yBodyRot = entity.getYRot();
            float f1 = (float) (this.speedModifier * entity.getAttributeValue(Attributes.MOVEMENT_SPEED));
            float f2 = Mth.lerp(0.125F, entity.getSpeed(), f1);
            entity.setSpeed(f2);
            entity.setDeltaMovement(entity.getDeltaMovement().add(f2 * x * 0.005, f2 * y * 0.1, f2 * z * 0.005));
        } else {
            if (!entity.onGround()) {
                entity.setDeltaMovement(entity.getDeltaMovement().add(0, -0.008, 0));
            }

            super.tick();
        }
    }

}
