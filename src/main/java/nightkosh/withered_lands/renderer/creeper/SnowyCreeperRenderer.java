package nightkosh.withered_lands.renderer.creeper;

import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.Identifier;
import nightkosh.withered_lands.core.WLTextures;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SnowyCreeperRenderer extends CreeperRenderer {

    public SnowyCreeperRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Nonnull
    @Override
    public Identifier getTextureLocation(@Nonnull CreeperRenderState renderState) {
        return WLTextures.SNOWY_CREEPER;
    }

}
