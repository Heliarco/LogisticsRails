package dk.vertexspace.voxelshapes;
import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import java.util.stream.Stream;

public class RailStraightShapes {
	private RailStraightShapes(){}
	public static final VoxelShape S_UP_NS = Stream.of(
		Block.makeCuboidShape(2, 0, 0, 14, 3, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_UP_EW = Stream.of(
		Block.makeCuboidShape(0, 0, 2, 16, 3, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_DOWN_NS = Stream.of(
		Block.makeCuboidShape(2, 13, 0, 14, 16, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_DOWN_EW = Stream.of(
		Block.makeCuboidShape(0, 13, 2, 16, 16, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_NORTH_UD = Stream.of(
		Block.makeCuboidShape(2, 0, 13, 14, 16, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_NORTH_EW = Stream.of(
		Block.makeCuboidShape(0, 2, 13, 16, 14, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_SOUTH_UD = Stream.of(
		Block.makeCuboidShape(2, 0, 0, 14, 16, 3)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_SOUTH_EW = Stream.of(
		Block.makeCuboidShape(0, 2, 0, 16, 14, 3)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_WEST_UD = Stream.of(
		Block.makeCuboidShape(13, 0, 2, 16, 16, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_WEST_NS = Stream.of(
		Block.makeCuboidShape(13, 2, 0, 16, 14, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_EAST_UD = Stream.of(
		Block.makeCuboidShape(0, 0, 2, 3, 16, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_EAST_NS = Stream.of(
		Block.makeCuboidShape(0, 2, 0, 3, 14, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
}