package dk.vertexspace.blocks.rails;

import dk.vertexspace.blocks.FaceAttached;
import dk.vertexspace.blocks.RailConnected;
import dk.vertexspace.init.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;


public abstract class RailBaseBlock extends FaceAttached implements RailConnected {

    protected RailBaseBlock() {
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
        if (player.getHeldItemMainhand().getItem() != ModItems.CLAWBAR.get()){
            return ActionResultType.PASS;
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
