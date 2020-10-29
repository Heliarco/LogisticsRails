package dk.vertexspace;

import dk.vertexspace.blocks.DebugRailV2;
import dk.vertexspace.baseclasses.BlockItemBase;
import dk.vertexspace.baseclasses.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static dk.vertexspace.constants.NameConstants.MOD_ID;

public class RegistryHandler {

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
    public static final RegistryObject<Block> DEBUG_RAIL_V2 = BLOCKS.register("debug_rail_v2", DebugRailV2::new);

    // Block Items
    public static final RegistryObject<Item> DEBUG_RAIL_V2_ITEM = ITEMS.register("debug_rail_v2", () -> new BlockItemBase(DEBUG_RAIL_V2.get()));

}
