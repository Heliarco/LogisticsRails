package dk.vertexspace.blocks.nodes;

import dk.vertexspace.blocks.RailConnected;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public abstract class NodeBase extends Block implements RailConnected {
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

    @Override
    @SuppressWarnings("deprecation")
    public boolean isTransparent(BlockState state) {
        return true;
    }


    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
