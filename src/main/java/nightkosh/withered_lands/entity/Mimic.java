package nightkosh.withered_lands.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.core.WLEntities;
import nightkosh.withered_lands.entity.ai.goal.*;
import nightkosh.withered_lands.entity.ai.move_control.JumpingMoveControl;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Mimic extends AMonster implements IJumpingCube {

    public enum Type {
        SIMPLE_DUNGEON("simple_dungeon"),
        NETHER_BRIDGE("nether_bridge"),
        DESERT_PYRAMID("desert_pyramid"),
        SHIPWRECK_TREASURE("shipwreck_treasure"),
        STRONGHOLD_CORRIDOR("stronghold_corridor"),
        STRONGHOLD_CROSSING("stronghold_crossing"),
        STRONGHOLD_LIBRARY("stronghold_library"),
        BASTION_BRIDGE("bastion_bridge"),
        BASTION_TREASURE("bastion_treasure");

        private final String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public static Type fromString(String str) {
            for (var t : values()) {
                if (t.name.equals(str)) {
                    return t;
                }
            }
            return SIMPLE_DUNGEON;
        }
    }

    private static final EntityDataAccessor<Boolean> HIDE_ID = SynchedEntityData.defineId(Mimic.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> CAN_HIDE_ID = SynchedEntityData.defineId(Mimic.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IDLE_ID = SynchedEntityData.defineId(Mimic.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState idleAnimation = new AnimationState();
    public final AnimationState jumpAnimationState = new AnimationState();

    private boolean wasOnGround = false;

    private Type mimicType = Type.SIMPLE_DUNGEON;

    public Mimic(EntityType<? extends AMonster> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new JumpingMoveControl(this);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MimicAttackGoal(this));
        this.goalSelector.addGoal(3, new HideAsChestGoal(this));
        this.goalSelector.addGoal(4, new MimicIdleGoal(this));
        this.goalSelector.addGoal(5, new MimicRandomDirectionGoal(this));
        this.goalSelector.addGoal(6, new MimicKeepOnJumpingGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new AttackIfToCloseGoal(this, Player.class, true, 4));
    }

    @Override
    protected void defineSynchedData(@Nonnull SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(HIDE_ID, true);
        builder.define(CAN_HIDE_ID, true);
        builder.define(IDLE_ID, false);
    }

    @Override
    protected void addAdditionalSaveData(@Nonnull ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.putBoolean("was_on_ground", this.wasOnGround);
        output.putString("mimic_type", this.mimicType.getName());
    }

    @Override
    protected void readAdditionalSaveData(@Nonnull ValueInput input) {
        super.readAdditionalSaveData(input);
        this.wasOnGround = input.getBooleanOr("was_on_ground", false);
        this.mimicType = Type.fromString(input.getStringOr("mimic_type", Type.SIMPLE_DUNGEON.getName()));
    }

    @Override
    public void tick() {
        super.tick();

        if (this.onGround() && !this.wasOnGround) {
            this.jumpAnimationState.stop();
            this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1) / 0.8F);
        } else if (!this.onGround() && this.wasOnGround) {
            this.jumpAnimationState.startIfStopped(this.tickCount);
        }

        this.wasOnGround = this.onGround();

        if (this.isHiding()) {
            this.jumpAnimationState.stop();
        }
        if (this.isIdle()) {
            this.idleAnimation.startIfStopped(this.tickCount);
        } else {
            this.idleAnimation.stop();
        }
    }

    @Override
    public void playerTouch(@Nonnull Player player) {
        if (this.level() instanceof ServerLevel level &&
                this.isAlive() &&
                this.isWithinMeleeAttackRange(player) &&
                this.hasLineOfSight(player)) {
            var damageSource = this.damageSources().mobAttack(this);
            if (player.hurtServer(level, damageSource, (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE))) {
                this.playSound(SoundEvents.HOGLIN_ATTACK, 1, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1);
                EnchantmentHelper.doPostAttackEffects(level, player, damageSource);
            }
        }
    }

    public void setType(Type type) {
        this.mimicType = type;
    }

    public void setCanHide(boolean canHide) {
        this.entityData.set(CAN_HIDE_ID, canHide);
    }

    public void setHiding(boolean isHiding) {
        this.entityData.set(HIDE_ID, isHiding);
    }

    public boolean canHide() {
        return this.entityData.get(CAN_HIDE_ID);
    }

    public boolean isHiding() {
        return this.entityData.get(HIDE_ID);
    }

    public void setIdle(boolean idle) {
        this.entityData.set(IDLE_ID, idle);
    }

    public boolean isIdle() {
        return this.entityData.get(IDLE_ID);
    }

    @Override
    public int getJumpDelay() {
        return this.random.nextInt(10) + 5;
    }

    @Override
    public boolean isPushable() {
        return !this.isHiding() && super.isPushable();
    }

    @Nonnull
    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSource) {
        return SoundEvents.WOOD_HIT;
    }

    @Nonnull
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.WOOD_BREAK;
    }

    @Override
    public SoundEvent getJumpSound() {
        return SoundEvents.WOOD_PLACE;
    }

    @Override
    public float getSoundVolume() {
        return 1;
    }

    public static void replaceChestByMimic(ServerLevel level, BlockPos pos, Player player, Type type) {
        var mimic = WLEntities.MIMIC.get().create(level, EntitySpawnReason.TRIGGERED);
        if (mimic != null) {
            level.removeBlockEntity(pos);
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);

            mimic.setPosRaw(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);

            level.addFreshEntity(mimic);

            mimic.setType(type);
            mimic.setPersistenceRequired();
            mimic.setLastHurtByMob(player);
            mimic.lookAt(player, 360, 360);
        }
    }

    public static AttributeSupplier createAttributeSupplier() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40)
                .add(Attributes.FOLLOW_RANGE, 35)
                .add(Attributes.MOVEMENT_SPEED, 0.8)
                .add(Attributes.ATTACK_DAMAGE, 6)
                .add(Attributes.ARMOR, 4)
                .build();
    }

    public static boolean checkSpawnRules(
            EntityType<? extends Mimic> entityType, ServerLevelAccessor levelAccessor,
            EntitySpawnReason spawnReason, BlockPos blockPos, RandomSource random) {
        return WLConfigs.MIMIC_SPAWN.get() &&
                blockPos.getY() < 10 &&
                levelAccessor.getEntitiesOfClass(Mimic.class, new AABB(blockPos).inflate(50)).isEmpty() &&
                checkCommonSpawnRules(levelAccessor, blockPos, random);
    }

}
