package nightkosh.withered_lands.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class AttackIfToCloseGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

    private final int range;

    public AttackIfToCloseGoal(Mob mob, Class<T> targetType, boolean mustSee, int range) {
        super(mob, targetType, 10, mustSee, false, null);
        this.range = range;
    }

    @Nonnull
    @Override
    protected AABB getTargetSearchArea(double targetDistance) {
        return this.mob.getBoundingBox().inflate(range, range, range);
    }

}
