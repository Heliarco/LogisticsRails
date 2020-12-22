package dk.vertexspace.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3d;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class PlacementHelpers {
    private PlacementHelpers(){}

    public static Direction getPrimaryLookDirectionExcept(PlayerEntity player, Direction ... skip){

        Direction[] l = getSortedLookDirections(player);

        for(int i = 0; i < 3; i++) { // We always have 3 directions !
            Direction dir = l[i];
            boolean found = true;
            for (int s = 0; s < skip.length; s++){
                if (skip[s] == dir) {
                    found = false;
                }
            }
            if (found) {
                return dir;
            }
        }

        throw new IllegalArgumentException();
    }

    public static Direction getPrimaryLookDirection(PlayerEntity player){
        return getSortedLookDirections(player)[0];
    }
    public static Direction getSecondaryLookDirection(PlayerEntity player) {
        return getSortedLookDirections(player)[1];
    }

    public static Direction[] getSortedLookDirections(PlayerEntity player) {

        // This look vector is normalized
        Vector3d lookVector = player.getLookVec();

        // Time for some mc basics.
        // X- = West
        // X = East
        // Y = Up
        // Y- = Down
        // Z = South,
        // Z- = North

        ArrayList<Pair<Direction, Double>> components = new ArrayList<>(3);

        components.add(Pair.with(
            lookVector.x >= 0 ? Direction.EAST : Direction.WEST,
            Math.abs(lookVector.x)
        ));

        components.add(Pair.with(
            lookVector.y >= 0 ? Direction.UP : Direction.DOWN,
            Math.abs(lookVector.y)
        ));

        components.add(Pair.with(
           lookVector.z >= 0 ? Direction.SOUTH : Direction.NORTH,
           Math.abs(lookVector.z)
        ));

        components.sort(
                (a,b) -> Double.compare(b.getValue1(), a.getValue1())
        );

        Direction [] r = new Direction[3];
        r[0] = components.get(0).getValue0();
        r[1] = components.get(1).getValue0();
        r[2] = components.get(2).getValue0();

        return r;
    }

}
