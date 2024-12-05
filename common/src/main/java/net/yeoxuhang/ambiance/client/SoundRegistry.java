package net.yeoxuhang.ambiance.client;

import net.minecraft.sounds.SoundEvent;
import net.yeoxuhang.ambiance.Ambiance;
import net.yeoxuhang.ambiance.platform.Services;

import java.util.function.Supplier;

public class SoundRegistry {

    public static final Supplier<SoundEvent> ENDER_EYE_PLACED = register("ender_eye_placed");

    private static Supplier<SoundEvent> register(String name) {
        return register(name, 16.0F);
    }

    private static Supplier register(String name, float range){
       return Services.REGISTRY.registerSound(name, range);
    }

    public static void init() {
        Ambiance.LOGGER_DEBUG.debug("Registered sounds");
    }
}