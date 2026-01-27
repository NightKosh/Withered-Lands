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
public class BlizzardWindCharge extends AWindCharge {

    public BlizzardWindCharge(EntityType<? extends AbstractWindCharge> entityType, Level level) {
        super(entityType, level);
    }

    public BlizzardWindCharge(ABreeze breeze, Level level) {
        super(breeze, level);
    }

    @Override
    protected void applyEffect(LivingEntity entity) {
        entity.setTicksFrozen(entity.getTicksFrozen() + TimeHelper.SECONDS_10);
        entity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, TimeHelper.SECONDS_5), this);
    }

}
