package nightkosh.withered_lands.renderer.swamp;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.animal.frog.FrogModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.FrogRenderState;
import net.minecraft.resources.Identifier;
import nightkosh.withered_lands.core.WLTextures;
import nightkosh.withered_lands.entity.swamp.GiantFrog;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GiantFrogRenderer extends MobRenderer<GiantFrog, FrogRenderState, FrogModel> {

    public GiantFrogRenderer(EntityRendererProvider.Context context) {
        super(context, new FrogModel(context.bakeLayer(ModelLayers.FROG)), 0.9F);
    }

    @Nonnull
    @Override
    public Identifier getTextureLocation(FrogRenderState state) {
        return WLTextures.GIANT_FROG;
    }

    @Override
    protected void scale(FrogRenderState state, PoseStack poseStack) {
        poseStack.scale(5, 5, 5);
    }

    @Override
    public FrogRenderState createRenderState() {
        return new FrogRenderState();
    }

    @Override
    public void extractRenderState(GiantFrog frog, FrogRenderState state, float partialTicks) {
        super.extractRenderState(frog, state, partialTicks);
        state.isSwimming = frog.isInWater();
        state.jumpAnimationState.copyFrom(frog.jumpAnimationState);
        state.croakAnimationState.copyFrom(frog.croakAnimationState);
        state.tongueAnimationState.copyFrom(frog.tongueAnimationState);
        state.swimIdleAnimationState.copyFrom(frog.swimIdleAnimationState);
    }

}
