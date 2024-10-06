package net.yeoxuhang.ambiance.compat;

import eu.midnightdust.lib.config.MidnightConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.yeoxuhang.ambiance.Ambiance;

public class AmbianceModMenuCompat implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> MidnightConfig.getScreen(parent, Ambiance.MOD_ID);
    }
}