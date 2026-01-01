package nightkosh.withered_lands.renderer.water;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import nightkosh.withered_lands.core.WLTextures;
import nightkosh.withered_lands.entity.water.AWaterWalkingMob;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class DrownedSailorRenderer extends HumanoidMobRenderer<AWaterWalkingMob, ZombieRenderState, HumanoidModel<ZombieRenderState>> {

    public DrownedSailorRenderer(EntityRendererProvider.Context context) {
        super(context, new HumanoidModel(context.bakeLayer(ModelLayers.ZOMBIE)), 0.5F);
    }

    @Override
    public ZombieRenderState createRenderState() {
        return new ZombieRenderState();
    }

    protected void setupRotations(ZombieRenderState state, @Nonnull PoseStack poseStack, float bodyRot, float scale) {
        super.setupRotations(state, poseStack, bodyRot, scale);
        float f = state.swimAmount;
        if (f > 0) {
            float f1 = -10 - state.xRot;
            float f2 = Mth.lerp(f, 0, f1);
            poseStack.rotateAround(Axis.XP.rotationDegrees(f2), 0, state.boundingBoxHeight / 2 / scale, 0);
        }
    }

    @Nonnull
    @Override
    public Identifier getTextureLocation(ZombieRenderState state) {
        return WLTextures.DROWNED_SAILOR;
    }

}
