package nightkosh.withered_lands.renderer.water.jellyfish;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import nightkosh.withered_lands.entity.water.jellyfish.AJellyfish;
import nightkosh.withered_lands.renderer.model.jellyfish.AJellyfishModel;
import nightkosh.withered_lands.renderer.render_sate.JellyfishRenderState;
import org.jspecify.annotations.Nullable;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class AJellyfishRenderer<E extends AJellyfish, S extends JellyfishRenderState, M extends AJellyfishModel<S>> extends
        MobRenderer<AJellyfish, JellyfishRenderState, AJellyfishModel<JellyfishRenderState>> {

    public AJellyfishRenderer(EntityRendererProvider.Context context, AJellyfishModel model) {
        super(context, model, 0.3F);
    }

    @Override
    protected int getBlockLightLevel(AJellyfish entity, @Nonnull BlockPos pos) {
        return 15;
    }

    @Override
    public JellyfishRenderState createRenderState() {
        return new JellyfishRenderState();
    }

    @Override
    public void extractRenderState(AJellyfish jellyfish, JellyfishRenderState state, float partialTick) {
        super.extractRenderState(jellyfish, state, partialTick);
        state.moveLegsAnimation.copyFrom(jellyfish.moveLegsAnimation);
        state.inflateAnimation.copyFrom(jellyfish.inflateAnimation);
    }

    @Override
    protected @Nullable RenderType getRenderType(
            JellyfishRenderState state, boolean bodyVisible, boolean translucent, boolean glowing) {
        if (glowing || !bodyVisible) {
            return super.getRenderType(state, bodyVisible, translucent, glowing);
        } else {
            return RenderTypes.entityTranslucent(this.getTextureLocation(state));
        }
    }

    @Override
    protected void setupRotations(JellyfishRenderState state, @Nonnull PoseStack poseStack, float bodyRot, float scale) {
        super.setupRotations(state, poseStack, bodyRot, scale);

        float f = 4.3F;
        float f1 = 0.6F;

        if (!state.isInWater) {
            f = 5.59F;
            f1 = 1.02F;
        }

        float f2 = f * Mth.sin(f1 * state.ageInTicks);
        poseStack.mulPose(Axis.YP.rotationDegrees(f2));
        if (!state.isInWater) {
            poseStack.translate(0.2F, 0.1F, 0);
            poseStack.mulPose(Axis.ZP.rotationDegrees(90));
        }
    }

}
