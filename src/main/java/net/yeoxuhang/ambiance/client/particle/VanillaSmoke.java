package net.yeoxuhang.ambiance.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.yeoxuhang.ambiance.client.particle.option.ColorParticleOption;

public class VanillaSmoke extends RisingParticle {
    private final IAnimatedSprite sprites;
    protected VanillaSmoke(ClientWorld pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed, float pQuadSizeMultiplier, IAnimatedSprite pSprites) {
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

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements IParticleFactory<ColorParticleOption> {
        private final IAnimatedSprite sprites;

        public Provider(IAnimatedSprite p_i51045_1_) {
            this.sprites = p_i51045_1_;
        }

        public Particle createParticle(ColorParticleOption pType, ClientWorld pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            VanillaSmoke particle = new VanillaSmoke(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed, 1.0F, this.sprites);
            particle.setColor(pType.getRed(), pType.getGreen(), pType.getBlue());
            return particle;
        }
    }
}
