package nightkosh.withered_lands.renderer.slime;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.slime.SlimeModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.entity.slime.ASlime;
import org.jspecify.annotations.Nullable;

import javax.annotation.Nonnull;

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
        if (!WLConfigs.SLIME_ITEMS_CUSTOM_RENDERER.get()) {
            this.addLayer(new ItemInSlimeLayer(this));
        }
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

    @Override
    public void submit(WLSlimeRenderState state, @Nonnull PoseStack poseStack,
                       @Nonnull SubmitNodeCollector collector, @Nonnull CameraRenderState cameraState) {
        if (WLConfigs.SLIME_ITEMS_CUSTOM_RENDERER.get() && !state.headItem.isEmpty()) {
            var sprite = state.headItem.pickParticleIcon(state.slime.getRandom());
            if (sprite != null) {
                poseStack.pushPose();

                float s = 0.7F;
                poseStack.scale(s, s, s);
                poseStack.translate(0, 0.30F * state.size, 0.0F);
                poseStack.mulPose(Axis.YP.rotationDegrees(-state.slime.getYRot()));

                collector.order(-1)
                        .submitCustomGeometry(
                                poseStack,
                                RenderTypes.entityCutoutNoCull(sprite.atlasLocation()),
                                (pose, vc) -> renderSpriteQuad(pose, vc, sprite, state.lightCoords, OverlayTexture.NO_OVERLAY));

                poseStack.popPose();
            }
        }

        super.submit(state, poseStack, collector, cameraState);
    }

    private static void renderSpriteQuad(PoseStack.Pose pose, VertexConsumer vc, TextureAtlasSprite sprite, int packedLight, int overlay) {
        var mat = pose.pose();

        float u0 = sprite.getU0();
        float u1 = sprite.getU1();
        float v0 = sprite.getV0();
        float v1 = sprite.getV1();

        float x0 = -0.5F, x1 = 0.5F;
        float y0 = -0.5F, y1 = 0.5F;
        float z = 0;

        float nx = 0, ny = 0, nz = 1;
        vc.addVertex(mat, x0, y0, z)
                .setColor(255, 255, 255, 255)
                .setUv(u0, v1)
                .setOverlay(overlay)
                .setLight(packedLight)
                .setNormal(pose, nx, ny, nz);
        vc.addVertex(mat, x1, y0, z)
                .setColor(255, 255, 255, 255)
                .setUv(u1, v1)
                .setOverlay(overlay)
                .setLight(packedLight)
                .setNormal(pose, nx, ny, nz);
        vc.addVertex(mat, x1, y1, z)
                .setColor(255, 255, 255, 255)
                .setUv(u1, v0)
                .setOverlay(overlay)
                .setLight(packedLight)
                .setNormal(pose, nx, ny, nz);
        vc.addVertex(mat, x0, y1, z)
                .setColor(255, 255, 255, 255)
                .setUv(u0, v0)
                .setOverlay(overlay)
                .setLight(packedLight)
                .setNormal(pose, nx, ny, nz);
    }

    public abstract Identifier getOuterTextureLocation(WLSlimeRenderState state);

}
