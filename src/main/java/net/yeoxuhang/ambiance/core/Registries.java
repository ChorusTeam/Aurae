package net.yeoxuhang.ambiance.core;

import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.IEventBus;
import net.yeoxuhang.ambiance.client.SoundRegistry;
import net.yeoxuhang.ambiance.client.particle.ParticleRegistry;

public class Registries {
    public static void init(IEventBus eventBus){
        ParticleRegistry.PARTICLES.register(eventBus);
        SoundRegistry.SOUND.register(eventBus);
    }
}
