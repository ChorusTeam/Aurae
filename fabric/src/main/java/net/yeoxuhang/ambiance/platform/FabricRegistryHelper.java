package net.yeoxuhang.ambiance.platform;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.yeoxuhang.ambiance.Ambiance;
import net.yeoxuhang.ambiance.platform.services.RegistryHelper;

import java.util.function.Function;
import java.util.function.Supplier;

public class FabricRegistryHelper implements RegistryHelper {

    @Override
    public <T extends ParticleType<?>> Supplier<SimpleParticleType> registerParticle(String name, Boolean bl) {
        SimpleParticleType particleType = Registry.register(BuiltInRegistries.PARTICLE_TYPE, Ambiance.name(name), new SimpleParticleType(bl));
        return ()-> particleType;
    }

    @Override
    public <T extends ParticleOptions> Supplier<ParticleType<T>> registerParticle(String name, boolean bl, Function<ParticleType<T>, MapCodec<T>> function, Function<ParticleType<T>, StreamCodec<? super RegistryFriendlyByteBuf, T>> function2) {
        ParticleType<T> particleType = Registry.register(BuiltInRegistries.PARTICLE_TYPE, Ambiance.name(name), new ParticleType<T>(bl) {
            public MapCodec<T> codec() {
                return function.apply(this);
            }
            public StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec() {
                return function2.apply(this);
            }
        });
        return ()-> particleType;
    }

    @Override
    public Supplier<SoundEvent> registerSound(String name, float distance) {
        ResourceLocation location = Ambiance.name(name);
        SoundEvent soundEvent = Registry.register(BuiltInRegistries.SOUND_EVENT, location, SoundEvent.createFixedRangeEvent(location, distance));
        return ()-> soundEvent;
    }
}
