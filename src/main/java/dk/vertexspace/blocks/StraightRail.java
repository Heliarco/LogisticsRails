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

public class StraightRail extends RailBase {



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


    @Override
    public boolean isTransparent(BlockState state) {
        return true;
    }










    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        super.fillStateContainer(builder);
    }
}
