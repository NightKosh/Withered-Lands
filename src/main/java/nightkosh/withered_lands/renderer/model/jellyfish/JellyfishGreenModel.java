package nightkosh.withered_lands.renderer.model.jellyfish;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import nightkosh.withered_lands.core.ModInfo;
import nightkosh.withered_lands.renderer.model.animation.GreenJellyfishAnimations;
import nightkosh.withered_lands.renderer.render_sate.JellyfishRenderState;

import static net.minecraft.resources.Identifier.fromNamespaceAndPath;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class JellyfishGreenModel<T extends JellyfishRenderState> extends AJellyfishModel<T> {

    public static final ModelLayerLocation LAYER = new ModelLayerLocation(
            fromNamespaceAndPath(ModInfo.ID, "green_jellyfish"), "main");

    private final ModelPart body;
    private final ModelPart tentacles;

    public JellyfishGreenModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.tentacles = this.body.getChild("tentacles");
    }

    @Override
    protected void initAnimations() {
        this.moveLegsAnimation = GreenJellyfishAnimations.MOVE_LEGS.bake(root);
        this.inflateAnimation = GreenJellyfishAnimations.INFLATE.bake(root);
    }

    public static LayerDefinition createBodyLayer() {
        var definition = new MeshDefinition();
        var root = definition.getRoot();

        var body = root.addOrReplaceChild("body",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-3.5F, 0, -3.5F, 7, 5, 7, new CubeDeformation(0)), 
                PartPose.offset(0, 12, 0));

        body.addOrReplaceChild("tentacles",
                CubeListBuilder.create()
                        .texOffs(0, 20)
                        .addBox(-2.5F, 0, -2.5F, 5, 7, 5, new CubeDeformation(0)), 
                PartPose.offset(0, 5, 0));

        return LayerDefinition.create(definition, 32, 32);
    }

}
