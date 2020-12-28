package dk.vertexspace.blocks.rails;

import dk.vertexspace.blocks.RailConnected;
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
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
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

        BlockState ourState = this.getDefaultState().with(ORIENTATION, orientation);

        RailConnection[] ourConnections = RailConnectionsHelper.getConnectionsFromState(ourState);

        for(RailConnection ourConnection: ourConnections) {
            
            // We have two facings
            BlockPos attachedToPos = pos.offset(ourConnection.getSide());
            BlockState otherBlock = worldIn.getBlockState(attachedToPos);

            if (! (otherBlock.getBlock() instanceof RailConnected)){
                // If a block in the direction of the connection is solid, we return true
                if (otherBlock.isSolidSide(worldIn, attachedToPos, ourConnection.getSide().getOpposite()))  {
                    return true;
                }
            }

            // Or if a block in the direction of the connection can connect here. We return true.
            if (canConnectTo(ourState, pos, ourConnection.getSide(), worldIn)) {
                return true;
            }
        }
        return false;
    }
}
