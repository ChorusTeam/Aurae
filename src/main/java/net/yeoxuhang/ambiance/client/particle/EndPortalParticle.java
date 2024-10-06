package net.yeoxuhang.ambiance.client.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;

public class EndPortalParticle extends TextureSheetParticle {

    EndPortalParticle(ClientLevel clientLevel, double d, double e, double f) {
        super(clientLevel, d, e, f, 0.0, 0.0, 0.0);
        this.lifetime = 100;
        this.gravity = 0.0F;
        this.quadSize = 0.5F;
        this.setSize(1F, 1F);

        this.xd = 0.0;
        this.yd = 0.0;
        this.zd = 0.0;
    }

    public void render(VertexConsumer vertexConsumer, Camera camera, float f) {
        //this.alpha = 1.0F - Mth.clamp(((float)this.age + f) / (float)this.lifetime, 0.0F, 1.0F);
        Quaternionf quaternionf = new Quaternionf();
        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(-1.57F), f, 1.5F, 0);
        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(1.57F), f, 1.5F, 0);
        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(-1.57F), f, 1.5F, 1F);
        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(1.57F), f, 1.5F, 1F);
        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(-1.57F), f, 1.5F, -1F);
        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(1.57F), f, 1.5F, -1F);

        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(-1.57F), f, -1.5F, 0);
        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(1.57F), f, -1.5F, 0);
        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(-1.57F), f, -1.5F, 1F);
        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(1.57F), f, -1.5F, 1F);
        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(-1.57F), f, -1.5F, -1F);
        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(1.57F), f, -1.5F, -1F);

        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(3.14F), f, 0, 1.5F);
        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(0), f, 0, 1.5F);
        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(3.14F), f, 1F, 1.5F);
        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(0), f, 1F, 1.5F);
        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(3.14F), f, -1F, 1.5F);
        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(0), f, -1F, 1.5F);

        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(3.14F), f, 0, -1.5F);
        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(0), f, 0, -1.5F);
        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(3.14F), f, 1F, -1.5F);
        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(0), f, 1F, -1.5F);
        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(3.14F), f, -1F, -1.5F);
        this.renderParticle(vertexConsumer, camera, quaternionf.rotationY(0), f, -1F, -1.5F);
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
        int i = 240;
        int j = i & 255;
        int k = i >> 16 & 255;
        j += (int)(g * 15.0F * 16.0F);
        if (j > 240) {
            j = 240;
        }

        return j | k << 16;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ < this.lifetime && !(this.alpha <= 0.0F)) {
            if (this.age >= this.lifetime - 60 && this.alpha > 0.01F) {
                this.alpha -= 0.015F;
            }
        } else {
            this.remove();
        }
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet spriteSet) {
            this.sprite = spriteSet;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i) {
            EndPortalParticle eyePlaceParticle = new EndPortalParticle(clientLevel, d, e, f);
            eyePlaceParticle.pickSprite(this.sprite);
            eyePlaceParticle.setAlpha(0.3F);
            return eyePlaceParticle;
        }
    }
}
