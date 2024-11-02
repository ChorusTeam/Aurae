package net.yeoxuhang.ambiance.platform.services;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Function;
import java.util.function.Supplier;

public interface RegistryHelper {

    <T extends ParticleType<?>> Supplier<SimpleParticleType> registerParticle(String name, Boolean bl);
    <T extends ParticleOptions> Supplier<ParticleType<T>> registerParticle(String string, boolean bl, final Function<ParticleType<T>, MapCodec<T>> function, final Function<ParticleType<T>, StreamCodec<? super RegistryFriendlyByteBuf, T>> function2);
    Supplier<SoundEvent> registerSound(String name, float distance);
}
