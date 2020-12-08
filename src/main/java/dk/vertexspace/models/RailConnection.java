package dk.vertexspace.models;

import net.minecraft.util.Direction;

// Represents a connectionpoint attached to the side of a rail. Handy for testing whether two rails connect
public class RailConnection {

    private final Direction side;
    private final Direction facing;


    public Direction getFacing(){
        return this.facing;
    }

    public Direction getSide(){
        return this.side;
    }

    public RailConnection(Direction facing, Direction side){
        this.facing = facing;
        this.side = side;
    }

    public boolean canConnectWith(RailConnection connection){
        return this.facing == connection.facing || this.side.getOpposite() == connection.side;
    }
}
