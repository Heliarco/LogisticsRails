package dk.vertexspace.voxelshapes;

import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.VoxelShape;

public class ShapeBase {

    protected ShapeBase(){}

    public static final VoxelShape PLACEHOLDER_SHAPE = Block.makeCuboidShape(1.0D, 1.0D, 1.0D, 15.0D, 15.0D, 15.0D);
}
