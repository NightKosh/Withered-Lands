package nightkosh.withered_lands.renderer.breeze;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.state.BreezeRenderState;
import net.minecraft.resources.Identifier;
import nightkosh.withered_lands.core.WLTextures;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class DirgeGaleWindLayer extends ABreezeWindLayer {

    public DirgeGaleWindLayer(RenderLayerParent<BreezeRenderState, EntityModel<BreezeRenderState>> renderer, EntityModelSet modelSet) {
        super(renderer, modelSet);
    }

    @Override
    @Nonnull
    protected Identifier getTextureLocation() {
        return WLTextures.DIRGE_GALE_WIND;
    }

}
