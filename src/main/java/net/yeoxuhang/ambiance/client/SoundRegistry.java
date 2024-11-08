package net.yeoxuhang.ambiance.client;

import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.yeoxuhang.ambiance.Ambiance;

public class SoundRegistry {
    public static final DeferredRegister<SoundEvent> SOUND = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Ambiance.MOD_ID); // Replace with your actual mod ID


    public static final RegistryObject<SoundEvent> ENDER_EYE_PLACED = register("ender_eye_placed");

    private static RegistryObject register(String name){
       return SOUND.register(name, ()-> new SoundEvent(Ambiance.rL(name)));
    }

    public static void init() {
        Ambiance.LOGGER_DEBUG.info("Registered sounds");
    }
}