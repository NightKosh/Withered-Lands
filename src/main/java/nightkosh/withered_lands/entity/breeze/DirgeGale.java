package nightkosh.withered_lands.entity.breeze;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.entity.projectile.AWindCharge;
import nightkosh.withered_lands.entity.projectile.DirgeGaleWindCharge;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class DirgeGale extends ABreeze {

    public DirgeGale(EntityType<? extends ABreeze> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void emitGroundParticles(int count) {
        if (!this.isPassenger()) {
            var vec3 = this.getBoundingBox().getCenter();
            var vec31 = new Vec3(vec3.x, this.position().y, vec3.z);
            addParticle(ParticleTypes.SOUL, vec31.x, vec31.y, vec31.z);
        }
    }

    @Override
    protected int getAmountPfBodyParticles() {
        return 1;
    }

    @Override
    protected ParticleOptions getParticle() {
        return ParticleTypes.TRIAL_OMEN;
    }

    @Override
    public AWindCharge getWindCharge(ABreeze breeze, Level level) {
        return new DirgeGaleWindCharge(breeze, level);
    }

    public static boolean checkSpawnRules(
            EntityType<? extends ABreeze> entityType, ServerLevelAccessor levelAccessor,
            EntitySpawnReason spawnReason, BlockPos blockPos, RandomSource random) {
        return WLConfigs.DIRGE_GALE_SPAWN.get() &&
                checkCommonSpawnRules(levelAccessor, blockPos, random);
    }

    public static AttributeSupplier createAttributeSupplier() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.63F)
                .add(Attributes.MAX_HEALTH, 30)
                .add(Attributes.FOLLOW_RANGE, 24)
                .add(Attributes.ATTACK_DAMAGE, 3)
                .build();
    }

}
