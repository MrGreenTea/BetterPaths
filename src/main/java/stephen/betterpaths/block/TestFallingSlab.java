//package stephen.betterpaths.block;
//
//import net.minecraft.block.*;
//import net.minecraft.block.enums.SlabType;
//import net.minecraft.client.util.ParticleUtil;
//import net.minecraft.entity.FallingBlockEntity;
//import net.minecraft.fluid.FluidState;
//import net.minecraft.fluid.Fluids;
//import net.minecraft.item.ItemPlacementContext;
//import net.minecraft.particle.BlockStateParticleEffect;
//import net.minecraft.particle.ParticleTypes;
//import net.minecraft.registry.tag.BlockTags;
//import net.minecraft.server.world.ServerWorld;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.Direction;
//import net.minecraft.util.math.random.Random;
//import net.minecraft.world.BlockView;
//import net.minecraft.world.World;
//import net.minecraft.world.WorldAccess;
//import org.jetbrains.annotations.Nullable;
//import stephen.betterpaths.entity.TestFallingSlabEntity;
//
//public class TestFallingSlab extends SlabBlock implements LandingBlock {
//    public TestFallingSlab(AbstractBlock.Settings settings) {
//        super(settings);
//    }
//
//    @Override
//    @Nullable
//    public BlockState getPlacementState(ItemPlacementContext ctx) {
//        BlockPos blockPos = ctx.getBlockPos();
//        BlockState blockState = ctx.getWorld().getBlockState(blockPos);
//        if (blockState.isOf(this)) {
//            return blockState.with(TYPE, SlabType.DOUBLE).with(WATERLOGGED, false);
//        }
//
//        FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
//        BlockState blockState2 = this.getDefaultState().with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
//        return blockState2;
//    }
//
//    public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity) {
//    }
//    @Override
//    public void onDestroyedOnLanding(World world, BlockPos pos, FallingBlockEntity fallingBlockEntity){
//
//    }
//    @Override
//    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
//        world.scheduleBlockTick(pos, this, this.getFallDelay());
//    }
//
//    @Override
//    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
//        world.scheduleBlockTick(pos, this, this.getFallDelay());
//        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
//    }
//
//    @Override
//    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
//        if (!TestFallingSlab.canFallThrough(world.getBlockState(pos.down()), state) || pos.getY() < world.getBottomY()) {
//            return;
//        }
//        TestFallingSlabEntity testFallingSlabEntity = TestFallingSlabEntity.spawnFromBlock(world, pos, state);
//        this.configureFallingSlabEntity(testFallingSlabEntity);
//    }
//
//    protected void configureFallingSlabEntity(TestFallingSlabEntity entity) {
//    }
//
//    /**
//     * Gets the amount of time in ticks this block will wait before attempting to start falling.
//     */
//    protected int getFallDelay() {
//        return 2;
//    }
//
//    public static boolean canFallThrough(BlockState stateBelow, BlockState state) {
//        return ((stateBelow.isAir() || stateBelow.isIn(BlockTags.FIRE) || stateBelow.isLiquid() || stateBelow.isReplaceable()) || (stateBelow.isOf(state.getBlock()) && stateBelow.get(TYPE) == SlabType.BOTTOM));
//    }
//
//    @Override
//    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
//        if (random.nextInt(16) == 0 && TestFallingSlab.canFallThrough(world.getBlockState(pos.down()), state)) {
//            ParticleUtil.spawnParticle(world, pos, random, new BlockStateParticleEffect(ParticleTypes.FALLING_DUST, state));
//        }
//    }
//    public int getColor(BlockState state, BlockView world, BlockPos pos) {
//        return -8356741;
//    }
//}
//
//
