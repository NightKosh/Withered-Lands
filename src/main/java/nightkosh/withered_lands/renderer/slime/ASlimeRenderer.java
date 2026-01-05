package nightkosh.withered_lands.renderer.slime;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.slime.SlimeModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import nightkosh.withered_lands.entity.slime.ASlime;
import org.jspecify.annotations.Nullable;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class ASlimeRenderer extends MobRenderer<ASlime, WLSlimeRenderState, SlimeModel> {

    public ASlimeRenderer(EntityRendererProvider.Context context) {
        super(context, new SlimeModel(context.bakeLayer(ModelLayers.SLIME)), 0.25F);
        this.addLayer(new WLSlimeOuterLayer(this, context.getModelSet()));
    }

    @Override
    protected float getShadowRadius(WLSlimeRenderState state) {
        return state.size * 0.25F;
    }

    @Override
    protected void scale(WLSlimeRenderState state, PoseStack poseStack) {
        poseStack.scale(0.999F, 0.999F, 0.999F);
        poseStack.translate(0, 0.001F, 0);
        float f2 = state.squish / (state.size * 0.5F + 1);
        float f3 = 1 / (f2 + 1);
        poseStack.scale(f3 * state.size, 1 / f3 * state.size, f3 * state.size);
    }

    @Override
    public WLSlimeRenderState createRenderState() {
        return new WLSlimeRenderState();
    }

    @Override
    public void extractRenderState(ASlime slime, WLSlimeRenderState state, float partialTick) {
        super.extractRenderState(slime, state, partialTick);
        state.slime = slime;
        state.squish = Mth.lerp(partialTick, slime.oSquish, slime.squish);
        state.size = slime.getSize();
    }

    @Override
    protected @Nullable RenderType getRenderType(
            WLSlimeRenderState state, boolean bodyVisible, boolean translucent, boolean glowing) {
        if (glowing || !bodyVisible) {
            return super.getRenderType(state, bodyVisible, translucent, glowing);
        } else {
            return RenderTypes.entityTranslucent(this.getTextureLocation(state));
        }
    }

    public abstract Identifier getOuterTextureLocation(WLSlimeRenderState state);

}
