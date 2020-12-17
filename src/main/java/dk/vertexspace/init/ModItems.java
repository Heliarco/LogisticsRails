package dk.vertexspace.init;

import dk.vertexspace.items.BlockItemBase;
import dk.vertexspace.items.ItemBase;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static dk.vertexspace.constants.NameConstants.MOD_ID;

public class ModItems {

    private ModItems(){}

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    // Crafting materials
    public static final RegistryObject<Item> CLAYCRETE = ITEMS.register("claycrete", ItemBase::new);
    public static final RegistryObject<Item> REDSTONE_ALLOY_INGOT = ITEMS.register("redstone_alloy_ingot", ItemBase::new);
    public static final RegistryObject<Item> REDSTONE_ALLOY_BLEND = ITEMS.register("redstone_alloy_blend", ItemBase::new);
    public static final RegistryObject<Item> LAPIS_ALLOY_INGOT = ITEMS.register("lapis_alloy_ingot", ItemBase::new);
    public static final RegistryObject<Item> LAPIS_ALLOY_BLEND = ITEMS.register("lapis_alloy_blend", ItemBase::new);

    // Block Items
    public static final RegistryObject<Item> RAIL_STRAIGHT = ITEMS.register("rail_straight",
            () -> new BlockItemBase(ModBlocks.RAIL_STRAIGHT.get()));

    public static final RegistryObject<Item> RAIL_TURN = ITEMS.register("rail_turn",
            () -> new BlockItemBase(ModBlocks.RAIL_TURN.get()));

    public static final RegistryObject<Item> RAIL_TSECTION = ITEMS.register("rail_tsection",
            () -> new BlockItemBase(ModBlocks.RAIL_TSECTION.get()));

    public static final RegistryObject<Item> RAIL_XSECTION = ITEMS.register("rail_xsection",
            () -> new BlockItemBase(ModBlocks.RAIL_XSECTION.get()));

    public static final RegistryObject<Item> RAIL_BEND_UP = ITEMS.register("rail_bend_up",
            () -> new BlockItemBase(ModBlocks.RAIL_BEND_UP.get()));

    public static final RegistryObject<Item> RAIL_BEND_DOWN = ITEMS.register("rail_bend_down",
            () -> new BlockItemBase(ModBlocks.RAIL_BEND_DOWN.get()));

    public static final RegistryObject<Item> NODE_CONSOLE = ITEMS.register("node_console",
            () -> new BlockItemBase(ModBlocks.NODE_CONSOLE.get()));

}
