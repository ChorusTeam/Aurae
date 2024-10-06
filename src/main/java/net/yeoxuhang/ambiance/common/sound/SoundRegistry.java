package net.yeoxuhang.ambiance.common.sound;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.yeoxuhang.ambiance.Ambiance;

public class SoundRegistry {

    public static final SoundEvent ENDER_EYE_PLACED = register("ender_eye_placed");

    private static SoundEvent register(String name) {
        return register(name, 16.0F);
    }

    private static SoundEvent register(String name, float range){
        ResourceLocation location = new ResourceLocation(Ambiance.MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, location, SoundEvent.createFixedRangeEvent(location, range));
    }

    public static void init() {
        Ambiance.LOGGER.debug("Registered sounds");
    }
}