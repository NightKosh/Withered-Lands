package nightkosh.withered_lands.renderer.wolf;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import nightkosh.withered_lands.core.WLTextures;
import nightkosh.withered_lands.entity.wolf.ZombieDog;
import nightkosh.withered_lands.renderer.model.UndeadWolfModel;
import nightkosh.withered_lands.renderer.render_sate.UndeadWolfRenderState;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ZombieDogRenderer extends MobRenderer<ZombieDog, UndeadWolfRenderState, UndeadWolfModel<UndeadWolfRenderState>> {

    public ZombieDogRenderer(EntityRendererProvider.Context context) {
        super(context, new UndeadWolfModel(context.bakeLayer(ModelLayers.WOLF)), 0.5F);
    }

    @Override
    public UndeadWolfRenderState createRenderState() {
        return new UndeadWolfRenderState();
    }

    @Nonnull
    @Override
    public Identifier getTextureLocation(UndeadWolfRenderState state) {
        return WLTextures.ZOMBIE_DOG;
    }

}
