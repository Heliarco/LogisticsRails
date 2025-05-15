package dk.vertexspace.blocks.rails;

import dk.vertexspace.stateproperties.RailRotation;
import dk.vertexspace.stateproperties.RailRotationProperty;
import dk.vertexspace.voxelshapes.RailTurnShapes;
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
public class RailTurnBlock extends RailBaseBlock {
    @Override
    protected BlockState rotateOnRightClick(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        return state.with(ROTATION, state.get(ROTATION).next());
    }



    public static final RailRotationProperty ROTATION = RailRotationProperty.create("rotation");


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
                        return RailTurnShapes.S_UP_NE;
                    case ROT_1:
                        return RailTurnShapes.S_UP_SE;
                    case ROT_2:
                        return RailTurnShapes.S_UP_SW;
                    case ROT_3:
                        return RailTurnShapes.S_UP_NW;
                }
                break;
            case DOWN:
                switch (rotation){
                    case ROT_0:
                        return RailTurnShapes.S_DOWN_SW;
                    case ROT_1:
                        return RailTurnShapes.S_DOWN_SE;
                    case ROT_2:
                        return RailTurnShapes.S_DOWN_NW;
                    case ROT_3:
                        return RailTurnShapes.S_DOWN_NE;
                }
                break;
            case EAST:
                switch (rotation){
                    case ROT_0:
                        return RailTurnShapes.S_EAST_DS;
                    case ROT_1:
                        return RailTurnShapes.S_EAST_US;
                    case ROT_2:
                        return RailTurnShapes.S_EAST_UN;
                    case ROT_3:
                        return RailTurnShapes.S_EAST_DN;
                }
                break;
            case WEST:
                switch (rotation){
                    case ROT_0:
                        return RailTurnShapes.S_WEST_DS;
                    case ROT_1:
                        return RailTurnShapes.S_WEST_DN;
                    case ROT_2:
                        return RailTurnShapes.S_WEST_UN;
                    case ROT_3:
                        return RailTurnShapes.S_WEST_US;
                }
                break;
            case NORTH:
                switch (rotation){
                    case ROT_0:
                        return RailTurnShapes.S_NORTH_DE;
                    case ROT_1:
                        return RailTurnShapes.S_NORTH_UE;
                    case ROT_2:
                        return RailTurnShapes.S_NORTH_UW;
                    case ROT_3:
                        return RailTurnShapes.S_NORTH_DW;
                }
                break;
            case SOUTH:
                switch (rotation){
                    case ROT_0:
                        return RailTurnShapes.S_SOUTH_DW;
                    case ROT_1:
                        return RailTurnShapes.S_SOUTH_UW;
                    case ROT_2:
                        return RailTurnShapes.S_SOUTH_UE;
                    case ROT_3:
                        return RailTurnShapes.S_SOUTH_DE;
                }
                break;

        }
        return ShapeBase.PLACEHOLDER_SHAPE;
    }
}
