package nightkosh.withered_lands.renderer.breeze;

import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.monster.breeze.BreezeModel;
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
public class DirgeGaleEyesLayer extends ABreezeEyesLayer {

    private static final RenderType DIRGE_GALE_EYES = RenderTypes.breezeEyes(WLTextures.DIRGE_GALE_EYES);

    public DirgeGaleEyesLayer(RenderLayerParent<BreezeRenderState, BreezeModel> renderer, EntityModelSet modelSet) {
        super(renderer, modelSet);
    }

    protected RenderType getRenderType() {
        return DIRGE_GALE_EYES;
    }

}
