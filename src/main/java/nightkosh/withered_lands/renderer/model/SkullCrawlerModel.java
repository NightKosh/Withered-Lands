package nightkosh.withered_lands.renderer.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;
import nightkosh.withered_lands.core.ModInfo;

import static net.minecraft.resources.Identifier.fromNamespaceAndPath;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SkullCrawlerModel<T extends LivingEntityRenderState> extends EntityModel<T> {

    public static final ModelLayerLocation LAYER = new ModelLayerLocation(
            fromNamespaceAndPath(ModInfo.ID, "skull_crawler"),
            "main");

    private static final float BASE_Z = 0.3490659F;
    private static final float ADV_BASE_Z = BASE_Z * 0.74F;
    private static final float BASE_Y = 0.1047198F;
    private static final float ADV_BASE_Y = BASE_Y * 2;

    private static final float R_BASE_Y = (float) Math.PI + BASE_Y;
    private static final float R_ADV_BASE_Y = (float) Math.PI + ADV_BASE_Y;

    private final ModelPart skull;
    private final ModelPart teeth;

    private final ModelPart rightLeg1;
    private final ModelPart rightLeg2;
    private final ModelPart rightLeg3;
    private final ModelPart rightLeg4;

    private final ModelPart leftLeg1;
    private final ModelPart leftLeg2;
    private final ModelPart leftLeg3;
    private final ModelPart leftLeg4;

    public SkullCrawlerModel(ModelPart root) {
        super(root);

        this.skull = root.getChild("skull");
        this.teeth = skull.getChild("teeth");

        this.rightLeg1 = root.getChild("right_leg_1");
        this.rightLeg2 = root.getChild("right_leg_2");
        this.rightLeg3 = root.getChild("right_leg_3");
        this.rightLeg4 = root.getChild("right_leg_4");

        this.leftLeg1 = root.getChild("left_leg_1");
        this.leftLeg2 = root.getChild("left_leg_2");
        this.leftLeg3 = root.getChild("left_leg_3");
        this.leftLeg4 = root.getChild("left_leg_4");
    }

    public static LayerDefinition createBodyLayer() {
        var mesh = new MeshDefinition();
        var root = mesh.getRoot();

        var skull = root.addOrReplaceChild(
                "skull",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(0, 0, 0, 8, 6, 8),
                PartPose.offsetAndRotation(-4, 16.6F, -4, -0.1745329F, 0, 0)
        );

        // teeth child
        skull.addOrReplaceChild(
                "teeth",
                CubeListBuilder.create()
                        .texOffs(0, 14)
                        .addBox(0, 0, 0, 4, 2, 1),
                PartPose.offset(2, 5.4F, 0.1F));

        var leg = CubeListBuilder.create()
                .texOffs(0, 17)
                .addBox(0, 0, 0, 3, 1, 1);

        // Right legs
        root.addOrReplaceChild("right_leg_1", leg, PartPose.offset(-3.9F, 22, -2.1F));
        root.addOrReplaceChild("right_leg_2", leg, PartPose.offset(-3.9F, 22, -0.9F));
        root.addOrReplaceChild("right_leg_3", leg, PartPose.offset(-3.9F, 22,  0.4F));
        root.addOrReplaceChild("right_leg_4", leg, PartPose.offset(-3.9F, 22,  1.6F));

        // Left legs
        root.addOrReplaceChild("left_leg_1", leg, PartPose.offset(3.7F, 22, -3.1F));
        root.addOrReplaceChild("left_leg_2", leg, PartPose.offset(3.7F, 22, -1.9F));
        root.addOrReplaceChild("left_leg_3", leg, PartPose.offset(3.7F, 22, -0.6F));
        root.addOrReplaceChild("left_leg_4", leg, PartPose.offset(3.7F, 22,  0.6F));

        return LayerDefinition.create(mesh, 32, 32);
    }


    @Override
    public void setupAnim(LivingEntityRenderState state) {
        // Базовые углы — как у тебя
        this.rightLeg1.zRot = -BASE_Z;
        this.rightLeg2.zRot = -ADV_BASE_Z;
        this.rightLeg3.zRot = -ADV_BASE_Z;
        this.rightLeg4.zRot = -BASE_Z;

        this.rightLeg1.yRot = -R_ADV_BASE_Y;
        this.rightLeg2.yRot = -R_BASE_Y;
        this.rightLeg3.yRot =  R_BASE_Y;
        this.rightLeg4.yRot =  R_ADV_BASE_Y;

        this.leftLeg1.zRot = BASE_Z;
        this.leftLeg2.zRot = ADV_BASE_Z;
        this.leftLeg3.zRot = ADV_BASE_Z;
        this.leftLeg4.zRot = BASE_Z;

        this.leftLeg1.yRot =  ADV_BASE_Y;
        this.leftLeg2.yRot =  BASE_Y;
        this.leftLeg3.yRot = -BASE_Y;
        this.leftLeg4.yRot = -ADV_BASE_Y;

        float limbSwing = state.walkAnimationPos * 0.6662F;
        float limbSwingAmount = state.walkAnimationSpeed;

        float firstY  = -(Mth.cos(limbSwing * 2) * 0.4F) * limbSwingAmount;
        float secondY = -(Mth.cos(limbSwing * 2 + Math.PI) * 0.4F) * limbSwingAmount;
        float thirdY  = -(Mth.cos(limbSwing * 2 + Math.PI / 2) * 0.4F) * limbSwingAmount;
        float fourthY = -(Mth.cos(limbSwing * 2 + Math.PI * 3 / 2F) * 0.4F) * limbSwingAmount;

        float firstZ  =  Mth.abs(Mth.sin(limbSwing) * 0.4F) * limbSwingAmount;
        float secondZ =  Mth.abs(Mth.sin(limbSwing + Math.PI) * 0.4F) * limbSwingAmount;
        float thirdZ  =  Mth.abs(Mth.sin(limbSwing + Math.PI / 2) * 0.4F) * limbSwingAmount;
        float fourthZ =  Mth.abs(Mth.sin(limbSwing + Math.PI * 3 / 2) * 0.4F) * limbSwingAmount;

        this.rightLeg1.yRot += firstY;
        this.rightLeg2.yRot += secondY;
        this.rightLeg3.yRot += thirdY;
        this.rightLeg4.yRot += fourthY;

        this.rightLeg1.zRot += firstZ;
        this.rightLeg2.zRot += secondZ;
        this.rightLeg3.zRot += thirdZ;
        this.rightLeg4.zRot += fourthZ;

        this.leftLeg1.yRot -= firstY;
        this.leftLeg2.yRot -= secondY;
        this.leftLeg3.yRot -= thirdY;
        this.leftLeg4.yRot -= fourthY;

        this.leftLeg1.zRot -= firstZ;
        this.leftLeg2.zRot -= secondZ;
        this.leftLeg3.zRot -= thirdZ;
        this.leftLeg4.zRot -= fourthZ;
    }
    
}
