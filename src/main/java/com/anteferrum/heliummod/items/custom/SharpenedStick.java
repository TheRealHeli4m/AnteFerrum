package com.anteferrum.heliummod.items.custom;

import com.anteferrum.heliummod.AnteFerrum;
import com.anteferrum.heliummod.entities.sharpened_stick.SharpenedStickEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

public class SharpenedStick extends Item {
    public SharpenedStick(Properties properties) {
        super(properties);
    }
    private int pickUpWait = 20;

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
        if (!(entity instanceof Player player)) { return false; };

        int requiredUseDuration = getUseDuration(stack, player) - timeLeft;
        if (requiredUseDuration < 10) { return false; }

        float f = EnchantmentHelper.getTridentSpinAttackStrength(stack, player);

        if (level instanceof ServerLevel serverLevel) {
            stack.hurtWithoutBreaking(1, player);

            ItemStack iStack = stack.consumeAndReturn(1, player);
            // this part was edited cuz running SharpenedStickEntity::new is broken for some reason?
            // following Kaupenjoes code here
            SharpenedStickEntity sharpenedStick = new SharpenedStickEntity(player, level);
            sharpenedStick.setXRot(player.getXRot());
            sharpenedStick.setYRot(player.getYRot());
            sharpenedStick.xRotO = player.getXRot();
            sharpenedStick.yRotO = player.getYRot();
            sharpenedStick.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, 2.5f, 1.0f);
            level.addFreshEntity(sharpenedStick);
            level.playSound(null, sharpenedStick, SoundEvents.TRIDENT_THROW.value(), SoundSource.PLAYERS, 1.0f, 1.0f);
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
