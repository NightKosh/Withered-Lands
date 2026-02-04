package nightkosh.withered_lands.entity.ghost;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.core.WLSounds;
import nightkosh.withered_lands.entity.ai.*;
import nightkosh.withered_lands.entity.ai.goal.AttackIfToCloseGoal;
import nightkosh.withered_lands.entity.ai.goal.ExtinguishLightGoal;
import nightkosh.withered_lands.entity.ai.goal.GhostRandomMoveGoal;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class HollowStalker extends AGhost {

    public static final int MAX_DEPTH = 45;

    public HollowStalker(EntityType<? extends HollowStalker> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new GhostMoveControl(this);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(4, new GhostAttackGoal(this));
        if (WLConfigs.HOLLOW_STALKER_EXTINGUISH_LIGHT.get()) {
            this.goalSelector.addGoal(5, new ExtinguishLightGoal(this, MAX_DEPTH));
        }
        this.goalSelector.addGoal(8, new GhostRandomMoveGoal(this));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 15));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
        this.targetSelector.addGoal(2, new AttackIfToCloseGoal(this, Player.class, true, 4));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return this.random.nextInt(30) == 0 ?
                WLSounds.HOLLOW_STALKER_ARE_YOU_THERE.get() :
                WLSounds.HOLLOW_STALKER_AMBIENT.get();
    }

    public static AttributeSupplier createAttributeSupplier() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 14)
                .add(Attributes.ATTACK_DAMAGE, 3)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .build();
    }

    public static boolean checkSpawnRules(
            EntityType<? extends HollowStalker> entityType, ServerLevelAccessor level,
            EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        return WLConfigs.HOLLOW_STALKER_SPAWN.get() &&
                pos.getY() < MAX_DEPTH &&
                checkCommonSpawnRules(level, pos, random);
    }

}
