package dk.vertexspace.voxelshapes;
import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import java.util.stream.Stream;

public class RailBendDownShapes {
	private RailBendDownShapes(){}
	public static final VoxelShape S_UN = Stream.of(
		Block.makeCuboidShape(2, 0, 13, 14, 3, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_UE = Stream.of(
		Block.makeCuboidShape(0, 0, 2, 3, 3, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_US = Stream.of(
		Block.makeCuboidShape(2, 0, 0, 14, 3, 3)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_UW = Stream.of(
		Block.makeCuboidShape(13, 0, 2, 16, 3, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_DN = Stream.of(
		Block.makeCuboidShape(2, 13, 13, 14, 16, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_DE = Stream.of(
		Block.makeCuboidShape(0, 13, 2, 3, 16, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_DS = Stream.of(
		Block.makeCuboidShape(2, 13, 0, 14, 16, 3)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_DW = Stream.of(
		Block.makeCuboidShape(13, 13, 2, 16, 16, 14)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_NW = Stream.of(
		Block.makeCuboidShape(13, 2, 13, 16, 14, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_NE = Stream.of(
		Block.makeCuboidShape(0, 2, 13, 3, 14, 16)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_SW = Stream.of(
		Block.makeCuboidShape(13, 2, 0, 16, 14, 3)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
	public static final VoxelShape S_SE = Stream.of(
		Block.makeCuboidShape(0, 2, 0, 3, 14, 3)).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();
}