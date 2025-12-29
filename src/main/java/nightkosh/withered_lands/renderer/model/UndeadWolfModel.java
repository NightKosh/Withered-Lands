package nightkosh.withered_lands.renderer.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import nightkosh.withered_lands.renderer.render_sate.UndeadWolfRenderState;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UndeadWolfModel<T extends UndeadWolfRenderState> extends EntityModel<T> {

    private static final float H_PI = 1.5707963267948966192313216916398F;

    private final ModelPart head;
    private final ModelPart realHead;
    private final ModelPart body;
    private final ModelPart rightHindLeg;
    private final ModelPart leftHindLeg;
    private final ModelPart rightFrontLeg;
    private final ModelPart leftFrontLeg;
    private final ModelPart tail;
    private final ModelPart realTail;
    private final ModelPart upperBody;

    public UndeadWolfModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild("head");
        this.realHead = this.head.getChild("real_head");
        this.body = modelPart.getChild("body");
        this.upperBody = modelPart.getChild("upper_body");
        this.rightHindLeg = modelPart.getChild("right_hind_leg");
        this.leftHindLeg = modelPart.getChild("left_hind_leg");
        this.rightFrontLeg = modelPart.getChild("right_front_leg");
        this.leftFrontLeg = modelPart.getChild("left_front_leg");
        this.tail = modelPart.getChild("tail");
        this.realTail = this.tail.getChild("real_tail");
    }

    public static MeshDefinition createMeshDefinition(CubeDeformation deformation) {
        var meshdefinition = new MeshDefinition();
        var partdefinition = meshdefinition.getRoot();
        var partDefinition = partdefinition.addOrReplaceChild(
                "head",
                CubeListBuilder.create(),
                PartPose.offset(-1, 13.5F, -7));
        partDefinition.addOrReplaceChild(
                "real_head",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-2, -3, -2, 6, 6, 4, deformation)
                        .texOffs(16, 14)
                        .addBox(-2, -5, 0, 2, 2, 1, deformation)
                        .texOffs(16, 14)
                        .addBox(2, -5, 0, 2, 2, 1, deformation)
                        .texOffs(0, 10)
                        .addBox(-0.5F, -0.001F, -5, 3, 3, 4, deformation),
                PartPose.ZERO
        );
        partdefinition.addOrReplaceChild(
                "body",
                CubeListBuilder.create()
                        .texOffs(18, 14)
                        .addBox(-3, -2, -3, 6, 9, 6, deformation),
                PartPose.offsetAndRotation(0, 14, 2, (float) (Math.PI / 2), 0, 0)
        );
        partdefinition.addOrReplaceChild(
                "upper_body",
                CubeListBuilder.create()
                        .texOffs(21, 0)
                        .addBox(-3, -3, -3, 8, 6, 7, deformation),
                PartPose.offsetAndRotation(-1, 14, -3, (float) (Math.PI / 2), 0, 0)
        );
        var cubelistbuilder = CubeListBuilder.create()
                .texOffs(0, 18)
                .addBox(0, 0, -1, 2, 8, 2, deformation);
        var cubelistbuilder1 = CubeListBuilder.create().mirror()
                .texOffs(0, 18)
                .addBox(0, 0, -1, 2, 8, 2, deformation);
        partdefinition.addOrReplaceChild("right_hind_leg", cubelistbuilder1, PartPose.offset(-2.5F, 16, 7));
        partdefinition.addOrReplaceChild("left_hind_leg", cubelistbuilder, PartPose.offset(0.5F, 16, 7));
        partdefinition.addOrReplaceChild("right_front_leg", cubelistbuilder1, PartPose.offset(-2.5F, 16, -4));
        partdefinition.addOrReplaceChild("left_front_leg", cubelistbuilder, PartPose.offset(0.5F, 16, -4));
        PartDefinition partdefinition2 = partdefinition.addOrReplaceChild(
                "tail", CubeListBuilder.create(), PartPose.offsetAndRotation(-1, 12, 8, (float) (Math.PI / 5), 0, 0)
        );
        partdefinition2.addOrReplaceChild(
                "real_tail",
                CubeListBuilder.create()
                        .texOffs(9, 18)
                        .addBox(0, 0, -1, 2, 8, 2, deformation),
                PartPose.ZERO
        );
        return meshdefinition;
    }

    public void setupAnim(T state) {
        super.setupAnim(state);
        float f = state.walkAnimationPos;
        float f1 = state.walkAnimationSpeed;
        this.tail.yRot = 0;

        this.rightHindLeg.xRot = Mth.cos(f * 0.6662F) * 1.4F * f1;
        this.leftHindLeg.xRot = Mth.cos(f * 0.6662F + Math.PI) * 1.4F * f1;
        this.rightFrontLeg.xRot = Mth.cos(f * 0.6662F + Math.PI) * 1.4F * f1;
        this.leftFrontLeg.xRot = Mth.cos(f * 0.6662F) * 1.4F * f1;

        this.realHead.zRot = state.headRollAngle + state.getBodyRollAngle(0);
        this.upperBody.zRot = state.getBodyRollAngle(-0.08F);
        this.body.zRot = state.getBodyRollAngle(-0.16F);
        this.realTail.zRot = state.getBodyRollAngle(-0.2F);
        this.head.xRot = state.xRot * H_PI;
        this.head.yRot = state.yRot * H_PI;
        this.tail.xRot = state.tailAngle;
    }

}
