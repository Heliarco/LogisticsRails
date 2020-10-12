package dk.brandbyge.alexander.util;

import dk.brandbyge.alexander.LogisticsRails;
import dk.brandbyge.alexander.items.ItemBase;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LogisticsRails.MOD_ID);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }


    // Items
    //public static final RegistryObject<Item> LOGISTICSBOT = ITEMS.register(name:"logisti");

    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", ItemBase::new);

}
