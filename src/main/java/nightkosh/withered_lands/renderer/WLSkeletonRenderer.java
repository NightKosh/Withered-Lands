package nightkosh.withered_lands.renderer;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.Identifier;
import nightkosh.withered_lands.core.WLTextures;
import nightkosh.withered_lands.entity.WLSkeleton;
import nightkosh.withered_lands.renderer.model.WLSkeletonModel;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WLSkeletonRenderer extends AbstractSkeletonRenderer<WLSkeleton, SkeletonRenderState> {

    public WLSkeletonRenderer(EntityRendererProvider.Context context) {
        super(context, ModelLayers.SKELETON_ARMOR, new WLSkeletonModel(context.bakeLayer(WLSkeletonModel.LAYER)));
    }

    @Nonnull
    @Override
    public Identifier getTextureLocation(SkeletonRenderState state) {
        return WLTextures.SKELETON;
    }

    @Override
    public SkeletonRenderState createRenderState() {
        return new SkeletonRenderState();
    }

}
