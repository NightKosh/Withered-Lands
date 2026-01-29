package nightkosh.withered_lands.renderer.breeze;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.breeze.BreezeModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.BreezeRenderState;
import net.minecraft.resources.Identifier;
import nightkosh.withered_lands.entity.breeze.ABreeze;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class ABreezeRenderer extends MobRenderer<ABreeze, BreezeRenderState, EntityModel<BreezeRenderState>> {

    public ABreezeRenderer(EntityRendererProvider.Context context) {
        this(context, new BreezeModel(context.bakeLayer(ModelLayers.BREEZE)), 0.5F);
        addWindLayer(this, context.getModelSet());
        addEyeLayer(this, context.getModelSet());
    }

    public ABreezeRenderer(EntityRendererProvider.Context context, EntityModel<BreezeRenderState> model, float shadowRadius) {
        super(context, model, shadowRadius);
    }

    @Override
    public BreezeRenderState createRenderState() {
        return new BreezeRenderState();
    }

    @Override
    public void extractRenderState(ABreeze breeze, BreezeRenderState state, float partialTick) {
        super.extractRenderState(breeze, state, partialTick);
        state.idle.copyFrom(breeze.idle);
        state.shoot.copyFrom(breeze.shoot);
        state.slide.copyFrom(breeze.slide);
        state.slideBack.copyFrom(breeze.slideBack);
        state.inhale.copyFrom(breeze.inhale);
        state.longJump.copyFrom(breeze.longJump);
    }

    @Nonnull
    @Override
    public abstract Identifier getTextureLocation(BreezeRenderState state);

    protected abstract void addWindLayer(ABreezeRenderer renderer, EntityModelSet modelSet);

    protected abstract void addEyeLayer(ABreezeRenderer renderer, EntityModelSet modelSet);

}
