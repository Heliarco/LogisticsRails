package dk.vertexspace.init;

import dk.vertexspace.nodes.Console;
import dk.vertexspace.rails.*;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static dk.vertexspace.constants.NameConstants.MOD_ID;

public class ModBlocks {

    private ModBlocks(){}
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    public static final RegistryObject<Block> RAIL_STRAIGHT = BLOCKS.register("rail_straight", RailStraight::new);
    public static final RegistryObject<Block> RAIL_TURN = BLOCKS.register("rail_turn", RailTurn::new);
    public static final RegistryObject<Block> RAIL_TSECTION = BLOCKS.register("rail_tsection", RailTSection::new);
    public static final RegistryObject<Block> RAIL_XSECTION = BLOCKS.register("rail_xsection", RailXSection::new);
    public static final RegistryObject<Block> RAIL_BEND_UP = BLOCKS.register("rail_bend_up", RailBendUp::new);
    public static final RegistryObject<Block> RAIL_BEND_DOWN = BLOCKS.register("rail_bend_down", RailBendDown::new);
    public static final RegistryObject<Block> NODE_CONSOLE = BLOCKS.register("node_console", Console::new);
}
