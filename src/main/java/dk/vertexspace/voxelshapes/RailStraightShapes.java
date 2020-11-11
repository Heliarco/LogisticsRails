package dk.vertexspace.voxelshapes;

import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class RailStraightShapes extends ShapeBase {
    public static final VoxelShape SHAPE_UP_NS =
            Stream.of(
                    Block.makeCuboidShape(10, 0, 0, 14, 1, 16),
                    Block.makeCuboidShape(2, 0, 0, 6, 1, 16),
                    Block.makeCuboidShape(12, 1, 0, 13, 3, 16),
                    Block.makeCuboidShape(11, 1, 0, 12, 3, 16),
                    Block.makeCuboidShape(4, 1, 0, 5, 3, 16),
                    Block.makeCuboidShape(3, 1, 0, 4, 3, 16),
                    Block.makeCuboidShape(6, 0, 7.5, 10, 0.9, 8.5)
            ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    public static final VoxelShape SHAPE_UP_EW = RotateModelY(SHAPE_UP_NS);











  /*  private static final Map<Direction, Map<Boolean, VoxelShape>>  shapeMap = new EnumMap<Direction, Map<Boolean, VoxelShape>>(Direction.class) {{
        put(Direction.UP, new HashMap<Boolean, VoxelShape>() {{
            put(true, SHAPE_UP_EW); put(false, SHAPE_UP_NS);
        }});
        put(Direction.DOWN, Collections.unmodifiableMap(new HashMap<Boolean, VoxelShape>() {{
            put(true, SHAPE_UP_EW);
            put(false, SHAPE_UP_NS);
        }}));
        put(Direction.NORTH, new HashMap<Boolean, VoxelShape>() {{
            put(true, SHAPE_UP_EW); put(false, SHAPE_UP_NS);
        }});
        put(Direction.SOUTH, new HashMap<Boolean, VoxelShape>() {{
            put(true, SHAPE_UP_EW); put(false, SHAPE_UP_NS);
        }});
        put(Direction.WEST, new HashMap<Boolean, VoxelShape>() {{
            put(true, SHAPE_UP_EW); put(false, SHAPE_UP_NS);
        }});
        put(Direction.EAST, new HashMap<Boolean, VoxelShape>() {{
            put(true, SHAPE_UP_EW); put(false, SHAPE_UP_NS);
        }});

        //etc
    }};


*/


}