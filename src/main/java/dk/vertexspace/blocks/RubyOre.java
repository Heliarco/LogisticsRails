package dk.vertexspace.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.ToolType;

// We are not forced to use oreblock super class for ore generation. Its here for the getExpoDrop method
public class RubyOre extends OreBlock {
    public RubyOre() {
        super(AbstractBlock.Properties.create(Material.IRON)
        .hardnessAndResistance(3.0f, 4.0f)
        .sound(SoundType.STONE)
        .func_235861_h_()
        .harvestLevel(2)
        .harvestTool(ToolType.PICKAXE));
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader reader, BlockPos pos, int fortune, int silkTouch){
        return 1;
    }
}
