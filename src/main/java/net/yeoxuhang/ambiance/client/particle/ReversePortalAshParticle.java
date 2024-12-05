package net.yeoxuhang.ambiance.client.particle;


import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.world.ClientWorld;
import net.yeoxuhang.ambiance.client.particle.option.TrialOption;

public class ReversePortalAshParticle extends PortalParticle {
    protected ReversePortalAshParticle(ClientWorld clientLevel, double d, double e, double f, double g, double h, double i, int age, float gravity,float size, IAnimatedSprite spriteSet) {
        super(clientLevel, d, e, f, g, h, i);
        this.lifetime = age;
        this.gravity = gravity;
        this.quadSize = size;
        this.pickSprite(spriteSet);
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            float f = (float)this.age / (float)this.lifetime;
            this.x += this.xd * (double)f;
            this.y += this.yd * (double)f;
            this.z += this.zd * (double)f;
        }
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
            ReversePortalAshParticle trialParticle = new ReversePortalAshParticle(clientLevel, d, e, f, g, h, i, endOption.getAge(), endOption.getGravity(), endOption.getSize(), sprites);
            trialParticle.setColor(endOption.getRed(), endOption.getGreen(), endOption.getBlue());
            trialParticle.setAlpha(endOption.getAlpha());
            return trialParticle;
        }
    }
}
