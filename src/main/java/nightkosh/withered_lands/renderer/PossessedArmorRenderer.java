package nightkosh.withered_lands.renderer;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.zombie.ZombieModel;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.Identifier;
import nightkosh.withered_lands.core.WLTextures;
import nightkosh.withered_lands.entity.PossessedArmor;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PossessedArmorRenderer extends HumanoidMobRenderer<PossessedArmor, ZombieRenderState, HumanoidModel<ZombieRenderState>> {

    public PossessedArmorRenderer(EntityRendererProvider.Context context) {
        super(context, new HumanoidModel(context.bakeLayer(ModelLayers.ZOMBIE)), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this,
                ArmorModelSet.bake(ModelLayers.ZOMBIE_ARMOR, context.getModelSet(), ZombieModel::new),
                context.getEquipmentRenderer()));
    }

    @Override
    public ZombieRenderState createRenderState() {
        return new ZombieRenderState();
    }

    @Nonnull
    @Override
    public Identifier getTextureLocation(ZombieRenderState state) {
        return WLTextures.POSSESSED_ARMOR;
    }

}
