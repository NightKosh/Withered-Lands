package nightkosh.withered_lands.renderer.bat;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.BatRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import nightkosh.withered_lands.entity.bat.AHostileBat;

import javax.annotation.Nonnull;

import static nightkosh.withered_lands.core.WLTextures.BLAZING_BAT;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlazingBatRenderer extends HostileBatRender {

    public BlazingBatRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected int getBlockLightLevel(AHostileBat entity, @Nonnull BlockPos pos) {
        return 10;
    }

    @Override
    @Nonnull
    public Identifier getTextureLocation(@Nonnull BatRenderState batRenderState) {
        return BLAZING_BAT;
    }

}
