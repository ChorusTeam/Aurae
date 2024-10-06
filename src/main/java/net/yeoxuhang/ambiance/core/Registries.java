package net.yeoxuhang.ambiance.core;

import net.yeoxuhang.ambiance.client.particle.ParticleRegistry;
import net.yeoxuhang.ambiance.common.sound.SoundRegistry;

public class Registries {
    public static void init(){
        ParticleRegistry.init();
        SoundRegistry.init();
    }
}
