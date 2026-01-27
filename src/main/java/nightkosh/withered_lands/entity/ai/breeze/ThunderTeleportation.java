package nightkosh.withered_lands.entity.ai.breeze;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import nightkosh.withered_lands.entity.breeze.ABreeze;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ThunderTeleportation extends LongJump {

    @Override
    protected boolean setNewJumpPos(ServerLevel level, ABreeze breeze, BlockPos pos) {
        if (level.canSeeSky(pos)) {
            breeze.getBrain().setMemory(MemoryModuleType.BREEZE_JUMP_TARGET, pos);
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void doJump(@Nonnull ServerLevel level, ABreeze breeze) {
        var teleportPos = breeze.getBrain().getMemory(MemoryModuleType.BREEZE_JUMP_TARGET).orElse(null);
        if (teleportPos == null) {
            breeze.setPose(Pose.STANDING);
        } else {
            if (breeze.isInWater()) {
                breeze.getBrain().setMemory(MemoryModuleType.BREEZE_LEAVING_WATER, Unit.INSTANCE);
            }

            breeze.makePoofParticles();
            BreezeUtil.createLightning(level, breeze.getX(), breeze.getY(), breeze.getZ());
            BreezeUtil.createLightning(level, teleportPos.getX(), teleportPos.getY(), teleportPos.getZ());
            breeze.snapTo(teleportPos.getX(), teleportPos.getY(), teleportPos.getZ());
            breeze.makePoofParticles();

            breeze.setPose(Pose.LONG_JUMPING);
        }

    }

}
