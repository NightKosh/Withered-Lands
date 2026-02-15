package nightkosh.withered_lands.renderer.model.jellyfish;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import nightkosh.withered_lands.core.ModInfo;
import nightkosh.withered_lands.renderer.model.animation.PinkJellyfishAnimations;
import nightkosh.withered_lands.renderer.render_sate.JellyfishRenderState;

import static net.minecraft.resources.Identifier.fromNamespaceAndPath;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class JellyfishPinkModel<T extends JellyfishRenderState> extends AJellyfishModel<T> {

    public static final ModelLayerLocation LAYER = new ModelLayerLocation(
            fromNamespaceAndPath(ModInfo.ID, "pink_jellyfish"), "main");

    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart tentacles;
    private final ModelPart tentacle_front_1;
    private final ModelPart tentacle_front_2;
    private final ModelPart tentacle_front_3;
    private final ModelPart tentacle_back_1;
    private final ModelPart tentacle_back_2;
    private final ModelPart tentacle_back_3;
    private final ModelPart tentacle_right;
    private final ModelPart tentacle_left;

    public JellyfishPinkModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.head = this.body.getChild("head");
        this.tentacles = this.head.getChild("tentacles");
        this.tentacle_front_1 = this.tentacles.getChild("tentacle_front_1");
        this.tentacle_front_2 = this.tentacles.getChild("tentacle_front_2");
        this.tentacle_front_3 = this.tentacles.getChild("tentacle_front_3");
        this.tentacle_back_1 = this.tentacles.getChild("tentacle_back_1");
        this.tentacle_back_2 = this.tentacles.getChild("tentacle_back_2");
        this.tentacle_back_3 = this.tentacles.getChild("tentacle_back_3");
        this.tentacle_right = this.tentacles.getChild("tentacle_right");
        this.tentacle_left = this.tentacles.getChild("tentacle_left");
    }

    @Override
    protected void initAnimations() {
        this.moveLegsAnimation = PinkJellyfishAnimations.MOVE_LEGS.bake(root);
        this.inflateAnimation = PinkJellyfishAnimations.INFLATE.bake(root);
    }

    public static LayerDefinition createBodyLayer() {
        var meshdefinition = new MeshDefinition();
        var root = meshdefinition.getRoot();

        var body = root.addOrReplaceChild("body",
                CubeListBuilder.create()
                        .texOffs(2, 1)
                        .addBox(-6.5F, 1, -6.5F, 13, 5, 13, new CubeDeformation(0)),
                PartPose.offset(0, 7, 0));

        var head = body.addOrReplaceChild("head",
                CubeListBuilder.create()
                        .texOffs(0, 21)
                        .addBox(-3, 0, -3, 6, 5, 6, new CubeDeformation(0)),
                PartPose.offset(0, 0, 0));

        var tentacles = head.addOrReplaceChild("tentacles", CubeListBuilder.create(), PartPose.offset(1, 0, -3));

        var tentacle_front_1 = tentacles.addOrReplaceChild("tentacle_front_1",
                CubeListBuilder.create()
                        .texOffs(10, 0)
                        .addBox(-0.5F, 0, 0.1F, 1, 9, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(1, 5, 0, -0.0436F, 0, -0.0873F));

        var tentacle_front_2 = tentacles.addOrReplaceChild("tentacle_front_2",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-0.5F, 0, 0.1F, 1, 12, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(-1, 5, 0, -0.0436F, 0, 0));

        var tentacle_front_3 = tentacles.addOrReplaceChild("tentacle_front_3",
                CubeListBuilder.create()
                        .texOffs(10, 0)
                        .addBox(-0.5F, 0, 0.1F, 1, 9, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(-3, 5, 0, -0.0436F, 0, 0.0436F));

        var tentacle_back_1 = tentacles.addOrReplaceChild("tentacle_back_1",
                CubeListBuilder.create()
                        .texOffs(10, 0)
                        .addBox(-0.5F, 0, -1.1F, 1, 9, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(1, 5, 6, 0.0436F, 0, -0.0436F));

        var tentacle_back_2 = tentacles.addOrReplaceChild("tentacle_back_2",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-0.5F, 0, -1.1F, 1, 12, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(-1, 5, 6, 0.0436F, 0, 0));

        var tentacle_back_3 = tentacles.addOrReplaceChild("tentacle_back_3",
                CubeListBuilder.create()
                        .texOffs(10, 0)
                        .addBox(-0.5F, 0, -1.1F, 1, 9, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(-3, 5, 6, 0.0436F, 0, 0.0436F));

        var tentacle_right = tentacles.addOrReplaceChild("tentacle_right",
                CubeListBuilder.create()
                        .texOffs(5, 0)
                        .addBox(-1.1F, 0, -0.5F, 1, 11, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(2, 5, 3, 0, 0, -0.0436F));

        var tentacle_left = tentacles.addOrReplaceChild("tentacle_left",
                CubeListBuilder.create()
                        .texOffs(5, 0)
                        .addBox(0.1F, 0, -0.5F, 1, 11, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(-4, 5, 3, 0, 0, 0.0436F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

}
