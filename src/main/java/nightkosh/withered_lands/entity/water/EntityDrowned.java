package nightkosh.withered_lands.entity.water;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import nightkosh.withered_lands.core.WLSounds;
import nightkosh.withered_lands.entity.ai.TridentAttackGoal;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityDrowned extends AWaterWalkingMob implements RangedAttackMob {

    public EntityDrowned(EntityType<? extends EntityDrowned> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(2, new TridentAttackGoal(this, 1, 40, 10));
    }

    @Override
    public void tick() {
        if (this.level() instanceof ServerLevel server && this.isInWater()) {
            server.sendParticles(ParticleTypes.BUBBLE,
                    this.getX() + 0.5 - this.random.nextDouble(),
                    this.getY() + 1.25 + this.random.nextDouble() * 1.5,
                    this.getZ() + 0.5 - this.random.nextDouble(),
                    2,
                    0, 0, 0,
                    0);
        }

        super.tick();
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
//        entity.addEffect(new MobEffectInstance(MobEffects., 150), this);
        // TODO
//        ((EntityPlayer) entity).addPotionEffect(new PotionEffect(GSPotion.CHOKE, 400));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.isInWater()) {
            return this.random.nextInt(10) > 2 ?
                    WLSounds.DROWNED_BUBBLES.get() :
                    WLSounds.DROWNED_GROWL.get();
        } else {
            return WLSounds.DROWNED_GROWL.get();
        }
    }

    public static AttributeSupplier createAttributeSupplier() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 3)
                .add(Attributes.FOLLOW_RANGE, 35)
                .add(Attributes.STEP_HEIGHT, 1)
                .add(Attributes.MAX_HEALTH, 30)
                .add(Attributes.ARMOR, 2)
                .build();
    }

    @Override
    public void performRangedAttack(LivingEntity target, float velocity) {
        var mainStack = this.getMainHandItem();
        var itemStack = mainStack.is(Items.TRIDENT) ?
                mainStack :
                new ItemStack(Items.TRIDENT);
        var throwntrident = new ThrownTrident(this.level(), this, itemStack);
        double x = target.getX() - this.getX();
        double y = target.getY(0.3333333333333333) - throwntrident.getY();
        double z = target.getZ() - this.getZ();
        double d = Math.sqrt(x * x + z * z);

        if (this.level() instanceof ServerLevel serverlevel) {
            Projectile.spawnProjectileUsingShoot(
                    throwntrident, serverlevel, itemStack,
                    x, y + d * 0.2F, z,
                    1.6F, 14 - this.level().getDifficulty().getId() * 4);
        }

        this.playSound(SoundEvents.DROWNED_SHOOT, 1, 1 / (this.getRandom().nextFloat() * 0.4F + 0.8F));

    }
}
