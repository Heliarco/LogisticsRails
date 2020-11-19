package dk.vertexspace.rails;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

@SuppressWarnings("java:S110") // We can't really control the inheritance depth here.
public class RailBendDown extends RailBase {
    @Override
    protected BlockState rotateOnRightClick(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        return null;
    }
}
