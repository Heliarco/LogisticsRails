package dk.vertexspace.init;

import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static dk.vertexspace.constants.NameConstants.MOD_ID;

public class ModTileEntityTypes {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MOD_ID);

    //https://www.youtube.com/watch?v=bUxuf8wKvPE&list=PLaevjqy3XufYmltqo0eQusnkKVN7MpTUe&index=19
    public static final RegistryObject<TileEntityType<ConsoleTileEntity>> CONSOLE = TILE_ENTITY_TYPES.register("node_console", () -> TileEntityType.Builder.create(ConsoleTileEntity::new, ModBlocks.NODE_CONSOLE).build(null));

}
