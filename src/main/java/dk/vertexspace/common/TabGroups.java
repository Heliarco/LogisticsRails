package dk.vertexspace.common;

import dk.vertexspace.util.RegistryHandler;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TabGroups {
    public static final ItemGroup TAB =
            new ItemGroup("vertexspace_logisticsrails_tab") {
                @Override
                @OnlyIn(Dist.CLIENT)
                public ItemStack createIcon()
                {
                    //return new ItemStack(Blocks.CHEST); // How to use built in defined
                    return new ItemStack(RegistryHandler.RUBY.get());
                }
            };

}
