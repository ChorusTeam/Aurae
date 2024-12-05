package net.yeoxuhang.ambiance.platform;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.yeoxuhang.ambiance.Ambiance;
import net.yeoxuhang.ambiance.platform.services.RegistryHelper;

import java.util.function.Function;
import java.util.function.Supplier;

public class FabricRegistryHelper implements RegistryHelper {
    @Override
    public <T extends ParticleOptions> Supplier<ParticleType<T>> registerParticle(String name, boolean bl,ParticleOptions.Deserializer<T> function, Function<ParticleType<T>, Codec<T>> function2) {
        ParticleType<T> particleType = Registry.register(Registry.PARTICLE_TYPE, Ambiance.rL(name), new ParticleType<T>(bl, function) {public Codec<T> codec() {return function2.apply(this);}});
        return ()-> particleType;
    }


    @Override
    public Supplier<SimpleParticleType> registerParticle(String name, Boolean bl) {
        SimpleParticleType particleType = Registry.register(Registry.PARTICLE_TYPE, Ambiance.rL(name), new SimpleParticleType(bl));
        return ()-> particleType;
    }

    @Override
    public Supplier<SoundEvent> registerSound(String name, float distance) {
        ResourceLocation location = Ambiance.rL(name);
        SoundEvent soundEvent = Registry.register(Registry.SOUND_EVENT, name, new SoundEvent(location));
        return ()-> soundEvent;
    }
}
