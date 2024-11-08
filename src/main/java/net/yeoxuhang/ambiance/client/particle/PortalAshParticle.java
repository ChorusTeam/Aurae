package net.yeoxuhang.ambiance.client.particle;


import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.world.ClientWorld;
import net.yeoxuhang.ambiance.client.particle.option.TrialOption;

public class PortalAshParticle extends PortalParticle {
    protected PortalAshParticle(ClientWorld clientLevel, double d, double e, double f, double g, double h, double i, int age, float gravity, float speed, float size, IAnimatedSprite spriteSet) {
        super(clientLevel, d, e, f, g, h, i);
        this.lifetime = age;
        this.gravity = gravity;
        this.quadSize = size;
        this.pickSprite(spriteSet);
    }

    @Override
    public int getLightColor(float f) {
        return 240;
    }

    public static class Provider implements IParticleFactory<TrialOption> {
        private final IAnimatedSprite sprites;

        public Provider(IAnimatedSprite spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(TrialOption endOption, ClientWorld clientLevel, double d, double e, double f, double g, double h, double i) {
            PortalAshParticle trialParticle = new PortalAshParticle(clientLevel, d, e, f, g, h, i, endOption.getAge(), endOption.getGravity(), endOption.getSpeed(), endOption.getSize(), sprites);
            trialParticle.setColor(endOption.getRed(), endOption.getGreen(), endOption.getBlue());
            trialParticle.setAlpha(endOption.getAlpha());
            return trialParticle;
        }
    }
}
