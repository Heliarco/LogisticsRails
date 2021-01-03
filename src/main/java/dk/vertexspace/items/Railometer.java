package dk.vertexspace.items;

import dk.vertexspace.LogisticsRails;
import dk.vertexspace.constants.Log;
import dk.vertexspace.constants.TabGroups;
import dk.vertexspace.models.RailConnection;
import dk.vertexspace.util.RailConnectionsHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.command.arguments.NBTCompoundTagArgument;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

public class Railometer extends ToolItem {
    public Railometer() {
        super(-2f, -1, ItemTier.IRON, new HashSet<Block>(),
                new Item.Properties().group(TabGroups.TAB));
    }
    private static final String MODE_TAG = "mode";

    private enum RailometerMode {
        CONNECTION_LIST(0, "List connections"),
        CONTROLLER_SCAN(1, "Scan for controller");


        private final int value;
        private final String description;
        RailometerMode(int value, String description) {
            this.value = value;
            this.description = description;
        }
        public String getDescription(){
            return this.description;
        }
        public int getValue() {
            return this.value;
        }
        public static RailometerMode fromValue(int value)
        {
            RailometerMode[] As = RailometerMode.values();
            for(int i = 0; i < As.length; i++)
            {
                if(As[i].getValue() == value)
                    return As[i];
            }
            return CONNECTION_LIST;
        }


    }

    // Right click on block
    @Override
    public ActionResultType onItemUse(ItemUseContext context) {


        World worldIn = context.getWorld();
        // We really only care about the logical client thread here. We just want to display data to the user
        if (worldIn.isRemote()) {
            return ActionResultType.func_233537_a_(worldIn.isRemote);
        }

        BlockPos blockpos = context.getPos();
        BlockState blockstate = worldIn.getBlockState(blockpos);

        RailometerMode mode = getModeFromItemStack(context.getItem());
        switch (mode) {
            case CONNECTION_LIST:
                showConnections(context);
                break;
            default:

                break;
        }
        return ActionResultType.func_233537_a_(worldIn.isRemote);
    }


    private void showConnections(ItemUseContext context) {

        BlockState state = context.getWorld().getBlockState(context.getPos());
        RailConnection[] possibleConnections = RailConnectionsHelper.getConnectedConnections(state);
        RailConnection[] connections = RailConnectionsHelper.getConnectedConnections(state, context.getPos(),context.getWorld());

        PlayerEntity p = context.getPlayer();
        Log.chat(p, "Connections");
        for (RailConnection c : possibleConnections) {
            if (connections.)

            Log.chat(p,c.getName2());
        }

    }



    private RailometerMode getModeFromItemStack(ItemStack stack){
        CompoundNBT tag = stack.getOrCreateTag();
        if(tag.contains(MODE_TAG)){
            return RailometerMode.fromValue(tag.getInt(MODE_TAG));
        }
        else {
            return RailometerMode.CONNECTION_LIST;
        }
    }


    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new StringTextComponent("Mode - " + RailometerMode.fromValue(stack.getOrCreateTag().getInt(MODE_TAG)).getDescription()));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    // Click in "air"
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (playerIn.isSneaking()){ // Shift right-click to change mode
            CompoundNBT tag = itemstack.getOrCreateTag();
            RailometerMode mode;
            if (tag.contains(MODE_TAG)){
                mode = RailometerMode.fromValue(tag.getInt(MODE_TAG));
            }
            else {
                mode = RailometerMode.CONNECTION_LIST;
            }
            mode = RailometerMode.fromValue(mode.getValue()+1);
            tag.putInt(MODE_TAG, mode.getValue());
            itemstack.setTag(tag);
        }

        return ActionResult.func_233538_a_(itemstack, worldIn.isRemote());
    }
}

