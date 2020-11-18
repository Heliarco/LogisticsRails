package dk.vertexspace.voxelshapes;
import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import java.util.stream.Stream;

public class RailTurnShapes {
	private RailTurnShapes(){}
	public static final VoxelShape S_UP_NE = Stream.of(
		Block.makeCuboidShape(2, 0, 0, 14, 3, 14),
		Block.makeCuboidShape(4, 0, 2, 16, 3, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_UP_SE = Stream.of(
		Block.makeCuboidShape(2, 0, 2, 16, 3, 14),
		Block.makeCuboidShape(2, 0, 4, 14, 3, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_UP_SW = Stream.of(
		Block.makeCuboidShape(2, 0, 2, 14, 3, 16),
		Block.makeCuboidShape(0, 0, 2, 12, 3, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_UP_NW = Stream.of(
		Block.makeCuboidShape(0, 0, 2, 14, 3, 14),
		Block.makeCuboidShape(2, 0, 0, 14, 3, 12)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_DOWN_SE = Stream.of(
		Block.makeCuboidShape(2, 13, 2, 14, 16, 16),
		Block.makeCuboidShape(4, 13, 2, 16, 16, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_DOWN_SW = Stream.of(
		Block.makeCuboidShape(0, 13, 2, 14, 16, 14),
		Block.makeCuboidShape(2, 13, 4, 14, 16, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_DOWN_NE = Stream.of(
		Block.makeCuboidShape(2, 13, 0, 14, 16, 14),
		Block.makeCuboidShape(0, 13, 2, 12, 16, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_DOWN_NW = Stream.of(
		Block.makeCuboidShape(2, 13, 2, 16, 16, 14),
		Block.makeCuboidShape(2, 13, 0, 14, 16, 12)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_NORTH_DE = Stream.of(
		Block.makeCuboidShape(2, 0, 13, 14, 14, 16),
		Block.makeCuboidShape(4, 2, 13, 16, 14, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_NORTH_UE = Stream.of(
		Block.makeCuboidShape(2, 2, 13, 16, 14, 16),
		Block.makeCuboidShape(2, 4, 13, 14, 16, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_NORTH_UW = Stream.of(
		Block.makeCuboidShape(2, 2, 13, 14, 16, 16),
		Block.makeCuboidShape(0, 2, 13, 12, 14, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_NORTH_DW = Stream.of(
		Block.makeCuboidShape(0, 2, 13, 14, 14, 16),
		Block.makeCuboidShape(2, 0, 13, 14, 12, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_SOUTH_UE = Stream.of(
		Block.makeCuboidShape(2, 2, 0, 14, 16, 3),
		Block.makeCuboidShape(4, 2, 0, 16, 14, 3)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_SOUTH_UW = Stream.of(
		Block.makeCuboidShape(0, 2, 0, 14, 14, 3),
		Block.makeCuboidShape(2, 4, 0, 14, 16, 3)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_SOUTH_DW = Stream.of(
		Block.makeCuboidShape(2, 0, 0, 14, 14, 3),
		Block.makeCuboidShape(0, 2, 0, 12, 14, 3)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_SOUTH_DE = Stream.of(
		Block.makeCuboidShape(2, 2, 0, 16, 14, 3),
		Block.makeCuboidShape(2, 0, 0, 14, 12, 3)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_WEST_UN = Stream.of(
		Block.makeCuboidShape(13, 2, 0, 16, 14, 14),
		Block.makeCuboidShape(13, 4, 2, 16, 16, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_WEST_US = Stream.of(
		Block.makeCuboidShape(13, 2, 2, 16, 16, 14),
		Block.makeCuboidShape(13, 2, 4, 16, 14, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_WEST_DS = Stream.of(
		Block.makeCuboidShape(13, 2, 2, 16, 14, 16),
		Block.makeCuboidShape(13, 0, 2, 16, 12, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_WEST_DN = Stream.of(
		Block.makeCuboidShape(13, 0, 2, 16, 14, 14),
		Block.makeCuboidShape(13, 2, 0, 16, 14, 12)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_EAST_DN = Stream.of(
		Block.makeCuboidShape(0, 2, 0, 3, 14, 14),
		Block.makeCuboidShape(0, 0, 2, 3, 12, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_EAST_UN = Stream.of(
		Block.makeCuboidShape(0, 2, 2, 3, 16, 14),
		Block.makeCuboidShape(0, 2, 0, 3, 14, 12)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_EAST_US = Stream.of(
		Block.makeCuboidShape(0, 2, 2, 3, 14, 16),
		Block.makeCuboidShape(0, 4, 2, 3, 16, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_EAST_DS = Stream.of(
		Block.makeCuboidShape(0, 0, 2, 3, 14, 14),
		Block.makeCuboidShape(0, 2, 4, 3, 14, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
}