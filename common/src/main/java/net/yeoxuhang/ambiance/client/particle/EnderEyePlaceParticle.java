package net.yeoxuhang.ambiance.client.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;

public class EnderEyePlaceParticle extends TextureSheetParticle {

    EnderEyePlaceParticle(ClientLevel clientLevel, double d, double e, double f) {
        super(clientLevel, d, e, f, 0.0, 0.0, 0.0);
        this.lifetime = 30;
        this.gravity = 0.1F;
        this.quadSize = 0.26F;
        this.setSize(0.8F, 0.8F);
        this.xd = 0.0;
        this.yd = 0.0;
        this.zd = 0.0;
    }

    public void render(VertexConsumer vertexConsumer, Camera camera, float f) {
        this.alpha = 1.0F - Mth.clamp(((float)this.age + f) / (float)this.lifetime, 0.0F, 1.0F);
        Quaternionf quaternionf = new Quaternionf();
        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(-1.57F), f, 0.26F, 0);
        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(1.57F), f, 0.26F, 0);

        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(-1.57F), f, -0.26F, 0);
        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(1.57F), f, -0.26F, 0);

        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(3.14F), f, 0, 0.26F);
        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(0), f, 0, 0.26F);

        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(3.14F), f, 0, -0.26F);
        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(0), f, 0, -0.26F);
    }

    protected void renderParticle(VertexConsumer vertexConsumer, Camera camera, Quaternionf quaternionf, float f, float x, float z) {
        this.renderRotatedQuad(vertexConsumer, camera, quaternionf, f, x, z);
    }

    protected void renderRotatedQuad(VertexConsumer vertexConsumer, Camera camera, Quaternionf quaternionf, float f, float x, float z) {
        Vec3 vec3 = camera.getPosition();
        float g = (float)(Mth.lerp(f, this.xo, this.x) - vec3.x() + x);
        float h = (float)(Mth.lerp(f, this.yo, this.y) - vec3.y());
        float i = (float)(Mth.lerp(f, this.zo, this.z) - vec3.z() + z);
        this.renderRotatedQuad(vertexConsumer, quaternionf, g, h, i, f);
    }

    public int getLightColor(float f) {
        float g = ((float)this.age + f) / (float)this.lifetime;
        g = Mth.clamp(g, 0.0F, 1.0F);
        int i = 255;
        int j = i & 255;
        int k = i >> 16 & 255;
        j += (int)(g * 15.0F * 16.0F);
        if (j > 240) {
            j = 240;
        }

        return j | k << 16;
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<ColorParticleOption> {
        private final SpriteSet sprite;

        public Provider(SpriteSet spriteSet) {
            this.sprite = spriteSet;
        }

        public Particle createParticle(ColorParticleOption simpleParticleType, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i) {
            EnderEyePlaceParticle eyePlaceParticle = new EnderEyePlaceParticle(clientLevel, d, e, f);
            eyePlaceParticle.pickSprite(this.sprite);
            eyePlaceParticle.setAlpha(1F);
            eyePlaceParticle.setColor(simpleParticleType.getRed(), simpleParticleType.getGreen(), simpleParticleType.getBlue());
            return eyePlaceParticle;
        }
    }
}
