package dk.vertexspace.blocks;

import dk.vertexspace.constants.Log;
import dk.vertexspace.rails.RailBase;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.stream.Stream;

public class DebugRailV2 extends RailBase {

    /*
    public static VoxelShape rotateShape(Direction from, Direction to, VoxelShape shape) {
        VoxelShape[] buffer = new VoxelShape[]{ shape, VoxelShapes.empty() };

        int times = (to.getHorizontalIndex() - from.getHorizontalIndex() + 4) % 4;
        for (int i = 0; i < times; i++) {
            buffer[0].forEachBox(
                (minX, minY, minZ, maxX, maxY, maxZ) ->
                    buffer[1] = VoxelShapes.or(
                        buffer[1], VoxelShapes.create(1-maxZ, minY, minX, 1-minZ, maxY, maxX)
                    )
                );
            buffer[0] = buffer[1];
            buffer[1] = VoxelShapes.empty();
        }

        return buffer[0];
    }*/

    // Voxelshapes are normalized to between 0 and 1
    private static VoxelShape RotateModelY(VoxelShape shape){
        VoxelShape[] outShape = new VoxelShape[]{VoxelShapes.empty()};

        shape.forEachBox(
                (minX, minY, minZ, maxX, maxY, maxZ) -> {
                    outShape[0] = VoxelShapes.or(outShape[0], VoxelShapes.create(
                            1-maxZ,
                            minY,
                            minX,
                            1-minZ,
                            maxY,
                            maxX));
                }
        );
        return outShape[0];
    }


    // Called on updates on the cardinal directions.
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {

        Log.info("Update post placement"
        ,stateIn
        ,facing
        ,facingState
        ,worldIn
        ,currentPos
        ,facingPos);



        return facing == Direction.DOWN && !this.isValidPosition(stateIn, worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }


    // This is called on placement (Curiously enough, is called twice oO)
    // Forces rail to be place on a solid surface below it. needs investigation
    // The state is input here, as we gave it in getStateForPlacement.
    // World in. Are we on server or on client right now?
    // Blockpos. Makes sense :) X,Y,Z
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        Log.info("isValidPosition",
        state,
        worldIn,
        pos);
        return hasEnoughSolidSide(worldIn, pos.down(), Direction.UP);


        isSolidSide // From abstract block. Checks if the block in the direction has a solid face (Does geometry checking, but cached)
        /*
        example from torchblock
         public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
      Direction direction = state.get(HORIZONTAL_FACING);
      BlockPos blockpos = pos.offset(direction.getOpposite());
      BlockState blockstate = worldIn.getBlockState(blockpos);
      return blockstate.isSolidSide(worldIn, blockpos, direction);
   }
         */
    }


    private static final VoxelShape SHAPE_N =
        Stream.of(
            Block.makeCuboidShape(5, 0, 0, 11, 2, 9),
            Block.makeCuboidShape(12, 0, 0, 14, 2, 16),
            Block.makeCuboidShape(2, 0, 0, 4, 2, 16)
        ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();


    private static final VoxelShape SHAPE_E = RotateModelY(SHAPE_N);
    private static final VoxelShape SHAPE_S = RotateModelY(SHAPE_E);
    private static final VoxelShape SHAPE_W = RotateModelY(SHAPE_S);


    @Override
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1f; // No shadows really its a tiny model
    }



    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context){
        switch(state.get(FACING)){
            case NORTH:
                return SHAPE_N;

            case SOUTH:
                return SHAPE_S;
            case EAST:
                return SHAPE_E;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N; // MBY reconsider this later?
        }
    }

    // IProperties
    // AttachedTo (UP, DOWN, NORTH, SOUTH, EAST, WEST)
    // Rotated (It has a default heading, have we rotated 90 degrees?)
    // private static final BooleanProperty ROTATED =  BooleanProperty.create("rotated");
    // private static final DirectionProperty FACING = BlockStateProperties.FACING;
    // This gives us 12 combinations. of straight rails. Should be enough to be annoying -.-

    // Blockstates are reference equatable with "==" !USE IT


    @Override
    public boolean isTransparent(BlockState state) {
        return true;
    }



    public DebugRailV2() {

    }
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack){
        Direction x = state.get(FACING);
        // Here we call "realignment" meaning rotation code :) When we can figure it out :D
        // We cannot change the facing though, since that is what the rail is "stuck" to. The above line is just to test :/
    }




    @Nullable
    @Override
    // Called when block is placed.
    // Here we can calculate the state of the block before ultimate placement
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState()
                .with(FACING, context.getPlacementHorizontalFacing().getOpposite());
                //.with(); // We can chain "with" statements to build up to the final state combination here


    }




    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder){
        builder.add(FACING);
    }


    // The default state of the block state combination is made up of the individual states default values.
    // This can be overridden by:
  //  this.setDefaultState(
    //this.stateContainer.getBaseState()
      //  .with(FACING, Direction.NORTH)
        //.with(OPEN, false)
        //.with(HINGE, DoorHingeSide.LEFT)
        //.with(POWERED, false)
        //.with(HALF, DoubleBlockHalf.LOWER)
//);

}
