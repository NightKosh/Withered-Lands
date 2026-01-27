package nightkosh.withered_lands.entity.ai.breeze;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Unit;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import nightkosh.withered_lands.entity.breeze.ABreeze;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ShootWhenStuck extends Behavior<ABreeze> {

    public ShootWhenStuck() {
        super(
                Map.of(
                        MemoryModuleType.ATTACK_TARGET,
                        MemoryStatus.VALUE_PRESENT,
                        MemoryModuleType.BREEZE_JUMP_INHALING,
                        MemoryStatus.VALUE_ABSENT,
                        MemoryModuleType.BREEZE_JUMP_TARGET,
                        MemoryStatus.VALUE_ABSENT,
                        MemoryModuleType.WALK_TARGET,
                        MemoryStatus.VALUE_ABSENT,
                        MemoryModuleType.BREEZE_SHOOT,
                        MemoryStatus.VALUE_ABSENT
                )
        );
    }

    @Override
    protected boolean checkExtraStartConditions(@Nonnull ServerLevel level, ABreeze breeze) {
        return breeze.isPassenger() || breeze.isInWater() || breeze.getEffect(MobEffects.LEVITATION) != null;
    }

    @Override
    protected boolean canStillUse(@Nonnull ServerLevel level, ABreeze breeze, long gameTime) {
        return false;
    }

    @Override
    protected void start(@Nonnull ServerLevel level, ABreeze breeze, long gameTime) {
        breeze.getBrain().setMemoryWithExpiry(MemoryModuleType.BREEZE_SHOOT, Unit.INSTANCE, 60);
    }

}
