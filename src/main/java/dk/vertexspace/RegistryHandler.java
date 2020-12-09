package dk.vertexspace;

import dk.vertexspace.nodes.Console;
import dk.vertexspace.rails.*;
import dk.vertexspace.items.BlockItemBase;
import dk.vertexspace.items.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static dk.vertexspace.constants.NameConstants.MOD_ID;

public class RegistryHandler {

    private RegistryHandler(){}

    // Registered names here are ALWAYS lowercase with underscore separators.
    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // Registers
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);


    // Crafting materials
    public static final RegistryObject<Item> CLAYCRETE = ITEMS.register("claycrete", ItemBase::new);
    public static final RegistryObject<Item> REDSTONE_ALLOY_INGOT = ITEMS.register("redstone_alloy_ingot", ItemBase::new);
    public static final RegistryObject<Item> REDSTONE_ALLOY_BLEND = ITEMS.register("redstone_alloy_blend", ItemBase::new);
    public static final RegistryObject<Item> LAPIS_ALLOY_INGOT = ITEMS.register("lapis_alloy_ingot", ItemBase::new);
    public static final RegistryObject<Item> LAPIS_ALLOY_BLEND = ITEMS.register("lapis_alloy_blend", ItemBase::new);

    // Blocks
    public static final RegistryObject<Block> RAIL_STRAIGHT = BLOCKS.register("rail_straight", RailStraight::new);
    public static final RegistryObject<Block> RAIL_TURN = BLOCKS.register("rail_turn", RailTurn::new);
    public static final RegistryObject<Block> RAIL_TSECTION = BLOCKS.register("rail_tsection", RailTSection::new);
    public static final RegistryObject<Block> RAIL_XSECTION = BLOCKS.register("rail_xsection", RailXSection::new);
    public static final RegistryObject<Block> RAIL_BEND_UP = BLOCKS.register("rail_bend_up", RailBendUp::new);
    public static final RegistryObject<Block> RAIL_BEND_DOWN = BLOCKS.register("rail_bend_down", RailBendDown::new);
    public static final RegistryObject<Block> NODE_CONSOLE = BLOCKS.register("node_console", Console::new);

    // Block Items
    public static final RegistryObject<Item> RAIL_STRAIGHT_ITEM = ITEMS.register("rail_straight", () -> new BlockItemBase(RAIL_STRAIGHT.get()));
    public static final RegistryObject<Item> RAIL_TURN_ITEM = ITEMS.register("rail_turn", () -> new BlockItemBase(RAIL_TURN.get()));
    public static final RegistryObject<Item> RAIL_TSECTION_ITEM = ITEMS.register("rail_tsection", () -> new BlockItemBase(RAIL_TSECTION.get()));
    public static final RegistryObject<Item> RAIL_XSECTION_ITEM = ITEMS.register("rail_xsection", () -> new BlockItemBase(RAIL_XSECTION.get()));
    public static final RegistryObject<Item> RAIL_BEND_UP_ITEM = ITEMS.register("rail_bend_up", () -> new BlockItemBase(RAIL_BEND_UP.get()));
    public static final RegistryObject<Item> RAIL_BEND_DOWN_ITEM = ITEMS.register("rail_bend_down", () -> new BlockItemBase(RAIL_BEND_DOWN.get()));
    public static final RegistryObject<Item> NODE_CONSOLE_ITEM = ITEMS.register("node_console", () -> new BlockItemBase(NODE_CONSOLE.get()));


}
