package nightkosh.withered_lands.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseTorchBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import nightkosh.withered_lands.core.WLAdvancements;
import org.jspecify.annotations.Nullable;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ExtinguishedTorchBlock extends BaseTorchBlock {

    public static final MapCodec<ExtinguishedTorchBlock> CODEC = simpleCodec(ExtinguishedTorchBlock::new);

    public ExtinguishedTorchBlock(Properties properties) {
        super(properties);
    }

    public ExtinguishedTorchBlock(ResourceKey key) {
        this(BlockBehaviour.Properties.of()
                .noCollision()
                .instabreak()
                .sound(SoundType.WOOD)
                .pushReaction(PushReaction.DESTROY)
                .setId(key));
    }

    @Nonnull
    @Override
    protected MapCodec<? extends BaseTorchBlock> codec() {
        return CODEC;
    }

    @Override
    public void playerDestroy(
            @Nonnull Level level, @Nonnull Player player, @Nonnull BlockPos pos, @Nonnull BlockState state,
            @Nullable BlockEntity blockEntity, @Nonnull ItemStack tool) {
        super.playerDestroy(level, player, pos, state, blockEntity, tool);

        WLAdvancements.giveAdvancement(player, level, WLAdvancements.LINGERING_SHADOW);
    }

}
