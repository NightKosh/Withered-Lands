package nightkosh.withered_lands.renderer.breeze;

import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
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
public class ThunderstormRenderer extends ABreezeRenderer {

    public ThunderstormRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void addWindLayer(ABreezeRenderer renderer, EntityModelSet modelSet) {
        this.addLayer(new ThunderstormWindLayer(this, modelSet));
    }

    @Override
    protected void addEyeLayer(ABreezeRenderer renderer, EntityModelSet modelSet) {
        this.addLayer(new ThunderstormEyesLayer(this, modelSet));
    }

    @Nonnull
    @Override
    public Identifier getTextureLocation(BreezeRenderState state) {
        return WLTextures.THUNDERSTORM;
    }

}
