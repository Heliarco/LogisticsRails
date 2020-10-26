package dk.vertexspace.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class DebugRailV2 extends Block {


    // This is a blockstate property of 4 values!
    private static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public DebugRailV2() {
        super(Block.Properties.create(Material.IRON)
                .hardnessAndResistance(1.0f, 1.0f)
                .sound(SoundType.ANVIL)
                .func_235861_h_() // Mapping missing of "setrequirestool" // New in forge 1.16.3 (or minecraft 1.16.3 whatever)
                .harvestLevel(0) // 0 is wood, 1 is stone, 2 is iron, 3 is diamond etc.
                .harvestTool(ToolType.PICKAXE));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    @Deprecated
    public BlockState rotate(BlockState state, Rotation rot){
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn){
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }


    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder){
        builder.add(FACING);
    }


}
