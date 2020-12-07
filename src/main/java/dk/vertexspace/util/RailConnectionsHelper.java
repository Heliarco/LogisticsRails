package dk.vertexspace.util;

import dk.vertexspace.models.RailConnection;
import dk.vertexspace.rails.RailBase;
import dk.vertexspace.rails.RailStraight;
import dk.vertexspace.rails.RailTurn;
import dk.vertexspace.rails.RailXSection;
import dk.vertexspace.stateproperties.RailBendKind;
import dk.vertexspace.stateproperties.RailRotation;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import org.apache.commons.lang3.NotImplementedException;
import org.javatuples.Pair;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class RailConnectionsHelper {
    private static String enumErrorText = "Non existing enum value?";

    private static EnumMap<Direction, RailConnection[]> xSectionConnections;
    private static EnumMap<Direction, EnumMap<RailRotation, RailConnection[]>> turnConnections;
    private static EnumMap<Direction, RailConnection[][]> straightConnection;
    private static EnumMap<RailBendKind, RailConnection[]> bendUpConnections;
    private static EnumMap<RailBendKind, RailConnection[]> bendDownConnections;


    static {
        // X section //
        xSectionConnections = new EnumMap<>(Direction.class);
        for(Direction facing: Direction.values()) {

            RailConnection[] c = Arrays.stream(Direction.values())
                    .filter(d -> d != facing && d != facing.getOpposite())
                    .map(orientation ->  new RailConnection(facing, orientation))
                    .toArray(RailConnection[]::new);

            xSectionConnections.put(facing, c);
        }

        // Turn section //
        turnConnections = new EnumMap<>(Direction.class);
        for(Direction facing: Direction.values()) {

            turnConnections.put(facing, new EnumMap<>(RailRotation.class));
            for(RailRotation rotation: RailRotation.values()) {

                RailConnection[] c = new RailConnection[2];

                Direction side1;
                Direction side2;
                switch(facing) {
                    case UP:
                        switch (rotation){
                            case ROT_0:
                                side1 = Direction.NORTH;
                                side2 = Direction.EAST;
                                break;
                            case ROT_1:
                                side1 = Direction.SOUTH;
                                side2 = Direction.EAST;
                                break;
                            case ROT_2:
                                side1 = Direction.SOUTH;
                                side2 = Direction.WEST;
                                break;
                            case ROT_3:
                                side1 = Direction.NORTH;
                                side2 = Direction.WEST;
                                break;
                            default:
                                throw new NullPointerException(enumErrorText);
                        }
                        break;
                    case DOWN:
                        switch (rotation){
                            case ROT_0:
                                side1 = Direction.SOUTH;
                                side2 = Direction.WEST;
                                break;
                            case ROT_1:
                                side1 = Direction.SOUTH;
                                side2 = Direction.EAST;
                                break;
                            case ROT_2:
                                side1 = Direction.NORTH;
                                side2 = Direction.WEST;
                                break;
                            case ROT_3:
                                side1 = Direction.NORTH;
                                side2 = Direction.EAST;
                                break;
                            default:
                                throw new NullPointerException(enumErrorText);
                        }
                        break;
                    case EAST:
                        switch (rotation){
                            case ROT_0:
                                side1 = Direction.DOWN;
                                side2 = Direction.SOUTH;
                                break;
                            case ROT_1:
                                side1 = Direction.UP;
                                side2 = Direction.SOUTH;
                                break;
                            case ROT_2:
                                side1 = Direction.UP;
                                side2 = Direction.NORTH;
                                break;
                            case ROT_3:
                                side1 = Direction.DOWN;
                                side2 = Direction.NORTH;
                                break;
                            default:
                                throw new NullPointerException(enumErrorText);
                        }
                        break;
                    case WEST:
                        switch (rotation){
                            case ROT_0:
                                side1 = Direction.DOWN;
                                side2 = Direction.SOUTH;
                                break;
                            case ROT_1:
                                side1 = Direction.DOWN;
                                side2 = Direction.NORTH;
                                break;
                            case ROT_2:
                                side1 = Direction.UP;
                                side2 = Direction.NORTH;
                                break;
                            case ROT_3:
                                side1 = Direction.UP;
                                side2 = Direction.SOUTH;
                                break;
                            default:
                                throw new NullPointerException(enumErrorText);
                        }
                        break;
                    case NORTH:
                        switch (rotation){
                            case ROT_0:
                                side1 = Direction.DOWN;
                                side2 = Direction.EAST;
                                break;
                            case ROT_1:
                                side1 = Direction.UP;
                                side2 = Direction.EAST;
                                break;
                            case ROT_2:
                                side1 = Direction.UP;
                                side2 = Direction.WEST;
                                break;
                            case ROT_3:
                                side1 = Direction.DOWN;
                                side2 = Direction.WEST;
                                break;
                            default:
                                throw new NullPointerException(enumErrorText);
                        }
                        break;
                    case SOUTH:
                        switch (rotation){
                            case ROT_0:
                                side1 = Direction.DOWN;
                                side2 = Direction.WEST;
                                break;
                            case ROT_1:
                                side1 = Direction.UP;
                                side2 = Direction.WEST;
                                break;
                            case ROT_2:
                                side1 = Direction.UP;
                                side2 = Direction.EAST;
                                break;
                            case ROT_3:
                                side1 = Direction.DOWN;
                                side2 = Direction.EAST;
                                break;
                            default:
                                throw new NullPointerException(enumErrorText);
                        }
                        break;
                    default:
                        throw new NullPointerException(enumErrorText);
                }

                c[0] = new RailConnection(facing, side1);
                c[1] = new RailConnection(facing, side2);

                turnConnections.get(facing).put(rotation, c);
            }
        }

        // Straight section //
        straightConnection = new EnumMap<Direction, RailConnection[][]>(Direction.class);
        for (Direction facing: Direction.values()){
            // 0 is non rotated, 1 is rotated

            RailConnection[][] a = new RailConnection[2][];
            a[0] = new RailConnection[2];
            a[1] = new RailConnection[2];

            straightConnection.put(facing, a);

            switch(facing){
                case UP:
                case DOWN:
                    a[0][0] = new RailConnection(facing, Direction.NORTH);
                    a[0][1] = new RailConnection(facing, Direction.SOUTH);
                    a[1][0] = new RailConnection(facing, Direction.EAST);
                    a[1][1] = new RailConnection(facing, Direction.WEST);
                    break;

                case EAST:
                case WEST:
                    a[0][0] = new RailConnection(facing, Direction.UP);
                    a[0][1] = new RailConnection(facing, Direction.DOWN);
                    a[1][0] = new RailConnection(facing, Direction.NORTH);
                    a[1][1] = new RailConnection(facing, Direction.SOUTH);
                    break;

                default: // North south
                    a[0][0] = new RailConnection(facing, Direction.UP);
                    a[0][1] = new RailConnection(facing, Direction.DOWN);
                    a[1][0] = new RailConnection(facing, Direction.EAST);
                    a[1][1] = new RailConnection(facing, Direction.WEST);
                    break;
            }
        }


        // Bend up //
        bendUpConnections = new EnumMap<Direction, EnumMap<RailBendKind, RailConnection[]>>(Direction.class);
        for (RailBendKind orientation: RailBendKind.values()) {

        }

        @Override
        public RailConnection[] getConnectionPoints(BlockState state) {
            RailBendKind orientation = state.get(ORIENTATION);
            RailConnection[] r = new RailConnection[2];
            Pair<Direction,Direction> ds = orientation.getDirections();

            r[0] = new RailConnection(ds.getValue0(), ds.getValue1());
            r[1] = new RailConnection(ds.getValue1(), ds.getValue0());
            return r;
        }



        // Bend down //
        bendDownConnections = new EnumMap<Direction, EnumMap<RailBendKind, RailConnection[]>>(Direction.class);
        for (RailBendKind orientation: RailBendKind.values()) {

        }
        @Override
        public RailConnection[] getConnectionPoints(BlockState state) {
            RailBendKind orientation = state.get(ORIENTATION);
            RailConnection[] r = new RailConnection[2];
            Pair<Direction,Direction> ds = orientation.getDirections();

            r[0] = new RailConnection(ds.getValue0(), ds.getValue1().getOpposite());
            r[1] = new RailConnection(ds.getValue1(), ds.getValue0().getOpposite());
            return r;
        }

        // T section //


    }




    public static RailConnection[] getConnectionsFromState(BlockState blockstate){
        Block block = blockstate.getBlock();


        if (!(block instanceof RailBase)){
            return new RailConnection[0];
        }

        Direction facing = blockstate.get(RailBase.FACING);


        if (block instanceof RailXSection) {
            return xSectionConnections.get(facing);
        }
        else if(block instanceof RailTurn) {
            RailRotation rotation = blockstate.get(RailTurn.ROTATION);
            return turnConnections.get(facing).get(rotation);
        }
        else if(block instanceof RailStraight) {
            boolean rotated = blockstate.get(RailStraight.ROTATED);
            return straightConnection.get(facing)[rotated ? 1 : 0];
        }

        else {
            throw new NotImplementedException("Don't know how to parse " + blockstate.toString() + " yet");
        }



    }
}
