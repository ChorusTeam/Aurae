package net.yeoxuhang.ambiance;

import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.ConfigHolder;
import dev.toma.configuration.config.format.ConfigFormats;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;
import net.minecraftforge.fml.loading.FMLLoader;
import net.yeoxuhang.ambiance.client.SoundRegistry;
import net.yeoxuhang.ambiance.client.particle.*;
import net.yeoxuhang.ambiance.config.AmbianceConfig;
import net.yeoxuhang.ambiance.core.Registries;
import net.yeoxuhang.ambiance.util.ParticleJsonValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Ambiance.MOD_ID)
public class Ambiance {

    public static final Logger LOGGER = LogManager.getLogger("Ambiance");
    public static final Logger LOGGER_DEBUG = LogManager.getLogger("Ambiance/Debug");
    public static AmbianceConfig config;
    public static final String MOD_ID = "ambiance";
    public Ambiance() {

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ConfigHolder<AmbianceConfig> configHolder = Configuration.registerConfig(AmbianceConfig.class, ConfigFormats.json());
        config = configHolder.getConfigInstance();

        Registries.init(eventBus);
        if (isModLoaded("endrem")){
            System.out.println("LOaded");
        }
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static boolean isModLoaded(String mod_id){
        return ModList.get().isLoaded(mod_id);
    }

    public static ResourceLocation rL(String string){
        return new ResourceLocation(Ambiance.MOD_ID, string);
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientEventSubscriber {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ResourceLocation particleJson = new ResourceLocation("ambiance", "particles/ash.json");
            ParticleJsonValidator.validateParticleJson(particleJson);
        }

        @SubscribeEvent
        public static void onParticleFactoryRegister(ParticleFactoryRegisterEvent event) {
            Minecraft.getInstance().particleEngine.register(ParticleRegistry.ASH.get(), AshParticle.Provider::new);
            Minecraft.getInstance().particleEngine.register(ParticleRegistry.END_PORTAL_ASH.get(), AshParticle.Provider::new);
            Minecraft.getInstance().particleEngine.register(ParticleRegistry.TRIAL.get(), TrialParticle.Provider::new);
            Minecraft.getInstance().particleEngine.register(ParticleRegistry.END_GATEWAY.get(), TrialParticle.Provider::new);
            Minecraft.getInstance().particleEngine.register(ParticleRegistry.FIRE_ASH.get(), FireAshParticle.Provider::new);
            Minecraft.getInstance().particleEngine.register(ParticleRegistry.PORTAL.get(), PortalAshParticle.Provider::new);
            Minecraft.getInstance().particleEngine.register(ParticleRegistry.ENDER_EYE_PLACE.get(), EnderEyePlaceParticle.Provider::new);
            Minecraft.getInstance().particleEngine.register(ParticleRegistry.COLOR_ASH.get(), VanillaSmoke.Provider::new);
        }
    }
}
