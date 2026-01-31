package nightkosh.withered_lands.renderer.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import nightkosh.withered_lands.core.ModInfo;

import static net.minecraft.resources.Identifier.fromNamespaceAndPath;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class HollowStalkerModel extends EntityModel<LivingEntityRenderState> {

    public static final ModelLayerLocation LAYER = new ModelLayerLocation(
            fromNamespaceAndPath(ModInfo.ID, "hollow_stalker"),
            "main");

    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart left_arm;
    private final ModelPart right_arm;

    public HollowStalkerModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.left_arm = this.body.getChild("left_arm");
        this.right_arm = this.body.getChild("right_arm");
    }

    public static LayerDefinition createBodyLayer() {
        var mesh = new MeshDefinition();
        var root = mesh.getRoot();

        var head = root.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-4, -27, -4, 8, 6, 8, new CubeDeformation(0))
                        .texOffs(0, 14)
                        .addBox(-2, -21, -4, 4, 1, 1, new CubeDeformation(0))
                        .texOffs(28, 8)
                        .addBox(-4.5F, -27.5F, -4.5F, 9, 8, 9, new CubeDeformation(0)),
                PartPose.offset(0, 24, 0));

        var body = root.addOrReplaceChild(
                "body",
                CubeListBuilder.create()
                        .texOffs(0, 17).addBox(-4, 0, -1, 8, 10, 4, new CubeDeformation(0))
                        .texOffs(0, 32)
                        .addBox(-4.5F, -0.5F, -1.5F, 9, 12, 5, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 5, 0, 0.2182F, 0, 0));

        var left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(5, 0, 1));

        var shackles_r1 = left_arm.addOrReplaceChild(
                "shackles_r1",
                CubeListBuilder.create()
                        .texOffs(33, 0)
                        .addBox(-0.5F, -1.7F, -1, 2, 2, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 3, -7, -0.0873F, 0, 0));

        var cover_r1 = left_arm.addOrReplaceChild(
                "cover_r1", CubeListBuilder.create()
                        .texOffs(29, 32).addBox(-0.5F, -0.5F, -5.5F, 2, 2, 6, new CubeDeformation(0))
                        .texOffs(25, 0)
                        .addBox(0, 0, -5, 1, 1, 5, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 0, 0, 0.4363F, 0, 0));

        var arm2_r1 = left_arm.addOrReplaceChild(
                "arm2_r1",
                CubeListBuilder.create()
                        .texOffs(25, 0)
                        .addBox(0, 0, -5, 1, 1, 5, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 2, -4, -0.0873F, 0, 0));

        var right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-5, 0, 1));

        var shackles_r2 = right_arm.addOrReplaceChild(
                "shackles_r2",
                CubeListBuilder.create()
                        .texOffs(33, 0)
                        .addBox(-11.5F, -1.7F, -1, 2, 2, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(10, 3, -7, -0.0873F, 0, 0));

        var cover_r2 = right_arm.addOrReplaceChild(
                "cover_r2",
                CubeListBuilder.create()
                        .texOffs(29, 32)
                        .addBox(-11.5F, -0.5F, -5.5F, 2, 2, 6, new CubeDeformation(0))
                        .texOffs(25, 0)
                        .addBox(-11, 0, -5, 1, 1, 5, new CubeDeformation(0)),
                PartPose.offsetAndRotation(10, 0, 0, 0.4363F, 0, 0));

        var arm2_r2 = right_arm.addOrReplaceChild(
                "arm2_r2",
                CubeListBuilder.create()
                        .texOffs(25, 0)
                        .addBox(-11, 0, -5, 1, 1, 5, new CubeDeformation(0)),
                PartPose.offsetAndRotation(10, 2, -4, -0.0873F, 0, 0));

        return LayerDefinition.create(mesh, 64, 64);
    }

}
