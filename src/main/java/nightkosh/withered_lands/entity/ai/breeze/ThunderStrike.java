package nightkosh.withered_lands.entity.ai.breeze;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import nightkosh.withered_lands.entity.breeze.ABreeze;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ThunderStrike extends BreezeShoot {

    @Override
    protected void attack(@Nonnull ServerLevel level, ABreeze breeze, LivingEntity target) {
        var pos = target.blockPosition();
        BreezeUtil.createLightning(
                level,
                pos.getX() + 3.5 - level.random.nextInt(7),
                pos.getY(),
                pos.getZ() + 3.5 - level.random.nextInt(7));
    }

    @Override
    protected int getShootCooldownTicks() {
        return 40;
    }

}
