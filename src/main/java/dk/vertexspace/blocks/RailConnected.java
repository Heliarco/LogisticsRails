package dk.vertexspace.blocks;

import dk.vertexspace.models.RailConnection;
import dk.vertexspace.util.RailConnectionsHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

import java.util.Arrays;
import java.util.Optional;

public interface RailConnected {
    default RailConnection[] getRailConnections(BlockState state){
        return RailConnectionsHelper.getConnectionsFromState(state);
    }



    default boolean canConnectTo(BlockPos me, Direction direction, IWorldReader worldIn) {
        return canConnectTo(worldIn.getBlockState(me), me, direction, worldIn);
    }

    default boolean canConnectTo(BlockState ourState, BlockPos me, Direction direction, IWorldReader worldIn){

        Block ourBlock = ourState.getBlock();

        BlockPos otherPos = me.offset(direction);
        BlockState otherState = worldIn.getBlockState(otherPos);
        Block otherBlock = otherState.getBlock();

        RailConnection[] ourConnections = RailConnectionsHelper.getConnectionsFromState(ourState);

        if ( !(otherBlock instanceof RailConnected) || !(ourBlock instanceof RailConnected)) {
            return false;
        }

        // We have the two blocks, and we know they are both rails.
        Optional<RailConnection> ourConnectionStream = Arrays.stream(ourConnections).filter(c -> c.getSide() == direction).findAny();
        if (!ourConnectionStream.isPresent()) {
            return false;
        }

        // Now we know our connection points in the direction of the other block
        else {
            RailConnection ourConnection = ourConnectionStream.get();
            RailConnection[] otherConnections = RailConnectionsHelper.getConnectionsFromState(otherState);

            // We want the connection pointing towards this block. We do not yet care about the plane
            Optional<RailConnection> success = Arrays.stream(otherConnections)
                    .filter(c -> c.getFacing() == ourConnection.getFacing())
                    .filter(c -> c.getSide().getOpposite() == ourConnection.getSide())
                    .findAny();

            return success.isPresent();
        }
    }
}
