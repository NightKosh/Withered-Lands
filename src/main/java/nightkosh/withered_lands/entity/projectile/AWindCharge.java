package nightkosh.withered_lands.entity.projectile;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.hurtingprojectile.windcharge.AbstractWindCharge;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import nightkosh.withered_lands.entity.breeze.ABreeze;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class AWindCharge extends AbstractWindCharge {

    private static final float RADIUS = 3;

    public AWindCharge(EntityType<? extends AbstractWindCharge> entityType, Level level) {
        super(entityType, level);
    }

    public AWindCharge(ABreeze breeze, Level level) {
        super(EntityType.BREEZE_WIND_CHARGE, level, breeze, breeze.getX(), breeze.getFiringYPosition(), breeze.getZ());
    }

    @Override
    protected void onHitEntity(@Nonnull EntityHitResult hitResult) {
        if (this.level() instanceof ServerLevel level) {
            var livingEntity = this.getOwner() instanceof LivingEntity living ? living : null;
            var target = hitResult.getEntity();
            if (livingEntity != null) {
                livingEntity.setLastHurtMob(target);
            }

            var damagesource = this.damageSources().windCharge(this, livingEntity);
            if (target.hurtServer(level, damagesource, 1) &&
                    target instanceof LivingEntity living) {
                EnchantmentHelper.doPostAttackEffects(level, living, damagesource);
                applyEffect(living);
            }

            this.explode(this.position());
        }
    }

    @Override
    protected void explode(Vec3 vec3) {
        this.level().explode(
                this,
                null,
                EXPLOSION_DAMAGE_CALCULATOR,
                vec3.x(), vec3.y(), vec3.z(),
                RADIUS,
                false,
                Level.ExplosionInteraction.TRIGGER,
                ParticleTypes.GUST_EMITTER_SMALL,
                ParticleTypes.GUST_EMITTER_LARGE,
                WeightedList.of(),
                SoundEvents.BREEZE_WIND_CHARGE_BURST);
    }

    protected abstract void applyEffect(LivingEntity entity);

}
