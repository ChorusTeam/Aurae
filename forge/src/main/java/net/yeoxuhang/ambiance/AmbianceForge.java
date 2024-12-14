package net.yeoxuhang.ambiance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.yeoxuhang.ambiance.client.particle.*;
import net.yeoxuhang.ambiance.platform.ForgeRegistryHelper;


@Mod(Ambiance.MOD_ID)
public class AmbianceForge {
    public AmbianceForge() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        Ambiance.init();
        ForgeRegistryHelper.PARTICLE_TYPES.register(eventBus);
        ForgeRegistryHelper.SOUND.register(eventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventBusSubscriber(modid = Ambiance.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientEventSubscriber {

        @SubscribeEvent
        public static void onParticleFactoryRegister(RegisterParticleProvidersEvent event) {
            event.register(ParticleRegistry.AIR.get(), EmptyParticle.Provider::new);
            event.register(ParticleRegistry.ASH.get(), AshParticle.Provider::new);
            event.register(ParticleRegistry.END_PORTAL_ASH.get(), AshParticle.Provider::new);
            event.register(ParticleRegistry.TRIAL.get(), TrialParticle.Provider::new);
            event.register(ParticleRegistry.END_GATEWAY.get(), TrialParticle.Provider::new);
            event.register(ParticleRegistry.FIRE_ASH.get(), FireAshParticle.Provider::new);
            event.register(ParticleRegistry.PORTAL.get(), PortalAshParticle.Provider::new);
            event.register(ParticleRegistry.REVERSE_PORTAL.get(), ReversePortalAshParticle.Provider::new);
            event.register(ParticleRegistry.ENDER_EYE_PLACE.get(), EnderEyePlaceParticle.Provider::new);
            event.register(ParticleRegistry.COLOR_ASH.get(), VanillaSmoke.Provider::new);
        }
    }

}
