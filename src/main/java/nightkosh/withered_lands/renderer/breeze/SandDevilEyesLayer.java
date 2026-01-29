package nightkosh.withered_lands.renderer.breeze;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.state.BreezeRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import nightkosh.withered_lands.core.WLTextures;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SandDevilEyesLayer extends ABreezeEyesLayer {

    private static final RenderType SAND_DEVIL_EYES = RenderTypes.breezeEyes(WLTextures.SAND_DEVIL_EYES);

    public SandDevilEyesLayer(RenderLayerParent<BreezeRenderState, EntityModel<BreezeRenderState>> renderer, EntityModelSet modelSet) {
        super(renderer, modelSet);
    }

    protected RenderType getRenderType() {
        return SAND_DEVIL_EYES;
    }

}
