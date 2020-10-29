package dk.vertexspace.baseclasses;

import dk.vertexspace.constants.TabGroups;
import net.minecraft.item.Item;

public class ItemBase extends Item {

    public ItemBase() {
        super(new Item.Properties().group(
                TabGroups.TAB
        ));
    }

}
