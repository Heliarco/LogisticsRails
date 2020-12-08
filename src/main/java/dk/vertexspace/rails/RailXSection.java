package dk.vertexspace.rails;

import dk.vertexspace.voxelshapes.RailXSectionShape;
import dk.vertexspace.voxelshapes.ShapeBase;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

@SuppressWarnings("java:S110") // We can't really control the inheritance depth here.
public class RailXSection extends RailBase{
    @Override
    protected BlockState rotateOnRightClick(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        return null;
    }



    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context){
        Direction facing = state.get(FACING);

        switch(facing) {
            case UP:
                return RailXSectionShape.S_UP;
            case DOWN:
                return RailXSectionShape.S_DOWN;
            case EAST:
                return RailXSectionShape.S_EAST;
            case WEST:
                return RailXSectionShape.S_WEST;
            case NORTH:
                return RailXSectionShape.S_NORTH;
            case SOUTH:
                return RailXSectionShape.S_SOUTH;
            default:
                return ShapeBase.PLACEHOLDER_SHAPE;
        }
    }
}
