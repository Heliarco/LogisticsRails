package dk.vertexspace.items;

import dk.vertexspace.constants.TabGroups;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraftforge.common.ToolType;

import java.util.HashSet;
import java.util.Set;

public class Clawbar extends ToolItem {
    public Clawbar() {
        super(-2f, -1, ItemTier.IRON, new HashSet<Block>(),
                new Item.Properties().group(
                TabGroups.TAB
        ));

    }
}

