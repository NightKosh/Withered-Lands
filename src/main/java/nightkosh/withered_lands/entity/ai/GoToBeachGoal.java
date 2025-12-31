package nightkosh.withered_lands.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import nightkosh.withered_lands.entity.water.AWaterWalkingMob;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GoToBeachGoal extends MoveToBlockGoal {

    public GoToBeachGoal(AWaterWalkingMob mob, double speedModifier) {
        super(mob, speedModifier, 8, 2);
    }

    @Override
    public boolean canUse() {
        return super.canUse()
                && !this.mob.level().isBrightOutside()
                && this.mob.isInWater()
                && this.mob.getY() >= this.mob.level().getSeaLevel() - 3;
    }

    @Override
    public boolean canContinueToUse() {
        return super.canContinueToUse();
    }

    @Override
    protected boolean isValidTarget(LevelReader levelReader, BlockPos blockPos) {
        var blockpos = blockPos.above();
        return levelReader.isEmptyBlock(blockpos) &&
                levelReader.isEmptyBlock(blockpos.above()) &&
                levelReader.getBlockState(blockPos).entityCanStandOn(levelReader, blockPos, this.mob);
    }

    @Override
    public void start() {
        ((AWaterWalkingMob) this.mob).setSearchingForLand(false);
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }

}
