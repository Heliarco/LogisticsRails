package dk.vertexspace.blocks.nodes;

import dk.vertexspace.blocks.FaceAttached;
import dk.vertexspace.blocks.RailConnected;
import dk.vertexspace.stateproperties.RailRotationProperty;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.state.StateContainer;
import net.minecraftforge.common.ToolType;

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
        builder.add(FACING);
        get state for placement here ? Most nodes will be exact copies in state logic
    }


    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
