package dk.vertexspace.rails;

import dk.vertexspace.constants.Log;
import dk.vertexspace.rails.RailBase;
import dk.vertexspace.util.PlayerEntityInteractions;
import dk.vertexspace.voxelshapes.RailStraightShapes;
import dk.vertexspace.voxelshapes.ShapeBase;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
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


public class RailStraight extends RailBase {
    public enum RailStraightOrientations {
        NORTH_SOUTH,
        EAST_WEST,
        UP_DOWN
    }


    // Inherits FACING
    public static final BooleanProperty ROTATED = BooleanProperty.create("rotated");


    public RailStraight() {

    }


    // Rightclick basically
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        return  ActionResultType.PASS;
    }





    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context){
        Direction facing = state.get(FACING);
        boolean rotated = state.get(ROTATED);

       // Log.info(state);

        switch(state.get(FACING)) {
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
                d = PlayerEntityInteractions.GetFirstLookDirExcept(player, Direction.UP, Direction.DOWN);
                if ( d == Direction.EAST || d == Direction.WEST )
                {
                    rotated = true;
                }
                break;
            case EAST:
            case WEST:
                d = PlayerEntityInteractions.GetFirstLookDirExcept(player, Direction.WEST, Direction.EAST);
                if ( d == Direction.NORTH || d == Direction.SOUTH )
                {
                    rotated = true;
                }
                break;
            default: // North south
            //case NORTH:
            //case SOUTH:
                d = PlayerEntityInteractions.GetFirstLookDirExcept(player, Direction.NORTH, Direction.SOUTH);
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
