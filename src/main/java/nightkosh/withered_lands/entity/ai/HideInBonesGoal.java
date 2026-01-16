package nightkosh.withered_lands.entity.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.neoforged.neoforge.event.EventHooks;
import nightkosh.withered_lands.entity.crawler.ASkullCrawler;

import java.util.EnumSet;

import static nightkosh.withered_lands.compatibility.GravestoneExtendedCompatibility.BONE_BLOCK;
import static nightkosh.withered_lands.compatibility.GravestoneExtendedCompatibility.BONE_BLOCK_SKULL;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class HideInBonesGoal extends RandomStrollGoal {

    private final ASkullCrawler crawler;
    private Direction selectedDirection;
    private boolean doHide;
    private boolean isExecuting = false;
    private int ticks;

    public HideInBonesGoal(ASkullCrawler crawler) {
        super(crawler, 1, 10);
        this.crawler = crawler;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        ticks++;
        if (ticks >= 200) {
            if (this.mob.getTarget() != null || !this.mob.getNavigation().isDone()) {
                return false;
            } else {
                var random = this.mob.getRandom();
                var level = this.mob.level();
                if (EventHooks.canEntityGrief(getServerLevel(level), this.mob) && random.nextInt(10) == 0) {
                    this.selectedDirection = Direction.getRandom(random);
                    var blockpos = BlockPos.containing(this.mob.getX(), this.mob.getY() + 0.5, this.mob.getZ())
                            .relative(this.selectedDirection);
                    var blockstate = level.getBlockState(blockpos);
                    if (blockstate.is(BONE_BLOCK) || blockstate.is(BONE_BLOCK_SKULL)) {
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
        boolean continueExecuting = !this.doHide && super.canContinueToUse();
        if (!continueExecuting) {
            isExecuting = false;
        }

        return continueExecuting;
    }

    @Override
    public void start() {
        if (!this.doHide) {
            super.start();
        } else {
            isExecuting = true;
            var level = crawler.level();
            var blockPos = crawler.blockPosition().relative(this.selectedDirection);
            var state = level.getBlockState(blockPos);
            if (state.is(BONE_BLOCK)) {
                level.setBlock(blockPos, crawler.getBoneBlock().defaultBlockState(), 3);
                crawler.spawnAnim();
                crawler.discard();
            } else if (state.is(BONE_BLOCK_SKULL)) {
                level.setBlock(blockPos, crawler.getBoneSkullBlock().defaultBlockState(), 3);
                crawler.spawnAnim();
                crawler.discard();
            }
        }
    }

    public boolean isExecuting() {
        return isExecuting;
    }

}
