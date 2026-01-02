package nightkosh.withered_lands.renderer.cat;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.FelineRenderState;
import net.minecraft.resources.Identifier;
import nightkosh.withered_lands.core.WLTextures;
import nightkosh.withered_lands.entity.cat.ZombieCat;
import nightkosh.withered_lands.renderer.model.UndeadCatModel;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ZombieCatRenderer extends MobRenderer<ZombieCat, FelineRenderState, UndeadCatModel<FelineRenderState>> {

    public ZombieCatRenderer(EntityRendererProvider.Context context) {
        super(context, new UndeadCatModel(context.bakeLayer(ModelLayers.CAT)), 0.5F);
    }

    @Override
    public FelineRenderState createRenderState() {
        return new FelineRenderState();
    }

    @Nonnull
    @Override
    public Identifier getTextureLocation(FelineRenderState state) {
        return WLTextures.ZOMBIE_CAT;
    }

}
