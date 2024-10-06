package net.yeoxuhang.ambiance.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.util.Mth;
import net.yeoxuhang.ambiance.client.particle.option.RespawnAnchorOption;

public class RespawnAnchorParticle extends BaseAshSmokeParticle {
    private final float rotSpeed;
    private final SpriteSet sprites;
    private final float size;

    protected RespawnAnchorParticle(ClientLevel clientLevel, double d, double e, double f, double g, double h, double i, float j, SpriteSet spriteSet, int age, float gravity, float size, float movementXY) {
        super(clientLevel, d, e, f, movementXY, gravity, movementXY, g, h, i, j, spriteSet, 1.0F, 30, gravity, true);
        this.rotSpeed = ((float)Math.random() - 0.5F) * 0.1F;
        this.roll = (float)Math.random() * 6.2831855F;
        this.sprites = spriteSet;
        this.lifetime = age;
        this.size = size;
    }

    @Override
    public float getQuadSize(float f) {
        return this.size * Mth.clamp(((float)this.age + f) / (float)this.lifetime * 64.0F, 0.0F, 1.0F);
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            if (this.age > this.lifetime / 2) {
                this.setAlpha(1.0F - ((float)this.age - (float)(this.lifetime / 3)) / (float)this.lifetime);
            }
            this.setSpriteFromAge(this.sprites);
            this.oRoll = this.roll;
            this.roll += 3.1415927F * this.rotSpeed * 2.0F;
            if (this.onGround) {
                this.oRoll = this.roll = 0.0F;
            }

            this.move(this.xd, this.yd, this.zd);
            this.yd += 0.000000300026077032;
            this.yd = Math.max(this.yd, +0.055000000059604645);
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Provider implements ParticleProvider<RespawnAnchorOption> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }
        public Particle createParticle(RespawnAnchorOption ashOption, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i) {
            RespawnAnchorParticle ashParticle = new RespawnAnchorParticle(clientLevel, d, e, f, 0.0, 0.0, 0.0, 1.0F, this.sprites, ashOption.getAge(), ashOption.getGravity(), ashOption.getSize(), ashOption.getMovementXY());
            ashParticle.setColor(ashOption.getRed(), ashOption.getGreen(), ashOption.getBlue());
            ashParticle.setAlpha(ashOption.getAlpha());
            ashParticle.setSize(ashOption.getSize(), ashOption.getSize());
            ashParticle.gravity = ashOption.getGravity();
            return ashParticle;
        }
    }
}
