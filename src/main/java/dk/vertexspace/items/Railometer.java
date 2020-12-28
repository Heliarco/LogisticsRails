package dk.vertexspace.items;

import dk.vertexspace.constants.TabGroups;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import java.util.HashSet;

public class Railometer extends ToolItem {
    public Railometer() {
        super(-2f, -1, ItemTier.IRON, new HashSet<Block>(),
                new Item.Properties().group(TabGroups.TAB));
    }

//
//
//    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
//        ItemStack itemstack = playerIn.getHeldItem(handIn);
//        if (playerIn.fishingBobber != null) {
//            if (!worldIn.isRemote) {
//                int i = playerIn.fishingBobber.handleHookRetraction(itemstack);
//                itemstack.damageItem(i, playerIn, (player) -> {
//                    player.sendBreakAnimation(handIn);
//                });
//            }
//
//            worldIn.playSound((PlayerEntity)null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
//        } else {
//            worldIn.playSound((PlayerEntity)null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_FISHING_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
//            if (!worldIn.isRemote) {
//                int k = EnchantmentHelper.getFishingSpeedBonus(itemstack);
//                int j = EnchantmentHelper.getFishingLuckBonus(itemstack);
//                worldIn.addEntity(new FishingBobberEntity(playerIn, worldIn, j, k));
//            }
//
//            playerIn.addStat(Stats.ITEM_USED.get(this));
//        }
//
//        return ActionResult.func_233538_a_(itemstack, worldIn.isRemote());
//    }


}

