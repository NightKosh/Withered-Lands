package nightkosh.withered_lands.renderer.slime;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import nightkosh.withered_lands.core.WLTextures;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class JungleSlimeRenderer extends ASlimeRenderer {

    public JungleSlimeRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    public Identifier getTextureLocation(WLSlimeRenderState state) {
        return WLTextures.JUNGLE_SLIME;
    }

}
