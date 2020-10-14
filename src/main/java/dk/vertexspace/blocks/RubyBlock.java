package dk.vertexspace.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class RubyBlock extends Block {

    public RubyBlock() {
        super(Block.Properties.create(Material.IRON)
                .hardnessAndResistance(5.0f, 6.0f)
                .sound(SoundType.METAL)
                .func_235861_h_() // Mapping missing of "setrequirestool"
                .harvestLevel(2) // 0 is wood, 1 is stone, 2 is iron, 3 is diamond etc.
                .harvestTool(ToolType.PICKAXE));
    }
}
