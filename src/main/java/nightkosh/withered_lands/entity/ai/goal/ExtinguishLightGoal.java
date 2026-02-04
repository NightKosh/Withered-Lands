package nightkosh.withered_lands.entity.ai.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import nightkosh.withered_lands.core.WLBlocks;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.core.WLSounds;
import nightkosh.withered_lands.entity.AMonster;
import nightkosh.withered_lands.helper.TimeHelper;

import static nightkosh.withered_lands.WitheredLandsMod.LOGGER;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ExtinguishLightGoal extends Goal {

    private static final int RANGE = 35;

    protected final Level level;
    protected final AMonster mob;
    protected final int maxDepth;

    private int timeToTryToLook = 0;

    private BlockPos lightSourcePos;

    public ExtinguishLightGoal(AMonster mob, int maxDepth) {
        this.mob = mob;
        this.level = mob.level();
        this.maxDepth = maxDepth;
    }

    @Override
    public boolean canUse() {
        return this.mob.blockPosition().getY() < maxDepth && !this.mob.getMoveControl().hasWanted();
    }

    @Override
    public boolean canContinueToUse() {
        return this.timeToTryToLook <= TimeHelper.SECONDS_8 ||
                (this.lightSourcePos != null && this.ifLightSourceAlive());
    }

    @Override
    public void tick() {
        timeToTryToLook++;
        if (this.lightSourcePos == null) {
            if (this.timeToTryToLook == TimeHelper.SECONDS_8) {
                if (WLConfigs.DEBUG_MODE.get()) {
                    LOGGER.info("ExtinguishLightGoal - going to look for light source");
                }
                this.lightSourcePos = tryToGetLightSource(this.mob.blockPosition());
            }
        } else {
            this.mob.getMoveControl().setWantedPosition(
                    lightSourcePos.getX() + 0.5,
                    lightSourcePos.getY() + 0.5,
                    lightSourcePos.getZ() + 0.5,
                    1);
            if (this.mob.distanceToSqr(this.lightSourcePos.getX(), this.lightSourcePos.getY(), this.lightSourcePos.getZ()) <= 2.5) {
                this.mob.getLookControl().setLookAt(
                        this.lightSourcePos.getX(),
                        this.lightSourcePos.getY(),
                        this.lightSourcePos.getZ(),
                        0.25F, 0.25F);

                this.extinguish();
                this.stop();
            }
        }
    }

    @Override
    public void stop() {
        this.lightSourcePos = null;
        this.timeToTryToLook = 0;
        this.mob.getNavigation().stop();
    }

    protected boolean ifLightSourceAlive() {
        return this.lightSourcePos != null && isAcceptedLightBlock(this.lightSourcePos);
    }

    protected boolean isAcceptedLightBlock(BlockPos pos) {
        var state = this.level.getBlockState(pos);
        return state != null &&
                (state.is(Blocks.TORCH) || state.is(Blocks.COPPER_TORCH) || state.is(Blocks.SOUL_TORCH) ||
                state.is(Blocks.WALL_TORCH) || state.is(Blocks.COPPER_WALL_TORCH) || state.is(Blocks.SOUL_WALL_TORCH));
    }

    protected void extinguish() {
        if (ifLightSourceAlive()) {
            if (WLConfigs.DEBUG_MODE.get()) {
                LOGGER.info("ExtinguishLightGoal - going to extinguish light source at {}", this.lightSourcePos.toShortString());
            }
            var state = this.level.getBlockState(lightSourcePos);
            if (state.is(Blocks.TORCH)) {
                this.level.setBlock(this.lightSourcePos, WLBlocks.EXTINGUISHED_TORCH.get().defaultBlockState(), 3);
            } else if (state.is(Blocks.WALL_TORCH)) {
                var newState = WLBlocks.EXTINGUISHED_WALL_TORCH.get().defaultBlockState()
                        .setValue(HorizontalDirectionalBlock.FACING, state.getValue(HorizontalDirectionalBlock.FACING));
                this.level.setBlock(this.lightSourcePos, newState, 3);
            } else if (state.is(Blocks.COPPER_TORCH)) {
                this.level.setBlock(this.lightSourcePos, WLBlocks.EXTINGUISHED_COPPER_TORCH.get().defaultBlockState(), 3);
            } else if (state.is(Blocks.COPPER_WALL_TORCH)) {
                var newState = WLBlocks.EXTINGUISHED_COPPER_WALL_TORCH.get().defaultBlockState()
                        .setValue(HorizontalDirectionalBlock.FACING, state.getValue(HorizontalDirectionalBlock.FACING));
                this.level.setBlock(this.lightSourcePos, newState, 3);
            } else if (state.is(Blocks.SOUL_TORCH)) {
                this.level.setBlock(this.lightSourcePos, WLBlocks.EXTINGUISHED_SOUL_TORCH.get().defaultBlockState(), 3);
            } else if (state.is(Blocks.SOUL_WALL_TORCH)) {
                var newState = WLBlocks.EXTINGUISHED_SOUL_WALL_TORCH.get().defaultBlockState()
                        .setValue(HorizontalDirectionalBlock.FACING, state.getValue(HorizontalDirectionalBlock.FACING));
                this.level.setBlock(this.lightSourcePos, newState, 3);
            }
            if (this.level instanceof ServerLevel server) {
                this.mob.playSound(WLSounds.HOLLOW_STALKER_EXTINGUISH.get(), 1, 1);
                server.sendParticles(ParticleTypes.POOF,
                        this.lightSourcePos.getX() + 0.5,
                        this.lightSourcePos.getY() + 0.5,
                        this.lightSourcePos.getZ() + 0.5,
                        1,
                        0, 0, 0,
                        0);
            }
        }
    }

    private BlockPos tryToGetLightSource(BlockPos pos) {
        int minX = pos.getX() - RANGE;
        int minY = Math.max(this.level.getMinY(), pos.getY() - RANGE);
        int minZ = pos.getZ() - RANGE;
        int maxX = pos.getX() + RANGE;
        int maxY = Math.min(this.maxDepth, pos.getY() + RANGE);
        int maxZ = pos.getZ() + RANGE;

        var newPos = new BlockPos.MutableBlockPos();
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    newPos.set(x, y, z);
                    if (isAcceptedLightBlock(newPos)) {
                        if (WLConfigs.DEBUG_MODE.get()) {
                            LOGGER.info("ExtinguishLightGoal - found acceptable light source at {}", newPos.toShortString());
                        }
                        return newPos;
                    }
                }
            }
        }
        return null;
    }

}
