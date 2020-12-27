package dk.vertexspace.items;

import dk.vertexspace.constants.TabGroups;
import net.minecraft.block.Block;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.item.ToolItem;

import java.util.HashSet;

public class DebugInspector extends ToolItem {
    public DebugInspector() {
        super(-2f, -1, ItemTier.IRON, new HashSet<Block>(),
                new Item.Properties().group(TabGroups.TAB));
    }
}

