package dk.vertexspace.tileentities;

import dk.vertexspace.init.ModTileEntityTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class SupplierTileEntity extends TileEntity implements ITickableTileEntity {
    public SupplierTileEntity() {
        super(ModTileEntityTypes.SUPPLIER.get());

    }

    @Override
    public void tick() {
        // Put supply logic here
    }
}
