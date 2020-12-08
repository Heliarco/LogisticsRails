package dk.vertexspace.voxelshapes;
import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import java.util.stream.Stream;

public class ConsoleShapes {
	private ConsoleShapes(){}
	public static final VoxelShape S_UN = Stream.of(
		Block.makeCuboidShape(0, 0, 0, 16, 16, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
}