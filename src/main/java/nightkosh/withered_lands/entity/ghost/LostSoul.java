package nightkosh.withered_lands.entity.ghost;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.entity.ai.goal.GhostAttackGoal;
import nightkosh.withered_lands.entity.ai.goal.GhostRandomMoveGoal;
import nightkosh.withered_lands.entity.ai.move_control.GhostMoveControl;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class LostSoul extends AGhost {

    public LostSoul(EntityType<? extends AGhost> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new GhostMoveControl(this);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(4, new GhostAttackGoal(this));
        this.goalSelector.addGoal(8, new GhostRandomMoveGoal(this));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 15));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void tick() {
        if (this.level() instanceof ServerLevel server) {
            server.sendParticles(ParticleTypes.SOUL_FIRE_FLAME,
                    this.getX() + 0.4 - this.random.nextDouble() * 0.8,
                    this.getY() + 0.1 + this.random.nextDouble() * 0.6,
                    this.getZ() + 0.4 - this.random.nextDouble() * 0.8,
                    5,
                    0, 0, 0,
                    0);
        }

        super.tick();
    }

    public static AttributeSupplier createAttributeSupplier() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6)
                .add(Attributes.ATTACK_DAMAGE, 2)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .build();
    }

    public static boolean checkSpawnRules(
            EntityType<? extends LostSoul> entityType, ServerLevelAccessor level,
            EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        return WLConfigs.LOST_SOUL_SPAWN.get() &&
                checkCommonSpawnRules(level, pos, random);
    }

}
