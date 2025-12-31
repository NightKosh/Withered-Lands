package nightkosh.withered_lands.renderer.water;

import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import nightkosh.withered_lands.core.WLTextures;
import nightkosh.withered_lands.renderer.model.PhantomDiverModel;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PhantomDiverFaceLayer extends EyesLayer<ZombieRenderState, PhantomDiverModel> {

    private static final RenderType PHANTOM_DIVER_FACE = RenderTypes.eyes(WLTextures.PHANTOM_DIVER_FACE);

    public PhantomDiverFaceLayer(RenderLayerParent<ZombieRenderState, PhantomDiverModel> renderer) {
        super(renderer);
    }

    @Nonnull
    @Override
    public RenderType renderType() {
        return PHANTOM_DIVER_FACE;
    }

}
