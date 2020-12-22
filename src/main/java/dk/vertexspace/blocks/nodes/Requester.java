package dk.vertexspace.blocks.nodes;

import dk.vertexspace.init.ModTileEntityTypes;
import dk.vertexspace.stateproperties.RailRotationProperty;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Requester extends NodeBase {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final RailRotationProperty ROTATION = RailRotationProperty.create("rotation");
    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
        builder.add(ROTATION);
    }


    @Override
    @SuppressWarnings("deprecation")
    @Nonnull
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(HORIZONTAL_FACING, rot.rotate(state.get(HORIZONTAL_FACING)));
    }


    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntityTypes.REQUESTER.get().create();

    }    @Nullable
    @Override
    // Called when block is placed.
    // Here we can calculate the state of the block before ultimate placement
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }



    @Override
    @SuppressWarnings("deprecation")
    @Nonnull
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(HORIZONTAL_FACING)));
    }
}
