package net.yeoxuhang.ambiance.mixin;

import net.minecraft.client.Minecraft;
import net.yeoxuhang.ambiance.client.AmbianceClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void onClientTick(CallbackInfo ci) {
        Minecraft client = Minecraft.getInstance();

        // Only proceed if in a client world
        if (client.level != null) {
            AmbianceClient.onTick();
        }
    }
}
