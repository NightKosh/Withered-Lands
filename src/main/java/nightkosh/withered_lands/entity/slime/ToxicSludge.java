package nightkosh.withered_lands.entity.slime;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ToxicSludge extends ASlime {

//    protected static final Item[] MIDDLE_ITEMS = {
//            Items.BONE,
//            Items.BONE,
//            Items.ROTTEN_FLESH,
//            Items.ROTTEN_FLESH,
//            Items.ARROW,
//            Items.SPIDER_EYE,
//            Item.getItemFromBlock(Blocks.AIR)
//    };

    public ToxicSludge(EntityType<? extends ASlime> entityType, Level level) {
        super(entityType, level);
    }


//    public static boolean replaceBlock(World world, BlockPos pos) {
//        IBlockState state = world.getBlockState(pos);
//        Block block = state.getBlock();
//        if (block == Blocks.GRASS || block == Blocks.GRASS_PATH || block == Blocks.FARMLAND || block == Blocks.MYCELIUM ||
//                block == Blocks.DIRT && state.equals(StateHelper.PODZOL)) {
//            world.setBlockState(pos, StateHelper.DIRT);
//            return true;
//        } else if (block == Blocks.STONE && state.equals(StateHelper.STONE) || block == Blocks.MOSSY_COBBLESTONE) {
//            world.setBlockState(pos, StateHelper.COBBLESTONE);
//            return true;
//        } else if (block == Blocks.STONEBRICK && (state.equals(StateHelper.STONEBRICK) || state.equals(StateHelper.STONEBRICK_MOSSY))) {
//            world.setBlockState(pos, StateHelper.STONEBRICK_CRACKED);
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    protected void doBlockCollisions() {
//        if (ExtendedConfig.toxicSludgeAndWaterChangeBlocks) {
//            AxisAlignedBB axisalignedbb = this.getEntityBoundingBox();
//            BlockPos.PooledMutableBlockPos posPoolBottom = BlockPos.PooledMutableBlockPos.retain(axisalignedbb.minX - 0.001, axisalignedbb.minY - 0.001, axisalignedbb.minZ - 0.001);
//            BlockPos.PooledMutableBlockPos posPoolTop = BlockPos.PooledMutableBlockPos.retain(axisalignedbb.maxX + 0.001, axisalignedbb.maxY + 0.001, axisalignedbb.maxZ + 0.001);
//            BlockPos.PooledMutableBlockPos pool = BlockPos.PooledMutableBlockPos.retain();
//
//            if (this.world.isAreaLoaded(posPoolBottom, posPoolTop)) {
//                for (int x = posPoolBottom.getX(); x <= posPoolTop.getX(); ++x) {
//                    for (int y = posPoolBottom.getY(); y <= posPoolTop.getY(); ++y) {
//                        for (int z = posPoolBottom.getZ(); z <= posPoolTop.getZ(); ++z) {
//                            pool.setPos(x, y, z);
//                            replaceBlock(this.world, pool);
//                        }
//                    }
//                }
//            }
//
//            posPoolBottom.release();
//            posPoolTop.release();
//            pool.release();
//        }
//
//        super.doBlockCollisions();
//    }
//
//    @Override
//    protected boolean spawnCustomParticles() {
//        int size = this.getSlimeSize();
//        for (int j = 0; j < size * 8; ++j) {
//            float f = this.rand.nextFloat() * ((float) Math.PI * 2F);
//            float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
//            float f2 = MathHelper.sin(f) * size * 0.5F * f1;
//            float f3 = MathHelper.cos(f) * size * 0.5F * f1;
//            World world = this.world;
//            double d0 = this.posX + (double) f2;
//            double d1 = this.posZ + (double) f3;
//            world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, this.getEntityBoundingBox().minY, d1, 0, 0, 0);
//        }
//
//        this.playSound(SoundEvents.BLOCK_LAVA_EXTINGUISH, this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1) / 0.8F);
//        return true;
//    }
//
//    @Override
//    protected ToxicSludge createInstance() {
//        return new ToxicSludge(this.world);
//    }
//
//    @Override
//    public void onDeath(DamageSource source) {
//        if (!this.world.isRemote && this.getSlimeSize() > 1) {
//            IBlockState state = world.getBlockState(this.getPosition());
//            if (state.getBlock().isReplaceable(this.world, this.getPosition())) {
//                world.setBlockState(this.getPosition(), GSBlock.TOXIC_WATER.getDefaultState());
//            }
//        }
//        super.onDeath(source);
//    }

    @Override
    protected void applyEffect(LivingEntity entity) {
//        entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 150), this);//GSPotion.RUST, 100
    }

//    protected boolean isValidLightLevel() {
//        BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);
//
//        if (this.world.getLightFor(EnumSkyBlock.SKY, blockpos) > this.rand.nextInt(32)) {
//            return false;
//        } else {
//            int i = this.world.getLightFromNeighbors(blockpos);
//
//            if (this.world.isThundering()) {
//                int j = this.world.getSkylightSubtracted();
//                this.world.setSkylightSubtracted(10);
//                i = this.world.getLightFromNeighbors(blockpos);
//                this.world.setSkylightSubtracted(j);
//            }
//
//            return i <= this.rand.nextInt(8);
//        }
//    }
//
//    @Override
//    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
//        super.dropFewItems(wasRecentlyHit, lootingModifier);
//
//        this.entityDropItem(this.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND), 0);
//        this.entityDropItem(this.getItemStackFromSlot(EntityEquipmentSlot.HEAD), 0);
//        this.entityDropItem(this.getItemStackFromSlot(EntityEquipmentSlot.CHEST), 0);
//        this.entityDropItem(this.getItemStackFromSlot(EntityEquipmentSlot.LEGS), 0);
//        this.entityDropItem(this.getItemStackFromSlot(EntityEquipmentSlot.FEET), 0);
//    }
//
//    @Override
//    public boolean getCanSpawnHere() {
//        if (this.world.getDifficulty() != EnumDifficulty.PEACEFUL && this.posY <= 30 && this.isValidLightLevel() &&
//                MobsHelper.isDimensionAllowedForSpawn(this.world)) {
//            IBlockState state = this.world.getBlockState((new BlockPos(this)).down());
//            Block block = state.getBlock();
//            return state.canEntitySpawn(this) &&
//                    (block == Blocks.STONE || block == Blocks.COBBLESTONE || block == Blocks.DIRT || block == Blocks.GRAVEL);
//        } else {
//            return false;
//        }
//    }

}
