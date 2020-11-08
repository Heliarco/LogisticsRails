package dk.vertexspace.items;

import dk.vertexspace.constants.TabGroups;
import net.minecraft.item.Item;

public class ItemBase extends Item {

    public ItemBase() {
        super(new Item.Properties().group(
                TabGroups.TAB
        ));
    }

}
