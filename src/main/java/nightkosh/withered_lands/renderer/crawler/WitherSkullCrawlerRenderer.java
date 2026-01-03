package nightkosh.withered_lands.renderer.crawler;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import nightkosh.withered_lands.core.WLTextures;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WitherSkullCrawlerRenderer extends ASkullCrawlerRenderer {

    public WitherSkullCrawlerRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Nonnull
    @Override
    public Identifier getTextureLocation(LivingEntityRenderState state) {
        return WLTextures.WITHER_SKULL_CRAWLER;
    }

}
