package nightkosh.withered_lands.condition;

import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.conditions.ICondition;
import nightkosh.withered_lands.core.WLConfigs;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public record SlimeCrownEnabledCondition() implements ICondition {

    public static final MapCodec<SlimeCrownEnabledCondition> CODEC =
            MapCodec.unit(new SlimeCrownEnabledCondition());

    @Override
    public boolean test(@Nonnull IContext context) {
        return WLConfigs.SLIME_RAIN_CRAFTABLE_CROWN.get();
    }

    @Nonnull
    @Override
    public MapCodec<? extends ICondition> codec() {
        return CODEC;
    }

}
