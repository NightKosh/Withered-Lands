package nightkosh.withered_lands.entity.water;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.core.WLMobEffects;
import nightkosh.withered_lands.core.WLSounds;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class DrownedSailor extends AWaterWalkingMob {

    public DrownedSailor(EntityType<? extends DrownedSailor> entityType, Level level) {
        super(entityType, level);
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

    protected void applyEffect(LivingEntity entity) {
        entity.addEffect(new MobEffectInstance(WLMobEffects.CALL_OF_THE_ABYSS, 400), this);
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

    public static boolean checkSpawnRules(
            EntityType<? extends AWaterWalkingMob> entityType, ServerLevelAccessor levelAccessor,
            EntitySpawnReason spawnReason, BlockPos blockPos, RandomSource random) {
        return WLConfigs.DROWNED_SAILOR_SPAWN.get() &&
                checkCommonSpawnRules(levelAccessor, spawnReason, blockPos, random);
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

}
