package dk.vertexspace.stateproperties;

import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;

public enum RailBendUpKind implements IStringSerializable {

    UP_EAST(Direction.UP, Direction.EAST),
    UP_WEST(Direction.UP, Direction.WEST),
    UP_NORTH(Direction.UP, Direction.NORTH),
    UP_SOUTH(Direction.UP, Direction.SOUTH),

    DOWN_EAST(Direction.DOWN, Direction.EAST),
    DOWN_WEST(Direction.DOWN, Direction.WEST),
    DOWN_SOUTH(Direction.DOWN, Direction.SOUTH),
    DOWN_NORTH(Direction.DOWN, Direction.NORTH),

    NORTH_EAST(Direction.NORTH, Direction.EAST),
    NORTH_WEST(Direction.NORTH, Direction.WEST),

    SOUTH_EAST(Direction.SOUTH, Direction.EAST),
    SOUTH_WEST(Direction.SOUTH, Direction.WEST);

    private final Direction item1;
    private final Direction item2;

    RailBendUpKind(Direction item1, Direction item2){
        this.item1 = item1;
        this.item2 = item2;
    }

    @Override
    public String func_176610_l() {
        return String.format("%s_%d", this.item1.name(), this.item2.name());
    }
}
