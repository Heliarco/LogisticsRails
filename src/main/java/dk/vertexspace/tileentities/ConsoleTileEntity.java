package dk.vertexspace.tileentities;

import dk.vertexspace.constants.Log;
import dk.vertexspace.init.ModTileEntityTypes;
import dk.vertexspace.models.RailNetwork;
import net.minecraft.block.BlockState;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.thread.SidedThreadGroups;

import java.util.ArrayDeque;
import java.util.Queue;

public class ConsoleTileEntity extends TileEntity implements ITickableTileEntity {

    private class ScanningStageData {
        private Queue<BlockPos> traverseQueue = new ArrayDeque<>();
        private RailNetwork inProgressNetwork = new RailNetwork();
    }


    private ConsoleLogicState currentState = ConsoleLogicState.SCANNING;
    private static final int TICK_INTERVAL = 20; // Every second
    private int tickCounter = 0;



    private enum ConsoleLogicState {
        SCANNING,   // Starting with the console block. Iterate the network. to build a new graph
        COMPARING,   // Comparing current network with an updated version
        SYNCHRONIZING, // Distributing network info to connected nodes and bots
        ROUTEMAPPING // Building network routes
    }

    public ConsoleTileEntity() {

        super(ModTileEntityTypes.CONSOLE.get());
    }

    @Override
    public void tick() {
        boolean dirty = false;
        if (!world.isRemote) {
            if(tickCounter >= TICK_INTERVAL) {
                dirty = this.update();
                tickCounter = 0;
            }
            tickCounter++;
        }
        if (dirty){
            this.markDirty();
        }
    }

    // return value indicates dirty
    private boolean update() {
        switch (this.currentState) {
            case SCANNING:

                break;
            default:
                this.currentState = ConsoleLogicState.SCANNING;
        }
        return false;
    }

    private RailNetwork scanNetwork() {

        return null;
    }





    public void read(BlockState state, CompoundNBT nbt) {

        super.read(state, nbt);
//        this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
//        ItemStackHelper.loadAllItems(nbt, this.items);
//        this.burnTime = nbt.getInt("BurnTime");
//        this.cookTime = nbt.getInt("CookTime");
//        this.cookTimeTotal = nbt.getInt("CookTimeTotal");
//        this.recipesUsed = this.getBurnTime(this.items.get(1));
//        CompoundNBT compoundnbt = nbt.getCompound("RecipesUsed");
//
//        for(String s : compoundnbt.keySet()) {
//            this.recipes.put(new ResourceLocation(s), compoundnbt.getInt(s));
//        }

    }

    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
//        compound.putInt("BurnTime", this.burnTime);
//        compound.putInt("CookTime", this.cookTime);
//        compound.putInt("CookTimeTotal", this.cookTimeTotal);
//        ItemStackHelper.saveAllItems(compound, this.items);
//        CompoundNBT compoundnbt = new CompoundNBT();
//        this.recipes.forEach((recipeId, craftedAmount) -> {
//            compoundnbt.putInt(recipeId.toString(), craftedAmount);
//        });
//        compound.put("RecipesUsed", compoundnbt);
        return compound;
    }
}
