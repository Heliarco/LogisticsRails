package dk.vertexspace.blocks.rails;

import dk.vertexspace.stateproperties.RailRotation;
import dk.vertexspace.stateproperties.RailRotationProperty;
import dk.vertexspace.voxelshapes.RailTSectionShapes;
import dk.vertexspace.voxelshapes.ShapeBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

@SuppressWarnings("java:S110") // We can't really control the inheritance depth here.
public class RailTSectionBlock extends RailBaseBlock {
    public static final RailRotationProperty ROTATION = RailRotationProperty.create("rotation");

    @Override
    protected BlockState rotateOnRightClick(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        return state.with(ROTATION, state.get(ROTATION).next());
    }




    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(ROTATION);
        super.fillStateContainer(builder);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context){
        Direction facing = state.get(FACING);
        RailRotation rotation = state.get(ROTATION);

        switch(facing) {
            case UP:
                switch (rotation){
                    case ROT_0:
                        return RailTSectionShapes.S_UP_E;
                    case ROT_1:
                        return RailTSectionShapes.S_UP_S;
                    case ROT_2:
                        return RailTSectionShapes.S_UP_W;
                    case ROT_3:
                        return RailTSectionShapes.S_UP_N;
                }
                break;
            case DOWN:
                switch (rotation){
                    case ROT_0:
                        return RailTSectionShapes.S_DOWN_E;
                    case ROT_1:
                        return RailTSectionShapes.S_DOWN_N;
                    case ROT_2:
                        return RailTSectionShapes.S_DOWN_W;
                    case ROT_3:
                        return RailTSectionShapes.S_DOWN_S;
                }
                break;
            case EAST:
                switch (rotation){
                    case ROT_0:
                        return RailTSectionShapes.S_EAST_D;
                    case ROT_1:
                        return RailTSectionShapes.S_EAST_S;
                    case ROT_2:
                        return RailTSectionShapes.S_EAST_U;
                    case ROT_3:
                        return RailTSectionShapes.S_EAST_N;
                }
                break;
            case WEST:
                switch (rotation){
                    case ROT_0:
                        return RailTSectionShapes.S_WEST_D;
                    case ROT_1:
                        return RailTSectionShapes.S_WEST_N;
                    case ROT_2:
                        return RailTSectionShapes.S_WEST_U;
                    case ROT_3:
                        return RailTSectionShapes.S_WEST_S;
                }
                break;
            case NORTH:
                switch (rotation){
                    case ROT_0:
                        return RailTSectionShapes.S_NORTH_D;
                    case ROT_1:
                        return RailTSectionShapes.S_NORTH_W;
                    case ROT_2:
                        return RailTSectionShapes.S_NORTH_U;
                    case ROT_3:
                        return RailTSectionShapes.S_NORTH_E;
                }
                break;
            case SOUTH:
                switch (rotation){
                    case ROT_0:
                        return RailTSectionShapes.S_SOUTH_D;
                    case ROT_1:
                        return RailTSectionShapes.S_SOUTH_W;
                    case ROT_2:
                        return RailTSectionShapes.S_SOUTH_U;
                    case ROT_3:
                        return RailTSectionShapes.S_SOUTH_E;
                }
                break;

        }
        return ShapeBase.PLACEHOLDER_SHAPE;
    }


}
