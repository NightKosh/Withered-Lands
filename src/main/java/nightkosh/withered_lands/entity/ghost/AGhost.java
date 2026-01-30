package nightkosh.withered_lands.entity.ghost;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import nightkosh.withered_lands.core.WLSounds;
import nightkosh.withered_lands.entity.AMonster;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class AGhost extends AMonster {

    protected static final EntityDataAccessor<Boolean> CHARGED_FLAG =
            SynchedEntityData.defineId(AGhost.class, EntityDataSerializers.BOOLEAN);

    protected AGhost(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData(@Nonnull SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(CHARGED_FLAG, false);
    }

    @Override
    public void tick() {
        this.noPhysics = true;
        super.tick();
        this.ghostTick();
        this.noPhysics = false;
        this.setNoGravity(true);
    }

    protected void ghostTick() {
        // in case you need to override tick()
    }

    public boolean isCharging() {
        return this.entityData.get(CHARGED_FLAG);
    }

    public void setIsCharging(boolean charging) {
        this.entityData.set(CHARGED_FLAG, charging);
    }

    @Override
    protected boolean isAffectedByBlocks() {
        return !this.isRemoved();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.VEX_AMBIENT;
    }

    @Nonnull
    @Override
    protected SoundEvent getDeathSound() {
        return WLSounds.HOLLOW_STALKER_DEATH.get();
    }

    @Nonnull
    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSource) {
        return SoundEvents.VEX_DEATH;
    }

    @Override
    public float getLightLevelDependentMagicValue() {
        return 1;
    }

}
