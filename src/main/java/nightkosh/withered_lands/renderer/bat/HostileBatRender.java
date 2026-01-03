package nightkosh.withered_lands.renderer.bat;

import net.minecraft.client.model.ambient.BatModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.BatRenderState;
import nightkosh.withered_lands.entity.bat.AHostileBat;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class HostileBatRender extends MobRenderer<AHostileBat, BatRenderState, BatModel> {

    public HostileBatRender(EntityRendererProvider.Context context) {
        super(context, new BatModel(context.bakeLayer(ModelLayers.BAT)), 0.25F);
    }

    @Override
    public void extractRenderState(AHostileBat bat, BatRenderState state, float xz) {
        super.extractRenderState(bat, state, xz);
        state.isResting = bat.isResting();
        state.flyAnimationState.copyFrom(bat.flyAnimationState);
        state.restAnimationState.copyFrom(bat.restAnimationState);
    }

    @Override
    public BatRenderState createRenderState() {
        return new BatRenderState();
    }

}
