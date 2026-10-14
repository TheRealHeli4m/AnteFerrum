package com.anteferrum.heliummod.blocks;

import com.anteferrum.heliummod.AnteFerrum;
import com.anteferrum.heliummod.blocks.custom.FlintBlock;
import com.anteferrum.heliummod.sounds.ModSounds;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlocks {
    public static final DeferredBlock<Block> flint = AnteFerrum.BLOCKS.registerBlock(
            "flint",
            FlintBlock::new,
            () -> BlockBehaviour.Properties.of()
                    .noCollision()
                    .noOcclusion()
                    .destroyTime(-1.0f)
                    .sound(ModSounds.silentMining)
    );

    public static final DeferredBlock<Block> sharpened_flint = AnteFerrum.BLOCKS.registerBlock(
            "sharpened_flint",
            FlintBlock::new,
            () -> BlockBehaviour.Properties.of()
                    .noCollision()
                    .noOcclusion()
                    .destroyTime(-1.0f)
                    .sound(ModSounds.silentMining)
    );

    public static void register(IEventBus eventBus) { }
}
