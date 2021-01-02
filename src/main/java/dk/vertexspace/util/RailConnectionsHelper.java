package dk.vertexspace.util;

import dk.vertexspace.blocks.FaceAttached;
import dk.vertexspace.blocks.RailConnected;
import dk.vertexspace.blocks.nodes.Console;
import dk.vertexspace.blocks.nodes.NodeBase;
import dk.vertexspace.models.RailConnection;
import dk.vertexspace.blocks.rails.*;
import dk.vertexspace.stateproperties.RailBendKind;
import dk.vertexspace.stateproperties.RailRotation;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import org.apache.commons.lang3.NotImplementedException;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RailConnectionsHelper {

    private RailConnectionsHelper() { }

    private static String enumErrorText = "Non existing enum value?";

    private static EnumMap<Direction, RailConnection[]> xSectionConnections;
    private static EnumMap<Direction, EnumMap<RailRotation, RailConnection[]>> turnConnections;
    private static EnumMap<Direction, EnumMap<RailRotation, RailConnection[]>> tSectionConnections;
    private static EnumMap<Direction, RailConnection[][]> straightConnection;
    private static EnumMap<RailBendKind, RailConnection[]> bendUpConnections;
    private static EnumMap<RailBendKind, RailConnection[]> bendDownConnections;

    private static EnumMap<Direction, RailConnection[]> consoleConnections;
    private static EnumMap<Direction, EnumMap<RailRotation, RailConnection[]>> nodeConnections;



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
        straightConnection = new EnumMap<>(Direction.class);
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
        bendUpConnections = new EnumMap<>(RailBendKind.class);
        for (RailBendKind orientation: RailBendKind.values()) {

            RailConnection[] r = new RailConnection[2];
            Pair<Direction,Direction> ds = orientation.getDirections();
            r[0] = new RailConnection(ds.getValue0(), ds.getValue1());
            r[1] = new RailConnection(ds.getValue1(), ds.getValue0());
            bendUpConnections.put(orientation, r);
        }

        // Bend down //
        bendDownConnections = new EnumMap<>(RailBendKind.class);
        for (RailBendKind orientation: RailBendKind.values()) {
            RailConnection[] r = new RailConnection[2];
            Pair<Direction,Direction> ds = orientation.getDirections();

            r[0] = new RailConnection(ds.getValue0(), ds.getValue1().getOpposite());
            r[1] = new RailConnection(ds.getValue1(), ds.getValue0().getOpposite());
            bendDownConnections.put(orientation, r);
        }

        // T section //
        tSectionConnections = new EnumMap<>(Direction.class);
        for (Direction facing: Direction.values()) {
            tSectionConnections.put(facing, new EnumMap<>(RailRotation.class));
            for(RailRotation rotation: RailRotation.values()) {

                Direction exSide;
                switch(facing) {
                    case UP:
                        switch (rotation){
                            case ROT_0:
                                exSide = Direction.EAST;
                                break;
                            case ROT_1:
                                exSide = Direction.SOUTH;
                                break;
                            case ROT_2:
                                exSide = Direction.WEST;
                                break;
                            case ROT_3:
                                exSide = Direction.NORTH;
                                break;
                            default:
                                throw new NullPointerException(enumErrorText);
                        }
                        break;
                    case DOWN:
                        switch (rotation){
                            case ROT_0:exSide = Direction.EAST;
                                break;
                            case ROT_1:exSide = Direction.NORTH;
                                break;
                            case ROT_2:exSide = Direction.WEST;
                                break;
                            case ROT_3:exSide = Direction.SOUTH;
                                break;
                            default:
                                throw new NullPointerException(enumErrorText);
                        }
                        break;
                    case EAST:
                        switch (rotation){
                            case ROT_0:exSide = Direction.DOWN;
                                break;
                            case ROT_1:exSide = Direction.SOUTH;
                                break;
                            case ROT_2:exSide = Direction.UP;
                                break;
                            case ROT_3:exSide = Direction.NORTH;
                                break;
                            default:
                                throw new NullPointerException(enumErrorText);
                        }
                        break;
                    case WEST:
                        switch (rotation){
                            case ROT_0:
                                exSide = Direction.DOWN;
                                break;
                            case ROT_1:
                                exSide = Direction.NORTH;
                                break;
                            case ROT_2:
                                exSide = Direction.UP;
                                break;
                            case ROT_3:
                                exSide = Direction.SOUTH;
                                break;
                            default:
                                throw new NullPointerException(enumErrorText);
                        }
                        break;
                    case NORTH:
                        switch (rotation){
                            case ROT_0:
                                exSide = Direction.DOWN;
                                break;
                            case ROT_1:
                                exSide = Direction.WEST;
                                break;
                            case ROT_2:
                                exSide = Direction.UP;
                                break;
                            case ROT_3:
                                exSide = Direction.EAST;
                                break;
                            default:
                                throw new NullPointerException(enumErrorText);
                        }
                        break;
                    case SOUTH:
                        switch (rotation){
                            case ROT_0:
                                exSide = Direction.DOWN;
                                break;
                            case ROT_1:
                                exSide = Direction.WEST;
                                break;
                            case ROT_2:
                                exSide = Direction.UP;
                                break;
                            case ROT_3:
                                exSide = Direction.EAST;
                                break;
                            default:
                                throw new NullPointerException(enumErrorText);
                        }
                        break;
                    default:
                        throw new NullPointerException(enumErrorText);
                }

                RailConnection[] c = Arrays.stream(Direction.values()).filter(
                        x -> x != facing &&
                             x != facing.getOpposite() &&
                             x != exSide)
                        .map(x -> new RailConnection(facing, x))
                        .toArray(RailConnection[]::new);

                tSectionConnections.get(facing).put(rotation, c);

            }
        }

        // Node console //
        consoleConnections = new EnumMap<>(Direction.class);
        consoleConnections.put(Direction.WEST, new RailConnection[]{new RailConnection(Direction.UP, Direction.EAST)});
        consoleConnections.put(Direction.EAST, new RailConnection[]{new RailConnection(Direction.UP, Direction.WEST)});
        consoleConnections.put(Direction.NORTH, new RailConnection[]{new RailConnection(Direction.UP, Direction.SOUTH)});
        consoleConnections.put(Direction.SOUTH, new RailConnection[]{new RailConnection(Direction.UP, Direction.NORTH)});


        nodeConnections = new EnumMap<>(Direction.class);
        for(Direction facing: Direction.values()) {
            nodeConnections.put(facing, new EnumMap<>(RailRotation.class));
            // We cheat a bit here and rely on the fact that the T-Section lacks a connection in the REVERSE direction of the connection we allow.
            for(RailRotation rot: RailRotation.values()) {
                RailConnection[] tSet = tSectionConnections.get(facing).get(rot);


                List<Direction> exclusions = Stream.concat(
                        Arrays.stream(new Direction[] {facing, facing.getOpposite()}),
                        Arrays.stream(tSet).map(RailConnection::getSide))
                    .collect(Collectors.toList());

                Direction side = Arrays.stream(Direction.values()).filter(c -> !exclusions.contains(c))
                        .findAny().orElseThrow(IllegalArgumentException::new);
                nodeConnections.get(facing).put(rot, new RailConnection[]{new RailConnection(facing, side.getOpposite())});
            }
        }
    }

    public static RailConnection[] getConnectionsFromState(BlockState blockstate){
        Block block = blockstate.getBlock();

        if (!(block instanceof RailConnected)){
            return new RailConnection[0];
        }

        if (block instanceof RailXSection) {
            Direction facing = blockstate.get(DirectionalBlock.FACING);
            return xSectionConnections.get(facing);
        }
        else if(block instanceof RailTurn) {
            Direction facing = blockstate.get(DirectionalBlock.FACING);
            RailRotation rotation = blockstate.get(RailTurn.ROTATION);
            return turnConnections.get(facing).get(rotation);
        }
        else if(block instanceof RailStraight) {
            Direction facing = blockstate.get(DirectionalBlock.FACING);
            boolean rotated = blockstate.get(RailStraight.ROTATED);
            return straightConnection.get(facing)[rotated ? 1 : 0];
        }
        else if(block instanceof RailBendDown){
            RailBendKind orientation = blockstate.get(RailBendDown.ORIENTATION);
            return bendDownConnections.get(orientation);
        }
        else if(block instanceof RailBendUp){
            RailBendKind orientation = blockstate.get(RailBendUp.ORIENTATION);
            return bendUpConnections.get(orientation);
        }
        else if(block instanceof RailTSection){
            Direction facing = blockstate.get(DirectionalBlock.FACING);
            RailRotation rotation = blockstate.get(RailTurn.ROTATION);
            return tSectionConnections.get(facing).get(rotation);
        }
        else if(block instanceof NodeBase){


            if (block instanceof Console){
                Direction facing = blockstate.get(Console.HORIZONTAL_FACING);
                return consoleConnections.get(facing);
            }
            else{
                Direction facing = blockstate.get(FaceAttached.FACING);
                RailRotation rotation = blockstate.get(NodeBase.ROTATION);
                return nodeConnections.get(facing).get(rotation);
            }
        }
        else {
            throw new NotImplementedException("Don't know how to parse " + blockstate.toString() + " yet");
        }
    }

    public static RailConnection[] getConnectedConnections(BlockState blockstate, BlockPos blockPos) {
        Block block = blockstate.getBlock();
        if (! (block instanceof RailConnected)) {
            return new RailConnection[0];
        }
        RailConnection[] potentialConnections = getConnectionsFromState(blockstate);

        List<RailConnection> r = new ArrayList<>(potentialConnections.length);

        for (RailConnection c : potentialConnections) {
            
        }





    }
}
