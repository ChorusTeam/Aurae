package net.yeoxuhang.ambiance.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.yeoxuhang.ambiance.client.particle.option.ColorParticleOption;

public class VanillaSmoke extends BaseAshSmokeParticle {
    private final SpriteSet sprites;
    protected VanillaSmoke(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed, float pQuadSizeMultiplier, SpriteSet pSprites) {
        super(pLevel, pX, pY, pZ, 0.1F, 0.1F, 0.1F, pXSpeed, pYSpeed, pZSpeed, pQuadSizeMultiplier, pSprites, 0.3F, 20, -0.05F, true);
        this.sprites = pSprites;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.age > this.lifetime / 2) {
            this.setAlpha(1.0F - ((float)this.age - (float)(this.lifetime / 3)) / (float)this.lifetime);
        }
        this.setSpriteFromAge(this.sprites);
    }

    public static class Provider implements ParticleProvider<ColorParticleOption> {
        private final SpriteSet sprites;

        public Provider(SpriteSet p_i51045_1_) {
            this.sprites = p_i51045_1_;
        }

        public Particle createParticle(ColorParticleOption pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            VanillaSmoke particle = new VanillaSmoke(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed, 1.0F, this.sprites);
            particle.setColor(pType.getRed(), pType.getGreen(), pType.getBlue());
            return particle;
        }
    }
}
