package net.yeoxuhang.ambiance;
import net.fabricmc.api.ModInitializer;

public class AmbianceFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Ambiance.init();
    }
}
