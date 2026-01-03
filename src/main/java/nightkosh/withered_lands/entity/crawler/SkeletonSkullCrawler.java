package nightkosh.withered_lands.entity.crawler;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SkeletonSkullCrawler extends ASkullCrawler {

    public SkeletonSkullCrawler(EntityType<? extends ASkullCrawler> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SKELETON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SKELETON_DEATH;
    }

    @Override
    public boolean canHideInBones() {
        return true;
    }

    public static AttributeSupplier createAttributeSupplier() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 12)
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.ATTACK_DAMAGE, 1.5)
                .build();
    }

}
