package nightkosh.withered_lands.renderer.model.jellyfish;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import nightkosh.withered_lands.core.ModInfo;
import nightkosh.withered_lands.renderer.model.animation.PurpleJellyfishAnimations;
import nightkosh.withered_lands.renderer.render_sate.JellyfishRenderState;

import static net.minecraft.resources.Identifier.fromNamespaceAndPath;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class JellyfishPurpleModel<T extends JellyfishRenderState> extends AJellyfishModel<T> {

    public static final ModelLayerLocation LAYER = new ModelLayerLocation(
            fromNamespaceAndPath(ModInfo.ID, "purple_jellyfish"), "main");

    private final ModelPart body;
    private final ModelPart fin_1;
    private final ModelPart fin_2;
    private final ModelPart fin_3;
    private final ModelPart fin_4;
    private final ModelPart tentacles_11;
    private final ModelPart tentacles_12;
    private final ModelPart tentacles_21;
    private final ModelPart tentacles_22;
    private final ModelPart tentacles_31;
    private final ModelPart tentacles_32;
    private final ModelPart tentacles_41;
    private final ModelPart tentacles_42;

    public JellyfishPurpleModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.fin_1 = this.body.getChild("fin_1");
        this.fin_2 = this.body.getChild("fin_2");
        this.fin_3 = this.body.getChild("fin_3");
        this.fin_4 = this.body.getChild("fin_4");
        this.tentacles_11 = this.body.getChild("tentacles_11");
        this.tentacles_12 = this.tentacles_11.getChild("tentacles_12");
        this.tentacles_21 = this.body.getChild("tentacles_21");
        this.tentacles_22 = this.tentacles_21.getChild("tentacles_22");
        this.tentacles_31 = this.body.getChild("tentacles_31");
        this.tentacles_32 = this.tentacles_31.getChild("tentacles_32");
        this.tentacles_41 = this.body.getChild("tentacles_41");
        this.tentacles_42 = this.tentacles_41.getChild("tentacles_42");
    }

    @Override
    protected void initAnimations() {
        this.moveLegsAnimation = PurpleJellyfishAnimations.MOVE_LEGS.bake(root);
        this.inflateAnimation = PurpleJellyfishAnimations.INFLATE.bake(root);
    }

    public static LayerDefinition createBodyLayer() {
        var meshdefinition = new MeshDefinition();
        var root = meshdefinition.getRoot();

        var body = root.addOrReplaceChild("body",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-7, -30, -7, 14, 8, 14, new CubeDeformation(0))
                        .texOffs(57, 0)
                        .addBox(-5.5F, -29, -5.5F, 11, 6, 11, new CubeDeformation(0))
                        .texOffs(102, 0)
                        .addBox(-4, -26, 0, 8, 26, 0, new CubeDeformation(0)),
                PartPose.offset(0, 24, 0));

        body.addOrReplaceChild("fin_1",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-1, 0, 0, 2, 5, 1, new CubeDeformation(0))
                        .texOffs(0, 0)
                        .addBox(-5, 0, 0, 2, 5, 1, new CubeDeformation(0))
                        .texOffs(0, 0)
                        .addBox(3, 0, 0, 2, 5, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, -26, -7, -0.0873F, 0, 0));

        body.addOrReplaceChild("fin_2",
                CubeListBuilder.create()
                        .texOffs(0, 7)
                        .addBox(-1, 0, -1, 2, 5, 1, new CubeDeformation(0))
                        .texOffs(0, 7)
                        .addBox(-5, 0, -1, 2, 5, 1, new CubeDeformation(0))
                        .texOffs(0, 7)
                        .addBox(3, 0, -1, 2, 5, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, -26, 7, 0.0873F, 0, 0));

        body.addOrReplaceChild("fin_3",
                CubeListBuilder.create()
                        .texOffs(61, 0)
                        .addBox(-1, 0, -1, 1, 5, 2, new CubeDeformation(0))
                        .texOffs(61, 0)
                        .addBox(-1, 0, -5, 1, 5, 2, new CubeDeformation(0))
                        .texOffs(61, 0)
                        .addBox(-1, 0, 3, 1, 5, 2, new CubeDeformation(0)),
                PartPose.offsetAndRotation(7, -26, 0, 0, 0, -0.0873F));

        body.addOrReplaceChild("fin_4",
                CubeListBuilder.create()
                        .texOffs(54, 0)
                        .addBox(0, 0, -1, 1, 5, 2, new CubeDeformation(0))
                        .texOffs(54, 0)
                        .addBox(0, 0, -5, 1, 5, 2, new CubeDeformation(0))
                        .texOffs(54, 0)
                        .addBox(0, 0, 3, 1, 5, 2, new CubeDeformation(0)),
                PartPose.offsetAndRotation(-7, -26, 0, 0, 0, 0.0873F));

        var tentacles_11 = body.addOrReplaceChild("tentacles_11",
                CubeListBuilder.create()
                        .texOffs(57, 18)
                        .addBox(-2, 0, 0, 4, 11, 3, new CubeDeformation(0)),
                PartPose.offsetAndRotation(-3, -22, 3, 0.1309F, -0.7854F, 0));

        tentacles_11.addOrReplaceChild("tentacles_12",
                CubeListBuilder.create()
                        .texOffs(72, 18)
                        .addBox(-2, 0, -2, 4, 11, 3, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 11, 2, 0.2182F, 0, 0));

        var tentacles_21 = body.addOrReplaceChild("tentacles_21",
                CubeListBuilder.create()
                        .texOffs(57, 18)
                        .addBox(-2, 0, 0, 4, 11, 3, new CubeDeformation(0)),
                PartPose.offsetAndRotation(3, -22, 3, 0.1309F, 0.7854F, 0));

        tentacles_21.addOrReplaceChild("tentacles_22",
                CubeListBuilder.create()
                        .texOffs(72, 18)
                        .addBox(4, 0, -2, 4, 11, 3, new CubeDeformation(0)),
                PartPose.offsetAndRotation(-6, 11, 2, 0.2182F, 0, 0));

        var tentacles_31 = body.addOrReplaceChild("tentacles_31",
                CubeListBuilder.create()
                        .texOffs(57, 18)
                        .addBox(-2, 0, 0, 4, 11, 3, new CubeDeformation(0)),
                PartPose.offsetAndRotation(-3, -22, -3, 0.1309F, -2.3562F, 0));

        tentacles_31.addOrReplaceChild("tentacles_32",
                CubeListBuilder.create()
                        .texOffs(72, 18)
                        .addBox(-2, 0, -2, 4, 11, 3, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 11, 2, 0.2182F, 0, 0));

        var tentacles_41 = body.addOrReplaceChild("tentacles_41",
                CubeListBuilder.create()
                        .texOffs(57, 18)
                        .addBox(-2, 0, 0, 4, 11, 3, new CubeDeformation(0)),
                PartPose.offsetAndRotation(3, -22, -3, 0.1309F, 2.3562F, 0));

        tentacles_41.addOrReplaceChild("tentacles_42",
                CubeListBuilder.create()
                        .texOffs(72, 18)
                        .addBox(4, 0, -2, 4, 11, 3, new CubeDeformation(0)),
                PartPose.offsetAndRotation(-6, 11, 2, 0.2182F, 0, 0));

        return LayerDefinition.create(meshdefinition, 128, 32);
    }

}
