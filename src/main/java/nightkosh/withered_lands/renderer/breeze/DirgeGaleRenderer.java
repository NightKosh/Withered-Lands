package nightkosh.withered_lands.renderer.breeze;

import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.BreezeRenderState;
import net.minecraft.resources.Identifier;
import nightkosh.withered_lands.core.WLTextures;
import nightkosh.withered_lands.renderer.model.DirgeGaleModel;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class DirgeGaleRenderer extends ABreezeRenderer {

    public DirgeGaleRenderer(EntityRendererProvider.Context context) {
        super(context, new DirgeGaleModel(context.bakeLayer(DirgeGaleModel.LAYER)), 0.5F);
        addWindLayer(this, context.getModelSet());
    }

    @Override
    protected void addWindLayer(ABreezeRenderer renderer, EntityModelSet modelSet) {
        this.addLayer(new DirgeGaleWindLayer(this, modelSet));
    }

    @Override
    protected void addEyeLayer(ABreezeRenderer renderer, EntityModelSet modelSet) {
    }

    @Nonnull
    @Override
    public Identifier getTextureLocation(BreezeRenderState state) {
        return WLTextures.DIRGE_GALE;
    }

}
