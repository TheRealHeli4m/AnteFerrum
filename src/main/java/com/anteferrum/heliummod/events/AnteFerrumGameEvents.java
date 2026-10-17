package com.anteferrum.heliummod.events;

import com.anteferrum.heliummod.blocks.ModBlocks;
import com.anteferrum.heliummod.items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.transaction.Transaction;

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

            if (level.random.nextFloat() <= 0.20f) {
                level.setBlock(pos, ModBlocks.sharpened_flint.get().defaultBlockState(), 3);

                level.playSeededSound(null, pos.getX(), pos.getY(), pos.getZ(),
                        SoundEvents.AMETHYST_BLOCK_BREAK,
                        SoundSource.BLOCKS, 0.1f, 0.5f,
                        level.getRandom().nextLong()
                );
            }
        }
    }

    @SubscribeEvent
    public static void rightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);

        pickUpItem(level, state.getBlock(), pos, ModBlocks.flint.get(), ModItems.flint.toStack(), event);
        pickUpItem(level, state.getBlock(), pos, ModBlocks.sharpened_flint.get(), ModItems.sharpened_flint.toStack(), event);
    }

    public static void pickUpItem(Level level, Block currentBlock, BlockPos currentBlockPos, Block targetBlock, ItemStack stack, PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();

        if (currentBlock == targetBlock) {
            Inventory inventory = player.getInventory();
            int slot = inventory.getSelectedSlot();
            if (player.getMainHandItem().isEmpty()) {
                inventory.setItem(slot, stack);
                level.setBlock(currentBlockPos, Blocks.AIR.defaultBlockState(), 3);

                level.playSeededSound(null, currentBlockPos.getX(), currentBlockPos.getY(), currentBlockPos.getZ(), SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.2f, 0.8f, level.getRandom().nextLong());
                level.playSeededSound(null, currentBlockPos.getX(), currentBlockPos.getY(), currentBlockPos.getZ(), SoundEvents.GRASS_STEP, SoundSource.BLOCKS, 0.2f, 1f, level.getRandom().nextLong());



            } else if (player.getMainHandItem().is(stack.getItem())) {
                player.getMainHandItem().grow(1);
                level.setBlock(currentBlockPos, Blocks.AIR.defaultBlockState(), 3);

                event.setCancellationResult(InteractionResult.SUCCESS);
                event.setCanceled(true);

                level.playSeededSound(null, currentBlockPos.getX(), currentBlockPos.getY(), currentBlockPos.getZ(), SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.2f, 0.8f, level.getRandom().nextLong());
                level.playSeededSound(null, currentBlockPos.getX(), currentBlockPos.getY(), currentBlockPos.getZ(), SoundEvents.GRASS_STEP, SoundSource.BLOCKS, 0.2f, 1f, level.getRandom().nextLong());
            }
        }
    }

    public static void register() {
        NeoForge.EVENT_BUS.register(AnteFerrumGameEvents.class);
    }
}
