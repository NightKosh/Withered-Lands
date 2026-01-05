package nightkosh.withered_lands.renderer.slime;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import nightkosh.withered_lands.core.WLTextures;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class VerdantSlimeRenderer extends ASlimeRenderer {

    public VerdantSlimeRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Nonnull
    @Override
    public Identifier getTextureLocation(WLSlimeRenderState state) {
        return WLTextures.VERDANT_SLIME_INNER;
    }

    @Override
    public Identifier getOuterTextureLocation(WLSlimeRenderState state) {
        return WLTextures.VERDANT_SLIME_OUTER;
    }

}
