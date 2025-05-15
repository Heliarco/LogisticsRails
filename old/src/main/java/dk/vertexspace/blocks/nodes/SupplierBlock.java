package dk.vertexspace.blocks.nodes;

import dk.vertexspace.init.ModTileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

@SuppressWarnings("java:S110") // We can't really control the inheritance depth here.
public class SupplierBlock extends NodeBaseBlock {
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntityTypes.SUPPLIER.get().create();
    }
}
