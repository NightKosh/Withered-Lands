package nightkosh.withered_lands.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.BaseTorchBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;

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

}
