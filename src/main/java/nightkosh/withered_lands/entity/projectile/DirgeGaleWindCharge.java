package nightkosh.withered_lands.entity.projectile;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.hurtingprojectile.windcharge.AbstractWindCharge;
import net.minecraft.world.level.Level;
import nightkosh.withered_lands.entity.breeze.ABreeze;
import nightkosh.withered_lands.helper.TimeHelper;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class DirgeGaleWindCharge extends AWindCharge {

    public DirgeGaleWindCharge(EntityType<? extends AbstractWindCharge> entityType, Level level) {
        super(entityType, level);
    }

    public DirgeGaleWindCharge(ABreeze breeze, Level level) {
        super(breeze, level);
    }

    @Override
    protected void applyEffect(LivingEntity entity) {
        //TODO RUST
        entity.igniteForTicks(TimeHelper.SECONDS_8);
    }

}
