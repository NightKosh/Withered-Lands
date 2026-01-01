package nightkosh.withered_lands.renderer.water;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwingAnimationType;
import nightkosh.withered_lands.core.WLTextures;
import nightkosh.withered_lands.entity.water.AWaterWalkingMob;
import nightkosh.withered_lands.renderer.model.PhantomDiverModel;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PhantomDiverRenderer extends HumanoidMobRenderer<AWaterWalkingMob, ZombieRenderState, PhantomDiverModel> {

    public PhantomDiverRenderer(EntityRendererProvider.Context context) {
        super(context, new PhantomDiverModel(context.bakeLayer(PhantomDiverModel.LAYER)), 0.5F);

        this.addLayer(new PhantomDiverFaceLayer(this));
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
    protected HumanoidModel.ArmPose getArmPose(AWaterWalkingMob mob, @Nonnull HumanoidArm arm) {
        var itemstack = mob.getItemHeldByArm(arm);
        if (mob.getMainArm() == arm && mob.isAggressive() && itemstack.is(Items.TRIDENT)) {
            return HumanoidModel.ArmPose.THROW_TRIDENT;
        } else {
            var swinganimation = mob.getItemHeldByArm(arm.getOpposite()).get(DataComponents.SWING_ANIMATION);
            return swinganimation != null && swinganimation.type() == SwingAnimationType.STAB ?
                    HumanoidModel.ArmPose.SPEAR :
                    super.getArmPose(mob, arm);
        }
    }

    @Nonnull
    @Override
    public Identifier getTextureLocation(ZombieRenderState state) {
        return WLTextures.PHANTOM_DIVER;
    }

}
