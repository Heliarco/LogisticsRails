package dk.vertexspace.rails;

import dk.vertexspace.stateproperties.RailBendUpKind;
import dk.vertexspace.stateproperties.RailBendUpKindProperty;
import dk.vertexspace.voxelshapes.ShapeBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import org.apache.commons.lang3.NotImplementedException;


public class RailBendUp extends RailBase {
    public static final RailBendUpKindProperty ORIENTATION = RailBendUpKindProperty.create("orientation");



    // Be carefull, while we inherit the base property "FACING" from the parent DirectionalBlock,
    // we skip registering the facing property. We need our own as we are a "Bi-directional" block.
    // This Specifically means we must not call super methods of getStateForPlacement and populateStateContainer


    @Override
    protected boolean handleRightClick(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        //BlockState s = state.with(ROTATED, !state.get(ROTATED));
        //worldIn.setBlockState(pos, s, 3);


        throw new NotImplementedException("");
        //return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context){

        RailBendUpKind orientation = state.get(ORIENTATION);

        switch(orientation) {
            default:
                return ShapeBase.PLACEHOLDER_SHAPE;
        }
    }


    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {

        PlayerEntity player = context.getPlayer();

        BlockState blockstate = this.getDefaultState();
        IWorldReader iworldreader = context.getWorld();
        BlockPos blockpos = context.getPos();
        //Direction[] adirection = context.getNearestLookingDirections();

        return blockstate.with(ORIENTATION, RailBendUpKind.DOWN_EAST);
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(ORIENTATION);
        super.fillStateContainer(builder);
    }




}
