package net.yeoxuhang.ambiance.core;

import net.yeoxuhang.ambiance.client.SoundRegistry;
import net.yeoxuhang.ambiance.client.particle.ParticleRegistry;

public class Registries {
    public static void init(){
        ParticleRegistry.init();
        SoundRegistry.init();
    }
}
