package nightkosh.withered_lands.renderer.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.monster.skeleton.SkeletonModel;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import nightkosh.withered_lands.core.ModInfo;

import static net.minecraft.resources.Identifier.fromNamespaceAndPath;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WLSkeletonModel<S extends SkeletonRenderState> extends SkeletonModel<S> {

    public static final ModelLayerLocation LAYER = new ModelLayerLocation(
            fromNamespaceAndPath(ModInfo.ID, "skeleton"),
            "main");

    public WLSkeletonModel(ModelPart modelPart) {
        super(modelPart);
    }

    public static LayerDefinition createBodyLayer() {
        var mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0);
        var root = mesh.getRoot();
        createDefaultSkeletonMesh(root);

        var skull = root.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-4, -7, -4, 8, 6, 8),
                PartPose.offset(0, 0, 0));

        skull.addOrReplaceChild(
                "teeth",
                CubeListBuilder.create()
                        .texOffs(54, 30)
                        .addBox(0, 0, 0, 4, 1, 1),
                PartPose.offset(-2F, -1F, -4F));

        return LayerDefinition.create(mesh, 64, 32);
    }

}
