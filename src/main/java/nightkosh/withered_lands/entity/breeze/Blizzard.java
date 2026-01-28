package nightkosh.withered_lands.entity.breeze;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.event.EventHooks;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.entity.projectile.AWindCharge;
import nightkosh.withered_lands.entity.projectile.BlizzardWindCharge;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Blizzard extends ABreeze {

    public Blizzard(EntityType<? extends ABreeze> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.level() instanceof ServerLevel level) {
            if (level.environmentAttributes().getValue(EnvironmentAttributes.SNOW_GOLEM_MELTS, this.position())) {
                this.hurtServer(level, this.damageSources().onFire(), 1);
            }

            if (!EventHooks.canEntityGrief(level, this)) {
                return;
            }

            var blockstate = Blocks.SNOW.defaultBlockState();
            for (int i = 0; i < 4; i++) {
                var blockpos = new BlockPos(
                        Mth.floor(this.getX() + (i % 2 * 2 - 1) * 0.25F),
                        Mth.floor(this.getY()),
                        Mth.floor(this.getZ() + (i / 2 % 2 * 2 - 1) * 0.25F));
                if (this.level().getBlockState(blockpos).isAir() && blockstate.canSurvive(this.level(), blockpos)) {
                    this.level().setBlockAndUpdate(blockpos, blockstate);
                    this.level().gameEvent(GameEvent.BLOCK_PLACE, blockpos, GameEvent.Context.of(this, blockstate));
                }
            }
        }
    }

    @Override
    protected ParticleOptions getParticle() {
        return ParticleTypes.SNOWFLAKE;
    }

    @Override
    public AWindCharge getWindCharge(ABreeze breeze, Level level) {
        return new BlizzardWindCharge(breeze, level);
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
        return WLConfigs.BLIZZARD_SPAWN.get() &&
                checkBreezeSpawnRules(levelAccessor, blockPos, random);
    }

}
