package com.anteferrum.heliummod.entities.sharpened_stick;

import com.anteferrum.heliummod.entities.ModEntities;
import com.anteferrum.heliummod.items.ModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

// potentially extend AbstractArrow instead
// ill figure this out later when im done with the item summoning
public class SharpenedStickEntity extends AbstractArrow implements ItemSupplier {
    public SharpenedStickEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public SharpenedStickEntity(LivingEntity shooter, Level level) {
        super(ModEntities.SHARPENED_STICK_ENTITY.get(), shooter, level, new ItemStack(ModItems.sharpened_stick.get()), null);
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.sharpened_stick.get());
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result); // runs onHitEntity of the PARENT CLASS??
        Entity entity = result.getEntity();
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), 4);

        if (!this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, (byte)3);
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(ModItems.sharpened_stick.get());
    }
}
