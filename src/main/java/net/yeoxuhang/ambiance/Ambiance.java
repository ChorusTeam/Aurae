package net.yeoxuhang.ambiance;

import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.ConfigHolder;
import dev.toma.configuration.config.format.ConfigFormats;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import net.yeoxuhang.ambiance.config.AmbianceConfig;
import net.yeoxuhang.ambiance.core.Registries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ambiance implements ModInitializer {

    @Override
    public void onInitialize() {
        Registries.init();
        ConfigHolder<AmbianceConfig> configHolder = Configuration.registerConfig(AmbianceConfig.class, ConfigFormats.json());
        config = configHolder.getConfigInstance();
    }

    public static AmbianceConfig config;
    public static final String MOD_ID = "ambiance";
    public static final String MOD_NAME = "Ambiance";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);
    public static final Logger LOGGER_DEBUG = LogManager.getLogger("Ambiance/Debug");

    public static boolean isModLoaded(String mod){
        return FabricLoader.getInstance().isModLoaded(mod);
    }

    public static ResourceLocation name(String name){
        return new ResourceLocation(MOD_ID, name);
    }
}
