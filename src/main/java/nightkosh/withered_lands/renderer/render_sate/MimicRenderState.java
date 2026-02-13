package nightkosh.withered_lands.renderer.render_sate;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class MimicRenderState extends LivingEntityRenderState {

    public MimicRenderState() {

    }

    public boolean isHiding;

    public final AnimationState idleAnimation = new AnimationState();
    public final AnimationState jumpAnimation = new AnimationState();

}
