package nightkosh.withered_lands.renderer.model.jellyfish;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import nightkosh.withered_lands.core.ModInfo;
import nightkosh.withered_lands.renderer.model.animation.YellowJellyfishAnimations;
import nightkosh.withered_lands.renderer.render_sate.JellyfishRenderState;

import static net.minecraft.resources.Identifier.fromNamespaceAndPath;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class JellyfishYellowModel<T extends JellyfishRenderState> extends AJellyfishModel<T> {

    public static final ModelLayerLocation LAYER = new ModelLayerLocation(
            fromNamespaceAndPath(ModInfo.ID, "yellow_jellyfish"), "main");

    private final ModelPart body;
    private final ModelPart tentacles_11;
    private final ModelPart tentacles_12;
    private final ModelPart tentacles_13;
    private final ModelPart tentacles_14;
    private final ModelPart head;

    public JellyfishYellowModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.tentacles_11 = this.body.getChild("tentacles_11");
        this.tentacles_12 = this.body.getChild("tentacles_12");
        this.tentacles_13 = this.body.getChild("tentacles_13");
        this.tentacles_14 = this.body.getChild("tentacles_14");
        this.head = this.body.getChild("head");
    }

    @Override
    protected void initAnimations() {
        this.moveLegsAnimation = YellowJellyfishAnimations.MOVE_LEGS.bake(root);
        this.inflateAnimation = YellowJellyfishAnimations.INFLATE.bake(root);
    }

    public static LayerDefinition createBodyLayer() {
        var meshdefinition = new MeshDefinition();
        var root = meshdefinition.getRoot();

        var body = root.addOrReplaceChild("body",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-7, 2, -7, 14, 5, 14, new CubeDeformation(0))
                        .texOffs(0, 20)
                        .addBox(-5, 0, -5, 10, 2, 10, new CubeDeformation(0)),
                PartPose.offset(0, 3, 0));

        body.addOrReplaceChild("tentacles_11",
                CubeListBuilder.create()
                        .texOffs(43, 0)
                        .addBox(-5, 0, 0, 10, 5, 0, new CubeDeformation(0)),
                PartPose.offset(0, 7, 6));

        body.addOrReplaceChild("tentacles_12",
                CubeListBuilder.create()
                        .texOffs(43, 0)
                        .addBox(-5, 0, 0, 10, 5, 0, new CubeDeformation(0)),
                PartPose.offset(0, 7, -6));

        body.addOrReplaceChild("tentacles_13",
                CubeListBuilder.create()
                        .texOffs(43, -10)
                        .addBox(0, 0, -5, 0, 5, 10, new CubeDeformation(0)),
                PartPose.offset(6, 7, 0));

        body.addOrReplaceChild("tentacles_14",
                CubeListBuilder.create()
                        .texOffs(43, -10)
                        .addBox(0, 0, -5, 0, 5, 10, new CubeDeformation(0)),
                PartPose.offset(-6, 7, 0));

        var head = body.addOrReplaceChild("head",
                CubeListBuilder.create()
                        .texOffs(41, 21)
                        .addBox(-2.5F, 0, -2.5F, 5, 6, 5, new CubeDeformation(0)),
                PartPose.offset(0, 5, 0));

        head.addOrReplaceChild("tentacles_22_r1",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-2.5F, -5, 0, 5, 10, 0, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 11, 0, 0, 0.7854F, 0));

        head.addOrReplaceChild("tentacles_21_r1",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-2.5F, 0, 0, 5, 10, 0, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 6, 0, 0, -0.7854F, 0));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

}
