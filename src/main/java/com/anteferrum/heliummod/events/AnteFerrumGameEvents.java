package com.anteferrum.heliummod.events;

import com.anteferrum.heliummod.blocks.ModBlocks;
import com.anteferrum.heliummod.items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

// mod game bus
// there are surprisingly 2 different event busses in neoforge, this class holding the game events, while the other holds mod events
// can simply hit cmd + click on the event to check what type of event it is
// most mod events would implement a modbusevent of some sort, while game events dont
public class AnteFerrumGameEvents {
    @SubscribeEvent
    public static void leftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        Level level = event.getLevel();

        if (level.isClientSide()) { return; } // this event fires on server and clientside, so limit it to one to stop it from playing many times
        // it also fires upon click and upon release, so limit it to upon click only
        if (event.getAction() != PlayerInteractEvent.LeftClickBlock.Action.START) { return; }

        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);

        Player player = event.getEntity();

        if (state.getBlock() == ModBlocks.flint.get() && player.getMainHandItem().is(ModItems.pebble)) {
            level.playSeededSound(null, pos.getX(), pos.getY(), pos.getZ(),
                    SoundEvents.STONE_BREAK,
                    SoundSource.BLOCKS, 2.0f, 1.0f,
                    level.getRandom().nextLong()
            );

            level.playSeededSound(null, pos.getX(), pos.getY(), pos.getZ(),
                    SoundEvents.NETHERITE_BLOCK_BREAK,
                    SoundSource.BLOCKS, 0.2f, 1.0f,
                    level.getRandom().nextLong()
            );
        }
    }

    public static void register() {
        NeoForge.EVENT_BUS.register(AnteFerrumGameEvents.class);
    }
}
