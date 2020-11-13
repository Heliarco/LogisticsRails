package dk.vertexspace.voxelshapes;
import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import java.util.stream.Stream;

public class RailStraightShapes {
	private RailStraightShapes(){}
	public static final VoxelShape S_UP_NS = Stream.of(
		Block.makeCuboidShape(10, 0, 0, 14, 1, 16),
		Block.makeCuboidShape(2, 0, 0, 6, 1, 16),
		Block.makeCuboidShape(12, 1, 0, 13, 3, 16),
		Block.makeCuboidShape(11, 1, 0, 12, 3, 16),
		Block.makeCuboidShape(4, 1, 0, 5, 3, 16),
		Block.makeCuboidShape(3, 1, 0, 4, 3, 16),
		Block.makeCuboidShape(6, 0, 7.5, 10, 0.9000000000000004, 8.5)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_UP_EW = Stream.of(
		Block.makeCuboidShape(0, 0, 10, 16, 1, 14),
		Block.makeCuboidShape(0, 0, 2, 16, 1, 6),
		Block.makeCuboidShape(0, 1, 12, 16, 3, 13),
		Block.makeCuboidShape(0, 1, 11, 16, 3, 12),
		Block.makeCuboidShape(0, 1, 4, 16, 3, 5),
		Block.makeCuboidShape(0, 1, 3, 16, 3, 4),
		Block.makeCuboidShape(7.5, 0, 6, 8.5, 0.9000000000000004, 10)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_DOWN_NS = Stream.of(
		Block.makeCuboidShape(10, 15, 0, 14, 16, 16),
		Block.makeCuboidShape(2, 15, 0, 6, 16, 16),
		Block.makeCuboidShape(12, 13, 0, 13, 15, 16),
		Block.makeCuboidShape(11, 13, 0, 12, 15, 16),
		Block.makeCuboidShape(4, 13, 0, 5, 15, 16),
		Block.makeCuboidShape(3, 13, 0, 4, 15, 16),
		Block.makeCuboidShape(6, 15.1, 7.5, 10, 16, 8.5)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_DOWN_EW = Stream.of(
		Block.makeCuboidShape(0, 15, 10, 16, 16, 14),
		Block.makeCuboidShape(0, 15, 2, 16, 16, 6),
		Block.makeCuboidShape(0, 13, 12, 16, 15, 13),
		Block.makeCuboidShape(0, 13, 11, 16, 15, 12),
		Block.makeCuboidShape(0, 13, 4, 16, 15, 5),
		Block.makeCuboidShape(0, 13, 3, 16, 15, 4),
		Block.makeCuboidShape(7.5, 15.1, 6, 8.5, 16, 10)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_NORTH_UD = Stream.of(
		Block.makeCuboidShape(10, 0, 15, 14, 16, 16),
		Block.makeCuboidShape(2, 0, 15, 6, 16, 16),
		Block.makeCuboidShape(12, 0, 13, 13, 16, 15),
		Block.makeCuboidShape(11, 0, 13, 12, 16, 15),
		Block.makeCuboidShape(4, 0, 13, 5, 16, 15),
		Block.makeCuboidShape(3, 0, 13, 4, 16, 15),
		Block.makeCuboidShape(6, 7.5, 15.1, 10, 8.5, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_NORTH_EW = Stream.of(
		Block.makeCuboidShape(0, 10, 15, 16, 14, 16),
		Block.makeCuboidShape(0, 2, 15, 16, 6, 16),
		Block.makeCuboidShape(0, 12, 13, 16, 13, 15),
		Block.makeCuboidShape(0, 11, 13, 16, 12, 15),
		Block.makeCuboidShape(0, 4, 13, 16, 5, 15),
		Block.makeCuboidShape(0, 3, 13, 16, 4, 15),
		Block.makeCuboidShape(7.5, 6, 15.1, 8.5, 10, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_SOUTH_UD = Stream.of(
		Block.makeCuboidShape(10, 0, 0, 14, 16, 1),
		Block.makeCuboidShape(2, 0, 0, 6, 16, 1),
		Block.makeCuboidShape(12, 0, 1, 13, 16, 3),
		Block.makeCuboidShape(11, 0, 1, 12, 16, 3),
		Block.makeCuboidShape(4, 0, 1, 5, 16, 3),
		Block.makeCuboidShape(3, 0, 1, 4, 16, 3),
		Block.makeCuboidShape(6, 7.5, 0, 10, 8.5, 0.9000000000000004)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_SOUTH_EW = Stream.of(
		Block.makeCuboidShape(0, 10, 0, 16, 14, 1),
		Block.makeCuboidShape(0, 2, 0, 16, 6, 1),
		Block.makeCuboidShape(0, 12, 1, 16, 13, 3),
		Block.makeCuboidShape(0, 11, 1, 16, 12, 3),
		Block.makeCuboidShape(0, 4, 1, 16, 5, 3),
		Block.makeCuboidShape(0, 3, 1, 16, 4, 3),
		Block.makeCuboidShape(7.5, 6, 0, 8.5, 10, 0.9000000000000004)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_WEST_UD = Stream.of(
		Block.makeCuboidShape(15, 0, 10, 16, 16, 14),
		Block.makeCuboidShape(15, 0, 2, 16, 16, 6),
		Block.makeCuboidShape(13, 0, 12, 15, 16, 13),
		Block.makeCuboidShape(13, 0, 11, 15, 16, 12),
		Block.makeCuboidShape(13, 0, 4, 15, 16, 5),
		Block.makeCuboidShape(13, 0, 3, 15, 16, 4),
		Block.makeCuboidShape(15.1, 7.5, 6, 16, 8.5, 10)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_WEST_NS = Stream.of(
		Block.makeCuboidShape(15, 10, 0, 16, 14, 16),
		Block.makeCuboidShape(15, 2, 0, 16, 6, 16),
		Block.makeCuboidShape(13, 12, 0, 15, 13, 16),
		Block.makeCuboidShape(13, 11, 0, 15, 12, 16),
		Block.makeCuboidShape(13, 4, 0, 15, 5, 16),
		Block.makeCuboidShape(13, 3, 0, 15, 4, 16),
		Block.makeCuboidShape(15.1, 6, 7.5, 16, 10, 8.5)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_EAST_UD = Stream.of(
		Block.makeCuboidShape(0, 0, 2, 1, 16, 6),
		Block.makeCuboidShape(0, 0, 10, 1, 16, 14),
		Block.makeCuboidShape(1, 0, 3, 3, 16, 4),
		Block.makeCuboidShape(1, 0, 4, 3, 16, 5),
		Block.makeCuboidShape(1, 0, 11, 3, 16, 12),
		Block.makeCuboidShape(1, 0, 12, 3, 16, 13),
		Block.makeCuboidShape(0, 7.5, 6, 0.9000000000000004, 8.5, 10)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_EAST_NS = Stream.of(
		Block.makeCuboidShape(0, 2, 0, 1, 6, 16),
		Block.makeCuboidShape(0, 10, 0, 1, 14, 16),
		Block.makeCuboidShape(1, 3, 0, 3, 4, 16),
		Block.makeCuboidShape(1, 4, 0, 3, 5, 16),
		Block.makeCuboidShape(1, 11, 0, 3, 12, 16),
		Block.makeCuboidShape(1, 12, 0, 3, 13, 16),
		Block.makeCuboidShape(0, 6, 7.5, 0.9000000000000004, 10, 8.5)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
}