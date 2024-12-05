package net.yeoxuhang.ambiance.client.particle;

import com.mojang.serialization.Codec;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.yeoxuhang.ambiance.Ambiance;
import net.yeoxuhang.ambiance.client.particle.option.AshOption;
import net.yeoxuhang.ambiance.client.particle.option.ColorParticleOption;
import net.yeoxuhang.ambiance.client.particle.option.TrialOption;
import net.yeoxuhang.ambiance.client.particle.type.*;


import java.util.function.Function;

public class ParticleRegistry {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Ambiance.MOD_ID);

    // Particle registrations
    public static final RegistryObject<ParticleType<AshOption>> ASH = register("ash", new AshParticleType(true));
    public static final RegistryObject<ParticleType<ColorParticleOption>> COLOR_ASH = register("color_ash", new ColorAshParticleType(true));
    public static final RegistryObject<ParticleType<AshOption>> END_PORTAL_ASH = register("end_portal_ash", new EndPortalAshParticleType(true));
    public static final RegistryObject<ParticleType<TrialOption>> TRIAL = register("trial", new TrialParticleType(true));
    public static final RegistryObject<ParticleType<ColorParticleOption>> FIRE_ASH = register("fire_ash", new FireAshParticleType(true));
    public static final RegistryObject<ParticleType<ColorParticleOption>> ENDER_EYE_PLACE = register("ender_eye_place", new EnderEyePlaceParticleType(true));
    public static final RegistryObject<ParticleType<TrialOption>> END_GATEWAY = register("end_gateway", new EndGatewayParticleType(true));
    public static final RegistryObject<ParticleType<TrialOption>> PORTAL = register("portal", new PortalParticleType(true));
    public static final RegistryObject<ParticleType<TrialOption>> REVERSE_PORTAL = register("reverse_portal", new ReversePortalParticleType(true));
    public static final RegistryObject<BasicParticleType> AIR = registerBasic("empty", false);

    /**
     * Register a basic particle type without custom data.
     *
     * @param name The particle's name
     * @param alwaysShow Whether the particle is always visible
     * @return The registry object for the basic particle type
     */
    private static RegistryObject<BasicParticleType> registerBasic(String name, boolean alwaysShow) {
        return PARTICLES.register(name, () -> new BasicParticleType(alwaysShow));
    }

    private static <T extends IParticleData> RegistryObject<ParticleType<T>> register(String name, ParticleType<T> particleType) {
        return PARTICLES.register(name, ()-> particleType);
    }

    /**
     * Initializes the particle registry.
     */
    public static void init() {
        Ambiance.LOGGER_DEBUG.debug("Registered particles");
    }
}
