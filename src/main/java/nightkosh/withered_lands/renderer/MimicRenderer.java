package nightkosh.withered_lands.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import nightkosh.withered_lands.core.WLTextures;
import nightkosh.withered_lands.entity.Mimic;
import nightkosh.withered_lands.renderer.model.MimicModel;
import nightkosh.withered_lands.renderer.render_sate.MimicRenderState;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class MimicRenderer extends MobRenderer<Mimic, MimicRenderState, MimicModel<MimicRenderState>> {

    public MimicRenderer(EntityRendererProvider.Context context) {
        super(context, new MimicModel(context.bakeLayer(MimicModel.LAYER)), 0.5F);
    }

    @Nonnull
    @Override
    public Identifier getTextureLocation(MimicRenderState renderState) {
        return WLTextures.MIMIC;
    }

    @Override
    public MimicRenderState createRenderState() {
        return new MimicRenderState();
    }

    @Override
    public void extractRenderState(Mimic mimic, MimicRenderState state, float partialTick) {
        super.extractRenderState(mimic, state, partialTick);
        state.isHiding = mimic.isHiding();
        state.idleAnimation.copyFrom(mimic.idleAnimation);
        state.jumpAnimation.copyFrom(mimic.jumpAnimationState);
    }

}
