package nightkosh.withered_lands.entity.bat;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import nightkosh.withered_lands.core.WLConfigs;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BatVolatile extends AHostileBat {

    private static final float EXPLOSION_RADIUS = 0.5F;

    public BatVolatile(EntityType<? extends AHostileBat> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void tick() {
        if (this.level() instanceof ServerLevel server) {
            server.sendParticles(ParticleTypes.ASH,
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
        if (WLConfigs.VOLATILE_BAT_EXPLOSION.get()) {
            this.explode();
            this.discard();
        }
    }

    private void explode() {
        if (this.level() instanceof ServerLevel serverlevel) {
            serverlevel.explode(this, this.getX(), this.getY(), this.getZ(),
                    EXPLOSION_RADIUS, Level.ExplosionInteraction.MOB);
        }
    }

    public static AttributeSupplier createAttributeSupplier() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2)
                .add(Attributes.FOLLOW_RANGE, 30)
                .add(Attributes.ATTACK_DAMAGE, 0.5)
                .build();
    }

    public static boolean checkSpawnRules(
            EntityType<? extends AHostileBat> entityType, ServerLevelAccessor level,
            EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        // light level should be ignored
        return WLConfigs.VOLATILE_BAT_SPAWN.get() && level.getDifficulty() != Difficulty.PEACEFUL;
    }

}
