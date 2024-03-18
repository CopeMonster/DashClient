package com.copemonster.mixins;

import com.copemonster.DashClient;
import com.copemonster.events.world.TickEvent;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(at = @At("HEAD"), method = "tick")
    private void onTick(CallbackInfo info) {
        TickEvent tickEvent = new TickEvent();

        DashClient.EVENT_BUS.post(tickEvent);
    }
}
