package net.yeoxuhang.ambiance.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.yeoxuhang.ambiance.client.particle.option.TrialOption;

public class TrialParticle extends TextureSheetParticle {
    private final float rotSpeed;
    private final SpriteSet sprites;
    private final double xStart;
    private final double yStart;
    private final double zStart;
    private final float speed;

    protected TrialParticle(ClientLevel clientLevel, double d, double e, double f, double g, double h, double i, SpriteSet spriteSet, int age, float gravity, float speed, float size) {
        super(clientLevel, d, e, f);
        this.pickSprite(spriteSet);
        //this.quadSize = 0.15F * (this.random.nextFloat() * 0.2F + 0.5F);
        this.xd = g;
        this.yd = h;
        this.zd = i;
        this.x = d;
        this.y = e;
        this.z = f;
        this.xStart = this.x;
        this.yStart = this.y;
        this.zStart = this.z;
        this.rotSpeed = ((float)Math.random() - 0.5F) * 0.1F;
        this.roll = (float)Math.random() * 6.2831855F;
        this.sprites = spriteSet;
        this.lifetime = age;
        this.gravity = gravity;
        this.speed = speed;
        this.quadSize = size;
    }

    public void move(double d, double e, double f) {
        this.setBoundingBox(this.getBoundingBox().move(d, e, f));
        this.setLocationFromBoundingbox();
    }

    public float getQuadSize(float f) {
        float g = ((float)this.age + f) / (float)this.lifetime;
        g = 1.0F - g;
        g *= g;
        g = 1.0F - g;
        return this.quadSize * g;
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            if (this.age > this.lifetime / 2) {
                this.setAlpha(1.0F - ((float)this.age - (float)(this.lifetime / 2)) / (float)this.lifetime);
            }
            this.setSpriteFromAge(this.sprites);
            this.oRoll = this.roll;
            this.roll += 3.1415927F * this.rotSpeed * 2.0F;
            if (this.onGround) {
                this.oRoll = this.roll = 0.0F;
            }
            else {
                float f = (float)this.age / (float)this.lifetime;
                float g = f;
                f = -f + f * f * 1.05F * speed;
                f = 1.0F - f;
                if (gravity < 0){
                    this.y = this.yStart + this.yd * (double)f + (double)(1.0F + g);
                } else {
                    this.y = this.yStart + this.yd * (double)f + (double)(1.0F - g);
                }
                this.x = this.xStart + this.xd * (double)f;
                this.z = this.zStart + this.zd * (double)f;
            }
        }
    }

    @Override
    protected int getLightColor(float f) {
        return 240;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Provider implements ParticleProvider<TrialOption> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(TrialOption endOption, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i) {
            TrialParticle trialParticle = new TrialParticle(clientLevel, d, e, f, g, h, i, this.sprites, endOption.getAge(), endOption.getGravity(), endOption.getSpeed(), endOption.getSize());
            trialParticle.setColor(endOption.getRed(), endOption.getGreen(), endOption.getBlue());
            trialParticle.setAlpha(endOption.getAlpha());
            return trialParticle;
        }
    }
}