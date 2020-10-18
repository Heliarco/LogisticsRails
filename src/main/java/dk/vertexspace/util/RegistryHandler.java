package dk.vertexspace.util;

import dk.vertexspace.LogisticsRails;
import dk.vertexspace.blocks.BlockItemBase;
import dk.vertexspace.blocks.RubyBlock;
import dk.vertexspace.common.TabGroups;
import dk.vertexspace.items.ItemBase;
import dk.vertexspace.tools.ModItemTier;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {

    //Registered names here are ALWAYS lowercase with underscore seperators. DO NOT RELY ON CAPITALIZATION

    public static void init() {

        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LogisticsRails.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LogisticsRails.MOD_ID);


    // Items
    public static final RegistryObject<Item> CLAYCRETE = ITEMS.register("claycrete", ItemBase::new);


    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", ItemBase::new);



    // Tools
    public static final RegistryObject<SwordItem> RUBY_SWORD = ITEMS.register("ruby_sword", () ->
            new SwordItem(
                    ModItemTier.RUBY,
                    20,
                    -2.4F, // Cooldown. Base value is 4 ... whatever
                    new Item.Properties().group(TabGroups.TAB) // Checkout what other properties are on item properties here!
            ));


    // Blocks
    public static final RegistryObject<Block> RUBY_BLOCK = BLOCKS.register("ruby_block", RubyBlock::new);

    // Block Items
    public static final RegistryObject<Item> RUBY_BLOCK_ITEM = ITEMS.register("ruby_block", () -> new BlockItemBase(RUBY_BLOCK.get()));



}
