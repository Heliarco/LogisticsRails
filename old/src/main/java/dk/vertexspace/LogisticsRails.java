package dk.vertexspace;

import dk.vertexspace.constants.NameConstants;
import dk.vertexspace.init.ModBlocks;
import dk.vertexspace.init.ModContainerTypes;
import dk.vertexspace.init.ModItems;
import dk.vertexspace.init.ModTileEntityTypes;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(NameConstants.MOD_ID)
public class LogisticsRails
{
    private static final Logger LOGGER = LogManager.getLogger();


    public LogisticsRails() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(this::setup);
        bus.addListener(this::doClientStuff);

        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModTileEntityTypes.TILE_ENTITY_TYPES.register(bus);
        ModContainerTypes.CONTAINER_TYPES.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
    }




    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }



}
