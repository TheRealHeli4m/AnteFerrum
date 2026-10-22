package com.anteferrum.heliummod.items;

import com.anteferrum.heliummod.AnteFerrum;
import com.anteferrum.heliummod.blocks.ModBlocks;
import com.anteferrum.heliummod.items.custom.SharpenedStick;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwingAnimationType;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.PiercingWeapon;
import net.minecraft.world.item.component.SwingAnimation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Optional;

public class ModItems {
    public static final DeferredItem<BlockItem> sharpened_flint = AnteFerrum.ITEMS.registerSimpleBlockItem("sharpened_flint", ModBlocks.sharpened_flint, props -> props);
    public static final DeferredItem<BlockItem> flint = AnteFerrum.ITEMS.registerSimpleBlockItem("flint", ModBlocks.flint, props -> props);
    public static final DeferredItem<Item> pebble = AnteFerrum.ITEMS.registerItem("pebble", Item::new, props -> props);
    public static final DeferredItem<Item> animal_sinew = AnteFerrum.ITEMS.registerItem("animal_sinew", Item::new, props -> props);

    public static final DeferredItem<Item> sharpened_stick = AnteFerrum.ITEMS.registerItem("sharpened_stick", SharpenedStick::new);

    // empty function to register this class -> allowing for items to be added
    public static void register(IEventBus eventBus) { }
}
