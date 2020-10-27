package dk.vertexspace.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
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
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class DebugRailV2 extends Block {

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
        return 0.6f; // Get back to this one later!
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


    // This is a blockstate property of 4 values!
    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public DebugRailV2() {
        super(Block.Properties.create(Material.IRON)
                .hardnessAndResistance(1.0f, 1.0f)
                .sound(SoundType.ANVIL)
                .func_235861_h_() // Mapping missing of "setrequirestool" // New in forge 1.16.3 (or minecraft 1.16.3 whatever)
                .harvestLevel(0) // 0 is wood, 1 is stone, 2 is iron, 3 is diamond etc.
                .harvestTool(ToolType.PICKAXE));
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
    @Deprecated
    public BlockState rotate(BlockState state, Rotation rot){
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn){
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
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
