package dk.vertexspace.items;

import dk.vertexspace.constants.TabGroups;
import net.minecraft.item.*;

import java.util.HashSet;

public class Clawbar extends ToolItem {
    public Clawbar() {
        super(-2f, -1, ItemTier.IRON, new HashSet<>(),
                new Item.Properties().group(
                TabGroups.TAB
        ));

    }
}

