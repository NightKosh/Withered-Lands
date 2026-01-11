package nightkosh.withered_lands.entity.ai.behavior;

import com.google.common.collect.ImmutableMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import nightkosh.withered_lands.entity.swamp.GiantFrog;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GiantFrogCroak extends Behavior<GiantFrog> {

    private static final int CROAK_TICKS = 60;
    private static final int TIME_OUT_DURATION = 100;
    private int croakCounter;

    public GiantFrogCroak() {
        super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT), TIME_OUT_DURATION);
    }

    @Override
    protected boolean checkExtraStartConditions(@Nonnull ServerLevel level, GiantFrog frog) {
        return frog.getPose() == Pose.STANDING;
    }

    @Override
    protected boolean canStillUse(@Nonnull ServerLevel level, GiantFrog p_217155_, long gameTime) {
        return this.croakCounter < CROAK_TICKS;
    }

    @Override
    protected void start(@Nonnull ServerLevel level, GiantFrog frog, long gameTime) {
        if (!frog.isInLiquid()) {
            frog.setPose(Pose.CROAKING);
            this.croakCounter = 0;
        }
    }

    @Override
    protected void stop(@Nonnull ServerLevel level, GiantFrog frog, long gameTime) {
        frog.setPose(Pose.STANDING);
    }

    @Override
    protected void tick(@Nonnull ServerLevel level, GiantFrog frog, long gameTime) {
        this.croakCounter++;
    }

}
