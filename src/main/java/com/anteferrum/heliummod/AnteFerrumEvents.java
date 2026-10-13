package com.anteferrum.heliummod;

import com.anteferrum.heliummod.loot.ModGlobalLootModifierProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

// super important that you actually spend time to setup event handler properly
public class AnteFerrumEvents {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(AnteFerrumRecipeProvider.Runner::new);
        event.createProvider(ModGlobalLootModifierProvider::new);
    }
}

