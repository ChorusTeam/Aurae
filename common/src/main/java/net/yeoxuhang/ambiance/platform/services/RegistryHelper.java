package net.yeoxuhang.ambiance.platform.services;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Function;
import java.util.function.Supplier;

public interface RegistryHelper {
    <T extends ParticleOptions> Supplier<ParticleType<T>> registerParticle(String name, boolean bl, ParticleOptions.Deserializer<T> function, final Function<ParticleType<T>, Codec<T>> function2);

    Supplier<SimpleParticleType> registerParticle(String name, Boolean bl);
    Supplier<SoundEvent> registerSound(String name, float distance);
}
