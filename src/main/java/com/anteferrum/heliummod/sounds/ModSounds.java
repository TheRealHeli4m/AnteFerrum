package com.anteferrum.heliummod.sounds;

import net.minecraft.sounds.SoundEvents;
import net.neoforged.neoforge.common.util.DeferredSoundType;

public class ModSounds {
    public static final DeferredSoundType silentMining = new DeferredSoundType(
            1.0f, 1.0f,
            () -> SoundEvents.EMPTY,
            () -> SoundEvents.EMPTY,
            () -> SoundEvents.STONE_PLACE,
            () -> SoundEvents.EMPTY,
            () -> SoundEvents.EMPTY
    );
}
