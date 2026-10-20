package com.anteferrum.heliummod.entities;

import com.anteferrum.heliummod.AnteFerrum;
import com.anteferrum.heliummod.entities.cow_carcass.CowCarcassEntity;
import com.anteferrum.heliummod.entities.sharpened_stick.SharpenedStickEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;

import java.util.function.Supplier;

public class ModEntities {
    public static final Supplier<EntityType<SharpenedStickEntity>> SHARPENED_STICK_ENTITY = AnteFerrum.ENTITY_TYPES.registerEntityType("sharpened_stick_entity", SharpenedStickEntity::new, MobCategory.MISC,
            sharpenedStickEntityBuilder -> sharpenedStickEntityBuilder.sized(0.5f, 0.25f));
    public static final Supplier<EntityType<CowCarcassEntity>> COW_CARCASS_ENTITY = AnteFerrum.ENTITY_TYPES.registerEntityType("cow_carcass_entity", CowCarcassEntity::new, MobCategory.MISC,
            cowCarcassEntityBuilder -> cowCarcassEntityBuilder.sized(1.0f, 1.0f));


    public static void register(IEventBus eventBus) { }
}
