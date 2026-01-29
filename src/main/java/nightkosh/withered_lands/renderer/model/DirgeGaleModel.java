package nightkosh.withered_lands.renderer.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.BreezeRenderState;
import nightkosh.withered_lands.core.ModInfo;

import static net.minecraft.resources.Identifier.fromNamespaceAndPath;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class DirgeGaleModel extends EntityModel<BreezeRenderState> {

    public static final ModelLayerLocation LAYER = new ModelLayerLocation(
            fromNamespaceAndPath(ModInfo.ID, "dirge_gale"), "main");

    public DirgeGaleModel(ModelPart modelPart) {
        super(modelPart);
    }

    public static LayerDefinition createBodyLayer() {
        return LayerDefinition.create(createBaseMesh(), 32, 32);
    }

    private static MeshDefinition createBaseMesh() {
        var mesh = new MeshDefinition();
        var root = mesh.getRoot();
        var body = root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0, 0, 0));

        var skull = body.addOrReplaceChild(
                "skull",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-4, -27, -4, 8, 6, 8, new CubeDeformation(0))
                        .texOffs(0, 14)
                        .addBox(-2, -21, -4, 4, 1, 1, new CubeDeformation(0)),
                PartPose.offset(0, 24, 0));


        var spine = body.addOrReplaceChild("spine", CubeListBuilder.create(), PartPose.offset(0, 24, 0));

        var spine11_r1 = spine.addOrReplaceChild(
                "spine11_r1",
                CubeListBuilder.create()
                        .texOffs(18, 17)
                        .addBox(-0.5F, 13.8F, -0.5F, 1, 1, 1, new CubeDeformation(0))

                        .texOffs(18, 17)
                        .addBox(-0.5F, 11.4F, -0.5F, 1, 1, 1, new CubeDeformation(0))

                        .texOffs(9, 17)
                        .addBox(-1, 5.8F, -1, 2, 2, 2, new CubeDeformation(0))

                        .texOffs(0, 17)
                        .addBox(-1, -5.2F, -1, 2, 2, 2, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, -15.8F, 1, 0, -0.7854F, 0));

        var spine10_r1 = spine.addOrReplaceChild(
                "spine10_r1",
                CubeListBuilder.create()
                        .texOffs(18, 17)
                        .addBox(-0.5F, 12.6F, -0.5F, 1, 1, 1, new CubeDeformation(0))
                        .texOffs(0, 17)
                        .addBox(-1, 3.6F, -1, 2, 2, 2, new CubeDeformation(0))
                        .texOffs(9, 17)
                        .addBox(-1, -3, -1, 2, 2, 2, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, -15.8F, 2, 0, -0.7854F, 0));

        var spine8_r1 = spine.addOrReplaceChild(
                "spine8_r1",
                CubeListBuilder.create()
                        .texOffs(18, 17)
                        .addBox(-0.5F, 9.2F, -0.5F, 1, 1, 1, new CubeDeformation(0))
                        .texOffs(0, 17)
                        .addBox(-1, 7.0F, -1, 2, 2, 2, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, -14.8F, 0, 0, -0.7854F, 0));

        PartDefinition spine4_r1 = spine.addOrReplaceChild(
                "spine4_r1",
                CubeListBuilder.create()
                        .texOffs(9, 17)
                        .addBox(-1, 0.4F, -1, 2, 2, 2, new CubeDeformation(0))
                        .texOffs(0, 17)
                        .addBox(-1, -1.8F, -1, 2, 2, 2, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, -14.8F, 3, 0, -0.7854F, 0));

        return mesh;
    }

}
