package nightkosh.withered_lands.entity.ai.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.EventHooks;
import nightkosh.withered_lands.entity.crawler.ASkullCrawler;

import java.util.EnumSet;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class HideInPilesOfBonesGoal extends RandomStrollGoal {

    private final ASkullCrawler crawler;
    private Direction selectedDirection;
    private boolean doHide;
    private int ticks;

    public HideInPilesOfBonesGoal(ASkullCrawler crawler) {
        super(crawler, 1, 10);
        this.crawler = crawler;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        ticks++;
        if (ticks >= 100 && !crawler.hideInBonesGoal.isExecuting() && !isNether()) {
            if (this.mob.getTarget() != null || !this.mob.getNavigation().isDone()) {
                return false;
            } else {
                var random = this.mob.getRandom();
                var level = this.mob.level();
                if (EventHooks.canEntityGrief(getServerLevel(level), this.mob) &&
                        random.nextInt(reducedTickDelay(10)) == 0) {
                    this.selectedDirection = Direction.getRandom(random);
                    var blockpos = BlockPos.containing(this.mob.getX(), this.mob.getY() + 0.5, this.mob.getZ())
                            .relative(this.selectedDirection);
                    var blockstate = level.getBlockState(blockpos);
                    var belowPos = blockpos.below();
                    if (blockstate.isAir() && level.getBlockState(belowPos).isFaceSturdy(level, belowPos, Direction.UP)) {
                        this.doHide = true;
                        return true;
                    }
                }

                this.doHide = false;
                return super.canUse();
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean canContinueToUse() {
        return !this.doHide && super.canContinueToUse();
    }

    @Override
    public void start() {
        if (!this.doHide) {
            super.start();
        } else if (!isNether()) {
            var level = crawler.level();
            var blockPos = crawler.blockPosition().relative(this.selectedDirection);

            level.setBlock(blockPos, crawler.getPilesOfBones().defaultBlockState(), 3);
            crawler.spawnAnim();
            crawler.discard();
        }
    }

    protected boolean isNether() {
        return this.crawler.level().dimension() == Level.NETHER;
    }

}
