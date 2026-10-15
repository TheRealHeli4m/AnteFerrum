package com.anteferrum.heliummod.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

// had to make this its own class cuz i wanted to resize the hitbox
public class FlintBlock extends Block {
    public FlintBlock(Properties p_49795_) {
        super(p_49795_);
    }

    // hitbox resizing, values range from 1.0 to 16.0D, three pairs of min-maxes which are x y z
    public static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 1.0D, 12.0D);

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
