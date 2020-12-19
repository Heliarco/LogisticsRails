package dk.vertexspace.blocks.rails;

import dk.vertexspace.models.RailConnection;
import dk.vertexspace.stateproperties.RailBendKind;
import dk.vertexspace.stateproperties.RailBendKindProperty;
import dk.vertexspace.util.RailConnectionsHelper;
import dk.vertexspace.voxelshapes.RailBendDownShapes;
import dk.vertexspace.voxelshapes.ShapeBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import org.javatuples.Pair;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Optional;

@SuppressWarnings("java:S110") // We can't really control the inheritance depth here.
public class RailBendDown extends RailBase {

    public static final RailBendKindProperty ORIENTATION = RailBendKindProperty.create("orientation");

    @Override
    protected BlockState rotateOnRightClick(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        int timeout = 0;
        RailBendKind orientation = state.get(ORIENTATION);
        while (timeout < 11){

            orientation = orientation.next();

            if (isValidPosition(orientation, worldIn, pos )) {

                return state.with(ORIENTATION, orientation);
            }
        }
        return null;
    }





    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context){

        RailBendKind orientation = state.get(ORIENTATION);

        switch(orientation) {
            case DOWN_EAST:
                return RailBendDownShapes.S_DE;
            case DOWN_NORTH:
                return RailBendDownShapes.S_DN;
            case DOWN_SOUTH:
                return RailBendDownShapes.S_DS;
            case DOWN_WEST:
                return RailBendDownShapes.S_DW;
            case NORTH_EAST:
                return RailBendDownShapes.S_NE;
            case NORTH_WEST:
                return RailBendDownShapes.S_NW;
            case SOUTH_EAST:
                return RailBendDownShapes.S_SE;
            case SOUTH_WEST:
                return RailBendDownShapes.S_SW;
            case UP_EAST:
                return RailBendDownShapes.S_UE;
            case UP_NORTH:
                return RailBendDownShapes.S_UN;
            case UP_SOUTH:
                return RailBendDownShapes.S_US;
            case UP_WEST:
                return RailBendDownShapes.S_UW;
            default:
                return ShapeBase.PLACEHOLDER_SHAPE;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {

        IWorldReader worldIn = context.getWorld();
        BlockPos pos = context.getPos();

        // Filter only valid orientations based on world geometry
        Optional<RailBendKind> kind = Arrays.stream(RailBendKind.values())

                .filter(bendKind -> isValidPosition(bendKind, worldIn, pos))

                // Filter those that match orientation
                /*.filter(bendKind -> {
                    Direction neededFace = primaryPlacementDirection.getOpposite();

                    return bendKind.getDirections().anyMatch(side -> side == neededFace);
                })*/
                .findAny();

        if (!kind.isPresent()) {
            return null;
        }

        BlockState blockstate = this.getDefaultState();
        return blockstate.with(ORIENTATION, kind.get());
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(ORIENTATION);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        // Basically copied from wall torch.
        RailBendKind orientation = state.get(ORIENTATION);
        return isValidPosition(orientation, worldIn, pos);
    }

    public boolean isValidPosition(RailBendKind orientation, IWorldReader worldIn, BlockPos pos) {
        RailConnection[] ourConnections = RailConnectionsHelper.getConnectionsFromState(this.getDefaultState().with(ORIENTATION, orientation));

        for(RailConnection ourConnection: ourConnections) {
            
            // We have two facings
            Direction ourFacing = ourConnection.getFacing();
            BlockPos attachedToPos = pos.offset(ourConnection.getSide());
            BlockState otherBlock = worldIn.getBlockState(attachedToPos);

            if (otherBlock.isSolidSide(worldIn, attachedToPos, ourFacing))  {
                return true;
            }
            if (otherBlock.getBlock() instanceof RailBase)
            {
                RailConnection[] otherConnections = RailConnectionsHelper.getConnectionsFromState(otherBlock);

                // We want the connection pointing towards this block. We do not yet care about the plane
                Optional<RailConnection> success = Arrays.stream(otherConnections)

                        .filter(c -> c.getFacing() == ourConnection.getFacing())
                        .filter(c -> c.getSide().getOpposite() == ourConnection.getSide())
                        .findAny();

                if (success.isPresent()){
                    return true;
                }


            }
        }
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    @Nonnull
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(ORIENTATION, state.get(ORIENTATION).next());
    }

    @Override
    @SuppressWarnings("deprecation")
    @Nonnull
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.with(ORIENTATION, state.get(ORIENTATION).next().next().next().next().next().next());
    }

}
