package com.example.fastplace.mixin;

import com.example.fastplace.FastPlaceClient;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Shadow private int itemUseCooldown;

    @Inject(method = "doItemUse", at = @At("TAIL"))
    private void fastplace$reduceCooldown(CallbackInfo ci) {
        if (FastPlaceClient.enabled) {
            this.itemUseCooldown = FastPlaceClient.cooldownTicks;
        }
    }
}
