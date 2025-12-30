package nightkosh.withered_lands.renderer.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.state.FelineRenderState;
import net.minecraft.util.Mth;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UndeadCatModel<T extends FelineRenderState> extends EntityModel<T> {

    protected final ModelPart leftHindLeg;
    protected final ModelPart rightHindLeg;
    protected final ModelPart leftFrontLeg;
    protected final ModelPart rightFrontLeg;
    protected final ModelPart tail1;
    protected final ModelPart tail2;
    protected final ModelPart head;
    protected final ModelPart body;

    public UndeadCatModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild("head");
        this.body = modelPart.getChild("body");
        this.tail1 = modelPart.getChild("tail1");
        this.tail2 = modelPart.getChild("tail2");
        this.leftHindLeg = modelPart.getChild("left_hind_leg");
        this.rightHindLeg = modelPart.getChild("right_hind_leg");
        this.leftFrontLeg = modelPart.getChild("left_front_leg");
        this.rightFrontLeg = modelPart.getChild("right_front_leg");
    }

    public static MeshDefinition createBodyMesh(CubeDeformation deformation) {
        var meshdefinition = new MeshDefinition();
        var partdefinition = meshdefinition.getRoot();
        var cubedeformation = new CubeDeformation(-0.02F);
        partdefinition.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                        .addBox("main", -2.5F, -2, -3, 5, 4, 5, deformation)
                        .addBox("nose", -1.5F, -0.001F, -4, 3, 2, 2, deformation, 0, 24)
                        .addBox("ear1", -2, -3, 0, 1, 1, 2, deformation, 0, 10)
                        .addBox("ear2", 1, -3, 0, 1, 1, 2, deformation, 6, 10),
                PartPose.offset(0, 15, -9)
        );
        partdefinition.addOrReplaceChild(
                "body",
                CubeListBuilder.create()
                        .texOffs(20, 0)
                        .addBox(-2, 3, -8, 4, 16, 6, deformation),
                PartPose.offsetAndRotation(0, 12, -10, (float) (Math.PI / 2), 0, 0)
        );
        partdefinition.addOrReplaceChild(
                "tail1",
                CubeListBuilder.create().
                        texOffs(0, 15)
                        .addBox(-0.5F, 0, 0, 1, 8, 1, deformation),
                PartPose.offsetAndRotation(0, 15, 8, 0.9F, 0, 0)
        );
        partdefinition.addOrReplaceChild(
                "tail2",
                CubeListBuilder.create()
                        .texOffs(4, 15)
                        .addBox(-0.5F, 0, 0, 1, 8, 1, cubedeformation),
                PartPose.offset(0, 20, 14)
        );
        var cubelistbuilder = CubeListBuilder.create()
                .texOffs(8, 13)
                .addBox(-1, 0, 1, 2, 6, 2, deformation);
        partdefinition.addOrReplaceChild("left_hind_leg", cubelistbuilder, PartPose.offset(1.1F, 18, 5));
        partdefinition.addOrReplaceChild("right_hind_leg", cubelistbuilder, PartPose.offset(-1.1F, 18, 5));
        var cubeListbuilder1 = CubeListBuilder.create()
                .texOffs(40, 0)
                .addBox(-1, 0, 0, 2, 10, 2, deformation);
        partdefinition.addOrReplaceChild("left_front_leg", cubeListbuilder1, PartPose.offset(1.2F, 14.1F, -5));
        partdefinition.addOrReplaceChild("right_front_leg", cubeListbuilder1, PartPose.offset(-1.2F, 14.1F, -5));
        return meshdefinition;
    }

    public void setupAnim(T renderState) {
        super.setupAnim(renderState);
        float f = renderState.ageScale;
        if (renderState.isCrouching) {
            this.body.y += f;
            this.head.y += 2 * f;
            this.tail1.y += f;
            this.tail2.y += -4 * f;
            this.tail2.z += 2 * f;
            this.tail1.xRot = (float) (Math.PI / 2);
            this.tail2.xRot = (float) (Math.PI / 2);
        } else if (renderState.isSprinting) {
            this.tail2.y = this.tail1.y;
            this.tail2.z += 2 * f;
            this.tail1.xRot = (float) (Math.PI / 2);
            this.tail2.xRot = (float) (Math.PI / 2);
        }

        this.head.xRot = renderState.xRot * (float) (Math.PI / 180.0);
        this.head.yRot = renderState.yRot * (float) (Math.PI / 180.0);
        if (!renderState.isSitting) {
            this.body.xRot = (float) (Math.PI / 2);
            float f1 = renderState.walkAnimationSpeed;
            float f2 = renderState.walkAnimationPos;
            if (renderState.isSprinting) {
                this.leftHindLeg.xRot = Mth.cos(f2 * 0.6662F) * f1;
                this.rightHindLeg.xRot = Mth.cos(f2 * 0.6662F + 0.3F) * f1;
                this.leftFrontLeg.xRot = Mth.cos(f2 * 0.6662F + (float) Math.PI + 0.3F) * f1;
                this.rightFrontLeg.xRot = Mth.cos(f2 * 0.6662F + (float) Math.PI) * f1;
                this.tail2.xRot = 1.7278761F + (float) (Math.PI / 10) * Mth.cos(f2) * f1;
            } else {
                this.leftHindLeg.xRot = Mth.cos(f2 * 0.6662F) * f1;
                this.rightHindLeg.xRot = Mth.cos(f2 * 0.6662F + (float) Math.PI) * f1;
                this.leftFrontLeg.xRot = Mth.cos(f2 * 0.6662F + (float) Math.PI) * f1;
                this.rightFrontLeg.xRot = Mth.cos(f2 * 0.6662F) * f1;
                if (!renderState.isCrouching) {
                    this.tail2.xRot = 1.7278761F + (float) (Math.PI / 4) * Mth.cos(f2) * f1;
                } else {
                    this.tail2.xRot = 1.7278761F + 0.47123894F * Mth.cos(f2) * f1;
                }
            }
        }

        if (renderState.relaxStateOneAmount > 0) {
            this.head.xRot = Mth.rotLerp(renderState.relaxStateOneAmount, this.head.xRot, -0.58177644F);
        }
    }

}
