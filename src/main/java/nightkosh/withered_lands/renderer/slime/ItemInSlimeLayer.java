package nightkosh.withered_lands.renderer.slime;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.monster.slime.SlimeModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemInSlimeLayer extends RenderLayer<WLSlimeRenderState, SlimeModel> {

    public ItemInSlimeLayer(RenderLayerParent<WLSlimeRenderState, SlimeModel> parent) {
        super(parent);
    }

    @Override
    public void submit(@Nonnull PoseStack poseStack, @Nonnull SubmitNodeCollector nodeCollector,
                       int packedLight, WLSlimeRenderState state, float yRot, float xRot) {
        var stackRenderState = state.headItem;

        if (!stackRenderState.isEmpty()) {
            poseStack.pushPose();
            poseStack.mulPose(Axis.ZP.rotationDegrees(180));

            if (state.size == 2) {
                float s = 0.35F;
                poseStack.translate(0, -1.2F, -0.1);
                poseStack.scale(s, s, s);
            } else {
                float s = 0.17F;
                poseStack.translate(0, -1.1F, -0.1);
                poseStack.scale(s, s, s);
            }

            stackRenderState.submit(poseStack, nodeCollector, packedLight, OverlayTexture.NO_OVERLAY, state.outlineColor);
            poseStack.popPose();
        }
    }

}
