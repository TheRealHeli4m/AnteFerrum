package com.anteferrum.heliummod.loot;

import com.anteferrum.heliummod.AnteFerrum;
import com.anteferrum.heliummod.items.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    // modid is added to the params by default but it actually breaks when you register this to the event bus
    // better off just including the modid call in your ModName class
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, AnteFerrum.MODID);
    }

    // pretty self-explanatory
    // runs AddItemModifier, which we designed to take 2 params: a conditions array and an item
    // conditions can be viewed by seeing the hierarchy (ctrl + h) of LootItemCondition, though most of them are easy enough to understand
    @Override
    protected void start() {
        this.add("custom_flint_to_gravel",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.GRAVEL).build(),
                        LootItemRandomChanceCondition.randomChance(0.5f).build()
                }, ModItems.flint.get()));

        this.add("pebble_to_gravel",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.GRAVEL).build(),
                        LootItemRandomChanceCondition.randomChance(0.9f).build()
                }, ModItems.pebble.get()));

        this.add("remove_flint_from_gravel",
                new RemoveItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.GRAVEL).build()
                }, Items.FLINT));

        this.add("remove_gravel_from_gravel",
                new RemoveItemModifier(new LootItemCondition[]{
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.GRAVEL).build()
                }, Items.GRAVEL));
    }
}
