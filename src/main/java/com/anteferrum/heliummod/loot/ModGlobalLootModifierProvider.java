package com.anteferrum.heliummod.loot;

import com.anteferrum.heliummod.AnteFerrum;
import com.anteferrum.heliummod.items.ModItems;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.EntityTypePredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

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
        this.add("no_beef",
                new RemoveItemModifier(new LootItemCondition[]{
                        LootTableIdCondition.builder(Identifier.withDefaultNamespace("entities/cow")).build(),
                }, Items.BEEF));
        this.add("no_leather",
                new RemoveItemModifier(new LootItemCondition[]{
                        LootTableIdCondition.builder(Identifier.withDefaultNamespace("entities/cow")).build(),
                }, Items.LEATHER));

        // flint and gravel
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

        // sticks
        // this is so inefficient but figuring out how to use BlockTags is too complicated for me
        this.add("more_sticks_oak", new AddItemModifier(new LootItemCondition[]{LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.OAK_LEAVES).build(), LootItemRandomChanceCondition.randomChance(0.5f).build()}, Items.STICK));
        this.add("more_sticks_birch", new AddItemModifier(new LootItemCondition[]{LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.BIRCH_LEAVES).build(), LootItemRandomChanceCondition.randomChance(0.5f).build()}, Items.STICK));
        this.add("more_sticks_spruce", new AddItemModifier(new LootItemCondition[]{LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.SPRUCE_LEAVES).build(), LootItemRandomChanceCondition.randomChance(0.5f).build()}, Items.STICK));
        this.add("more_sticks_dark", new AddItemModifier(new LootItemCondition[]{LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.DARK_OAK_LEAVES).build(), LootItemRandomChanceCondition.randomChance(0.5f).build()}, Items.STICK));
        this.add("more_sticks_acacia", new AddItemModifier(new LootItemCondition[]{LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.ACACIA_LEAVES).build(), LootItemRandomChanceCondition.randomChance(0.5f).build()}, Items.STICK));
        this.add("more_sticks_azelea", new AddItemModifier(new LootItemCondition[]{LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.AZALEA_LEAVES).build(), LootItemRandomChanceCondition.randomChance(0.5f).build()}, Items.STICK));
        this.add("more_sticks_cherry", new AddItemModifier(new LootItemCondition[]{LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.CHERRY_LEAVES).build(), LootItemRandomChanceCondition.randomChance(0.5f).build()}, Items.STICK));
        this.add("more_sticks_jungle", new AddItemModifier(new LootItemCondition[]{LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.JUNGLE_LEAVES).build(), LootItemRandomChanceCondition.randomChance(0.5f).build()}, Items.STICK));
        this.add("more_sticks_mangrove", new AddItemModifier(new LootItemCondition[]{LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.MANGROVE_LEAVES).build(), LootItemRandomChanceCondition.randomChance(0.5f).build()}, Items.STICK));
        this.add("more_sticks_pale", new AddItemModifier(new LootItemCondition[]{LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.PALE_OAK_LEAVES).build(), LootItemRandomChanceCondition.randomChance(0.5f).build()}, Items.STICK));
    }
}
