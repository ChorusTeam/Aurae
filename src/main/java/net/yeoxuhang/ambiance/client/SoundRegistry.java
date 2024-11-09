package net.yeoxuhang.ambiance.client;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.yeoxuhang.ambiance.Ambiance;

public class SoundRegistry {

    public static final SoundEvent ENDER_EYE_PLACED = register("ender_eye_placed");

    public static SoundEvent register(String name) {
        ResourceLocation location = Ambiance.name(name);
        return Registry.register(Registry.SOUND_EVENT, location, new SoundEvent(location));
    }

    public static void init() {
        Ambiance.LOGGER_DEBUG.info("Registered sounds");
    }
}