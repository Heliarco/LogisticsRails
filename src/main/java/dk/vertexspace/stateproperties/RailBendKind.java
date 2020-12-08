package dk.vertexspace.stateproperties;

import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import org.apache.commons.lang3.ArrayUtils;
import org.javatuples.Pair;

public enum RailBendKind implements IStringSerializable {

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
    private final Pair<Direction,Direction> directions;

    RailBendKind(Direction item1, Direction item2){
        this.directions = new Pair<>(item1, item2);
        this.name = String.format("%s_%s", item1.func_176610_l(), item2.func_176610_l());
    }


    public Pair<Direction,Direction> getDirections(){
        return directions;
    }

    @Override
    public String func_176610_l() {
        return this.name;
    }

    public RailBendKind next(){

        RailBendKind[] values = RailBendKind.values();
        int i = ArrayUtils.indexOf(values,this);
        i++;
        if(i >= values.length){
            i = 0;
        }
        return values[i];
    }

}
