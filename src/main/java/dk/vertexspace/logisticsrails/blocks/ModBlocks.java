package dk.vertexspace.logisticsrails.blocks;

import dk.vertexspace.logisticsrails.LogisticsRails;
import dk.vertexspace.logisticsrails.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LogisticsRails.MODID);

    public static final RegistryObject<Block> CONDUCTING_ALLOY = registerBlock("conducting_alloy_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));


    // Registration here is more complicated, since when we register the block, we ALSO, want to register the item back at block items :)
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlock(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block){
        ModItems.ITEMS.register(name, () ->  new BlockItem(block.get(), new Item.Properties()));
    }

    // Called by main method at the appropriate event
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

    // Called by main method at the appropriate event
    public static void buildCreativeTab(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.CONDUCTING_ALLOY);
        }
    }
}
