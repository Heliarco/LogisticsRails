package dk.vertexspace.voxelshapes;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

public class ShapeBase {

    protected ShapeBase(){}

    public static final VoxelShape PLACEHOLDER_SHAPE = Block.makeCuboidShape(1.0D, 1.0D, 1.0D, 15.0D, 15.0D, 15.0D);

    // Voxelshapes are normalized to between 0 and 1
    protected static VoxelShape rotateModelX(VoxelShape shape){
        VoxelShape[] outShape = new VoxelShape[]{VoxelShapes.empty()};

        shape.forEachBox(
                (minX, minY, minZ, maxX, maxY, maxZ) -> outShape[0] = VoxelShapes.or(outShape[0], VoxelShapes.create(
                        minX,
                        1 - maxZ,
                        minY,
                        maxX,
                        1 - minZ,
                        maxY))

        );
        return outShape[0];
    }

    protected static VoxelShape rotateModelY(VoxelShape shape){
        VoxelShape[] outShape = new VoxelShape[]{VoxelShapes.empty()};

        shape.forEachBox(
                (minX, minY, minZ, maxX, maxY, maxZ) -> outShape[0] = VoxelShapes.or(outShape[0], VoxelShapes.create(
                        1-maxZ,
                        minY,
                        minX,
                        1-minZ,
                        maxY,
                        maxX))
        );
        return outShape[0];
    }

    protected static VoxelShape rotateModelZ(VoxelShape shape){
        VoxelShape[] outShape = new VoxelShape[]{VoxelShapes.empty()};

        shape.forEachBox(
                (minX, minY, minZ, maxX, maxY, maxZ) -> outShape[0] = VoxelShapes.or(outShape[0], VoxelShapes.create(
                        minX,
                        minY,
                        minZ,  // -
                        maxX,
                        maxY,
                        maxZ)) // -
        );
        return outShape[0];
    }


}
