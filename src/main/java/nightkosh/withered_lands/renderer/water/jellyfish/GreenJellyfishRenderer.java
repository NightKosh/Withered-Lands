package nightkosh.withered_lands.renderer.water.jellyfish;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import nightkosh.withered_lands.core.WLTextures;
import nightkosh.withered_lands.entity.water.jellyfish.AJellyfish;
import nightkosh.withered_lands.renderer.model.jellyfish.JellyfishGreenModel;
import nightkosh.withered_lands.renderer.render_sate.JellyfishRenderState;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GreenJellyfishRenderer extends AJellyfishRenderer<AJellyfish, JellyfishRenderState, JellyfishGreenModel<JellyfishRenderState>> {

    public GreenJellyfishRenderer(EntityRendererProvider.Context context) {
        super(context, new JellyfishGreenModel(context.bakeLayer(JellyfishGreenModel.LAYER)));
    }

    @Nonnull
    @Override
    public Identifier getTextureLocation(JellyfishRenderState state) {
        return WLTextures.JELLYFISH_GREEN;
    }

}
