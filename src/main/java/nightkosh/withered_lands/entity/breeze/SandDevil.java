package nightkosh.withered_lands.entity.breeze;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
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
import net.minecraft.world.level.block.Blocks;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.entity.projectile.AWindCharge;
import nightkosh.withered_lands.entity.projectile.SandDevilWindCharge;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SandDevil extends ABreeze {

    private static final BlockParticleOption PARTICLE = new BlockParticleOption(
            ParticleTypes.BLOCK, Blocks.SAND.defaultBlockState());

    public SandDevil(EntityType<? extends ABreeze> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected ParticleOptions getParticle() {
        return PARTICLE;
    }

    @Override
    public AWindCharge getWindCharge(ABreeze breeze, Level level) {
        return new SandDevilWindCharge(breeze, level);
    }

    public static AttributeSupplier createAttributeSupplier() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.63F)
                .add(Attributes.MAX_HEALTH, 30)
                .add(Attributes.FOLLOW_RANGE, 24)
                .add(Attributes.ATTACK_DAMAGE, 3)
                .build();
    }

    public static boolean checkSpawnRules(
            EntityType<? extends ABreeze> entityType, ServerLevelAccessor levelAccessor,
            EntitySpawnReason spawnReason, BlockPos blockPos, RandomSource random) {
        return WLConfigs.SAND_DEVIL_SPAWN.get() &&
                checkBreezeSpawnRules(levelAccessor, blockPos, random);
    }

}
