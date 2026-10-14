package com.anteferrum.heliummod.items;

import com.anteferrum.heliummod.AnteFerrum;
import com.anteferrum.heliummod.blocks.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItems {
    public static final DeferredItem<Item> sharpened_flint = AnteFerrum.ITEMS.registerItem(
            "sharpened_flint",
            Item::new,
            props -> props
    );

    public static final DeferredItem<BlockItem> flint = AnteFerrum.ITEMS.registerSimpleBlockItem("flint", ModBlocks.flint, props -> props);
    public static final DeferredItem<Item> pebble = AnteFerrum.ITEMS.registerItem("pebble", Item::new, props -> props);

    // empty function to register this class -> allowing for items to be added
    public static void register(IEventBus eventBus) { }
}
