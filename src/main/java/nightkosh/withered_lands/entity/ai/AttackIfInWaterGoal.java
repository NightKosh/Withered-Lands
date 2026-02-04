package nightkosh.withered_lands.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class AttackIfInWaterGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

    public AttackIfInWaterGoal(Mob mob, Class<T> targetType, boolean mustSee) {
        super(mob, targetType, 10, mustSee, false,
                (entity, level) -> entity.isInWater());
    }

}
