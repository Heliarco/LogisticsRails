package dk.vertexspace.util;

import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

public class VoxelShapeHelper {

    // Voxelshapes are normalized to between 0 and 1
    private static VoxelShape RotateModelX(VoxelShape shape){
        VoxelShape[] outShape = new VoxelShape[]{VoxelShapes.empty()};

        shape.forEachBox(
                (minX, minY, minZ, maxX, maxY, maxZ) -> {
                    outShape[0] = VoxelShapes.or(outShape[0], VoxelShapes.create(
                            minX, //
                            minY,
                            minZ,
                            maxX, //
                            maxY,
                            maxZ));
                }
        );
        return outShape[0];
    }


    private static VoxelShape RotateModelY(VoxelShape shape){
        VoxelShape[] outShape = new VoxelShape[]{VoxelShapes.empty()};

        shape.forEachBox(
                (minX, minY, minZ, maxX, maxY, maxZ) -> {
                    outShape[0] = VoxelShapes.or(outShape[0], VoxelShapes.create(
                            1-maxZ,
                            minY,
                            minX,
                            1-minZ,
                            maxY,
                            maxX));
                }
        );
        return outShape[0];
    }


    private static VoxelShape RotateModelZ(VoxelShape shape){
        VoxelShape[] outShape = new VoxelShape[]{VoxelShapes.empty()};

        shape.forEachBox(
                (minX, minY, minZ, maxX, maxY, maxZ) -> {
                    outShape[0] = VoxelShapes.or(outShape[0], VoxelShapes.create(
                            minX,
                            minY,
                            minZ, //
                            maxX,
                            maxY,
                            maxZ)); //
                }
        );
        return outShape[0];
    }







}