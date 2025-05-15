package dk.vertexspace.tileentities;

import dk.vertexspace.init.ModTileEntityTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class RequesterTileEntity extends TileEntity implements ITickableTileEntity {
    public RequesterTileEntity() {
        super(ModTileEntityTypes.REQUESTER.get());
    }

    @Override
    public void tick() {
        // Fill this bad boy in later <#
    }
}
