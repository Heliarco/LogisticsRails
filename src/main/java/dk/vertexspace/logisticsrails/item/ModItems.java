package dk.vertexspace.logisticsrails.item;

import dk.vertexspace.logisticsrails.LogisticsRails;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, LogisticsRails.MODID);

    public static final RegistryObject<Item> LogisticsRail = ITEMS.register("logisticsrail", () ->
            new Item(
                    new Item.Properties().setId(ITEMS.key("logisticsrail"))
            ));

    public static final RegistryObject<Item> LogisticsWrench = ITEMS.register("logisticswrench", () ->
            new Item(
                    new Item.Properties().setId(ITEMS.key("logisticswrench"))
            ));


    // Called by main method at the appropriate event
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

    // Called by main method at the appropriate event
    public static void buildCreativeTab(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.LogisticsRail);
            event.accept(ModItems.LogisticsWrench);
        }
    }
}
