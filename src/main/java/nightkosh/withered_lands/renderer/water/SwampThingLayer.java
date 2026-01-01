package nightkosh.withered_lands.renderer.water;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import nightkosh.withered_lands.core.WLTextures;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SwampThingLayer extends RenderLayer<ZombieRenderState, HumanoidModel<ZombieRenderState>> {

    private final HumanoidModel model;

    public SwampThingLayer(RenderLayerParent<ZombieRenderState, HumanoidModel<ZombieRenderState>> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.model = new HumanoidModel(modelSet.bakeLayer(ModelLayers.ZOMBIE));

    }

    public void submit(@Nonnull PoseStack poseStack, @Nonnull SubmitNodeCollector nodeCollector,
                       int packedLight, ZombieRenderState renderState, float yRot, float xRot) {
        float s = 1.03F;
        poseStack.pushPose();
        poseStack.scale(s, s, s);

        coloredCutoutModelCopyLayerRender(
                this.model, WLTextures.SWAMP_THING_OVERLAY,
                poseStack, nodeCollector, packedLight, renderState, -1, 1);

        poseStack.popPose();
    }

}
