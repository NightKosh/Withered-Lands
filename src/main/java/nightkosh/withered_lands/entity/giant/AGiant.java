package nightkosh.withered_lands.entity.giant;

import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.monster.zombie.ZombieVillager;
import net.minecraft.world.entity.monster.zombie.ZombifiedPiglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import nightkosh.withered_lands.entity.AMonster;
import nightkosh.withered_lands.entity.wolf.ZombieDog;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class AGiant extends AMonster {

    public AGiant(EntityType<? extends AGiant> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1, false));
        this.goalSelector.addGoal(4, new MoveTowardsRestrictionGoal(this, 1));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this)
                .setAlertOthers(Zombie.class, ZombifiedPiglin.class, ZombieVillager.class, ZombieDog.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public boolean checkSpawnRules(@Nonnull LevelAccessor levelAccessor, @Nonnull EntitySpawnReason spawnReason) {
        if (levelAccessor instanceof ServerLevelAccessor sa) {
            return isDarkEnoughToSpawn(sa, this.blockPosition(), random);
        } else {
            return super.checkSpawnRules(levelAccessor, spawnReason);
        }
    }

}
