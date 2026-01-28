package nightkosh.withered_lands.renderer.bat;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.BatRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import nightkosh.withered_lands.core.WLTextures;

import javax.annotation.Nonnull;

import static nightkosh.withered_lands.core.WLTextures.FLYING_FOX;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class FlyingFoxRenderer extends HostileBatRender {

    private static final RenderType BAT_EYES = RenderTypes.eyes(WLTextures.FLYING_FOX_EYES);

    public FlyingFoxRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void scale(BatRenderState state, PoseStack poseStack) {
        poseStack.scale(3, 3, 3);
    }

    @Nonnull
    @Override
    public Identifier getTextureLocation(@Nonnull BatRenderState batRenderState) {
        return FLYING_FOX;
    }

    @Override
    protected BatEyesLayer getEyesLayer() {
        return new BatEyesLayer(this, BAT_EYES);
    }

}
