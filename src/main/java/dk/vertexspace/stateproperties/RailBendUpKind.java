package dk.vertexspace.stateproperties;

import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.stream.Stream;

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

    private final String name;
    private final Direction [] directions;

    RailBendUpKind(Direction item1, Direction item2){
        this.directions = new Direction[2];
        directions[0] = item1;
        directions[1] = item2;

        this.name = String.format("%s_%s", item1.func_176610_l(), item2.func_176610_l());
    }

    public Stream<Direction> getElements(){
        return Arrays.stream(this.directions);
    }

    @Override
    public String func_176610_l() {
        return this.name;


    }

    public RailBendUpKind next(){

        RailBendUpKind[] values = RailBendUpKind.values();
        int i = ArrayUtils.indexOf(values,this);
        i++;
        if(i >= values.length){
            i = 0;
        }
        return values[i];

/*
        switch(this){
            case UP_EAST:
                return UP_WEST;
            case UP_WEST:
                return UP_NORTH;
            case UP_NORTH:
                return UP_SOUTH;
            case UP_SOUTH:
                return DOWN_EAST;

            case DOWN_EAST:
                return DOWN_WEST;
            case DOWN_WEST:
                return DOWN_SOUTH;
            case DOWN_SOUTH:
                return DOWN_NORTH;
            case DOWN_NORTH:
                return NORTH_EAST;

            case NORTH_EAST:
                return NORTH_WEST;
            case NORTH_WEST:
                return SOUTH_EAST;

            case SOUTH_EAST:
                return SOUTH_WEST;
            case SOUTH_WEST:
                return UP_EAST;
        }
        return UP_EAST;*/
    }

}
