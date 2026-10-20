package com.anteferrum.heliummod.entities.cow_carcass;

import com.anteferrum.heliummod.AnteFerrum;
import com.anteferrum.heliummod.items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

// i still dont fully understand how the entity registering system works
public class CowCarcassEntity extends Entity {
    public static final EntityDataAccessor<Integer> HARVEST_COUNT = SynchedEntityData.defineId(CowCarcassEntity.class, EntityDataSerializers.INT);

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(HARVEST_COUNT, 3);
    }

    public CowCarcassEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public boolean isPickable() { // apparently this is false on default and would make the player raytrace skip over
        return true;
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        Level level = player.level();
        BlockPos pos = this.blockPosition();
        int data = this.getEntityData().get(HARVEST_COUNT);

        if (level.isClientSide()) { return InteractionResult.PASS; }

        if (player.getMainHandItem().is(ModItems.sharpened_flint) && !player.getCooldowns().isOnCooldown(ModItems.sharpened_flint.toStack())) {
            ItemEntity leather = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.LEATHER));
            leather.setDeltaMovement(level.random.nextGaussian() * 0.05, level.random.nextGaussian() * 0.05 + 0.2, level.random.nextGaussian() * 0.05);

            ItemEntity meat = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.BEEF));
            meat.setDeltaMovement(level.random.nextGaussian() * 0.05, level.random.nextGaussian() * 0.05 + 0.2, level.random.nextGaussian() * 0.05);

            level.addFreshEntity(meat);
            level.addFreshEntity(leather);

            player.getCooldowns().addCooldown(ModItems.sharpened_flint.toStack(), 10);
            this.getEntityData().set(HARVEST_COUNT, data - 1);
            return InteractionResult.SUCCESS;
        }

        if (data <= 0) {
            this.discard();
        }

        return InteractionResult.PASS;
    }

    @Override
    public boolean hurtServer(ServerLevel serverLevel, DamageSource damageSource, float v) { return false; }

    @Override
    protected void readAdditionalSaveData(ValueInput valueInput) { }

    @Override
    protected void addAdditionalSaveData(ValueOutput valueOutput) { }
}
