package nightkosh.withered_lands.entity.ai.goal;

import net.minecraft.world.entity.ai.goal.Goal;
import net.neoforged.neoforge.event.EventHooks;
import nightkosh.withered_lands.entity.crawler.ASkullCrawler;

import static nightkosh.withered_lands.compatibility.GravestoneExtendedCompatibility.*;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SummonSkullCrawlersGoal extends Goal {

    private ASkullCrawler crawler;
    private int summonColdown;

    public SummonSkullCrawlersGoal(ASkullCrawler crawler) {
        this.crawler = crawler;
    }

    public void resetSummonColdown() {
        if (this.summonColdown == 0) {
            this.summonColdown = 10;
        }
    }

    @Override
    public boolean canUse() {
        return this.summonColdown > 0;
    }

    @Override
    public void tick() {
        this.summonColdown--;

        if (this.summonColdown <= 0) {
            var level = this.crawler.level();
            var random = this.crawler.getRandom();
            var blockPos = this.crawler.blockPosition();

            for (int i = 0; i <= 5 && i >= -5; i = i <= 0 ? 1 - i : -i) {
                for (int j = 0; j <= 10 && j >= -10; j = j <= 0 ? 1 - j : -j) {
                    for (int k = 0; k <= 10 && k >= -10; k = k <= 0 ? 1 - k : -k) {
                        var pos = blockPos.offset(j, i, k);
                        var state = level.getBlockState(pos);

                        if (state.is(PILE_OF_BONES_SKULL_SKELETON_CRAWLER) || state.is(PILE_OF_BONES_SKULL_STRAY_CRAWLER) ||
                                state.is(PILE_OF_BONES_SKULL_BOGGED_CRAWLER) || state.is(PILE_OF_BONES_SKULL_PARCHED_CRAWLER) ||
                                state.is(PILE_OF_BONES_SKULL_WITHER_CRAWLER) || state.is(PILE_OF_BONES_SKULL_ZOMBIE_CRAWLER) ||
                                state.is(PILE_OF_BONES_SKULL_HUSK_CRAWLER) || state.is(PILE_OF_BONES_SKULL_DROWNED_CRAWLER) ||
                                state.is(PILE_OF_BONES_SKULL_PIGLIN_CRAWLER) ||
                                state.is(BONE_BLOCK_SKELETON_CRAWLER) || state.is(BONE_BLOCK_SKULL_SKELETON_CRAWLER) ||
                                state.is(BONE_BLOCK_STRAY_CRAWLER) || state.is(BONE_BLOCK_SKULL_STRAY_CRAWLER) ||
                                state.is(BONE_BLOCK_BOGGED_CRAWLER) || state.is(BONE_BLOCK_SKULL_BOGGED_CRAWLER) ||
                                state.is(BONE_BLOCK_PARCHED_CRAWLER) || state.is(BONE_BLOCK_SKULL_PARCHED_CRAWLER) ||
                                state.is(BONE_BLOCK_WITHER_CRAWLER) || state.is(BONE_BLOCK_SKULL_WITHER_CRAWLER) ||
                                state.is(BONE_BLOCK_ZOMBIE_CRAWLER) || state.is(BONE_BLOCK_SKULL_ZOMBIE_CRAWLER) ||
                                state.is(BONE_BLOCK_HUSK_CRAWLER) || state.is(BONE_BLOCK_SKULL_HUSK_CRAWLER) ||
                                state.is(BONE_BLOCK_DROWNED_CRAWLER) || state.is(BONE_BLOCK_SKULL_DROWNED_CRAWLER) ||
                                state.is(BONE_BLOCK_PIGLIN_CRAWLER) || state.is(BONE_BLOCK_SKULL_PIGLIN_CRAWLER)) {
                            if (EventHooks.canEntityGrief(getServerLevel(level), this.crawler)) {
                                level.destroyBlock(pos, true);
                            }

                            if (random.nextBoolean()) {
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

}
