package net.yeoxuhang.ambiance.client.particle;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.yeoxuhang.ambiance.Ambiance;
import net.yeoxuhang.ambiance.client.particle.option.AshOption;
import net.yeoxuhang.ambiance.client.particle.option.TrialOption;

import java.util.function.Function;
import java.util.function.Supplier;

import static net.yeoxuhang.ambiance.platform.Services.REGISTRY;

public class ParticleRegistry {

    public static final Supplier<ParticleType<AshOption>> ASH =
            register("ash", true, ashOptionParticleType -> AshOption.CODEC, ashOptionParticleType -> AshOption.STREAM_CODEC);

    public static final Supplier<ParticleType<ColorParticleOption>> COLOR_ASH =
            register("color_ash", false, ColorParticleOption::codec, ColorParticleOption::streamCodec);

    public static final Supplier<ParticleType<AshOption>> END_PORTAL_ASH =
            register("end_portal_ash", true, ashOptionParticleType -> AshOption.CODEC, ashOptionParticleType -> AshOption.STREAM_CODEC);

    public static final Supplier<ParticleType<TrialOption>> TRIAL =
            register("end", true, endOptionParticleType -> TrialOption.CODEC, endOptionParticleType -> TrialOption.STREAM_CODEC);

    public static final Supplier<ParticleType<ColorParticleOption>> FIRE_ASH =
            register("fire_ash", true, ColorParticleOption::codec, ColorParticleOption::streamCodec);

    public static final Supplier<ParticleType<ColorParticleOption>> ENDER_EYE_PLACE = register("ender_eye_place", false, ColorParticleOption::codec, ColorParticleOption::streamCodec);

    public static final Supplier<ParticleType<TrialOption>> END_GATEWAY =
            register("end_gateway", true, endOptionParticleType -> TrialOption.CODEC, endOptionParticleType -> TrialOption.STREAM_CODEC);

    public static final Supplier<ParticleType<TrialOption>> PORTAL =
            register("portal", true, endOptionParticleType -> TrialOption.CODEC, endOptionParticleType -> TrialOption.STREAM_CODEC);
    public static final Supplier<SimpleParticleType> AIR =
            register("empty", false);


    private static Supplier<SimpleParticleType> register(String string, boolean bl) {
        Supplier<SimpleParticleType> particleType = REGISTRY.registerParticle(string, bl);
        return particleType;
        //
    }

    private static <T extends ParticleOptions> Supplier<ParticleType<T>> register(String string, boolean bl, final Function<ParticleType<T>, MapCodec<T>> function, final Function<ParticleType<T>, StreamCodec<? super RegistryFriendlyByteBuf, T>> function2) {
        Supplier<ParticleType<T>> particleType = REGISTRY.registerParticle(string, bl, function, function2);
        return particleType;
    }

    public static void init() {
        Ambiance.LOGGER_DEBUG.info("Registered particles");
    }
}
