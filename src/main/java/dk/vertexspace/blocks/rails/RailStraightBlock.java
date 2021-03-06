package dk.vertexspace.blocks.rails;

import dk.vertexspace.util.PlacementHelpers;
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
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

@SuppressWarnings("java:S110") // We can't really control the inheritance depth here.
public class RailStraightBlock extends RailBaseBlock {
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
                return rotated ? RailStraightShapes.S_UP_EW : RailStraightShapes.S_UP_NS;
            case DOWN:
                return rotated ? RailStraightShapes.S_DOWN_EW : RailStraightShapes.S_DOWN_NS;
            case EAST:
                return rotated ? RailStraightShapes.S_EAST_NS : RailStraightShapes.S_EAST_UD;
            case WEST:
                return rotated ? RailStraightShapes.S_WEST_NS : RailStraightShapes.S_WEST_UD;
            case NORTH:
                return rotated ? RailStraightShapes.S_NORTH_EW : RailStraightShapes.S_NORTH_UD;
            case SOUTH:
                return rotated ? RailStraightShapes.S_SOUTH_EW : RailStraightShapes.S_SOUTH_UD;
            default:
                return ShapeBase.PLACEHOLDER_SHAPE;
        }
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
                d = PlacementHelpers.getPrimaryLookDirectionExcept(player, Direction.UP, Direction.DOWN);
                if ( d == Direction.EAST || d == Direction.WEST)
                {
                    rotated = true;
                }
                break;
            case EAST:
            case WEST:
                d = PlacementHelpers.getPrimaryLookDirectionExcept(player, Direction.WEST, Direction.EAST);
                if ( d == Direction.NORTH || d == Direction.SOUTH )
                {
                    rotated = true;
                }
                break;
            default: // North south
            //case NORTH:
            //case SOUTH:
                d = PlacementHelpers.getPrimaryLookDirectionExcept(player, Direction.NORTH, Direction.SOUTH);
                if ( d == Direction.EAST || d == Direction.WEST )
                {
                    rotated = true;
                }
                break;
        }

        return superState.with(ROTATED, rotated);
    }

    @Override
    protected BlockState rotateOnRightClick(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        return state.with(ROTATED, !state.get(ROTATED));
    }


    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(ROTATED);
        super.fillStateContainer(builder);
    }
}
