package dk.vertexspace.init;

import dk.vertexspace.blocks.ExampleChestBlock;;
import dk.vertexspace.blocks.nodes.*;
import dk.vertexspace.blocks.rails.*;
import dk.vertexspace.constants.NameConstants;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static dk.vertexspace.constants.NameConstants.MOD_ID;

@SuppressWarnings("unused")
public class ModBlocks {

    private ModBlocks(){}
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    public static final RegistryObject<Block> RAIL_STRAIGHT = BLOCKS.register(NameConstants.RAIL_STRAIGHT, RailStraightBlock::new);
    public static final RegistryObject<Block> RAIL_TURN = BLOCKS.register(NameConstants.RAIL_TURN, RailTurnBlock::new);
    public static final RegistryObject<Block> RAIL_TSECTION = BLOCKS.register(NameConstants.RAIL_TSECTION, RailTSectionBlock::new);
    public static final RegistryObject<Block> RAIL_XSECTION = BLOCKS.register(NameConstants.RAIL_XSECTION, RailXSectionBlock::new);
    public static final RegistryObject<Block> RAIL_BEND_UP = BLOCKS.register(NameConstants.RAIL_BEND_UP, RailBendUpBlock::new);
    public static final RegistryObject<Block> RAIL_BEND_DOWN = BLOCKS.register(NameConstants.RAIL_BEND_DOWN, RailBendDownBlock::new);

    public static final RegistryObject<Block> NODE_CONSOLE = BLOCKS.register(NameConstants.NODE_CONSOLE, ConsoleBlock::new);
    public static final RegistryObject<Block> NODE_SUPPLIER = BLOCKS.register(NameConstants.NODE_SUPPLIER, SupplierBlock::new);
    public static final RegistryObject<Block> NODE_REQUESTER = BLOCKS.register(NameConstants.NODE_REQUESTER, RequesterBlock::new);

    public static final RegistryObject<Block> EXAMPLE_CHEST = BLOCKS.register("example_chest", ExampleChestBlock::new);


}
