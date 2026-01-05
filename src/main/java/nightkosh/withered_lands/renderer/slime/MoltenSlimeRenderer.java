package nightkosh.withered_lands.renderer.slime;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import nightkosh.withered_lands.core.WLTextures;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class MoltenSlimeRenderer extends ASlimeRenderer {

    private static final RenderType MOLTEN_SLIME = RenderTypes.eyes(WLTextures.MOLTEN_SLIME_INNER);

    public MoltenSlimeRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Nonnull
    @Override
    public Identifier getTextureLocation(WLSlimeRenderState state) {
        return WLTextures.MOLTEN_SLIME_INNER;
    }

    @Override
    public Identifier getOuterTextureLocation(WLSlimeRenderState state) {
        return WLTextures.MOLTEN_SLIME_OUTER;
    }

}
