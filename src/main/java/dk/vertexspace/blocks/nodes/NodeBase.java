package dk.vertexspace.blocks.nodes;

import dk.vertexspace.blocks.FaceAttached;
import dk.vertexspace.blocks.RailConnected;
import dk.vertexspace.stateproperties.RailRotationProperty;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public abstract class NodeBase extends FaceAttached implements RailConnected {
    public NodeBase() {
        super(AbstractBlock.Properties.create(Material.MISCELLANEOUS)
                .hardnessAndResistance(1.0f, 1.0f)
                .setRequiresTool()
                .sound(SoundType.METAL)
                .harvestLevel(1) //
                .harvestTool(ToolType.PICKAXE)
                .notSolid() // Wont reduce neighbor block vertices
        );
    }
    public static final RailRotationProperty ROTATION = RailRotationProperty.create("rotation");

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        super.fillStateContainer(builder);
        builder.add(ROTATION);
    }

    @Nullable
    @Override
    // Called when block is placed.
    // Here we can calculate the state of the block before ultimate placement
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
