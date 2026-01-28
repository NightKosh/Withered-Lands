package nightkosh.withered_lands.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ConversionParams;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class AUndeadPet extends AMonster {

    public AUndeadPet(EntityType<? extends AUndeadPet> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(7, new LeapAtTargetGoal(this, 0.3F));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
    }

    protected boolean convertToZombie(ServerLevel level, Mob pet, EntityType<? extends AUndeadPet> entityType) {
        var zombie = pet.convertTo(
                entityType,
                ConversionParams.single(pet, true, true),
                outcome -> {
                    outcome.finalizeSpawn(
                            level,
                            level.getCurrentDifficultyAt(outcome.blockPosition()),
                            EntitySpawnReason.CONVERSION,
                            new Zombie.ZombieGroupData(false, true)
                    );
                    EventHooks.onLivingConvert(pet, outcome);
                    if (!this.isSilent()) {
                        level.levelEvent(null, 1026, this.blockPosition(), 0);
                    }
                }
        );
        return zombie != null;
    }

}
