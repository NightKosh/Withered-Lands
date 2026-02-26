package nightkosh.withered_lands.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class MossLayerBlock extends Block {

    private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 1, 16);
    private static final MapCodec<MossLayerBlock> CODEC = simpleCodec(MossLayerBlock::new);

    public MossLayerBlock(Properties properties) {
        super(properties);
    }

    public MossLayerBlock(ResourceKey key) {
        this(Properties.of()
                .mapColor(MapColor.COLOR_GREEN)
                .replaceable()
                .forceSolidOff()
                .randomTicks()
                .strength(0.1F)
                .sound(SoundType.MOSS_CARPET)
                .pushReaction(PushReaction.DESTROY)
                .setId(key));
    }

    @Nonnull
    @Override
    public MapCodec<MossLayerBlock> codec() {
        return CODEC;
    }

    @Nonnull
    @Override
    protected VoxelShape getShape(
            @Nonnull BlockState state, @Nonnull BlockGetter level,
            @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected boolean useShapeForLightOcclusion(@Nonnull BlockState state) {
        return true;
    }

    @Override
    protected boolean canSurvive(@Nonnull BlockState state, LevelReader level, BlockPos pos) {
        var stateBelow = level.getBlockState(pos.below());
        return !stateBelow.isAir() && stateBelow.isSolidRender();
    }

    @Nonnull
    @Override
    public MutableComponent getName() {
        return Blocks.MOSS_CARPET.getName();
    }

    @Override
    protected BlockState updateShape(
            BlockState state,
            @Nonnull LevelReader level,
            @Nonnull ScheduledTickAccess tickAccess,
            @Nonnull BlockPos pos,
            @Nonnull Direction direction,
            @Nonnull BlockPos pos1,
            @Nonnull BlockState state1,
            @Nonnull RandomSource random) {
        return !state.canSurvive(level, pos) ?
                Blocks.AIR.defaultBlockState() :
                super.updateShape(state, level, tickAccess, pos, direction, pos1, state1, random);
    }

    @Override
    protected boolean canBeReplaced(@Nonnull BlockState state, BlockPlaceContext useContext) {
        return !useContext.replacingClickedOnBlock() || useContext.getClickedFace() == Direction.UP;
    }

}
