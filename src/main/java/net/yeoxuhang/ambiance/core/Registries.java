package net.yeoxuhang.ambiance.core;

import net.yeoxuhang.ambiance.client.particle.ParticleRegistry;
import net.yeoxuhang.ambiance.client.SoundRegistry;

public class Registries {
    public static void init(){
        ParticleRegistry.init();
        SoundRegistry.init();
    }
}
