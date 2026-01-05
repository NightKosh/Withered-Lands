package nightkosh.withered_lands.renderer.water.fish;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.animal.fish.TropicalFishLargeModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.TropicalFishRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import nightkosh.withered_lands.core.WLTextures;
import nightkosh.withered_lands.entity.water.fish.Piranha;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PiranhaRenderer extends MobRenderer<Piranha, TropicalFishRenderState, TropicalFishLargeModel> {

    public PiranhaRenderer(EntityRendererProvider.Context context) {
        super(context, new TropicalFishLargeModel(context.bakeLayer(ModelLayers.TROPICAL_FISH_LARGE)), 0.15F);
    }

    @Nonnull
    @Override
    public Identifier getTextureLocation(TropicalFishRenderState p_468510_) {
        return WLTextures.PIRANHA;
    }

    @Override
    public TropicalFishRenderState createRenderState() {
        return new TropicalFishRenderState();
    }

    @Override
    protected void setupRotations(TropicalFishRenderState state, @Nonnull PoseStack poseStack, float bodyRot, float scale) {
        super.setupRotations(state, poseStack, bodyRot, scale);
        float f = 4.3F * Mth.sin(0.6F * state.ageInTicks);
        poseStack.mulPose(Axis.YP.rotationDegrees(f));
        if (!state.isInWater) {
            poseStack.translate(0.2F, 0.1F, 0);
            poseStack.mulPose(Axis.ZP.rotationDegrees(90));
        }
    }

}
