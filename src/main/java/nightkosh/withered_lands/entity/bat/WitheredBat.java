package nightkosh.withered_lands.entity.bat;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.helper.TimeHelper;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WitheredBat extends AHostileBat {

    public WitheredBat(EntityType<? extends AHostileBat> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void tick() {
        if (this.level() instanceof ServerLevel server) {
            server.sendParticles(ParticleTypes.SMOKE,
                    this.getX() + 0.25 - this.random.nextDouble() * 0.5,
                    this.getY() + 0.25 + this.random.nextDouble() * 0.5,
                    this.getZ() + 0.25 - this.random.nextDouble() * 0.5,
                    5,
                    0, 0, 0,
                    0);
        }

        super.tick();
    }

    @Override
    protected void applyEffect(LivingEntity entity) {
        super.applyEffect(entity);
        if (WLConfigs.WITHERED_BAT_WITHER_DEBUFF.get()) {
            entity.addEffect(new MobEffectInstance(MobEffects.WITHER, TimeHelper.SECONDS_8), this);
        }
    }

    public static boolean checkSpawnRules(
            EntityType<? extends AHostileBat> entityType, ServerLevelAccessor level,
            EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        // light level should be ignored
        return WLConfigs.WITHERED_BAT_SPAWN.get() && level.getDifficulty() != Difficulty.PEACEFUL;
    }

}
