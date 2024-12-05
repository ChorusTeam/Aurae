package net.yeoxuhang.ambiance.platform;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.yeoxuhang.ambiance.Ambiance;
import net.yeoxuhang.ambiance.platform.services.RegistryHelper;

import java.util.function.Function;
import java.util.function.Supplier;

public class ForgeRegistryHelper implements RegistryHelper {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Ambiance.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Ambiance.MOD_ID);

    @Override
    public <T extends ParticleOptions> Supplier<ParticleType<T>> registerParticle(String name, boolean bl, ParticleOptions.Deserializer<T> function, Function<ParticleType<T>, Codec<T>> function2) {
        return PARTICLE_TYPES.register(name, ()-> new ParticleType<>(bl, function) {
            @Override
            public Codec<T> codec() {
                return function2.apply(this);
            }
        });
    }

    @Override
    public Supplier<SimpleParticleType> registerParticle(String name, Boolean bl) {
        return PARTICLE_TYPES.register(name, ()-> new SimpleParticleType(bl));
    }

    @Override
    public Supplier<SoundEvent> registerSound(String name, float distance) {
        return SOUND.register(name, ()-> new SoundEvent(Ambiance.rL(name)));
    }
}
