package nightkosh.withered_lands.renderer.model.jellyfish;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import nightkosh.withered_lands.core.ModInfo;
import nightkosh.withered_lands.renderer.model.animation.BlueJellyfishAnimations;
import nightkosh.withered_lands.renderer.render_sate.JellyfishRenderState;

import static net.minecraft.resources.Identifier.fromNamespaceAndPath;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class JellyfishBlueModel<T extends JellyfishRenderState> extends AJellyfishModel<T> {

    public static final ModelLayerLocation LAYER = new ModelLayerLocation(
            fromNamespaceAndPath(ModInfo.ID, "blue_jellyfish"), "main");

    private final ModelPart body;
    private final ModelPart tentacles_body;
    private final ModelPart tentacles_head;

    public JellyfishBlueModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.tentacles_body = this.body.getChild("tentacles_body");
        this.tentacles_head = this.body.getChild("tentacles_head");
    }

    @Override
    protected void initAnimations() {
        this.moveLegsAnimation = BlueJellyfishAnimations.MOVE_LEGS.bake(root);
        this.inflateAnimation = BlueJellyfishAnimations.INFLATE.bake(root);
    }

    public static LayerDefinition createBodyLayer() {
        var meshdefinition = new MeshDefinition();
        var root = meshdefinition.getRoot();

        var body = root.addOrReplaceChild("body",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-8, 0, -8, 16, 8, 16, new CubeDeformation(0))
                        .texOffs(0, 25)
                        .addBox(-3, -1, -3, 6, 14, 6, new CubeDeformation(0)),
                PartPose.offset(0, -4, 0));

        body.addOrReplaceChild("body_2_r1",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-8, -5, -8, 16, 8, 16, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 6, 0, 0, -0.7854F, 0));

        body.addOrReplaceChild("tentacles_body",
                CubeListBuilder.create()
                        .texOffs(25, 25)
                        .addBox(-7, -1, 3, 4, 17, 4, new CubeDeformation(0))
                        .texOffs(42, 25)
                        .addBox(-7, -1, -7, 4, 17, 4, new CubeDeformation(0))
                        .texOffs(25, 43)
                        .addBox(3, -1, 3, 4, 17, 4, new CubeDeformation(0))
                        .texOffs(46, 43)
                        .addBox(3, -1, -7, 4, 17, 4, new CubeDeformation(0)),
                PartPose.offset(0, 2, 0));

        body.addOrReplaceChild("tentacles_head",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-3, -1, 0, 6, 15, 0, new CubeDeformation(0))
                        .texOffs(0, -6)
                        .addBox(0, -1, -3, 0, 15, 6, new CubeDeformation(0)),
                PartPose.offsetAndRotation(0, 13, 0, 0, -0.7854F, 0));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

}
