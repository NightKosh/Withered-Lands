package nightkosh.withered_lands.renderer.ghost;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import nightkosh.withered_lands.core.WLTextures;
import nightkosh.withered_lands.entity.ghost.LostSoul;
import nightkosh.withered_lands.renderer.model.LostSoulModel;
import org.jspecify.annotations.Nullable;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class LostSoulRenderer extends MobRenderer<LostSoul, LivingEntityRenderState, LostSoulModel> {

    public LostSoulRenderer(EntityRendererProvider.Context context) {
        super(context, new LostSoulModel(context.bakeLayer(LostSoulModel.LAYER)), 0.3F);
    }

    @Override
    protected @Nullable RenderType getRenderType(
            LivingEntityRenderState state, boolean bodyVisible, boolean translucent, boolean glowing) {
        return RenderTypes.itemEntityTranslucentCull(this.getTextureLocation(state));
    }

    @Override
    protected int getBlockLightLevel(LostSoul entity, @Nonnull BlockPos pos) {
        return 15;
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Nonnull
    @Override
    public Identifier getTextureLocation(LivingEntityRenderState state) {
        return WLTextures.LOST_SOUL;
    }

}
