package nightkosh.withered_lands.renderer.water;


import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.Identifier;
import nightkosh.withered_lands.core.WLTextures;
import nightkosh.withered_lands.entity.water.AWaterWalkingMob;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SwampThingRenderer extends HumanoidMobRenderer<AWaterWalkingMob, ZombieRenderState, HumanoidModel<ZombieRenderState>> {

    public SwampThingRenderer(EntityRendererProvider.Context context) {
        super(context, new HumanoidModel(context.bakeLayer(ModelLayers.ZOMBIE)), 0.5F);
        this.addLayer(new SwampThingLayer(this, context.getModelSet()));
    }

    @Override
    public ZombieRenderState createRenderState() {
        return new ZombieRenderState();
    }

    @Nonnull
    @Override
    public Identifier getTextureLocation(ZombieRenderState entity) {
        return WLTextures.SWAMP_THING;
    }

}
