package nightkosh.withered_lands.renderer.water.fish;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.animal.fish.SalmonModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.SalmonRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import nightkosh.withered_lands.core.WLTextures;
import nightkosh.withered_lands.entity.water.fish.Pike;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PikeRenderer extends MobRenderer<Pike, SalmonRenderState, SalmonModel> {

    public PikeRenderer(EntityRendererProvider.Context context) {
        super(context, new SalmonModel(context.bakeLayer(ModelLayers.SALMON_LARGE)), 0.15F);
    }

    @Nonnull
    @Override
    public Identifier getTextureLocation(SalmonRenderState state) {
        return WLTextures.PIKE;
    }

    @Override
    public SalmonRenderState createRenderState() {
        return new SalmonRenderState();
    }

    @Override
    protected void setupRotations(SalmonRenderState salmonRenderState, @Nonnull PoseStack poseStack, float bodyRot, float scale) {
        super.setupRotations(salmonRenderState, poseStack, bodyRot, scale);

        float f = 4.3F;
        float f1 = 0.6F;

        if (!salmonRenderState.isInWater) {
            f = 5.59F;
            f1 = 1.02F;
        }

        float f2 = f * Mth.sin(f1 * salmonRenderState.ageInTicks);
        poseStack.mulPose(Axis.YP.rotationDegrees(f2));
        if (!salmonRenderState.isInWater) {
            poseStack.translate(0.2F, 0.1F, 0);
            poseStack.mulPose(Axis.ZP.rotationDegrees(90));
        }
    }

}
