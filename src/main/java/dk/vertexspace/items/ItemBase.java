package dk.vertexspace.items;

import dk.vertexspace.LogisticsRails;
import dk.vertexspace.common.TabGroups;
import dk.vertexspace.util.RegistryHandler;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemBase extends Item {

    public ItemBase() {
        super(new Item.Properties().group(
                TabGroups.TAB
        ));
    }

}
