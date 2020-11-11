package dk.vertexspace.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3d;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayerEntityInteractions {

    public static Stream<Direction> GetSortedLookDirections(PlayerEntity player) {


        if (player == null){
            Stream.<Direction>empty();
        }

        // This look vector is normalized
        Vector3d lookVector = player.getLookVec();

        // Time for some mc basics.
        // X- = West
        // X = East
        // Y = Up
        // Y- = Down
        // Z = South,
        // Z- = North

        ArrayList<Pair<Direction, Double>> components = new ArrayList<>();

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

        return components.stream().map(Pair::getValue0);
    }

}
