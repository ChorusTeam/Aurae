package net.yeoxuhang.ambiance.platform;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.yeoxuhang.ambiance.Ambiance;
import net.yeoxuhang.ambiance.platform.services.RegistryHelper;

import java.util.function.Function;
import java.util.function.Supplier;

public class NeoForgeRegistryHelper implements RegistryHelper {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, Ambiance.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND = DeferredRegister.create(Registries.SOUND_EVENT, Ambiance.MOD_ID);


    @Override
    public <T extends ParticleType<?>> Supplier<SimpleParticleType> registerParticle(String name, Boolean bl) {
        return PARTICLE_TYPES.register(name, ()-> new SimpleParticleType(bl));
    }

    @Override
    public <T extends ParticleOptions> Supplier<ParticleType<T>> registerParticle(String string, boolean bl, Function<ParticleType<T>, MapCodec<T>> function, Function<ParticleType<T>, StreamCodec<? super RegistryFriendlyByteBuf, T>> function2) {
        return PARTICLE_TYPES.register(string, ()-> new ParticleType<T>(bl) {
            public MapCodec<T> codec() {
                return function.apply(this);
            }
            public StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec() {
                return function2.apply(this);
            }
        });
    }

    @Override
    public Supplier<SoundEvent> registerSound(String name, float distance) {
        return SOUND.register(name, ()-> SoundEvent.createFixedRangeEvent(Ambiance.name(name), distance));
    }
}
