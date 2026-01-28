package nightkosh.withered_lands.entity.breeze;

import com.mojang.serialization.Dynamic;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.entity.ai.breeze.BreezeAi;
import nightkosh.withered_lands.entity.projectile.AWindCharge;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Thunderstorm extends ABreeze {

    public Thunderstorm(EntityType<? extends ABreeze> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected ParticleOptions getParticle() {
        return ParticleTypes.ELECTRIC_SPARK;
    }

    @Override
    protected Brain<?> makeBrain(Dynamic<?> dynamic) {
        return BreezeAi.makeBrain(this, this.brainProvider().makeBrain(dynamic), true);
    }

    @Override
    public void thunderHit(@Nonnull ServerLevel level, @Nonnull LightningBolt lightning) {
    }

    @Override
    public AWindCharge getWindCharge(ABreeze breeze, Level level) {
        return null;
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
        return WLConfigs.THUNDERSTORM_SPAWN.get() &&
                checkBreezeSpawnRules(levelAccessor, blockPos, random);
    }

}
