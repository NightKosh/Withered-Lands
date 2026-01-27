package nightkosh.withered_lands.entity.ai.breeze;

import com.google.common.annotations.VisibleForTesting;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.behavior.MoveToTargetSink;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SlideToTargetSink extends MoveToTargetSink {

    @VisibleForTesting
    public SlideToTargetSink(int i, int j) {
        super(i, j);
    }

    @Override
    protected void start(@Nonnull ServerLevel level, @Nonnull Mob mob, long gameTime) {
        super.start(level, mob, gameTime);
        mob.playSound(SoundEvents.BREEZE_SLIDE);
        mob.setPose(Pose.SLIDING);
    }

    @Override
    protected void stop(@Nonnull ServerLevel level, @Nonnull Mob mob, long gameTime) {
        super.stop(level, mob, gameTime);
        mob.setPose(Pose.STANDING);
        if (mob.getBrain().hasMemoryValue(MemoryModuleType.ATTACK_TARGET)) {
            mob.getBrain().setMemoryWithExpiry(MemoryModuleType.BREEZE_SHOOT, Unit.INSTANCE, 60L);
        }
    }

}
