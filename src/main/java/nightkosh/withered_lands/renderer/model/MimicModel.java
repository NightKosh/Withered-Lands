package nightkosh.withered_lands.renderer.model;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import nightkosh.withered_lands.core.ModInfo;
import nightkosh.withered_lands.renderer.model.animation.MimicAnimations;
import nightkosh.withered_lands.renderer.render_sate.MimicRenderState;

import static net.minecraft.resources.Identifier.fromNamespaceAndPath;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class MimicModel<T extends MimicRenderState> extends EntityModel<T> {

    public static final ModelLayerLocation LAYER = new ModelLayerLocation(
            fromNamespaceAndPath(ModInfo.ID, "mimic"), "main");

    private final ModelPart fullBody;
    private final ModelPart head;
    private final ModelPart teeth2;
    private final ModelPart body;
    private final ModelPart teeth;
    private final ModelPart tongue;
    private final KeyframeAnimation idleAnimation;
    private final KeyframeAnimation jumpAnimation;

    public MimicModel(ModelPart root) {
        super(root);
        this.fullBody = root.getChild("full_body");
        this.head = this.fullBody.getChild("head");
        this.teeth2 = this.head.getChild("teeth2");
        this.body = this.fullBody.getChild("body");
        this.teeth = this.body.getChild("teeth");
        this.tongue = this.body.getChild("tongue");
        this.idleAnimation = MimicAnimations.IDLE.bake(root);
        this.jumpAnimation = MimicAnimations.JUMP.bake(root);
    }

    public static LayerDefinition createBodyLayer() {
        var meshdefinition = new MeshDefinition();
        var partdefinition = meshdefinition.getRoot();

        var fullBody = partdefinition.addOrReplaceChild("full_body", CubeListBuilder.create(), PartPose.offset(0, 24, -7));

        var head = fullBody.addOrReplaceChild("head",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-7, -5, -14, 14, 5, 14, new CubeDeformation(0))
                        .texOffs(0, 0)
                        .addBox(-1, -2, -15, 2, 4, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, -10, 14, -0.7854F, 0, 0));

        var teeth2 = head.addOrReplaceChild("teeth2", CubeListBuilder.create(), PartPose.offset(0, 0, 0));

        var left_4_r1 = teeth2.addOrReplaceChild("left_4_r1",
                CubeListBuilder.create()
                        .texOffs(0, 59)
                        .addBox(0, 0, -0.3F, 1, 1, 1, new CubeDeformation(0))
                        .texOffs(0, 55)
                        .addBox(0, 0, -4.7F, 1, 2, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(5, 0, -5, 0, 0, -0.0436F));

        var left_3_r1 = teeth2.addOrReplaceChild("left_3_r1",
                CubeListBuilder.create().texOffs(0, 59)
                        .addBox(0, 0, -0.5F, 1, 1, 1,
                                new CubeDeformation(0))
                        .texOffs(0, 50)
                        .addBox(0, 0, -4.9F, 1, 3, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(5, 0, -7, 0, 0, -0.0873F));

        var right_4_r1 = teeth2.addOrReplaceChild("right_4_r1",
                CubeListBuilder.create()
                        .texOffs(0, 59)
                        .addBox(0, 0, -0.3F, 1, 1, 1, new CubeDeformation(0))
                        .texOffs(0, 55)
                        .addBox(0, 0, -4.7F, 1, 2, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(-6, 0, -5, 0, 0, 0.0436F));

        var right_3_r1 = teeth2.addOrReplaceChild("right_3_r1",
                CubeListBuilder.create()
                        .texOffs(0, 59)
                        .addBox(0, 0, -0.5F, 1, 1, 1, new CubeDeformation(0))
                        .texOffs(0, 50)
                        .addBox(0, 0, -4.9F, 1, 3, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(-6, 0, -7, 0, 0, 0.0873F));

        var front_3_3_r1 = teeth2.addOrReplaceChild("front_3_3_r1",
                CubeListBuilder.create()
                        .texOffs(0, 55)
                        .addBox(-0.7F, 0, -1, 1, 2, 1, new CubeDeformation(0))
                        .texOffs(0, 55)
                        .addBox(-2.2F, 0, -1, 1, 2, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(1, 0, -12, 0.0436F, 0, 0));

        var front_2_2_r1 = teeth2.addOrReplaceChild("front_2_2_r1",
                CubeListBuilder.create()
                        .texOffs(0, 50)
                        .addBox(-0.3F, 0, -1, 1, 3, 1, new CubeDeformation(0))
                        .texOffs(0, 50)
                        .addBox(-4.7F, 0, -1, 1, 3, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(2, 0, -12, -0.0436F, 0, 0));

        var front_2_r1 = teeth2.addOrReplaceChild("front_2_r1",
                CubeListBuilder.create()
                        .texOffs(0, 44).addBox(-0.1F, 0, -1, 1, 4, 1, new CubeDeformation(0))
                        .texOffs(0, 44)
                        .addBox(-8.9F, 0, -1, 1, 4, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(4, 0, -12, -0.0873F, 0, 0));

        var body = fullBody.addOrReplaceChild("body",
                CubeListBuilder.create()
                        .texOffs(0, 19)
                        .addBox(-7, -10, -7, 14, 10, 14, new CubeDeformation(0)),
                PartPose.offset(0, 0, 7));

        var teeth = body.addOrReplaceChild("teeth", CubeListBuilder.create(), PartPose.offset(-6, -9, 0));

        var left_5_r1 = teeth.addOrReplaceChild("left_5_r1",
                CubeListBuilder.create()
                        .texOffs(5, 59)
                        .addBox(0, -1, -0.2F, 1, 1, 1, new CubeDeformation(0))
                        .texOffs(5, 55)
                        .addBox(0, -2, -4.6F, 1, 2, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(11, -1, 3, 0, 0, 0.0436F));

        var left_4_r2 = teeth.addOrReplaceChild("left_4_r2",
                CubeListBuilder.create()
                        .texOffs(5, 55)
                        .addBox(0, -2, -0.4F, 1, 2, 1, new CubeDeformation(0))
                        .texOffs(5, 50)
                        .addBox(0, -3, -4.8F, 1, 3, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(11, -1, 1, 0, 0, 0.0873F));

        var left_1_r1 = teeth.addOrReplaceChild("left_1_r1",
                CubeListBuilder.create()
                        .texOffs(5, 44)
                        .addBox(0, -4, -1, 1, 4, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(11, -1, -5, 0.0873F, 0, 0.0436F));

        var right_5_r1 = teeth.addOrReplaceChild("right_5_r1",
                CubeListBuilder.create()
                        .texOffs(5, 59)
                        .addBox(0, -1, -0.2F, 1, 1, 1, new CubeDeformation(0))
                        .texOffs(5, 55)
                        .addBox(0, -2, -4.6F, 1, 2, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, -1, 3, 0, 0, -0.0436F));

        var right_4_r2 = teeth.addOrReplaceChild("right_4_r2",
                CubeListBuilder.create()
                        .texOffs(5, 55)
                        .addBox(0, -2, -0.4F, 1, 2, 1, new CubeDeformation(0))
                        .texOffs(5, 50)
                        .addBox(0, -3, -4.8F, 1, 3, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, -1, 1, 0, 0, -0.0873F));

        var right_1_r1 = teeth.addOrReplaceChild("right_1_r1",
                CubeListBuilder.create()
                        .texOffs(5, 44)
                        .addBox(0, -4, -1, 1, 4, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, -1, -5, 0.0873F, 0, -0.0436F));

        var front_2_r2 = teeth.addOrReplaceChild("front_2_r2",
                CubeListBuilder.create()
                        .texOffs(5, 55)
                        .addBox(-0.2F, -2, -1, 1, 2, 1, new CubeDeformation(0))
                        .texOffs(5, 55)
                        .addBox(-6.8F, -2, -1, 1, 2, 1, new CubeDeformation(0)),
                PartPose.offsetAndRotation(9, -1, -5, 0.0436F, 0, 0));

        var tongue = body.addOrReplaceChild("tongue",
                CubeListBuilder.create()
                        .texOffs(11, 44)
                        .addBox(-3, 0, -6, 4, 1, 6, new CubeDeformation(0)),
                PartPose.offsetAndRotation(1, -10, 0, -0.2618F, 0, 0));

        var tongue_2_r1 = tongue.addOrReplaceChild("tongue_2_r1",
                CubeListBuilder.create()
                        .texOffs(10, 52)
                        .addBox(-3, -1, -6, 4, 1, 7, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 1, -6, 1.3526F, 0, 0));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T state) {
        super.setupAnim(state);
        if (state.isHiding) {
            this.head.xRot = 0;
            this.tongue.visible = false;
        } else {
            this.tongue.visible = true;
        }

        this.idleAnimation.apply(state.idleAnimation, state.ageInTicks);
        this.jumpAnimation.apply(state.jumpAnimation, state.ageInTicks);
    }

}
