package com.anteferrum.heliummod.events;

import com.anteferrum.heliummod.entities.ModEntities;
import com.anteferrum.heliummod.entities.sharpened_stick.SharpenedStickModel;
import com.anteferrum.heliummod.entities.sharpened_stick.SharpenedStickRenderer;
import com.anteferrum.heliummod.recipes.AnteFerrumRecipeProvider;
import com.anteferrum.heliummod.loot.ModGlobalLootModifierProvider;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;

// mod event bus
// super important that you actually spend time to setup event handler properly
public class AnteFerrumModEvents {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(AnteFerrumRecipeProvider.Runner::new);
        event.createProvider(ModGlobalLootModifierProvider::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SharpenedStickModel.LAYER_LOCATION, SharpenedStickModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.SHARPENED_STICK_ENTITY.get(), context -> new SharpenedStickRenderer(context));
    }

    public static void register(IEventBus bus) { };
}

