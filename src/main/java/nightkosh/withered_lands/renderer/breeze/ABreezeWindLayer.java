package nightkosh.withered_lands.renderer.breeze;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.breeze.BreezeModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.BreezeRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class ABreezeWindLayer extends RenderLayer<BreezeRenderState, BreezeModel> {
    private final BreezeModel model;

    public ABreezeWindLayer(RenderLayerParent<BreezeRenderState, BreezeModel> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.model = new BreezeModel(modelSet.bakeLayer(ModelLayers.BREEZE_WIND));
    }

    @Override
    public void submit(@Nonnull PoseStack poseStack, SubmitNodeCollector nodeCollector,
                       int packedLight, BreezeRenderState state, float yRot, float xRot) {
        var rendertype = RenderTypes.breezeWind(getTextureLocation(), this.xOffset(state.ageInTicks) % 1, 0);
        nodeCollector.order(1)
                .submitModel(this.model, state, poseStack, rendertype, packedLight,
                        OverlayTexture.NO_OVERLAY, -1, null,
                        state.outlineColor, null);
    }

    private float xOffset(float tickCount) {
        return tickCount * 0.02F;
    }

    @Nonnull
    protected abstract Identifier getTextureLocation();

}
