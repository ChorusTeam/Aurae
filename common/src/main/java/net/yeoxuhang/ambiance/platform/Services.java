package net.yeoxuhang.ambiance.platform;

import net.yeoxuhang.ambiance.Ambiance;
import net.yeoxuhang.ambiance.platform.services.IPlatformHelper;
import net.yeoxuhang.ambiance.platform.services.RegistryHelper;

import java.util.ServiceLoader;

public class Services {
    // mod is loaded.
    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);
    public static final RegistryHelper REGISTRY = load(RegistryHelper.class);

    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz).findFirst().orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        Ambiance.LOGGER_DEBUG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}
