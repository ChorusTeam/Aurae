package net.yeoxuhang.ambiance.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.yeoxuhang.ambiance.client.particle.*;

@Environment(EnvType.CLIENT)
public class AmbianceClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.ASH, AshParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.END_PORTAL_ASH, AshParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.RESPAWN_ANCHOR, AshParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.END, TrialParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.END_GATEWAY, TrialParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.FIRE_ASH, FireAshParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.SOUL_FIRE_ASH, FireAshParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.END_PORTAL, EndPortalParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.ENDER_EYE_PLACE, EnderEyePlaceParticle.Provider::new);
    }
}
