package nightkosh.withered_lands.entity.ai.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import nightkosh.withered_lands.core.WLAdvancements;
import org.jspecify.annotations.Nullable;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class AttackIfInWaterGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

    private boolean giveAchievement;

    public AttackIfInWaterGoal(Mob mob, Class<T> targetType, boolean mustSee) {
        this(mob, targetType, mustSee, false);
    }

    public AttackIfInWaterGoal(Mob mob, Class<T> targetType, boolean mustSee, boolean giveAchievement) {
        super(mob, targetType, 10, mustSee, false,
                (entity, level) -> entity.isInWater());
        this.giveAchievement = giveAchievement;
    }


    @Override
    public void start() {
        super.start();
        if (this.giveAchievement && this.target instanceof Player player) {
            WLAdvancements.giveAdvancement(player, mob.level(), WLAdvancements.GET_OUT_OF_MY_SWAMP);
        }
    }

}
