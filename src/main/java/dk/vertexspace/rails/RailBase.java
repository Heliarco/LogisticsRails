package dk.vertexspace.rails;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;


public class RailBase extends DirectionalBlock {

    // Inherited properties:

    // We are plainly assuming all rails have roughly the same properties
    public RailBase() {
        super(Block.Properties.create(Material.MISCELLANEOUS)
                .hardnessAndResistance(1.0f, 1.0f)
                .sound(SoundType.METAL)
                .func_235861_h_() // Mapping missing of "setrequirestool" // New in forge 1.16.3 (or minecraft 1.16.3 whatever)
                .harvestLevel(0) // 0 is wood, 1 is stone, 2 is iron, 3 is diamond etc.
                .harvestTool(ToolType.PICKAXE)
                .notSolid() // doesn't stop grass growth. Presumably
                .doesNotBlockMovement()
        );
    }


    @Override
    @SuppressWarnings("deprecation")
    @Nonnull
    public BlockState rotate(BlockState state, Rotation rot) {
        throw new OutOfMemoryError("FIND ME");
        //return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    @SuppressWarnings("deprecation")
    @Nonnull
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        throw new OutOfMemoryError("FIND ME");
        //return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }






}
