package nightkosh.withered_lands.renderer.wolf;


import net.minecraft.client.model.animal.wolf.WolfModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.WolfArmorLayer;
import net.minecraft.client.renderer.entity.state.WolfRenderState;
import net.minecraft.resources.Identifier;
import nightkosh.withered_lands.entity.wolf.AUndeadDog;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class UndeadDogRender<T extends AUndeadDog> extends MobRenderer<AUndeadDog, WolfRenderState, WolfModel> {

    public UndeadDogRender(EntityRendererProvider.Context context) {
        super(context, new WolfModel(context.bakeLayer(ModelLayers.WOLF)), 0.5F);
        this.addLayer(new WolfArmorLayer(this, context.getModelSet(), context.getEquipmentRenderer()));
//        this(renderManager, new ModelUndeadDog());
    }

//
//    public UndeadDogRender(RenderManager renderManager, ModelUndeadDog model) {
//        super(renderManager, model, 0.5F);
//    }
//
//    @Override
//    protected float handleRotationFloat(EntityUndeadDog undeadDog, float par2) {
//        return undeadDog.getTailRotation();
//    }
//
//
//
    public Identifier getTextureLocation(WolfRenderState p_364302_) {
        return p_364302_.texture;
    }
//
    public WolfRenderState createRenderState() {
        return new WolfRenderState();
    }
//
//    public void extractRenderState(T wolf, WolfRenderState state, float xz) {
//        super.extractRenderState(wolf, state, xz);
//        state.isAngry = true;
//        state.tailAngle = wolf.getTailAngle();
//        state.headRollAngle = wolf.getHeadRollAngle(xz);
//        state.shakeAnim = 0;//wolf.getShakeAnim(xz);
//        state.texture = wolf.getTexture();
//        state.wetShade = 0;//wolf.getWetShade(xz);
//        state.collarColor = wolf.isTame() ? wolf.getCollarColor() : null;
//        state.bodyArmorItem = wolf.getBodyArmorItem().copy();
//    }

}