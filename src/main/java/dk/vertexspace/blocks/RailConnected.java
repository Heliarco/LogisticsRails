package dk.vertexspace.blocks;

import dk.vertexspace.blocks.rails.RailBase;
import dk.vertexspace.models.RailConnection;
import dk.vertexspace.util.RailConnectionsHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;

import java.util.Arrays;
import java.util.Optional;

public interface RailConnected {
    default RailConnection[] getRailConnections(BlockState state){
        return RailConnectionsHelper.getConnectionsFromState(state);
    }

    default boolean canConnectTo(BlockState me, Direction direction, BlockState other){
        Block otherBlock = other.getBlock();
        RailConnection[] ourConnections = RailConnectionsHelper.getConnectionsFromState(me);

        for(RailConnection ourConnection: ourConnections) {

            if (otherBlock instanceof RailConnected) {
                RailConnection[] otherConnections = RailConnectionsHelper.getConnectionsFromState(other);

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

}
