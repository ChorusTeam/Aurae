package net.yeoxuhang.ambiance.client.particle;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.yeoxuhang.ambiance.Ambiance;
import net.yeoxuhang.ambiance.client.particle.option.AshOption;
import net.yeoxuhang.ambiance.client.particle.option.ColorParticleOption;
import net.yeoxuhang.ambiance.client.particle.option.TrialOption;

import java.util.function.Function;
import java.util.function.Supplier;

import static net.yeoxuhang.ambiance.platform.Services.REGISTRY;

public class ParticleRegistry {

    public static final Supplier<ParticleType<AshOption>> ASH =
            register("ash", true, AshOption.DESERIALIZER,(codec) -> AshOption.CODEC);

    public static final Supplier<ParticleType<ColorParticleOption>> COLOR_ASH =
            register("color_ash", false, ColorParticleOption.DESERIALIZER, (codec) -> ColorParticleOption.CODEC);

    public static final Supplier<ParticleType<AshOption>> END_PORTAL_ASH =
            register("end_portal_ash", true, AshOption.DESERIALIZER, (codec) -> AshOption.CODEC);

    public static final Supplier<ParticleType<TrialOption>> TRIAL =
            register("end", true, TrialOption.DESERIALIZER, (codec)-> TrialOption.CODEC);

    public static final Supplier<ParticleType<ColorParticleOption>> FIRE_ASH =
            register("fire_ash", true, ColorParticleOption.DESERIALIZER, (codec) -> ColorParticleOption.CODEC);

    public static final Supplier<ParticleType<ColorParticleOption>> ENDER_EYE_PLACE =
            register("ender_eye_place", false, ColorParticleOption.DESERIALIZER, (codec) -> ColorParticleOption.CODEC);

    public static final Supplier<ParticleType<TrialOption>> END_GATEWAY =
            register("end_gateway", true,  TrialOption.DESERIALIZER, (codec) -> TrialOption.CODEC);

    public static final Supplier<ParticleType<TrialOption>> PORTAL =
            register("portal", true, TrialOption.DESERIALIZER, (codec) -> TrialOption.CODEC);
    public static final Supplier<ParticleType<TrialOption>> REVERSE_PORTAL =
            register("reverse_portal", false, TrialOption.DESERIALIZER, (codec) -> TrialOption.CODEC);

    public static final Supplier<SimpleParticleType> AIR =
            register("empty", false);


    private static Supplier<SimpleParticleType> register(String string, boolean bl) {
        return REGISTRY.registerParticle(string, bl);
    }

    private static <T extends ParticleOptions> Supplier<ParticleType<T>> register(String string, boolean bl, ParticleOptions.Deserializer<T> function, final Function<ParticleType<T>, Codec<T>> function2) {
        return REGISTRY.registerParticle(string, bl, function, function2);
    }

    public static void init() {
        Ambiance.LOGGER_DEBUG.debug("Registered particles");
    }
}
