package nightkosh.withered_lands.renderer.crawler;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import nightkosh.withered_lands.entity.crawler.ASkullCrawler;
import nightkosh.withered_lands.renderer.model.SkullCrawlerModel;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class ASkullCrawlerRenderer<T extends ASkullCrawler> extends MobRenderer<T, LivingEntityRenderState, SkullCrawlerModel<LivingEntityRenderState>> {

    public ASkullCrawlerRenderer(EntityRendererProvider.Context context) {
        super(context, new SkullCrawlerModel(context.bakeLayer(SkullCrawlerModel.LAYER)), 0.8F);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    protected float getFlipDegrees() {
        return 180;
    }

}
