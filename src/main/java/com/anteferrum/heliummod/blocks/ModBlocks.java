package com.anteferrum.heliummod.blocks;

import com.anteferrum.heliummod.AnteFerrum;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlocks {
    public static final DeferredBlock<Block> flint = AnteFerrum.BLOCKS.registerBlock(
            "flint",
            Block::new,
            () -> BlockBehaviour.Properties.of()
                    .noCollision()
                    .noOcclusion()
    );
    
    public static void register(IEventBus eventBus) { }
}
