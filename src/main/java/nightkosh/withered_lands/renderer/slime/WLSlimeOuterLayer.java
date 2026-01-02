package nightkosh.withered_lands.renderer.slime;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.slime.SlimeModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;

import javax.annotation.Nonnull;

public class WLSlimeOuterLayer extends RenderLayer<WLSlimeRenderState, SlimeModel> {

    private final SlimeModel model;

    private final ASlimeRenderer renderer;

    public WLSlimeOuterLayer(ASlimeRenderer renderer, EntityModelSet modelSet) {
        super(renderer);
        this.model = new SlimeModel(modelSet.bakeLayer(ModelLayers.SLIME_OUTER));
        this.renderer = renderer;
    }

    @Override
    public void submit(@Nonnull PoseStack poseStack, @Nonnull SubmitNodeCollector nodeCollector,
                       int packedLight, WLSlimeRenderState renderState, float yRot, float xRot) {
        boolean flag = renderState.appearsGlowing() && renderState.isInvisible;
        if (!renderState.isInvisible || flag) {
            int i = LivingEntityRenderer.getOverlayCoords(renderState, 0.0F);
            if (flag) {
                nodeCollector.order(1).submitModel(
                        this.model, renderState, poseStack,
                        RenderTypes.outline(renderer.getTextureLocation(renderState)),
                        packedLight, i, -1, null,
                        renderState.outlineColor, null
                );
            } else {
                nodeCollector.order(1).submitModel(
                        this.model, renderState, poseStack,
                        RenderTypes.entityTranslucent(renderer.getTextureLocation(renderState)),
                        packedLight, i, -1, null,
                        renderState.outlineColor, null
                );
            }
        }
    }

}
