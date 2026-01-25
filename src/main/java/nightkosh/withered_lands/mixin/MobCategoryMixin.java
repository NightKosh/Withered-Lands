package nightkosh.withered_lands.mixin;

import net.minecraft.world.entity.MobCategory;
import nightkosh.withered_lands.core.WLConfigs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@Mixin(MobCategory.class)
public abstract class MobCategoryMixin {

    private static final int DEFAULT_CAP = 70;

    @Final
    @Shadow
    @Mutable
    private int max;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void wl$changeCaps(CallbackInfo ci) {
        MobCategory self = (MobCategory) (Object) this;

        if (self.name().equals("MONSTER")) {
            this.max = (int) (DEFAULT_CAP * WLConfigs.MOB_CAP_MULTIPLIER.get());
        }
    }

}
