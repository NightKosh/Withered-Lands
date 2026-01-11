package nightkosh.withered_lands.entity.ai.navigation;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.level.pathfinder.AmphibiousNodeEvaluator;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.pathfinder.PathfindingContext;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GiantFrogNodeEvaluator extends AmphibiousNodeEvaluator {

    private final BlockPos.MutableBlockPos belowPos = new BlockPos.MutableBlockPos();

    public GiantFrogNodeEvaluator(boolean prefersShallowSwimming) {
        super(prefersShallowSwimming);
    }

    @Nonnull
    @Override
    public Node getStart() {
        return !this.mob.isInWater() ?
                super.getStart() :
                this.getStartNode(
                        new BlockPos(Mth.floor(
                                this.mob.getBoundingBox().minX),
                                Mth.floor(this.mob.getBoundingBox().minY),
                                Mth.floor(this.mob.getBoundingBox().minZ)));
    }

    @Nonnull
    @Override
    public PathType getPathType(PathfindingContext pathfindingContext, int x, int y, int z) {
        this.belowPos.set(x, y - 1, z);
        return pathfindingContext.getBlockState(this.belowPos).is(BlockTags.FROG_PREFER_JUMP_TO) ?
                PathType.OPEN :
                super.getPathType(pathfindingContext, x, y, z);
    }

}
