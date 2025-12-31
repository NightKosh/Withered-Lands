package nightkosh.withered_lands.renderer.model;

import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.util.Mth;
import nightkosh.withered_lands.core.ModInfo;

import static net.minecraft.resources.Identifier.fromNamespaceAndPath;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PhantomDiverModel extends HumanoidModel<ZombieRenderState> {

    public static final ModelLayerLocation LAYER = new ModelLayerLocation(
            fromNamespaceAndPath(ModInfo.ID, "phantom_diver"),
            "main");

    public final ModelPart helmetBase;
    public final ModelPart rim;

    public PhantomDiverModel(ModelPart root) {
        super(root);

        this.helmetBase = this.head.getChild("helmet_base");
        this.rim = this.head.getChild("rim");
    }

    public static LayerDefinition createBodyLayer() {
        var mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0);
        var root = mesh.getRoot();
        var head = root.getChild("head");

        head.addOrReplaceChild(
                "helmet_base",
                CubeListBuilder.create()
                        .texOffs(0, 32)
                        .addBox(-4.5F, -8.5F, -4.5F, 9, 9, 9),
                PartPose.ZERO);

        head.addOrReplaceChild(
                "rim",
                CubeListBuilder.create()
                        .texOffs(38, 6)
                        .addBox(-4, -8, -6, 8, 8, 2),
                PartPose.ZERO
        );

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(ZombieRenderState state) {
        super.setupAnim(state);

        AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, state.isAggressive, state);

        if (state.leftArmPose == HumanoidModel.ArmPose.THROW_TRIDENT) {
            this.leftArm.xRot = this.leftArm.xRot * 0.5F - (float) Math.PI;
            this.leftArm.yRot = 0;
        }

        if (state.rightArmPose == HumanoidModel.ArmPose.THROW_TRIDENT) {
            this.rightArm.xRot = this.rightArm.xRot * 0.5F - (float) Math.PI;
            this.rightArm.yRot = 0;
        }

        float f = state.swimAmount;
        if (f > 0) {
            this.rightArm.xRot = Mth.rotLerpRad(f, this.rightArm.xRot, (float) (-Math.PI * 4 / 5)) + f * 0.35F * Mth.sin(0.1F * state.ageInTicks);
            this.leftArm.xRot = Mth.rotLerpRad(f, this.leftArm.xRot, (float) (-Math.PI * 4 / 5)) - f * 0.35F * Mth.sin(0.1F * state.ageInTicks);
            this.rightArm.zRot = Mth.rotLerpRad(f, this.rightArm.zRot, -0.15F);
            this.leftArm.zRot = Mth.rotLerpRad(f, this.leftArm.zRot, 0.15F);
            this.leftLeg.xRot = this.leftLeg.xRot - f * 0.55F * Mth.sin(0.1F * state.ageInTicks);
            this.rightLeg.xRot = this.rightLeg.xRot + f * 0.55F * Mth.sin(0.1F * state.ageInTicks);
            this.head.xRot = 0;
        }
    }

}
