package dk.vertexspace.blocks.nodes;

import dk.vertexspace.blocks.FaceAttached;
import dk.vertexspace.blocks.RailConnected;
import dk.vertexspace.stateproperties.RailRotation;
import dk.vertexspace.stateproperties.RailRotationProperty;
import dk.vertexspace.util.PlacementHelpers;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public abstract class NodeBase extends FaceAttached implements RailConnected {
    protected NodeBase() {
        super(AbstractBlock.Properties.create(Material.MISCELLANEOUS)
                .hardnessAndResistance(1.0f, 1.0f)
                .setRequiresTool()
                .sound(SoundType.METAL)
                .harvestLevel(1) //
                .harvestTool(ToolType.PICKAXE)
                .notSolid() // Wont reduce neighbor block vertices
        , false);
    }
    public static final RailRotationProperty ROTATION = RailRotationProperty.create("rotation");

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        super.fillStateContainer(builder);
        builder.add(ROTATION);
    }

    @Nullable
    @Override
    // Called when block is placed.
    // Here we can calculate the state of the block before ultimate placement
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState baseState = super.getStateForPlacement(context);
        Direction facing = baseState.get(FACING);

        Direction look;
        RailRotation rotation = RailRotation.ROT_0;

        // The default is ROT_0, so we ONLY care about cases where it should NOT be rot_0!
        switch(facing) {
            case UP:
                look = PlacementHelpers.getPrimaryLookDirectionExcept(context.getPlayer(), Direction.UP, Direction.DOWN);
                switch (look) {
                    case SOUTH:
                        rotation = RailRotation.ROT_1;
                        break;
                    case WEST:
                        rotation = RailRotation.ROT_2;
                        break;
                    case NORTH:
                        rotation = RailRotation.ROT_3;
                        break;
                }
                break;
            case DOWN:
                look = PlacementHelpers.getPrimaryLookDirectionExcept(context.getPlayer(), Direction.UP, Direction.DOWN);
                switch (look) {
                    case NORTH:
                        rotation = RailRotation.ROT_1;
                        break;
                    case WEST:
                        rotation = RailRotation.ROT_2;
                        break;
                    case SOUTH:
                        rotation = RailRotation.ROT_3;
                        break;
                }
                break;
            case NORTH:
                look = PlacementHelpers.getPrimaryLookDirectionExcept(context.getPlayer(), Direction.NORTH, Direction.SOUTH);
                switch (look) {
                    case WEST:
                        rotation = RailRotation.ROT_1;
                        break;
                    case UP:
                        rotation = RailRotation.ROT_2;
                        break;
                    case EAST:
                        rotation = RailRotation.ROT_3;
                        break;
                }
                break;
            case SOUTH:
                look = PlacementHelpers.getPrimaryLookDirectionExcept(context.getPlayer(), Direction.NORTH, Direction.SOUTH);
                switch (look) {
                    case WEST:
                        rotation = RailRotation.ROT_1;
                        break;
                    case UP:
                        rotation = RailRotation.ROT_2;
                        break;
                    case EAST:
                        rotation = RailRotation.ROT_3;
                        break;
                }
                break;
            case EAST:
                look = PlacementHelpers.getPrimaryLookDirectionExcept(context.getPlayer(), Direction.EAST, Direction.WEST);
                switch (look) {
                    case SOUTH:
                        rotation = RailRotation.ROT_1;
                        break;
                    case UP:
                        rotation = RailRotation.ROT_2;
                        break;
                    case NORTH:
                        rotation = RailRotation.ROT_3;
                        break;
                }
                break;
            case WEST:
                look = PlacementHelpers.getPrimaryLookDirectionExcept(context.getPlayer(), Direction.EAST, Direction.WEST);
                switch (look) {
                    case NORTH:
                        rotation = RailRotation.ROT_1;
                        break;
                    case UP:
                        rotation = RailRotation.ROT_2;
                        break;
                    case SOUTH:
                        rotation = RailRotation.ROT_3;
                        break;
                }
                break;
        }

        //get rotation here.


        return baseState.with(ROTATION, rotation);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
