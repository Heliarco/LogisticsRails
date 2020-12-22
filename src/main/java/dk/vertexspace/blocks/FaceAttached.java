package dk.vertexspace.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;

import javax.annotation.Nonnull;

public class FaceAttached  extends DirectionalBlock {
    protected FaceAttached(Properties builder) {
        super(builder);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isTransparent(BlockState state) {
        return true;
    }

    @Override
    @SuppressWarnings("deprecation")
    @Nonnull
    public BlockState rotate(BlockState state, Rotation rot) {
        return state;
    }


    @Override
    @SuppressWarnings("deprecation")
    @Nonnull
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state;
    }



}
