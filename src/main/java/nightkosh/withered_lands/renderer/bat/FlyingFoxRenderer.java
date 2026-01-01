package nightkosh.withered_lands.renderer.bat;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.BatRenderState;
import net.minecraft.resources.Identifier;

import javax.annotation.Nonnull;

import static nightkosh.withered_lands.core.WLTextures.FLYING_FOX;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class FlyingFoxRenderer extends HostileBatRender {


    public FlyingFoxRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void scale(BatRenderState state, PoseStack poseStack) {
        poseStack.scale(2, 2, 2);
    }

    @Override
    @Nonnull
    public Identifier getTextureLocation(@Nonnull BatRenderState batRenderState) {
        return FLYING_FOX;
    }

}
