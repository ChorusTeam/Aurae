package net.yeoxuhang.ambiance;

import com.google.common.base.Suppliers;
import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.format.ConfigFormats;
import net.minecraft.resources.ResourceLocation;
import net.yeoxuhang.ambiance.config.AmbianceConfig;
import net.yeoxuhang.ambiance.core.Registries;
import net.yeoxuhang.ambiance.platform.Services;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public class Ambiance {
    public static AmbianceConfig config;
    public static final String MOD_ID = "ambiance";
    public static final String MOD_NAME = "Ambiance";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);
    public static final Logger LOGGER_DEBUG = LogManager.getLogger("Ambiance/Debug");

    public static void init() {
        config = Configuration.registerConfig(AmbianceConfig.class, ConfigFormats.json()).getConfigInstance();
        Registries.init();
    }
    public static Supplier<Boolean> isModLoaded(String mod){
        return Suppliers.memoize(() -> Services.PLATFORM.isModLoaded(mod));
    }

    public static ResourceLocation rL(String string){
        return new ResourceLocation(Ambiance.MOD_ID, string);
    }
}
