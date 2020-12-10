package dk.vertexspace.events;


import dk.vertexspace.RegistryHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static dk.vertexspace.constants.NameConstants.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents {

    /*
    @SubscribeEvent
    public static void onJumpWithStick(LivingEvent.LivingJumpEvent event) {
        LivingEntity player = event.getEntityLiving();
        if (player.getHeldItemMainhand().getItem() == Items.STICK) {
            World world = player.getEntityWorld();
            world.setBlockState(player.func_233580_cy_().add(0,-1,0), RegistryHandler.RAIL_STRAIGHT.get().getDefaultState()); // getPosition
        }
    }

    @SubscribeEvent
    public static void onDamageSheep(AttackEntityEvent event) {

    }*/
}
