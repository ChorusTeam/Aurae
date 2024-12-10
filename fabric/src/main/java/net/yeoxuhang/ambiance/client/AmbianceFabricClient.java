package net.yeoxuhang.ambiance.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.yeoxuhang.ambiance.client.particle.*;

public final class AmbianceFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.AIR.get(), EmptyParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.ASH.get(), AshParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.END_PORTAL_ASH.get(), AshParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.TRIAL.get(), TrialParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.END_GATEWAY.get(), TrialParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.FIRE_ASH.get(), FireAshParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.PORTAL.get(), PortalAshParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.REVERSE_PORTAL.get(), ReversePortalAshParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.ENDER_EYE_PLACE.get(), EnderEyePlaceParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.COLOR_ASH.get(), VanillaSmoke.Provider::new);
    }
}
