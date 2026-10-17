package com.anteferrum.heliummod.items.custom;

import com.anteferrum.heliummod.AnteFerrum;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.ThrownTrident;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SharpenedStick extends Item {
    public SharpenedStick(Properties properties) {
        super(properties);
    }

    // apparently this needs to be shut down for releaseUsing to work properly
    @Override
    public boolean useOnRelease(ItemStack stack) {
        return false;
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.TRIDENT;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }

    // copied from TridentItem class in minecraft's source code
    @Override
    public boolean releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        AnteFerrum.LOGGER.info("shoot");
        if (!(entity instanceof Player player)) { return false; };

        int requiredUseDuration = getUseDuration(stack, player) - timeLeft;
        if (requiredUseDuration < 10) { return false; }

        float f = EnchantmentHelper.getTridentSpinAttackStrength(stack, player);

        if (level instanceof ServerLevel serverLevel) {
            stack.hurtWithoutBreaking(1, player);

            ItemStack iStack = stack.consumeAndReturn(1, player);
            ThrownTrident throwntrident = (ThrownTrident) Projectile.spawnProjectileFromRotation(ThrownTrident::new, serverLevel, iStack, player, 0.0F, 2.5F, 1.0F);
            level.playSound(null, throwntrident, SoundEvents.SNOWBALL_THROW, SoundSource.PLAYERS, 1.0f, 1.0f);
            return true;
        }
        return false;
    }

    // NEEDS to override this for any "usage" of this item to happen
    // super important
    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        return InteractionResult.CONSUME;
    }
}
