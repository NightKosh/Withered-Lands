package nightkosh.withered_lands.renderer.model.jellyfish;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import nightkosh.withered_lands.renderer.render_sate.JellyfishRenderState;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class AJellyfishModel<T extends JellyfishRenderState> extends EntityModel<T> {

    protected KeyframeAnimation moveLegsAnimation;
    protected KeyframeAnimation inflateAnimation;

    public AJellyfishModel(ModelPart root) {
        super(root);
        initAnimations();
    }

    protected abstract void initAnimations();

    @Override
    public void setupAnim(T state) {
        super.setupAnim(state);

        this.moveLegsAnimation.apply(state.moveLegsAnimation, state.ageInTicks);
        this.inflateAnimation.apply(state.inflateAnimation, state.ageInTicks);
    }

}
