package dk.vertexspace.rails;

import dk.vertexspace.stateproperties.RailRotationProperty;
import dk.vertexspace.voxelshapes.RailStraightShapes;
import dk.vertexspace.voxelshapes.ShapeBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

@SuppressWarnings("java:S110") // We can't really control the inheritance depth here.
public class RailTurn extends RailBase {
    @Override
    protected boolean handleRightClick(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        BlockState s = state.with(ROTATION, state.get(ROTATION).next());
        worldIn.setBlockState(pos, s, 3);
        return true;
    }

    public static final RailRotationProperty ROTATION = RailRotationProperty.create("rotation");


    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(ROTATION);
        super.fillStateContainer(builder);
    }

}
