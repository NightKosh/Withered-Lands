package nightkosh.withered_lands.renderer.bat;

import net.minecraft.client.model.ambient.BatModel;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.state.BatRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BatEyesLayer extends EyesLayer<BatRenderState, BatModel> {

    private final RenderType batEyes;

    public BatEyesLayer(RenderLayerParent<BatRenderState, BatModel> renderer, RenderType eyes) {
        super(renderer);
        this.batEyes = eyes;
    }

    @Nonnull
    @Override
    public RenderType renderType() {
        return batEyes;
    }

}
