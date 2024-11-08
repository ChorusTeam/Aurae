package net.yeoxuhang.ambiance.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.MathHelper;
import net.yeoxuhang.ambiance.client.particle.option.AshOption;

public class AshParticle extends RisingParticle {
    private final float rotSpeed;
    private final IAnimatedSprite sprites;
    private final float size;
    private final float speed;

    protected AshParticle(ClientWorld clientLevel, double d, double e, double f, double g, double h, double i, float j, IAnimatedSprite spriteSet, int age, float gravity, float size, float movementXY) {
        super(clientLevel, d, e, f, movementXY, gravity, movementXY, g, h, i, j, spriteSet, 1.0F, 30, gravity, true);
        this.rotSpeed = ((float)Math.random() - 0.5F) * 0.1F;
        this.roll = (float)Math.random() * 6.2831855F;
        this.sprites = spriteSet;
        this.lifetime = age;
        this.size = size;
        this.speed = gravity;
    }

    @Override
    public float getQuadSize(float f) {
        return this.size * MathHelper.clamp(((float)this.age + f) / (float)this.lifetime * 64.0F, 0.0F, 1.0F);
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
            this.yd += 0.000000300026077032 / speed;
            this.yd = Math.max(this.yd, +0.055000000059604645);
        }
    }

    @Override
    protected int getLightColor(float f) {
        return 240;
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements IParticleFactory<AshOption> {
        private final IAnimatedSprite sprites;

        public Provider(IAnimatedSprite spriteSet) {
            this.sprites = spriteSet;
        }
        public Particle createParticle(AshOption ashOption, ClientWorld clientLevel, double d, double e, double f, double g, double h, double i) {
            AshParticle ashParticle = new AshParticle(clientLevel, d, e, f, 0.0, 0.0, 0.0, 1.0F, this.sprites, ashOption.getAge(), ashOption.getGravity(), ashOption.getSize(), ashOption.getMovementXY());
            ashParticle.setColor(ashOption.getRed(), ashOption.getGreen(), ashOption.getBlue());
            ashParticle.setAlpha(ashOption.getAlpha());
            ashParticle.setSize(ashOption.getSize(), ashOption.getSize());
            ashParticle.gravity = ashOption.getGravity();
            return ashParticle;
        }
    }
}