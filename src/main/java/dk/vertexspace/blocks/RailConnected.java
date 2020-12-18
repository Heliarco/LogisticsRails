package dk.vertexspace.blocks;

import dk.vertexspace.models.RailConnection;
import dk.vertexspace.util.RailConnectionsHelper;
import net.minecraft.block.BlockState;

public interface RailConnected {
    default RailConnection[] getRailConnections(BlockState state){
        return RailConnectionsHelper.getConnectionsFromState(state);
    }
}
