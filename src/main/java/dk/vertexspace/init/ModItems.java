package dk.vertexspace.init;

import dk.vertexspace.constants.NameConstants;
import dk.vertexspace.items.BlockItemBase;
import dk.vertexspace.items.Clawbar;
import dk.vertexspace.items.ItemBase;
import dk.vertexspace.items.Railometer;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static dk.vertexspace.constants.NameConstants.MOD_ID;

@SuppressWarnings("unused")
public class ModItems {

    private ModItems(){}

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);

    // Crafting materials
    public static final RegistryObject<Item> CLAYCRETE = ITEMS.register(NameConstants.CLAYCRETE, ItemBase::new);
    public static final RegistryObject<Item> REDSTONE_ALLOY_INGOT = ITEMS.register(NameConstants.REDSTONE_ALLOY_INGOT, ItemBase::new);
    public static final RegistryObject<Item> REDSTONE_ALLOY_BLEND = ITEMS.register(NameConstants.REDSTONE_ALLOY_BLEND, ItemBase::new);
    public static final RegistryObject<Item> LAPIS_ALLOY_INGOT = ITEMS.register(NameConstants.LAPIS_ALLOY_INGOT, ItemBase::new);
    public static final RegistryObject<Item> LAPIS_ALLOY_BLEND = ITEMS.register(NameConstants.LAPIS_ALLOY_BLEND, ItemBase::new);
    public static final RegistryObject<Item> DIAMOND_NUGGET = ITEMS.register(NameConstants.DIAMOND_NUGGET, ItemBase::new);

    public static final RegistryObject<Item> BOT_CORE = ITEMS.register(NameConstants.BOT_CORE, ItemBase::new);
    public static final RegistryObject<Item> BOT_CORE_ASSEMBLY = ITEMS.register(NameConstants.BOT_CORE_ASSEMBLY, ItemBase::new);
    public static final RegistryObject<Item> MACHINE_CORE = ITEMS.register(NameConstants.MACHINE_CORE, ItemBase::new);
    public static final RegistryObject<Item> MACHINE_CORE_ASSEMBLY = ITEMS.register(NameConstants.MACHINE_CORE_ASSEMBLY, ItemBase::new);
    public static final RegistryObject<Item> CONSOLE_CORE = ITEMS.register(NameConstants.CONSOLE_CORE, ItemBase::new);
    public static final RegistryObject<Item> CONSOLE_CORE_ASSEMBLY = ITEMS.register(NameConstants.CONSOLE_CORE_ASSEMBLY, ItemBase::new);


    public static final RegistryObject<Item> CLAWBAR = ITEMS.register(NameConstants.CLAWBAR, Clawbar::new);
    public static final RegistryObject<Item> RAILOMETER = ITEMS.register(NameConstants.RAILOMETER, Railometer::new);


    // Block Items
    public static final RegistryObject<Item> RAIL_STRAIGHT = ITEMS.register(NameConstants.RAIL_STRAIGHT,
            () -> new BlockItemBase(ModBlocks.RAIL_STRAIGHT.get()));

    public static final RegistryObject<Item> RAIL_TURN = ITEMS.register(NameConstants.RAIL_TURN,
            () -> new BlockItemBase(ModBlocks.RAIL_TURN.get()));

    public static final RegistryObject<Item> RAIL_TSECTION = ITEMS.register(NameConstants.RAIL_TSECTION,
            () -> new BlockItemBase(ModBlocks.RAIL_TSECTION.get()));

    public static final RegistryObject<Item> RAIL_XSECTION = ITEMS.register(NameConstants.RAIL_XSECTION,
            () -> new BlockItemBase(ModBlocks.RAIL_XSECTION.get()));

    public static final RegistryObject<Item> RAIL_BEND_UP = ITEMS.register(NameConstants.RAIL_BEND_UP,
            () -> new BlockItemBase(ModBlocks.RAIL_BEND_UP.get()));

    public static final RegistryObject<Item> RAIL_BEND_DOWN = ITEMS.register(NameConstants.RAIL_BEND_DOWN,
            () -> new BlockItemBase(ModBlocks.RAIL_BEND_DOWN.get()));

    public static final RegistryObject<Item> NODE_CONSOLE = ITEMS.register(NameConstants.NODE_CONSOLE,
            () -> new BlockItemBase(ModBlocks.NODE_CONSOLE.get()));

    public static final RegistryObject<Item> NODE_SUPPLIER = ITEMS.register(NameConstants.NODE_SUPPLIER,
            () -> new BlockItemBase(ModBlocks.NODE_SUPPLIER.get()));

    public static final RegistryObject<Item> NODE_REQUESTER = ITEMS.register(NameConstants.NODE_REQUESTER,
            () -> new BlockItemBase(ModBlocks.NODE_REQUESTER.get()));



}
