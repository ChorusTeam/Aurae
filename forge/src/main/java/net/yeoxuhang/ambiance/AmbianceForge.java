package net.yeoxuhang.ambiance;
import net.minecraft.client.Minecraft;
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
        ForgeRegistryHelper.PARTICLE_TYPES.register(eventBus);
        ForgeRegistryHelper.SOUND.register(eventBus);
        Ambiance.init();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventBusSubscriber(modid = Ambiance.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientEventSubscriber {

        @SubscribeEvent
        public static void onParticleFactoryRegister(RegisterParticleProvidersEvent event) {
            Minecraft.getInstance().particleEngine.register(ParticleRegistry.AIR.get(), new EmptyParticle.Provider());
            Minecraft.getInstance().particleEngine.register(ParticleRegistry.ASH.get(), AshParticle.Provider::new);
            Minecraft.getInstance().particleEngine.register(ParticleRegistry.END_PORTAL_ASH.get(), AshParticle.Provider::new);
            Minecraft.getInstance().particleEngine.register(ParticleRegistry.TRIAL.get(), TrialParticle.Provider::new);
            Minecraft.getInstance().particleEngine.register(ParticleRegistry.END_GATEWAY.get(), TrialParticle.Provider::new);
            Minecraft.getInstance().particleEngine.register(ParticleRegistry.FIRE_ASH.get(), FireAshParticle.Provider::new);
            Minecraft.getInstance().particleEngine.register(ParticleRegistry.PORTAL.get(), PortalAshParticle.Provider::new);
            Minecraft.getInstance().particleEngine.register(ParticleRegistry.REVERSE_PORTAL.get(), ReversePortalAshParticle.Provider::new);
            Minecraft.getInstance().particleEngine.register(ParticleRegistry.ENDER_EYE_PLACE.get(), EnderEyePlaceParticle.Provider::new);
            Minecraft.getInstance().particleEngine.register(ParticleRegistry.COLOR_ASH.get(), VanillaSmoke.Provider::new);
        }
    }

}
