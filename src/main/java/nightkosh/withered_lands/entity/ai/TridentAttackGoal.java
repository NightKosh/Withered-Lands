package nightkosh.withered_lands.entity.ai;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.item.Items;
import nightkosh.withered_lands.entity.water.AWaterWalkingMob;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TridentAttackGoal  extends RangedAttackGoal {

    private final AWaterWalkingMob waterMob;

    public TridentAttackGoal(RangedAttackMob mob, double speedModifier, int attackInterval, float attackRadius) {
        super(mob, speedModifier, attackInterval, attackRadius);
        this.waterMob = (AWaterWalkingMob) mob;
    }

    @Override
    public boolean canUse() {
        return super.canUse() && this.waterMob.getMainHandItem().is(Items.TRIDENT);
    }

    @Override
    public void start() {
        super.start();
        this.waterMob.setAggressive(true);
        this.waterMob.startUsingItem(InteractionHand.MAIN_HAND);
    }

    @Override
    public void stop() {
        super.stop();
        this.waterMob.stopUsingItem();
        this.waterMob.setAggressive(false);
    }

}
