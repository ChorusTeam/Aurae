package net.yeoxuhang.ambiance.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class EmptyParticle extends NoRenderParticle {
    public EmptyParticle(ClientLevel level, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(level, x, y, z, motionX, motionY, motionZ);
        age = 1;
    }
    public static class Provider implements ParticleProvider<SimpleParticleType> {

        public Provider() {
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double motionX, double motionY, double motionZ) {
            return new EmptyParticle(world, x, y, z, motionX, motionY, motionZ);
        }
    }
}