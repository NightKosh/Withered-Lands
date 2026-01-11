package nightkosh.withered_lands.entity.projectile;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import nightkosh.withered_lands.core.WLEntities;
import nightkosh.withered_lands.helper.TimeHelper;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class FrozenSnowball extends ThrowableItemProjectile {

    private static final ItemStack ITEM = new ItemStack(Items.SNOWBALL);

    public FrozenSnowball(EntityType<? extends FrozenSnowball> entityType, Level level) {
        super(entityType, level);
    }

    public FrozenSnowball(Level level, LivingEntity entity) {
        super(WLEntities.FROZEN_SNOWBALL.get(), entity, level, ITEM);
    }

    @Nonnull
    @Override
    protected Item getDefaultItem() {
        return Items.SNOWBALL;
    }

    private ParticleOptions getParticle() {
        ItemStack itemstack = this.getItem();
        return itemstack.isEmpty() ?
                ParticleTypes.ITEM_SNOWBALL :
                new ItemParticleOption(ParticleTypes.ITEM, itemstack);
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 3) {
            ParticleOptions particleoptions = this.getParticle();

            for (int i = 0; i < 8; i++) {
                this.level().addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        var entity = entityHitResult.getEntity();
        int damage = entity instanceof Blaze ? 3 : 1;
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), damage);
        entity.setTicksFrozen(Math.min(entity.getTicksFrozen() + TimeHelper.SECONDS_10, 400));
    }

    @Override
    protected void onHit(@Nonnull HitResult hitResult) {
        super.onHit(hitResult);
        if (!this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

}
