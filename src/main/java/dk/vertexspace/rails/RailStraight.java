package dk.vertexspace.rails;

import dk.vertexspace.util.PlayerEntityInteractions;
import dk.vertexspace.voxelshapes.RailStraightShapes;
import dk.vertexspace.voxelshapes.ShapeBase;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.TransformationMatrix;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;


public class RailStraight extends RailBase {
    public enum RailStraightOrientations {
        NORTH_SOUTH,
        EAST_WEST,
        UP_DOWN
    }

    //TransformationMatrix
    // Inherits FACING
    public static final BooleanProperty ROTATED = BooleanProperty.create("rotated");

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context){
        Direction facing = state.get(FACING);
        boolean rotated = state.get(ROTATED);

        switch(facing) {
            case UP:
                if (rotated) {
                    return RailStraightShapes.SHAPE_UP_EW;
                }
                else {
                    return RailStraightShapes.SHAPE_UP_NS;
                }
            case DOWN:
                break;
            case  EAST:
                break;
            case NORTH:
                break;
            case SOUTH:
                break;
            case WEST:
                break;

            default:
                return ShapeBase.PLACEHOLDER_SHAPE;
        }
        return ShapeBase.PLACEHOLDER_SHAPE;
    }


    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState superState = super.getStateForPlacement(context);
        PlayerEntity player = context.getPlayer();

        if (superState == null)
            return null;

        Direction facing = superState.get(FACING);

        boolean rotated = false;
        Direction d;
        switch (facing) {
            case UP:
            case DOWN:
                d = PlayerEntityInteractions.getFirstLookDirExcept(player, Direction.UP, Direction.DOWN);
                if ( d == Direction.EAST || d == Direction.WEST)
                {
                    rotated = true;
                }
                break;
            case EAST:
            case WEST:
                d = PlayerEntityInteractions.getFirstLookDirExcept(player, Direction.WEST, Direction.EAST);
                if ( d == Direction.NORTH || d == Direction.SOUTH )
                {
                    rotated = true;
                }
                break;
            default: // North south
            //case NORTH:
            //case SOUTH:
                d = PlayerEntityInteractions.getFirstLookDirExcept(player, Direction.NORTH, Direction.SOUTH);
                if ( d == Direction.EAST || d == Direction.WEST )
                {
                    rotated = true;
                }
                break;
        }

        return superState.with(ROTATED, rotated);
    }








        @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(ROTATED);
        super.fillStateContainer(builder);
    }
}
