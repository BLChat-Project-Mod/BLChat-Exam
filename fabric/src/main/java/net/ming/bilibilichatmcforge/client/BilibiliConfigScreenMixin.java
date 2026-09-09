package net.ming.bilibilichatmcforge.mixin.client;

import net.ming.bilibilichatmcforge.client.BilibiliConfigScreen;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = BilibiliConfigScreen.class, priority = 1000)
public class BilibiliConfigScreenMixin {
    // Fabric uses Mixin instead of direct class extension for some GUI hooks.
    // If no actual mixin behavior is required, keep this as a placeholder.
}