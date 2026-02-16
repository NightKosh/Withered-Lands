package nightkosh.withered_lands.renderer.model.jellyfish;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import nightkosh.withered_lands.core.ModInfo;
import nightkosh.withered_lands.renderer.model.animation.WhiteJellyfishAnimations;
import nightkosh.withered_lands.renderer.render_sate.JellyfishRenderState;

import static net.minecraft.resources.Identifier.fromNamespaceAndPath;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class JellyfishWhiteModel<T extends JellyfishRenderState> extends AJellyfishModel<T> {

    public static final ModelLayerLocation LAYER = new ModelLayerLocation(
            fromNamespaceAndPath(ModInfo.ID, "white_jellyfish"), "main");

    private final ModelPart body;
    private final ModelPart body_2;
    private final ModelPart tentacles;
    private final ModelPart tentacle_1;
    private final ModelPart tentacle_1_1;
    private final ModelPart tentacle_1_2;
    private final ModelPart tentacle_2;
    private final ModelPart tentacle_2_1;
    private final ModelPart tentacle_2_2;
    private final ModelPart tentacle_3;
    private final ModelPart tentacle_3_1;
    private final ModelPart tentacle_3_2;
    private final ModelPart tentacle_4;
    private final ModelPart tentacle_4_1;
    private final ModelPart tentacle_4_2;
    private final ModelPart side_strings_1;
    private final ModelPart side_strings_2;
    private final ModelPart side_strings_3;
    private final ModelPart side_strings_4;

    public JellyfishWhiteModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.body_2 = this.body.getChild("body_2");
        this.tentacles = this.body_2.getChild("tentacles");
        this.tentacle_1 = this.tentacles.getChild("tentacle_1");
        this.tentacle_1_1 = this.tentacle_1.getChild("tentacle_1_1");
        this.tentacle_1_2 = this.tentacle_1_1.getChild("tentacle_1_2");
        this.tentacle_2 = this.tentacles.getChild("tentacle_2");
        this.tentacle_2_1 = this.tentacle_2.getChild("tentacle_2_1");
        this.tentacle_2_2 = this.tentacle_2_1.getChild("tentacle_2_2");
        this.tentacle_3 = this.tentacles.getChild("tentacle_3");
        this.tentacle_3_1 = this.tentacle_3.getChild("tentacle_3_1");
        this.tentacle_3_2 = this.tentacle_3_1.getChild("tentacle_3_2");
        this.tentacle_4 = this.tentacles.getChild("tentacle_4");
        this.tentacle_4_1 = this.tentacle_4.getChild("tentacle_4_1");
        this.tentacle_4_2 = this.tentacle_4_1.getChild("tentacle_4_2");
        this.side_strings_1 = this.body.getChild("side_strings_1");
        this.side_strings_2 = this.body.getChild("side_strings_2");
        this.side_strings_3 = this.body.getChild("side_strings_3");
        this.side_strings_4 = this.body.getChild("side_strings_4");
    }

    @Override
    protected void initAnimations() {
        this.moveLegsAnimation = WhiteJellyfishAnimations.MOVE_LEGS.bake(root);
        this.inflateAnimation = WhiteJellyfishAnimations.INFLATE.bake(root);
    }

    public static LayerDefinition createBodyLayer() {
        var meshdefinition = new MeshDefinition();
        var root = meshdefinition.getRoot();

        var body = root.addOrReplaceChild("body",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-16, -50, -16, 32, 5, 32, new CubeDeformation(0))
                        .texOffs(0, 38)
                        .addBox(-12, -53, -12, 24, 3, 24, new CubeDeformation(0))
                        .texOffs(0, 66)
                        .addBox(-8, -55, -8, 16, 2, 16, new CubeDeformation(0)),
                PartPose.offset(0, 14, 0));

        var body_2 = body.addOrReplaceChild("body_2",
                CubeListBuilder.create()
                        .texOffs(0, 85)
                        .addBox(-6, -3, -6, 12, 8, 12, new CubeDeformation(0)),
                PartPose.offset(0, -45, 0));

        var tentacles = body_2.addOrReplaceChild("tentacles", CubeListBuilder.create(), PartPose.offset(0, 5, 0));

        var tentacle_1 = tentacles.addOrReplaceChild("tentacle_1",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-1, 0, -2, 3, 10, 3, new CubeDeformation(0))
                        .texOffs(13, 0)
                        .addBox(0, 2, -4, 4, 6, 4, new CubeDeformation(0)),
                PartPose.offset(2, 0, -2));

        var tentacle_1_1 = tentacle_1.addOrReplaceChild("tentacle_1_1",
                CubeListBuilder.create()
                        .texOffs(49, 85)
                        .addBox(0, 0, 0, 4, 20, 0, new CubeDeformation(0)),
                PartPose.offsetAndRotation(-1, 10, -2, 0, -0.7854F, 0));

        var tentacle_1_2 = tentacle_1_1.addOrReplaceChild("tentacle_1_2",
                CubeListBuilder.create()
                        .texOffs(49, 85)
                        .addBox(0, 0, 0, 4, 20, 0, new CubeDeformation(0)),
                PartPose.offset(0, 20, 0));

        var tentacle_2 = tentacles.addOrReplaceChild("tentacle_2",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-2, 0, -2, 3, 10, 3, new CubeDeformation(0))
                        .texOffs(13, 0)
                        .addBox(-4, 2, -4, 4, 6, 4, new CubeDeformation(0)),
                PartPose.offset(-2, 0, -2));

        var tentacle_2_1 = tentacle_2.addOrReplaceChild("tentacle_2_1",
                CubeListBuilder.create()
                        .texOffs(49, 85)
                        .addBox(-4, 0, 0, 4, 20, 0, new CubeDeformation(0)),
                PartPose.offsetAndRotation(1, 10, -2, 0, 0.7854F, 0));

        var tentacle_2_2 = tentacle_2_1.addOrReplaceChild("tentacle_2_2",
                CubeListBuilder.create()
                        .texOffs(49, 85)
                        .addBox(-4, 0, 0, 4, 20, 0, new CubeDeformation(0)),
                PartPose.offset(0, 20, 0));

        var tentacle_3 = tentacles.addOrReplaceChild("tentacle_3",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-1, 0, -1, 3, 10, 3, new CubeDeformation(0))
                        .texOffs(13, 0)
                        .addBox(0, 2, 0, 4, 6, 4, new CubeDeformation(0)),
                PartPose.offset(2, 0, 2));

        var tentacle_3_1 = tentacle_3.addOrReplaceChild("tentacle_3_1",
                CubeListBuilder.create()
                        .texOffs(49, 85)
                        .addBox(0, 0, 0, 4, 20, 0, new CubeDeformation(0)),
                PartPose.offsetAndRotation(-1, 10, 2, 0, 0.7854F, 0));

        var tentacle_3_2 = tentacle_3_1.addOrReplaceChild("tentacle_3_2",
                CubeListBuilder.create()
                        .texOffs(49, 85)
                        .addBox(0, 0, 0, 4, 20, 0, new CubeDeformation(0)),
                PartPose.offset(0, 20, 0));

        var tentacle_4 = tentacles.addOrReplaceChild("tentacle_4",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-2, 0, -1, 3, 10, 3, new CubeDeformation(0))
                        .texOffs(13, 0)
                        .addBox(-4, 2, 0, 4, 6, 4, new CubeDeformation(0)),
                PartPose.offset(-2, 0, 2));

        var tentacle_4_1 = tentacle_4.addOrReplaceChild("tentacle_4_1",
                CubeListBuilder.create()
                        .texOffs(49, 85)
                        .addBox(-4, 0, 0, 4, 20, 0, new CubeDeformation(0)),
                PartPose.offsetAndRotation(1, 10, 2, 0, -0.7854F, 0));

        tentacle_4_1.addOrReplaceChild("tentacle_4_2",
                CubeListBuilder.create()
                        .texOffs(49, 85)
                        .addBox(-4, 0, 0, 4, 20, 0, new CubeDeformation(0)),
                PartPose.offset(0, 20, 0));

        body.addOrReplaceChild("side_strings_1",
                CubeListBuilder.create()
                        .texOffs(72, 67)
                        .addBox(-14, 0, 0, 28, 30, 0, new CubeDeformation(0)),
                PartPose.offset(0, -46, -14));

        body.addOrReplaceChild("side_strings_2",
                CubeListBuilder.create()
                        .texOffs(72, 67)
                        .addBox(-14, 0, 0, 28, 30, 0, new CubeDeformation(0)),
                PartPose.offset(0, -46, 14));

        body.addOrReplaceChild("side_strings_3",
                CubeListBuilder.create()
                        .texOffs(72, 39)
                        .addBox(0, 0, -14, 0, 30, 28, new CubeDeformation(0)),
                PartPose.offset(14, -46, 0));

        body.addOrReplaceChild("side_strings_4",
                CubeListBuilder.create()
                        .texOffs(72, 39)
                        .addBox(0, 0, -14, 0, 30, 28, new CubeDeformation(0)),
                PartPose.offset(-14, -46, 0));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

}
