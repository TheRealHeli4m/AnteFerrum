package com.anteferrum.heliummod.events;

import com.anteferrum.heliummod.recipes.AnteFerrumRecipeProvider;
import com.anteferrum.heliummod.loot.ModGlobalLootModifierProvider;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;

// mod event bus
// super important that you actually spend time to setup event handler properly
public class AnteFerrumModEvents {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(AnteFerrumRecipeProvider.Runner::new);
        event.createProvider(ModGlobalLootModifierProvider::new);
    }

    public static void register(IEventBus bus) { };
}

