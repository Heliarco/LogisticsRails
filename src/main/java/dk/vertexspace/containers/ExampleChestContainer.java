package dk.vertexspace.containers;

import dk.vertexspace.init.ModContainerTypes;
import dk.vertexspace.tileentities.ExampleChestTileEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.IWorldPosCallable;

public class ExampleChestContainer extends Container {
    public final ExampleChestTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;

    public ExampleChestContainer(final int  windowId, final PlayerInventory playerInventory, final ExampleChestTileEntity tileEntity) {
        super(ModContainerTypes.EXAMPLE_CHEST.get(), windowId);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());
    }

    // Main inventory



}
