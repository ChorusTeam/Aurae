package net.yeoxuhang.ambiance.client.particle;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.yeoxuhang.ambiance.Ambiance;
import net.yeoxuhang.ambiance.client.particle.option.AshOption;
import net.yeoxuhang.ambiance.client.particle.option.TrialOption;

import java.util.function.Function;

public class ParticleRegistry {
    public static final ParticleType<AshOption> ASH =
            register("ash", true, ashOptionParticleType -> AshOption.CODEC, ashOptionParticleType -> AshOption.STREAM_CODEC);
    public static final ParticleType<AshOption> END_PORTAL_ASH =
            register("end_portal_ash", true, ashOptionParticleType -> AshOption.CODEC, ashOptionParticleType -> AshOption.STREAM_CODEC);

    public static final ParticleType<AshOption> RESPAWN_ANCHOR =
            register("respawn_anchor", true, ashOptionParticleType -> AshOption.CODEC, ashOptionParticleType -> AshOption.STREAM_CODEC);

    public static final ParticleType<TrialOption> END =
            register("end", true, endOptionParticleType -> TrialOption.CODEC, endOptionParticleType -> TrialOption.STREAM_CODEC);

    public static final ParticleType<ColorParticleOption> FIRE_ASH =
            register("fire_ash", true, ColorParticleOption::codec, ColorParticleOption::streamCodec);

    public static final ParticleType<ColorParticleOption> SOUL_FIRE_ASH =
            register("soul_ash", true, ColorParticleOption::codec, ColorParticleOption::streamCodec);
    public static final ParticleType<ColorParticleOption> ENDER_EYE_PLACE = register("ender_eye_place", false, ColorParticleOption::codec, ColorParticleOption::streamCodec);

    public static final ParticleType<TrialOption> END_GATEWAY =
            register("end_gateway", true, endOptionParticleType -> TrialOption.CODEC, endOptionParticleType -> TrialOption.STREAM_CODEC);

    public static final SimpleParticleType ENDER_EYE =
            register("ender_eye", false);

    public static final SimpleParticleType END_PORTAL =
            register("end_portal", false);

    public static final SimpleParticleType AIR =
            register("empty", false);



    private static SimpleParticleType register(String string, boolean bl) {
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, Ambiance.MOD_ID + ":" + string, new SimpleParticleType(bl));
    }

    private static <T extends ParticleOptions> ParticleType<T> register(String string, boolean bl, final Function<ParticleType<T>, MapCodec<T>> function, final Function<ParticleType<T>, StreamCodec<? super RegistryFriendlyByteBuf, T>> function2) {
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, Ambiance.MOD_ID + ":" + string, new ParticleType<T>(bl) {
            public MapCodec<T> codec() {
                return function.apply(this);
            }

            public StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec() {
                return function2.apply(this);
            }
        });
    }


    public static void init() {
        Ambiance.LOGGER.debug("Registered particles");
    }
}
