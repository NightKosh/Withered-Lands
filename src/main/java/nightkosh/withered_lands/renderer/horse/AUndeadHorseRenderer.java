package nightkosh.withered_lands.renderer.horse;

import net.minecraft.client.model.animal.equine.HorseModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.HorseRenderState;
import net.minecraft.world.entity.EquipmentSlot;
import nightkosh.withered_lands.entity.horse.AUndeadHorse;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class AUndeadHorseRenderer extends MobRenderer<AUndeadHorse, HorseRenderState, HorseModel> {

    public AUndeadHorseRenderer(EntityRendererProvider.Context context) {
        super(context, new HorseModel(context.bakeLayer(ModelLayers.HORSE)), 0.75F);
    }

    @Override
    public HorseRenderState createRenderState() {
        return new HorseRenderState();
    }

    @Override
    public void extractRenderState(AUndeadHorse horse, HorseRenderState state, float partialTick) {
        super.extractRenderState(horse, state, partialTick);
        state.saddle = horse.getItemBySlot(EquipmentSlot.SADDLE).copy();
        state.bodyArmorItem = horse.getBodyArmorItem().copy();
        state.isRidden = horse.isVehicle();
    }

}
