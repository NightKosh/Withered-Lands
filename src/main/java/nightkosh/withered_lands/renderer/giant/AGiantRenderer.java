package nightkosh.withered_lands.renderer.giant;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.zombie.GiantZombieModel;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import nightkosh.withered_lands.entity.giant.AGiant;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class AGiantRenderer extends MobRenderer<AGiant, ZombieRenderState, HumanoidModel<ZombieRenderState>> {

    public AGiantRenderer(EntityRendererProvider.Context context) {
        super(context, new GiantZombieModel(context.bakeLayer(ModelLayers.GIANT)), 0.5F * 6);//0.5F * scale);
        this.addLayer(new ItemInHandLayer<>(this));
        this.addLayer(
                new HumanoidArmorLayer<>(
                        this,
                        ArmorModelSet.bake(ModelLayers.GIANT_ARMOR, context.getModelSet(), GiantZombieModel::new),
                        context.getEquipmentRenderer()
                )
        );
    }

    @Override
    public ZombieRenderState createRenderState() {
        return new ZombieRenderState();
    }

    @Override
    public void extractRenderState(AGiant giant, ZombieRenderState state, float partialTick) {
        super.extractRenderState(giant, state, partialTick);
        HumanoidMobRenderer.extractHumanoidRenderState(giant, state, partialTick, this.itemModelResolver);
    }

}
