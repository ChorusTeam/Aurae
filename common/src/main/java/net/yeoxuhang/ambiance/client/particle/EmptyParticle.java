package net.yeoxuhang.ambiance.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class EmptyParticle extends TextureSheetParticle {
    public EmptyParticle(ClientLevel level, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(level, x, y, z, motionX, motionY, motionZ);
    }

    @Override
    public void tick() {
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.NO_RENDER;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {

        private static SpriteSet spriteset;
        public Provider(SpriteSet spriteSet) {
            spriteset = spriteSet;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel world, double x, double y, double z, double motionX, double motionY, double motionZ) {
            EmptyParticle particle = new EmptyParticle(world, x, y, z, motionX, motionY, motionZ);
            particle.pickSprite(spriteset);
            return particle;
        }
    }
}