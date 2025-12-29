package nightkosh.withered_lands.renderer.wolf;

import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import nightkosh.withered_lands.core.WLTextures;
import nightkosh.withered_lands.renderer.model.UndeadWolfModel;
import nightkosh.withered_lands.renderer.render_sate.BarghestRenderState;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BarghestEyesLayer extends EyesLayer<BarghestRenderState, UndeadWolfModel<BarghestRenderState>> {

    private static final RenderType BARGHEST_EYES = RenderTypes.eyes(WLTextures.BARGHEST_EYES);

    public BarghestEyesLayer(RenderLayerParent<BarghestRenderState, UndeadWolfModel<BarghestRenderState>> renderer) {
        super(renderer);
    }

    @Nonnull
    @Override
    public RenderType renderType() {
        return BARGHEST_EYES;
    }

}
