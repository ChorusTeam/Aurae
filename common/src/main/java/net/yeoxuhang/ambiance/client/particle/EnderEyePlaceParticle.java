package net.yeoxuhang.ambiance.client.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.yeoxuhang.ambiance.client.particle.option.ColorParticleOption;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class EnderEyePlaceParticle extends TextureSheetParticle {

    protected EnderEyePlaceParticle(ClientLevel world, double x, double y, double z) {
        super(world, x, y, z);
        this.lifetime = 30;
        this.gravity = 0.1F;
        this.quadSize = 0.26F;
        this.setSize(0.8F, 0.8F);
        this.xd = 0.0;
        this.yd = 0.0;
        this.zd = 0.0;
    }

    @Override
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

    protected void renderRotatedQuad(VertexConsumer pBuffer, Quaternionf pQuaternion, float pX, float pY, float pZ, float pPartialTicks) {
        float f = this.getQuadSize(pPartialTicks);
        float f1 = this.getU0();
        float f2 = this.getU1();
        float f3 = this.getV0();
        float f4 = this.getV1();
        int i = this.getLightColor(pPartialTicks);
        this.renderVertex(pBuffer, pQuaternion, pX, pY, pZ, 1.0F, -1.0F, f, f2, f4, i);
        this.renderVertex(pBuffer, pQuaternion, pX, pY, pZ, 1.0F, 1.0F, f, f2, f3, i);
        this.renderVertex(pBuffer, pQuaternion, pX, pY, pZ, -1.0F, 1.0F, f, f1, f3, i);
        this.renderVertex(pBuffer, pQuaternion, pX, pY, pZ, -1.0F, -1.0F, f, f1, f4, i);
    }

    private void renderVertex(VertexConsumer pBuffer, Quaternionf pQuaternion, float pX, float pY, float pZ, float pXOffset, float pYOffset, float pQuadSize, float pU, float pV, int pPackedLight) {
        Vector3f vector3f = (new Vector3f(pXOffset, pYOffset, 0.0F)).rotate(pQuaternion).mul(pQuadSize).add(pX, pY, pZ);
        pBuffer.vertex(vector3f.x(), vector3f.y(), vector3f.z()).uv(pU, pV).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(pPackedLight).endVertex();
    }

    @Override
    public int getLightColor(float partialTicks) {
        float ageFactor = ((float) this.age + partialTicks) / (float) this.lifetime;
        ageFactor = Mth.clamp(ageFactor, 0.0F, 1.0F);
        int light = 240; // Full brightness
        return light;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<ColorParticleOption> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle createParticle(ColorParticleOption colorOption, ClientLevel world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            EnderEyePlaceParticle particle = new EnderEyePlaceParticle(world, x, y, z);
            particle.pickSprite(this.spriteSet);
            particle.setAlpha(1F);
            particle.setColor(colorOption.getRed(), colorOption.getGreen(), colorOption.getBlue());
            return particle;
        }
    }
}
