package nightkosh.withered_lands.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ExtinguishedWallTorchBlock extends ExtinguishedTorchBlock {

    public static final MapCodec<ExtinguishedWallTorchBlock> CODEC = simpleCodec(ExtinguishedWallTorchBlock::new);
    public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
    private static final Map<Direction, VoxelShape> SHAPES = Shapes.rotateHorizontal(Block.boxZ(5, 3, 13, 11, 16));

    public ExtinguishedWallTorchBlock(Properties properties) {
        super(properties);
    }

    public ExtinguishedWallTorchBlock(ResourceKey key) {
        this(Properties.of()
                .noCollision()
                .instabreak()
                .sound(SoundType.WOOD)
                .pushReaction(PushReaction.DESTROY)
                .setId(key));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Nonnull
    @Override
    protected MapCodec<? extends BaseTorchBlock> codec() {
        return CODEC;
    }

    @Nonnull
    @Override
    protected VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter level,
                                  @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return getShape(state);
    }

    public static VoxelShape getShape(BlockState state) {
        return SHAPES.get(state.getValue(FACING));
    }

    @Override
    protected boolean canSurvive(BlockState state, @Nonnull LevelReader level, @Nonnull BlockPos pos) {
        return canSurvive(level, pos, state.getValue(FACING));
    }

    public static boolean canSurvive(LevelReader level, BlockPos pos, Direction facing) {
        var blockpos = pos.relative(facing.getOpposite());
        return level.getBlockState(blockpos)
                .isFaceSturdy(level, blockpos, facing);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        var blockState = this.defaultBlockState();
        var levelReader = context.getLevel();
        var blockPos = context.getClickedPos();
        var adirection = context.getNearestLookingDirections();

        for (var direction : adirection) {
            if (direction.getAxis().isHorizontal()) {
                blockState = blockState.setValue(FACING, direction.getOpposite());
                if (blockState.canSurvive(levelReader, blockPos)) {
                    return blockState;
                }
            }
        }

        return null;
    }

    @Nonnull
    @Override
    protected BlockState updateShape(
            @Nonnull BlockState state, @Nonnull LevelReader levelReader, @Nonnull ScheduledTickAccess tickAccess,
            @Nonnull BlockPos blockPos, Direction direction,
            @Nonnull BlockPos blockPos1, @Nonnull BlockState state1, @Nonnull RandomSource randomSource
    ) {
        return direction.getOpposite() == state.getValue(FACING) &&
                !state.canSurvive(levelReader, blockPos) ?
                Blocks.AIR.defaultBlockState() :
                state;
    }

    @Nonnull
    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Nonnull
    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

}
