package nightkosh.withered_lands.renderer.water.fish;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.animal.fish.CodModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import nightkosh.withered_lands.core.WLTextures;
import nightkosh.withered_lands.entity.water.fish.Minnow;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class MinnowRenderer extends MobRenderer<Minnow, LivingEntityRenderState, CodModel> {

    public MinnowRenderer(EntityRendererProvider.Context context) {
        super(context, new CodModel(context.bakeLayer(ModelLayers.COD)), 0.3F);
    }

    @Nonnull
    @Override
    public Identifier getTextureLocation(LivingEntityRenderState state) {
        return WLTextures.MINNOW;
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    protected void setupRotations(LivingEntityRenderState state, @Nonnull PoseStack poseStack, float bodyRot, float scale) {
        super.setupRotations(state, poseStack, bodyRot, scale);
        float f = 4.3F * Mth.sin(0.6F * state.ageInTicks);
        poseStack.mulPose(Axis.YP.rotationDegrees(f));
        if (!state.isInWater) {
            poseStack.translate(0.1F, 0.1F, -0.1F);
            poseStack.mulPose(Axis.ZP.rotationDegrees(90));
        }
    }

}
