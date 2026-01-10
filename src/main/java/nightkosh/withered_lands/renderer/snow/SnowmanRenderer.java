package nightkosh.withered_lands.renderer.snow;

import net.minecraft.client.model.animal.golem.SnowGolemModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.SnowGolemRenderState;
import net.minecraft.resources.Identifier;
import nightkosh.withered_lands.core.WLTextures;
import nightkosh.withered_lands.entity.snow.Snowman;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class SnowmanRenderer extends MobRenderer<Snowman, SnowGolemRenderState, SnowGolemModel> {

    public SnowmanRenderer(EntityRendererProvider.Context context) {
        super(context, new SnowGolemModel(context.bakeLayer(ModelLayers.SNOW_GOLEM)), 0.5F);
    }

    @Nonnull
    @Override
    public Identifier getTextureLocation(SnowGolemRenderState state) {
        return WLTextures.SNOWMAN;
    }

    @Override
    public SnowGolemRenderState createRenderState() {
        return new SnowGolemRenderState();
    }

}
