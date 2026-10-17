package com.anteferrum.heliummod.entities;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

// potentially extend AbstractArrow instead
// ill figure this out later when im done with the item summoning
public class SharpenedStickEntity extends Entity {
    public SharpenedStickEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    // apparently a key that is used to get and set data in the class
    public static final EntityDataAccessor<Integer> STICK_DATA = SynchedEntityData.defineId(SharpenedStickEntity.class, EntityDataSerializers.INT);

    // defines the data that needs to be synced (?)
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(STICK_DATA, 0);
    }

    // return false is by default here
    // but basically what it does is that it makes the entity unkillable
    // for example it wont be deleted by fire like a dropped item entity
    @Override
    public boolean hurtServer(ServerLevel serverLevel, DamageSource damageSource, float v) {
        return false;
    }

    @Override
    protected void readAdditionalSaveData(ValueInput valueInput) {

    }

    @Override
    protected void addAdditionalSaveData(ValueOutput valueOutput) {

    }
}
