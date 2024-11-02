package net.yeoxuhang.ambiance;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.yeoxuhang.ambiance.client.particle.*;
import net.yeoxuhang.ambiance.platform.NeoForgeRegistryHelper;


@Mod(value = Ambiance.MOD_ID, dist = Dist.CLIENT)
public class AmbianceNeoForge {
    public AmbianceNeoForge(IEventBus eventBus) {
        Ambiance.init();
        NeoForgeRegistryHelper.PARTICLE_TYPES.register(eventBus);
        NeoForgeRegistryHelper.SOUND.register(eventBus);
        eventBus.addListener(this::registerParticleFactory);
    }

    @SubscribeEvent
    public void registerParticleFactory(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ParticleRegistry.ASH.get(), AshParticle.Provider::new);
        event.registerSpriteSet(ParticleRegistry.END_PORTAL_ASH.get(), AshParticle.Provider::new);
        event.registerSpriteSet(ParticleRegistry.TRIAL.get(), TrialParticle.Provider::new);
        event.registerSpriteSet(ParticleRegistry.END_GATEWAY.get(), TrialParticle.Provider::new);
        event.registerSpriteSet(ParticleRegistry.FIRE_ASH.get(), FireAshParticle.Provider::new);
        event.registerSpriteSet(ParticleRegistry.PORTAL.get(), PortalAshParticle.Provider::new);
        event.registerSpriteSet(ParticleRegistry.ENDER_EYE_PLACE.get(), EnderEyePlaceParticle.Provider::new);
        event.registerSpriteSet(ParticleRegistry.COLOR_ASH.get(), VanillaSmoke.Provider::new);
    }

}
