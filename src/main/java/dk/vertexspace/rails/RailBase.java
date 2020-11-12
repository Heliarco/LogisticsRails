package dk.vertexspace.rails;

import dk.vertexspace.constants.Log;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class RailBase extends DirectionalBlock {

    public RailBase() {
        super(Block.Properties.create(Material.MISCELLANEOUS)
                .hardnessAndResistance(1.0f, 1.0f)
                .sound(SoundType.METAL)
                .func_235861_h_() // setrequirestool
                .harvestLevel(0) //
                .harvestTool(ToolType.PICKAXE)
                .notSolid() // doesn't stop grass growth. Presumably
              //  .doesNotBlockMovement()
        );
    }

    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        // Basically copied from wall torch.
        Direction currentFacing = state.get(FACING);
        BlockPos attachedToPos = pos.offset(currentFacing.getOpposite());
        BlockState blockstate = worldIn.getBlockState(attachedToPos);
        return blockstate.isSolidSide(worldIn, attachedToPos, currentFacing);
    }

    @Nullable
    @Override
    // Called when block is placed.
    // Here we can calculate the state of the block before ultimate placement
    public BlockState getStateForPlacement(BlockItemUseContext context) {

        BlockState blockstate = this.getDefaultState();
        IWorldReader iworldreader = context.getWorld();
        BlockPos blockpos = context.getPos();
        Direction[] adirection = context.getNearestLookingDirections();

        for(Direction direction : adirection) {
                Direction direction1 = direction.getOpposite();
                blockstate = blockstate.with(FACING, direction1);
                if (blockstate.isValidPosition(iworldreader, blockpos)) {
                    return blockstate;
                }
        }

        return null;
    }


    // Rightclick basically
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        return  ActionResultType.PASS;

        return ActionResultType.func_233537_a_(worldIn.isRemote);
    }



    @Override
    public boolean isTransparent(BlockState state) {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    @Nonnull
    public BlockState rotate(BlockState state, Rotation rot) {
         return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    @SuppressWarnings("deprecation")
    @Nonnull
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return !this.isValidPosition(stateIn, worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1f; // No shadows really its a tiny model
    }


    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(FACING);
    }

}
