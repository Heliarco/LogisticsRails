package dk.vertexspace.blocks;

import dk.vertexspace.init.ModTileEntityTypes;
import dk.vertexspace.tileentities.ExampleChestTileEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class ExampleChestBlock extends Block {
    public ExampleChestBlock() {
        super(AbstractBlock.Properties.create(Material.MISCELLANEOUS)
                .hardnessAndResistance(1.0f, 1.0f)
                .setRequiresTool()
                .sound(SoundType.WOOD)
                .harvestLevel(1) //
                .harvestTool(ToolType.PICKAXE)
                .notSolid());
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntityTypes.EXAMPLE_CHEST.get().create();
    }
ChestTileEntity
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote) { // we are on the logical server
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile instanceof ExampleChestTileEntity){
                NetworkHooks.openGui((ServerPlayerEntity) player,(ExampleChestTileEntity) tile, pos);
                return ActionResultType.SUCCESS;
            }
        }
        return  ActionResultType.CONSUME;
    }
    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()){
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof ExampleChestTileEntity) {
                InventoryHelper.dropItems(worldIn, pos, ((ExampleChestTileEntity)te).getItems());
            }
        }
    }



    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.hasTileEntity() && state.getBlock() != newState.getBlock()) { state.hasTileEntity is no bueno!
            // drops everything in the inventory
            worldIn.getTileEntity(pos).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                for (int i = 0; i < h.getSlots(); i++) {
                    spawnAsEntity(worldIn, pos, h.getStackInSlot(i));
                }
            });
            worldIn.removeTileEntity(pos);
        }
    }



}
