package dk.vertexspace.constants;

import dk.vertexspace.RegistryHandler;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static dk.vertexspace.constants.NameConstants.TAB_ID;

public class TabGroups {
    private TabGroups(){}
    public static final ItemGroup TAB =
            new ItemGroup(TAB_ID) {
                @Override
                @OnlyIn(Dist.CLIENT)
                public ItemStack createIcon()
                {
                    //return new ItemStack(Blocks.CHEST); // How to use built in defined
                    return new ItemStack(RegistryHandler.CLAYCRETE.get());
                }
            };
}
