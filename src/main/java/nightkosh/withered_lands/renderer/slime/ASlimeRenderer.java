package nightkosh.withered_lands.renderer.slime;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.slime.SlimeModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SlimeOuterLayer;
import net.minecraft.client.renderer.entity.state.SlimeRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import nightkosh.withered_lands.entity.slime.ASlime;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class ASlimeRenderer extends MobRenderer<ASlime, WLSlimeRenderState, SlimeModel> {

    private static final ItemModelResolver ITEM_MODEL_RESOLVER = Minecraft.getInstance().getItemModelResolver();

    public ASlimeRenderer(EntityRendererProvider.Context context) {
        super(context, new SlimeModel(context.bakeLayer(ModelLayers.SLIME)), 0.25F);
        this.addLayer(new WLSlimeOuterLayer(this, context.getModelSet()));
    }

    @Override
    protected float getShadowRadius(WLSlimeRenderState state) {
        return state.size * 0.25F;
    }

    @Override
    protected void scale(WLSlimeRenderState state, PoseStack poseStack) {
        float f = 0.999F;
        poseStack.scale(0.999F, 0.999F, 0.999F);
        poseStack.translate(0.0F, 0.001F, 0.0F);
        float f1 = state.size;
        float f2 = state.squish / (f1 * 0.5F + 1.0F);
        float f3 = 1.0F / (f2 + 1.0F);
        poseStack.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
    }

    @Override
    public WLSlimeRenderState createRenderState() {
        return new WLSlimeRenderState();
    }

    @Override
    public void extractRenderState(ASlime slime, WLSlimeRenderState state, float partialTick) {
        super.extractRenderState(slime, state, partialTick);
        state.slime = slime;
        state.squish = Mth.lerp(partialTick, slime.oSquish, slime.squish);
        state.size = slime.getSize();
    }

    @Override
    public void submit(WLSlimeRenderState state, PoseStack poseStack, SubmitNodeCollector nodeCollector, CameraRenderState camera) {
        super.submit(state, poseStack, nodeCollector, camera);
//        boolean flag = state.appearsGlowing() && state.isInvisible;
//        if (!state.isInvisible || flag) {
//            if (state.size > 1) {
//                if (state.size > 2) {
//                    if (!sludge.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).isEmpty()) {
//                        CorpseRendererHelper.renderCorpseInsideSlime(EnumCorpse.getById((byte) sludge.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItemDamage()),
//                                sludge.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getTagCompound(), x, y, z);
//                    }
//                    renderItem(state, state.slime, new ItemStack(Items.GOLDEN_APPLE), poseStack);// x + 0.5, y + 1, z + 0.5, entityYaw, partialTicks);
//                    renderItem(sludge.world, sludge.getItemStackFromSlot(EntityEquipmentSlot.CHEST), x - 0.5, y + 1, z - 0.5, entityYaw, partialTicks);
//                    renderItem(sludge.world, sludge.getItemStackFromSlot(EntityEquipmentSlot.LEGS), x + 0.5, y, z - 0.5, entityYaw, partialTicks);
//                    renderItem(sludge.world, sludge.getItemStackFromSlot(EntityEquipmentSlot.FEET), x - 0.5, y, z + 0.5, entityYaw, partialTicks);
//                } else {
//                    renderItem(sludge.world, sludge.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND), x - 0.25, y - 0.25, z + 0.25, entityYaw, partialTicks);
//                    renderItem(sludge.world, sludge.getItemStackFromSlot(EntityEquipmentSlot.HEAD), x + 0.25, y + 0.25, z + 0.25, entityYaw, partialTicks);
//                    renderItem(sludge.world, sludge.getItemStackFromSlot(EntityEquipmentSlot.CHEST), x - 0.25, y + 0.252, z - 0.25, entityYaw, partialTicks);
//                    renderItem(sludge.world, sludge.getItemStackFromSlot(EntityEquipmentSlot.LEGS), x + 0.25, y - 0.25, z - 0.25, entityYaw, partialTicks);
//                    renderItem(sludge.world, sludge.getItemStackFromSlot(EntityEquipmentSlot.FEET), x, y, z, entityYaw, partialTicks);
//                }
//            }
//        }


    }

    private void renderItem(WLSlimeRenderState state, ASlime entity, ItemStack stack, PoseStack poseStack) {

        poseStack.pushPose();

//        // Смещение, чтобы предмет “сел” в ладонь (подстраивается под модель/предмет)
//        poseStack.translate((rightHand ? -1 : 1) * 0.0625F, 0.45F, -0.05F);

//        // Поворот предмета в ладони
//        poseStack.mulPose(com.mojang.math.Axis.XP.rotationDegrees(-90.0F));
//        poseStack.mulPose(com.mojang.math.Axis.YP.rotationDegrees(180.0F));

        ITEM_MODEL_RESOLVER.updateForLiving(state.headItem, stack, ItemDisplayContext.HEAD, entity);
        // Рендер предмета
//        this.itemInHandRenderer.renderItem(
//                entity,
//                stack,
//                rightHand ? ItemDisplayContext.THIRD_PERSON_RIGHT_HAND : ItemDisplayContext.THIRD_PERSON_LEFT_HAND,
//                !rightHand, // leftHand = true => зеркалим
//                poseStack,
//                buffer,
//                packedLight
//        );

        poseStack.popPose();
    }



}
