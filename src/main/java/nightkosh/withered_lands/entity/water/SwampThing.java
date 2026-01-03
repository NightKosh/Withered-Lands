package nightkosh.withered_lands.entity.water;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
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
import nightkosh.withered_lands.core.WLSounds;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SwampThing extends AWaterWalkingMob {

    public SwampThing(EntityType<? extends SwampThing> entityType, Level level) {
        super(entityType, level);
    }

    protected void applyEffect(LivingEntity entity) {
        entity.addEffect(new MobEffectInstance(net.minecraft.world.effect.MobEffects.SLOWNESS, 300), this);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return WLSounds.SWAMP_THING_GROWL.get();
    }

    public static AttributeSupplier createAttributeSupplier() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 4)
                .add(Attributes.FOLLOW_RANGE, 35)
                .add(Attributes.STEP_HEIGHT, 1)
                .add(Attributes.MAX_HEALTH, 30)
                .add(Attributes.ARMOR, 3)
                .build();
    }

    public static boolean checkSpawnRules(
            EntityType<? extends SwampThing> entityType, ServerLevelAccessor levelAccessor,
            EntitySpawnReason spawnReason, BlockPos blockPos, RandomSource random) {
        return WLConfigs.SWAMP_THING_SPAWN.get() &&
                checkCommonSpawnRules(levelAccessor, spawnReason, blockPos, random);
    }

    public static boolean checkCommonSpawnRules(
            ServerLevelAccessor levelAccessor, EntitySpawnReason spawnReason,
            BlockPos blockPos, RandomSource random) {
        if (!levelAccessor.getFluidState(blockPos.below()).is(FluidTags.WATER) && !EntitySpawnReason.isSpawner(spawnReason)) {
            return false;
        } else {
            boolean flag = levelAccessor.getDifficulty() != Difficulty.PEACEFUL &&
                    (EntitySpawnReason.ignoresLightRequirements(spawnReason) || isDarkEnoughToSpawn(levelAccessor, blockPos, random)) &&
                    (EntitySpawnReason.isSpawner(spawnReason) || levelAccessor.getFluidState(blockPos).is(FluidTags.WATER));
            if (!flag || !EntitySpawnReason.isSpawner(spawnReason)) {
                return flag;
            } else {
                return true;
            }
        }
    }

}
