package nightkosh.withered_lands.entity.bat;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.event.EventHooks;
import nightkosh.withered_lands.core.WLConfigs;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ChorusBat extends HostileBat {

    private static final int DIAMETER = 16;

    public ChorusBat(EntityType<? extends HostileBat> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void applyEffect(LivingEntity entity) {
        teleport(entity.level(), entity);
    }

    private void teleport(Level level, LivingEntity entity) {
        if (entity.isAlive()) {
            for (int i = 0; i < 16; i++) {

                double x = entity.getX() + (entity.getRandom().nextDouble() - 0.5) * DIAMETER;
                double y = Mth.clamp(
                        entity.getY() + (entity.getRandom().nextDouble() - 0.5) * DIAMETER,
                        level.getMinY(),
                        level.getMinY() + ((ServerLevel) level).getLogicalHeight() - 1);
                double z = entity.getZ() + (entity.getRandom().nextDouble() - 0.5) * DIAMETER;
                if (entity.isPassenger()) {
                    entity.stopRiding();
                }

                var vec3 = entity.position();
                var event = EventHooks.onItemConsumptionTeleport(entity, new ItemStack(Items.CHORUS_FRUIT), x, y, z);
                if (!event.isCanceled() && entity.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true)) {
                    level.gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(entity));

                    level.playSound(entity, entity.getX(), entity.getY(), entity.getZ(),
                            SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS);
                    entity.resetFallDistance();
                    if (entity instanceof Player player) {
                        player.resetCurrentImpulseContext();
                    }
                    break;
                }
            }
        }
    }

    public static boolean checkSpawnRules(
            EntityType<? extends HostileBat> entityType, ServerLevelAccessor level,
            EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
        return WLConfigs.CHORUS_BAT_SPAWN.get() && checkCommonSpawnRules(level, pos, random);
    }

}
