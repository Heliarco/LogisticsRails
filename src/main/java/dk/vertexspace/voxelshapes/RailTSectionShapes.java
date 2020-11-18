package dk.vertexspace.voxelshapes;
import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import java.util.stream.Stream;

public class RailTSectionShapes {
	private RailTSectionShapes(){}
	public static final VoxelShape S_UP_N = Stream.of(
		Block.makeCuboidShape(0, 0, 2, 16, 3, 14),
		Block.makeCuboidShape(2, 0, 4, 14, 3, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_UP_E = Stream.of(
		Block.makeCuboidShape(2, 0, 0, 14, 3, 16),
		Block.makeCuboidShape(0, 0, 2, 12, 3, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_UP_S = Stream.of(
		Block.makeCuboidShape(0, 0, 2, 16, 3, 14),
		Block.makeCuboidShape(2, 0, 0, 14, 3, 12)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_UP_W = Stream.of(
		Block.makeCuboidShape(2, 0, 0, 14, 3, 16),
		Block.makeCuboidShape(4, 0, 2, 16, 3, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_DOWN_S = Stream.of(
		Block.makeCuboidShape(0, 13, 2, 16, 16, 14),
		Block.makeCuboidShape(2, 13, 0, 14, 16, 12)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_DOWN_W = Stream.of(
		Block.makeCuboidShape(2, 13, 0, 14, 16, 16),
		Block.makeCuboidShape(4, 13, 2, 16, 16, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_DOWN_N = Stream.of(
		Block.makeCuboidShape(0, 13, 2, 16, 16, 14),
		Block.makeCuboidShape(2, 13, 4, 14, 16, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_DOWN_E = Stream.of(
		Block.makeCuboidShape(2, 13, 0, 14, 16, 16),
		Block.makeCuboidShape(0, 13, 2, 12, 16, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_NORTH_D = Stream.of(
		Block.makeCuboidShape(0, 2, 13, 16, 14, 16),
		Block.makeCuboidShape(2, 4, 13, 14, 16, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_NORTH_W = Stream.of(
		Block.makeCuboidShape(2, 0, 13, 14, 16, 16),
		Block.makeCuboidShape(0, 2, 13, 12, 14, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_NORTH_U = Stream.of(
		Block.makeCuboidShape(0, 2, 13, 16, 14, 16),
		Block.makeCuboidShape(2, 0, 13, 14, 12, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_NORTH_E = Stream.of(
		Block.makeCuboidShape(2, 0, 13, 14, 16, 16),
		Block.makeCuboidShape(4, 2, 13, 16, 14, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_SOUTH_U = Stream.of(
		Block.makeCuboidShape(0, 2, 0, 16, 14, 3),
		Block.makeCuboidShape(2, 0, 0, 14, 12, 3)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_SOUTH_W = Stream.of(
		Block.makeCuboidShape(2, 0, 0, 14, 16, 3),
		Block.makeCuboidShape(4, 2, 0, 16, 14, 3)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_SOUTH_D = Stream.of(
		Block.makeCuboidShape(0, 2, 0, 16, 14, 3),
		Block.makeCuboidShape(2, 4, 0, 14, 16, 3)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_SOUTH_E = Stream.of(
		Block.makeCuboidShape(2, 0, 0, 14, 16, 3),
		Block.makeCuboidShape(0, 2, 0, 12, 14, 3)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_WEST_N = Stream.of(
		Block.makeCuboidShape(13, 0, 2, 16, 16, 14),
		Block.makeCuboidShape(13, 2, 4, 16, 14, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_WEST_U = Stream.of(
		Block.makeCuboidShape(13, 2, 0, 16, 14, 16),
		Block.makeCuboidShape(13, 0, 2, 16, 12, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_WEST_S = Stream.of(
		Block.makeCuboidShape(13, 0, 2, 16, 16, 14),
		Block.makeCuboidShape(13, 2, 0, 16, 14, 12)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_WEST_D = Stream.of(
		Block.makeCuboidShape(13, 2, 0, 16, 14, 16),
		Block.makeCuboidShape(13, 4, 2, 16, 16, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_EAST_N = Stream.of(
		Block.makeCuboidShape(0, 0, 2, 3, 16, 14),
		Block.makeCuboidShape(0, 2, 4, 3, 14, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_EAST_U = Stream.of(
		Block.makeCuboidShape(0, 2, 0, 3, 14, 16),
		Block.makeCuboidShape(0, 0, 2, 3, 12, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_EAST_S = Stream.of(
		Block.makeCuboidShape(0, 0, 2, 3, 16, 14),
		Block.makeCuboidShape(0, 2, 0, 3, 14, 12)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_EAST_D = Stream.of(
		Block.makeCuboidShape(0, 2, 0, 3, 14, 16),
		Block.makeCuboidShape(0, 4, 2, 3, 16, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
}