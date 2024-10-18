package net.yeoxuhang.ambiance;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.yeoxuhang.ambiance.config.AmbianceConfig;
import net.yeoxuhang.ambiance.core.Registries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ambiance implements ModInitializer {

    public static final String MOD_ID = "ambiance";
    public static final String MOD_NAME = "Ambiance";
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);
    @Override
    public void onInitialize() {
        LOGGER.info("A small Mod that add new ambient effects to the Minecraft!");
        Registries.init();
        MidnightConfig.init(MOD_ID, AmbianceConfig.class);
    }
}
