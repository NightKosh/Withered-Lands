package nightkosh.withered_lands.renderer.wolf;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import nightkosh.withered_lands.core.WLTextures;
import nightkosh.withered_lands.entity.wolf.Barghest;
import nightkosh.withered_lands.renderer.model.UndeadWolfModel;
import nightkosh.withered_lands.renderer.render_sate.BarghestRenderState;
import org.jspecify.annotations.Nullable;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BarghestRenderer extends MobRenderer<Barghest, BarghestRenderState, UndeadWolfModel<BarghestRenderState>> {

    public BarghestRenderer(EntityRendererProvider.Context context) {
        super(context, new UndeadWolfModel(context.bakeLayer(ModelLayers.WOLF)), 0.5F);
        this.addLayer(new BarghestEyesLayer(this));
    }

    @Override
    public BarghestRenderState createRenderState() {
        return new BarghestRenderState();
    }

    @Override
    protected void scale(BarghestRenderState state, PoseStack poseStack) {
        poseStack.scale(2, 2, 2);
    }

    @Override
    public void extractRenderState(Barghest barghest, BarghestRenderState state, float xz) {
        super.extractRenderState(barghest, state, xz);
        state.isInvisible = barghest.isBarghestInvisible();
    }

    @Override
    protected @Nullable RenderType getRenderType(BarghestRenderState state, boolean p_115323_, boolean p_115324_, boolean p_115325_) {
        var identifier = this.getTextureLocation(state);
        if (state.isInvisible) {
            return RenderTypes.itemEntityTranslucentCull(identifier);
        } else {
            return this.model.renderType(identifier);
        }
    }

    @Nonnull
    @Override
    public Identifier getTextureLocation(BarghestRenderState state) {
        if (state.isInvisible) {
            return WLTextures.BARGHEST_INVISIBLE;
        } else {
            return WLTextures.BARGHEST;
        }
    }

}
