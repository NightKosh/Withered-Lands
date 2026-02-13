package nightkosh.withered_lands.entity.ai.move_control;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import nightkosh.withered_lands.entity.AMonster;
import nightkosh.withered_lands.entity.IJumpingCube;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class JumpingMoveControl extends MoveControl {

    private float yRot;
    private int jumpDelay;
    private final AMonster mob;
    private boolean isAggressive;

    public JumpingMoveControl(AMonster mob) {
        super(mob);
        this.mob = mob;
        this.yRot = 180 * mob.getYRot() / (float) Math.PI;
    }

    public void setDirection(float yRot, boolean aggressive) {
        this.yRot = yRot;
        this.isAggressive = aggressive;
    }

    public void setWantedMovement() {
        this.operation = MoveControl.Operation.MOVE_TO;
    }

    public void setWantedIdle() {
        this.operation = Operation.WAIT;
    }

    @Override
    public void tick() {
        this.mob.setYRot(this.rotlerp(this.mob.getYRot(), this.yRot, 90));
        this.mob.yHeadRot = this.mob.getYRot();
        this.mob.yBodyRot = this.mob.getYRot();
        if (this.operation != MoveControl.Operation.MOVE_TO) {
            this.mob.setZza(0);
        } else {
            this.operation = MoveControl.Operation.WAIT;
            this.mob.setZza(1);
            this.mob.setSpeed((float) this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
            if (this.mob.onGround()) {
                if (this.jumpDelay-- <= 0 && this.mob instanceof IJumpingCube jumpingMob) {
                    this.jumpDelay = jumpingMob.getJumpDelay();
                    if (this.isAggressive) {
                        this.jumpDelay /= 2;
                    }

                    this.mob.getJumpControl().jump();

                    double boost = 0.15;
                    if (this.isAggressive) {
                        boost *= 1.8;
                    }
                    var look = this.mob.getLookAngle();
                    var dm = this.mob.getDeltaMovement();
                    this.mob.setDeltaMovement(
                            dm.x + look.x * boost,
                            dm.y,
                            dm.z + look.z * boost
                    );

                    this.mob.playSound(
                            jumpingMob.getJumpSound(), jumpingMob.getSoundVolume(),
                            ((this.mob.getRandom().nextFloat() - this.mob.getRandom().nextFloat()) * 0.2F + 1) * 0.8F);
                } else {
                    this.mob.xxa = 0;
                    this.mob.zza = 0;
                    this.mob.setSpeed(0);
                }
            }
        }
    }

}
