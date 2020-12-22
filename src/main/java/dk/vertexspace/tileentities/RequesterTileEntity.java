package dk.vertexspace.tileentities;

import dk.vertexspace.init.ModTileEntityTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class RequesterTileEntity extends TileEntity implements ITickableTileEntity {
    public RequesterTileEntity() {
        super(ModTileEntityTypes.REQUESTER.get());
    }

    @Override
    public void tick() {

    }
}
