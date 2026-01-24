package nightkosh.withered_lands.entity.crawler;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import nightkosh.withered_lands.helper.TimeHelper;

import javax.annotation.Nonnull;

import static nightkosh.withered_lands.compatibility.GravestoneExtendedCompatibility.*;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BoggedSkullCrawler extends SkeletonSkullCrawler {

    public BoggedSkullCrawler(EntityType<? extends ASkullCrawler> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public Block getPilesOfBones() {
        return PILE_OF_BONES_SKULL_BOGGED_CRAWLER;
    }

    @Override
    public Block getBoneSkullBlock() {
        return BONE_BLOCK_SKULL_BOGGED_CRAWLER;
    }

    @Override
    protected void applyEffect(LivingEntity entity) {
        entity.addEffect(new MobEffectInstance(MobEffects.POISON, TimeHelper.SECONDS_5), this);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.BOGGED_AMBIENT;
    }

    @Nonnull
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.BOGGED_HURT;
    }

    @Nonnull
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BOGGED_DEATH;
    }

    public static AttributeSupplier createAttributeSupplier() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 12)
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.ATTACK_DAMAGE, 2)
                .build();
    }

}
