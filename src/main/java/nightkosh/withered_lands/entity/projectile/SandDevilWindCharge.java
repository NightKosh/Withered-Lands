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
public class SandDevilWindCharge extends AWindCharge {

    public SandDevilWindCharge(EntityType<? extends AbstractWindCharge> entityType, Level level) {
        super(entityType, level);
    }

    public SandDevilWindCharge(ABreeze breeze, Level level) {
        super(breeze, level);
    }

    @Override
    protected void applyEffect(LivingEntity entity) {
        entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, TimeHelper.SECONDS_10), this);
        entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, TimeHelper.SECONDS_10), this);
    }

}
