package nightkosh.withered_lands.renderer.bat;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.BatRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import nightkosh.withered_lands.core.WLTextures;

import javax.annotation.Nonnull;

import static nightkosh.withered_lands.core.WLTextures.CAVE_BAT;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CaveBatRenderer extends HostileBatRender {

    private static final RenderType BAT_EYES = RenderTypes.eyes(WLTextures.CAVE_BAT_EYES);

    public CaveBatRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Nonnull
    @Override
    public Identifier getTextureLocation(@Nonnull BatRenderState batRenderState) {
        return CAVE_BAT;
    }

    @Override
    protected BatEyesLayer getEyesLayer() {
        return new BatEyesLayer(this, BAT_EYES);
    }

}
