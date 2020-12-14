package dk.vertexspace.events;


import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

import static dk.vertexspace.constants.NameConstants.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents {


//    @SubscribeEvent
//    public static void onJumpWithStick(LivingEvent.LivingJumpEvent event) {
//        LivingEntity player = event.getEntityLiving();
//        if (player.getHeldItemMainhand().getItem() == Items.STICK) {
//            World world = player.getEntityWorld();
//            world.setBlockState(player.func_233580_cy_().add(0,-1,0), RegistryHandler.RAIL_STRAIGHT.get().getDefaultState()); // getPosition
//        }
//    }
//
//    @SubscribeEvent
//    public static void onDamageSheep(AttackEntityEvent event) {
//        if (event.getEntityLiving().getHeldItemMainhand().getItem() == Items.APPLE) {
//            if (event.getTarget().isAlive()){
//                LivingEntity target = (LivingEntity) event.getTarget();
//                if (target instanceof SheepEntity) {
//                    target.addPotionEffect(new EffectInstance(Effects.POISON, 10 * 20, 8));
//                    target.setFire(1);
//                    target.setHeadRotation(90,90);
//                    target.setGlowing(true);
//
//
//                    PlayerEntity player = event.getPlayer();
//
//                    if (!player.getEntityWorld().isRemote) {
//                        String msg = TextFormatting.RED + "That sheep isn't feeling so well...";
//
//                        player.sendMessage(new StringTextComponent(msg), player.getUniqueID());
//                    }
//
//
//                }
//            }
//        }
//    }
//
//    @SubscribeEvent
//    public static void onCraftingTableOpen(GuiOpenEvent event){
//        if (event.isCancelable()) {
//            if (event.getGui() instanceof CraftingScreen) {
//                event.setCanceled(true);
//            }
//        }
//    }


}
