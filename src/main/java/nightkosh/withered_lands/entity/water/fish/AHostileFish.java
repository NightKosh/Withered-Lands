package nightkosh.withered_lands.entity.water.fish;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.fish.AbstractSchoolingFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class AHostileFish extends AbstractSchoolingFish {

    public AHostileFish(EntityType<? extends AHostileFish> entityType, Level level) {
        super(entityType, level);
        setControl();
    }

    protected void setControl() {
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1, false));

        this.goalSelector.addGoal(3, new RandomSwimmingGoal(this, 1, 10));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new FollowFlockLeaderGoal(this));

        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1));
        this.goalSelector.addGoal(6, new MeleeAttackGoal(this, 1, true));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8));

        if (followBoat()) {
            this.goalSelector.addGoal(8, new FollowPlayerRiddenEntityGoal(this, AbstractBoat.class));
        }

        registerTargetGoals();
    }

    protected boolean followBoat() {
        return true;
    }

    protected void registerTargetGoals() {
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
    }

    @Override
    public boolean doHurtTarget(@Nonnull ServerLevel level, @Nonnull Entity entity) {
        if (!super.doHurtTarget(level, entity)) {
            return false;
        } else {
            if (entity instanceof LivingEntity living) {
                applyEffect(living);
            }

            return true;
        }
    }

    protected void applyEffect(LivingEntity entity) {

    }

    @Override
    protected void travelInWater(@Nonnull Vec3 vec3, double p_481909_, boolean xz1, double xz2) {
        this.moveRelative(this.getSpeed(), vec3);
        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.9));
        if (this.getTarget() == null) {
            this.setDeltaMovement(this.getDeltaMovement().add(0, -0.005, 0));
        }
    }

    @Nonnull
    @Override
    protected InteractionResult mobInteract(@Nonnull Player player, @Nonnull InteractionHand hand) {
        return InteractionResult.FAIL;
    }

    @Nonnull
    @Override
    public ItemStack getBucketItemStack() {
        return ItemStack.EMPTY;
    }

    protected static boolean checkCommonSpawnRules(ServerLevelAccessor level, BlockPos pos, RandomSource random, Class clazz) {
        return level.getDifficulty() != Difficulty.PEACEFUL && checkDensity(level, pos, clazz);
    }

    protected static boolean checkDensity(ServerLevelAccessor level, BlockPos pos, Class<AHostileFish> clazz) {
        var player = level.getNearestPlayer(pos.getX(), pos.getY(), pos.getZ(), 70, false);
        return player != null &&
                pos.getY() < player.getBlockY() + 45 && pos.getY() > player.getBlockY() - 45 &&
                level.getEntitiesOfClass(clazz, new AABB(pos).inflate(100)).size() <= 12;
    }

}
