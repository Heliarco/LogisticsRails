package dk.vertexspace.init;

import dk.vertexspace.constants.NameConstants;
import dk.vertexspace.tileentities.ConsoleTileEntity;
import dk.vertexspace.tileentities.RequesterTileEntity;
import dk.vertexspace.tileentities.SupplierTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static dk.vertexspace.constants.NameConstants.MOD_ID;

public class ModTileEntityTypes {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MOD_ID);

    //https://www.youtube.com/watch?v=bUxuf8wKvPE&list=PLaevjqy3XufYmltqo0eQusnkKVN7MpTUe&index=19
    public static final RegistryObject<TileEntityType<ConsoleTileEntity>> CONSOLE = TILE_ENTITY_TYPES.register(NameConstants.NODE_CONSOLE,
            () -> TileEntityType.Builder.create(ConsoleTileEntity::new, ModBlocks.NODE_CONSOLE.get()).build(null));

    public static final RegistryObject<TileEntityType<RequesterTileEntity>> REQUESTER = TILE_ENTITY_TYPES.register(NameConstants.NODE_REQUESTER,
            () -> TileEntityType.Builder.create(RequesterTileEntity::new, ModBlocks.NODE_REQUESTER.get()).build(null));

    public static final RegistryObject<TileEntityType<SupplierTileEntity>> SUPPLIER = TILE_ENTITY_TYPES.register(NameConstants.NODE_SUPPLIER,
            () -> TileEntityType.Builder.create(SupplierTileEntity::new, ModBlocks.NODE_SUPPLIER.get()).build(null));

}
