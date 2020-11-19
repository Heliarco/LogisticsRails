package dk.vertexspace.rails;

import dk.vertexspace.stateproperties.RailBendUpKind;
import dk.vertexspace.stateproperties.RailBendUpKindProperty;
import dk.vertexspace.voxelshapes.RailBendUpShapes;
import dk.vertexspace.voxelshapes.ShapeBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Optional;

@SuppressWarnings("java:S110") // We can't really control the inheritance depth here.
public class RailBendUp extends RailBase {
    public static final RailBendUpKindProperty ORIENTATION = RailBendUpKindProperty.create("orientation");



    // Be carefull, while we inherit the base property "FACING" from the parent DirectionalBlock,
    // we skip registering the facing property. We need our own as we are a "Bi-directional" block.
    // This Specifically means we must not call super methods of getStateForPlacement and populateStateContainer


    @Override
    protected BlockState rotateOnRightClick(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        int timeout = 0;
        RailBendUpKind orientation = state.get(ORIENTATION);
        while (timeout < 11){

            orientation = orientation.next();

            if (isValidPosition(orientation, worldIn, pos )) {

                return state.with(ORIENTATION, orientation);
            }
        }
        return null;

    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context){

        RailBendUpKind orientation = state.get(ORIENTATION);

        switch(orientation) {
            case DOWN_EAST:
                return RailBendUpShapes.S_DE;
            case DOWN_NORTH:
                return RailBendUpShapes.S_DN;
            case DOWN_SOUTH:
                return RailBendUpShapes.S_DS;
            case DOWN_WEST:
                return RailBendUpShapes.S_DW;
            case NORTH_EAST:
                return RailBendUpShapes.S_NE;
            case NORTH_WEST:
                return RailBendUpShapes.S_NW;
            case SOUTH_EAST:
                return RailBendUpShapes.S_SE;
            case SOUTH_WEST:
                return RailBendUpShapes.S_SW;
            case UP_EAST:
                return RailBendUpShapes.S_UE;
            case UP_NORTH:
                return RailBendUpShapes.S_UN;
            case UP_SOUTH:
                return RailBendUpShapes.S_US;
            case UP_WEST:
                return RailBendUpShapes.S_UW;
            default:
                return ShapeBase.PLACEHOLDER_SHAPE;
        }
    }


    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {

        IWorldReader worldIn = context.getWorld();
        BlockPos pos = context.getPos();

        Direction primaryPlacementDirection = context.getNearestLookingDirections()[0];

        // Filter only valid orientations based on world geometry
        Optional<RailBendUpKind> kind = Arrays.stream(RailBendUpKind.values())
            .filter(bendKind -> isValidPosition(bendKind, worldIn, pos))

        // Filter those that match orientation
            .filter(bendKind -> {
                Direction neededFace = primaryPlacementDirection.getOpposite();

                return bendKind.getElements().anyMatch(side -> side == neededFace);
            }).findAny();

        if (!kind.isPresent()) {
            return null;
        }

        BlockState blockstate = this.getDefaultState();
        return blockstate.with(ORIENTATION, kind.get());
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(ORIENTATION);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        // Basically copied from wall torch.
        RailBendUpKind orientation = state.get(ORIENTATION);
        return isValidPosition(orientation, worldIn, pos);

    }

    public boolean isValidPosition(RailBendUpKind orientation, IWorldReader worldIn, BlockPos pos) {
        return orientation.getElements().allMatch(side -> {
            BlockPos attachedToPos = pos.offset(side.getOpposite());
            BlockState blockstate = worldIn.getBlockState(attachedToPos);
            return blockstate.isSolidSide(worldIn, attachedToPos, side);
        });
    }



    @Override
    @SuppressWarnings("deprecation")
    @Nonnull
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(ORIENTATION, state.get(ORIENTATION).next());
    }


    @Override
    @SuppressWarnings("deprecation")
    @Nonnull
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.with(ORIENTATION, state.get(ORIENTATION).next().next().next().next().next().next());
    }

}
