package net.yeoxuhang.ambiance.client.particle;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.yeoxuhang.ambiance.client.particle.option.ColorParticleOption;

@OnlyIn(Dist.CLIENT)
public class EnderEyePlaceParticle extends SpriteTexturedParticle {

    protected EnderEyePlaceParticle(ClientWorld world, double x, double y, double z) {
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
    public void render(IVertexBuilder vertexBuilder, ActiveRenderInfo camera, float partialTicks) {
        this.alpha = 1.0F - MathHelper.clamp(((float) this.age + partialTicks) / (float) this.lifetime, 0.0F, 1.0F);
        Quaternion y = new Quaternion(new Vector3f(0, 1, 0), 0, true);
        this.renderParticle(vertexBuilder, camera, y, partialTicks, 0, 0.26F);
        this.renderParticle(vertexBuilder, camera, y, partialTicks, 0, -0.26F);
        Quaternion y1 = new Quaternion(new Vector3f(0, 1, 0), 180, true);
        this.renderParticle(vertexBuilder, camera, y1, partialTicks, 0, 0.26F);
        this.renderParticle(vertexBuilder, camera, y1, partialTicks, 0, -0.26F);
        Quaternion y2 = new Quaternion(new Vector3f(0, 1, 0), 90, true);
        this.renderParticle(vertexBuilder, camera, y2, partialTicks, 0.26F, 0);
        this.renderParticle(vertexBuilder, camera, y2, partialTicks, -0.26F, 0);
        Quaternion y3 = new Quaternion(new Vector3f(0, 1, 0), -90, true);
        this.renderParticle(vertexBuilder, camera, y3, partialTicks, 0.26F, 0);
        this.renderParticle(vertexBuilder, camera, y3, partialTicks, -0.26F, 0);
    }

    protected void renderParticle(IVertexBuilder vertexBuilder, ActiveRenderInfo camera, Quaternion quaternion, float partialTicks, float offsetX, float offsetZ) {
        renderRotatedQuad(vertexBuilder, camera, quaternion, partialTicks, offsetX, offsetZ);
    }

    protected void renderRotatedQuad(IVertexBuilder vertexBuilder, ActiveRenderInfo camera, Quaternion quaternion, float partialTicks, float offsetX, float offsetZ) {
        Vector3d cameraPos = camera.getPosition();
        float x = (float) (MathHelper.lerp(partialTicks, this.xo, this.x) - cameraPos.x + offsetX);
        float y = (float) (MathHelper.lerp(partialTicks, this.yo, this.y) - cameraPos.y);
        float z = (float) (MathHelper.lerp(partialTicks, this.zo, this.z) - cameraPos.z + offsetZ);
        this.renderQuad(vertexBuilder, quaternion, x, y, z, partialTicks);
    }

    protected void renderQuad(IVertexBuilder buffer, Quaternion quaternion, float x, float y, float z, float partialTicks) {
        float quadSize = this.getQuadSize(partialTicks);
        float u0 = this.getU0();
        float u1 = this.getU1();
        float v0 = this.getV0();
        float v1 = this.getV1();
        int light = this.getLightColor(partialTicks);

        Vector3f[] vertices = new Vector3f[]{
                new Vector3f(-1.0F, -1.0F, 0.0F),
                new Vector3f(-1.0F, 1.0F, 0.0F),
                new Vector3f(1.0F, 1.0F, 0.0F),
                new Vector3f(1.0F, -1.0F, 0.0F)
        };

        for (Vector3f vertex : vertices) {
            vertex.mul(quadSize);
            vertex.transform(quaternion);
            vertex.add(x, y, z);
        }

        buffer.vertex(vertices[0].x(), vertices[0].y(), vertices[0].z())
                .uv(u1, v1).color(rCol, gCol, bCol, alpha)
                .uv2(light).endVertex();
        buffer.vertex(vertices[1].x(), vertices[1].y(), vertices[1].z())
                .uv(u1, v0).color(rCol, gCol, bCol, alpha)
                .uv2(light).endVertex();
        buffer.vertex(vertices[2].x(), vertices[2].y(), vertices[2].z())
                .uv(u0, v0).color(rCol, gCol, bCol, alpha)
                .uv2(light).endVertex();
        buffer.vertex(vertices[3].x(), vertices[3].y(), vertices[3].z())
                .uv(u0, v1).color(rCol, gCol, bCol, alpha)
                .uv2(light).endVertex();
    }


    @Override
    public int getLightColor(float partialTicks) {
        float ageFactor = ((float) this.age + partialTicks) / (float) this.lifetime;
        ageFactor = MathHelper.clamp(ageFactor, 0.0F, 1.0F);
        int light = 240; // Full brightness
        return light;
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements IParticleFactory<ColorParticleOption> {
        private final IAnimatedSprite spriteSet;

        public Provider(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle createParticle(ColorParticleOption colorOption, ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            EnderEyePlaceParticle particle = new EnderEyePlaceParticle(world, x, y, z);
            particle.pickSprite(this.spriteSet);
            particle.setAlpha(1F);
            particle.setColor(colorOption.getRed(), colorOption.getGreen(), colorOption.getBlue());
            return particle;
        }
    }
}
