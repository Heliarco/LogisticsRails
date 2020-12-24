package dk.vertexspace.init;

import dk.vertexspace.blocks.nodes.Console;
import dk.vertexspace.blocks.nodes.Requester;
import dk.vertexspace.blocks.nodes.Supplier;
import dk.vertexspace.blocks.rails.*;
import dk.vertexspace.constants.NameConstants;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static dk.vertexspace.constants.NameConstants.MOD_ID;

public class ModBlocks {

    private ModBlocks(){}
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    public static final RegistryObject<Block> RAIL_STRAIGHT = BLOCKS.register(NameConstants.RAIL_STRAIGHT, RailStraight::new);
    public static final RegistryObject<Block> RAIL_TURN = BLOCKS.register(NameConstants.RAIL_TURN, RailTurn::new);
    public static final RegistryObject<Block> RAIL_TSECTION = BLOCKS.register(NameConstants.RAIL_TSECTION, RailTSection::new);
    public static final RegistryObject<Block> RAIL_XSECTION = BLOCKS.register(NameConstants.RAIL_XSECTION, RailXSection::new);
    public static final RegistryObject<Block> RAIL_BEND_UP = BLOCKS.register(NameConstants.RAIL_BEND_UP, RailBendUp::new);
    public static final RegistryObject<Block> RAIL_BEND_DOWN = BLOCKS.register(NameConstants.RAIL_BEND_DOWN, RailBendDown::new);

    public static final RegistryObject<Block> NODE_CONSOLE = BLOCKS.register(NameConstants.NODE_CONSOLE, Console::new);
    public static final RegistryObject<Block> NODE_SUPPLIER = BLOCKS.register(NameConstants.NODE_SUPPLIER, Supplier::new);
    public static final RegistryObject<Block> NODE_REQUESTER = BLOCKS.register(NameConstants.NODE_REQUESTER, Requester::new);



}
