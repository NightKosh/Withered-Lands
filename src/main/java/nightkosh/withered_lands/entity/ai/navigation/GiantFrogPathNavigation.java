package nightkosh.withered_lands.entity.ai.navigation;

import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.PathType;
import nightkosh.withered_lands.entity.swamp.GiantFrog;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GiantFrogPathNavigation extends AmphibiousPathNavigation {

    public GiantFrogPathNavigation(GiantFrog mob, Level level) {
        super(mob, level);
    }

    @Override
    public boolean canCutCorner(@Nonnull PathType pathType) {
        return pathType != PathType.WATER_BORDER && super.canCutCorner(pathType);
    }

    @Nonnull
    @Override
    protected PathFinder createPathFinder(int xz) {
        this.nodeEvaluator = new GiantFrogNodeEvaluator(true);
        return new PathFinder(this.nodeEvaluator, xz);
    }

}
