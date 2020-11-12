package dk.vertexspace.voxelshapes;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

import java.util.stream.Stream;

public class RailStraightShapes extends ShapeBase {

    private RailStraightShapes(){}

    public static final VoxelShape SHAPE_UP_NS =
            Stream.of(
                    Block.makeCuboidShape(10, 0, 0, 14, 1, 16),
                    Block.makeCuboidShape(2, 0, 0, 6, 1, 16),
                    Block.makeCuboidShape(12, 1, 0, 13, 3, 16),
                    Block.makeCuboidShape(11, 1, 0, 12, 3, 16),
                    Block.makeCuboidShape(4, 1, 0, 5, 3, 16),
                    Block.makeCuboidShape(3, 1, 0, 4, 3, 16),
                    Block.makeCuboidShape(6, 0, 7.5, 10, 0.9, 8.5)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    public static final VoxelShape SHAPE_UP_EW = rotateModelY(SHAPE_UP_NS);

}