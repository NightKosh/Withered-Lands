package nightkosh.withered_lands.entity.ai.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class AttackIfToCloseGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

    private final int range;
    private boolean aggressive = false;

    public AttackIfToCloseGoal(Mob mob, Class<T> targetType, boolean mustSee, int range) {
        super(mob, targetType, 10, mustSee, false, null);
        this.range = range;
    }

    @Override
    public void start() {
        super.start();
        if (this.target != null) {
            this.aggressive = true;
        }
    }

    @Override
    protected double getFollowDistance() {
        return this.aggressive ?
                super.getFollowDistance() :
                range;
    }

}
