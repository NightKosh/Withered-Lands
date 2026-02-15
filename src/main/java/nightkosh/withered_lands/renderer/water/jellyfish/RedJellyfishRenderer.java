package nightkosh.withered_lands.renderer.water.jellyfish;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import nightkosh.withered_lands.core.WLTextures;
import nightkosh.withered_lands.entity.water.jellyfish.AJellyfish;
import nightkosh.withered_lands.renderer.model.jellyfish.JellyfishRedModel;
import nightkosh.withered_lands.renderer.render_sate.JellyfishRenderState;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class RedJellyfishRenderer extends AJellyfishRenderer<AJellyfish, JellyfishRenderState, JellyfishRedModel<JellyfishRenderState>> {

    public RedJellyfishRenderer(EntityRendererProvider.Context context) {
        super(context, new JellyfishRedModel(context.bakeLayer(JellyfishRedModel.LAYER)));
    }

    @Nonnull
    @Override
    public Identifier getTextureLocation(JellyfishRenderState state) {
        return WLTextures.JELLYFISH_RED;
    }

}
