package com.anteferrum.heliummod;

import com.anteferrum.heliummod.blocks.ModBlocks;
import com.anteferrum.heliummod.items.ModItems;
import com.anteferrum.heliummod.loot.AddItemModifier;
import com.anteferrum.heliummod.loot.ModLootModifiers;
import com.anteferrum.heliummod.loot.RemoveItemModifier;
import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.*;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import java.util.function.Supplier;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(AnteFerrum.MODID)
public class AnteFerrum {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "anteferrum";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "anteferrum" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "anteferrum" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "anteferrum" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    // Creates a new food item with the id "anteferrum:example_id", nutrition 1 and saturation 2
    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item", p -> p.food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(1).saturationModifier(2f).build()));

    // Creates a creative tab with the id "anteferrum:example_tab" for the example item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.anteferrum")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> EXAMPLE_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(EXAMPLE_ITEM.get());// Add the example item to the tab. For your own tabs, this method is preferred over the event
            }).build());

    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, AnteFerrum.MODID);

    public static final Supplier<MapCodec<AddItemModifier>> ADD_ITEM_MODIFIER =
            GLOBAL_LOOT_MODIFIER_SERIALIZERS.register("add_item_modifier", () -> AddItemModifier.CODEC);

    public static final Supplier<MapCodec<RemoveItemModifier>> REMOVE_ITEM_MODIFIER =
            GLOBAL_LOOT_MODIFIER_SERIALIZERS.register("remove_item_modifier", () -> RemoveItemModifier.CODEC);

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public AnteFerrum(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        GLOBAL_LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (AnteFerrum) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);
        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(AnteFerrumEvents::gatherData);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.LOG_DIRT_BLOCK.getAsBoolean()) {
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        }

        LOGGER.info("{}{}", Config.MAGIC_NUMBER_INTRODUCTION.get(), Config.MAGIC_NUMBER.getAsInt());

        Config.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModItems.flint);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
