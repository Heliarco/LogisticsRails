package dk.vertexspace.voxelshapes;
import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import java.util.stream.Stream;

public class RailXSectionShape {
	private RailXSectionShape(){}
	public static final VoxelShape S_UP = Stream.of(
		Block.makeCuboidShape(2, 0, 0, 14, 3, 16),
		Block.makeCuboidShape(0, 0, 2, 16, 3, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_DOWN = Stream.of(
		Block.makeCuboidShape(2, 13, 0, 14, 16, 16),
		Block.makeCuboidShape(0, 13, 2, 16, 16, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_NORTH = Stream.of(
		Block.makeCuboidShape(2, 0, 13, 14, 16, 16),
		Block.makeCuboidShape(0, 2, 13, 16, 14, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_SOUTH = Stream.of(
		Block.makeCuboidShape(2, 0, 0, 14, 16, 3),
		Block.makeCuboidShape(0, 2, 0, 16, 14, 3)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_WEST = Stream.of(
		Block.makeCuboidShape(13, 2, 0, 16, 14, 16),
		Block.makeCuboidShape(13, 0, 2, 16, 16, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_EAST = Stream.of(
		Block.makeCuboidShape(0, 2, 0, 3, 14, 16),
		Block.makeCuboidShape(0, 0, 2, 3, 16, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
}