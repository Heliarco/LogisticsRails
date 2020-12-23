package dk.vertexspace.blocks.rails;

import dk.vertexspace.blocks.FaceAttached;
import dk.vertexspace.blocks.RailConnected;
import dk.vertexspace.constants.Log;
import dk.vertexspace.models.RailConnection;
import dk.vertexspace.util.RailConnectionsHelper;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public abstract class RailBase extends FaceAttached implements RailConnected {

    protected RailBase() {
        super(AbstractBlock.Properties.create(Material.MISCELLANEOUS)
                .hardnessAndResistance(1.0f, 1.0f)
                .sound(SoundType.METAL)
                .setRequiresTool()
                .harvestLevel(1)
                .harvestTool(ToolType.PICKAXE)
                .notSolid() // Wont reduce neighbor block vertices
        , true);
    }




    // Right click basically
    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        if (!player.getHeldItemMainhand().isEmpty()){
            return ActionResultType.PASS;
        }

        if(player.isSneaking()){

            RailConnection[] c = RailConnectionsHelper.getConnectionsFromState(state);
            Log.info(player, state);
            for(RailConnection r : c) {
                Log.info(player, r.getName2());
            }

            return ActionResultType.func_233537_a_(worldIn.isRemote);
        }


        BlockState newState = this.rotateOnRightClick(state, worldIn, pos, player, handIn, hit);

        if (newState != null){
            worldIn.setBlockState(pos, newState, 3);
        }
        else {
            return ActionResultType.FAIL;
        }

        return ActionResultType.func_233537_a_(worldIn.isRemote);
    }

    protected abstract BlockState rotateOnRightClick(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit);





    @Override
    @SuppressWarnings("deprecation")
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1f; // No shadows really its a tiny model
    }
}
