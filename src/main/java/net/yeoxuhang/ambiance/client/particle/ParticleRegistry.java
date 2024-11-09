package net.yeoxuhang.ambiance.client.particle;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.yeoxuhang.ambiance.Ambiance;
import net.yeoxuhang.ambiance.client.particle.option.AshOption;
import net.yeoxuhang.ambiance.client.particle.option.ColorParticleOption;
import net.yeoxuhang.ambiance.client.particle.option.TrialOption;
import net.yeoxuhang.ambiance.client.particle.type.*;

import java.util.function.Function;

public class ParticleRegistry {
    
    // Particle registrations
    public static final ParticleType<AshOption> ASH = register("ash", new AshParticleType(true));
    public static final ParticleType<ColorParticleOption> COLOR_ASH = register("color_ash", new ColorAshParticleType(true));
    public static final ParticleType<AshOption> END_PORTAL_ASH = register("end_portal_ash", new EndPortalAshParticleType(true));
    public static final ParticleType<TrialOption> TRIAL = register("trial", new TrialParticleType(true));
    public static final ParticleType<ColorParticleOption> FIRE_ASH = register("fire_ash", new FireAshParticleType(true));
    public static final ParticleType<ColorParticleOption> ENDER_EYE_PLACE = register("ender_eye_place", new EnderEyePlaceParticleType(true));
    public static final ParticleType<TrialOption> END_GATEWAY = register("end_gateway", new EndGatewayParticleType(true));
    public static final ParticleType<TrialOption> PORTAL = register("portal", new PortalParticleType(true));
    public static final ParticleType<TrialOption> REVERSE_PORTAL = register("reverse_portal", new ReversePortalParticleType(true));
    public static final SimpleParticleType AIR = register("empty", false);

    private static <T extends ParticleOptions> ParticleType<T> register(String name, ParticleType<T> particleType) {
        return Registry.register(Registry.PARTICLE_TYPE, Ambiance.name(name), particleType);
    }

    private static SimpleParticleType register(String name, boolean alwaysShow) {
        return Registry.register(Registry.PARTICLE_TYPE, Ambiance.name(name), new SimpleParticleType(alwaysShow));
    }

    /**
     * Initializes the particle registry.
     */
    public static void init() {
        Ambiance.LOGGER_DEBUG.debug("Registered particles");
    }
}
