package com.anteferrum.heliummod.entities;

import com.anteferrum.heliummod.AnteFerrum;
import com.anteferrum.heliummod.entities.sharpened_stick.SharpenedStickEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;

import java.util.function.Supplier;

public class ModEntities {
    public static final Supplier<EntityType<SharpenedStickEntity>> SHARPENED_STICK_ENTITY = AnteFerrum.ENTITY_TYPES.registerEntityType("sharpened_stick_entity", SharpenedStickEntity::new, MobCategory.MISC);

    public static void register(IEventBus eventBus) { }
}
