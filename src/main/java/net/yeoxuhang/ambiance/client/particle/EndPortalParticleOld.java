package net.yeoxuhang.ambiance.client.particle;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.yeoxuhang.ambiance.Ambiance;
import net.yeoxuhang.ambiance.client.model.EndPortalModel;

public class EndPortalParticleOld extends TextureSheetParticle {
    EndPortalModel enderEye;
    RenderType renderType;
    ResourceLocation renderEyeLayer = new ResourceLocation(Ambiance.MOD_ID, "textures/particle/end_portal_ll.png");

    EndPortalParticleOld(ClientLevel clientLevel, double d, double e, double f) {
        super(clientLevel, d, e, f, 0.0, 0.0, 0.0);
        this.enderEye = new EndPortalModel(Minecraft.getInstance().getEntityModels().bakeLayer(EndPortalModel.LAYER_LOCATION));
        this.quadSize = 0.25F;
        this.lifetime = 100;
        this.gravity = 0.0F;
        this.xd = 0.0;
        this.yd = 0.0;
        this.zd = 0.0;
    }

    public void render(VertexConsumer vertexConsumer, Camera camera, float f) {
        this.alpha = 1.0F - Mth.clamp(((float)this.age + f) / (float)this.lifetime, 0.0F, 1.0F);

        this.renderType = RenderType.entityTranslucentEmissive(renderEyeLayer);

        Vec3 vec3d = camera.getPosition();
        float x = (float) (Mth.lerp(f, this.xo, this.x) - vec3d.x);
        float y = (float) (Mth.lerp(f, this.yo, this.y) - vec3d.y);
        float z = (float) (Mth.lerp(f, this.zo, this.z) - vec3d.z);

        PoseStack poseStack = new PoseStack();
        poseStack.translate(x, y, z);
        poseStack.translate(0.5, 0.2, 0.5);
        MultiBufferSource.BufferSource immediate = Minecraft.getInstance().renderBuffers().bufferSource();
        VertexConsumer vertexConsumer2 = immediate.getBuffer(renderType);
        int light = this.getLightColor(f);
        this.enderEye.renderToBuffer(poseStack, vertexConsumer2, light, OverlayTexture.NO_OVERLAY);

        immediate.endBatch();
    }


    @Override
    public int getLightColor(float f) {
        int i = 240;
        float g = (float)this.age / (float)this.lifetime;
        g *= g;
        g *= g;
        int j = i & 255;
        int k = 0;
        k += (int)(g * 15.0F * 16.0F);
        if (k > 240) {
            k = 240;
        }

        return j | k << 16;
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.CUSTOM;
    }

    @Environment(EnvType.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet spriteSet) {
            this.sprite = spriteSet;
        }

        public Particle createParticle(SimpleParticleType endPortalOption, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i) {
            EndPortalParticleOld particle = new EndPortalParticleOld(clientLevel, d, e, f);
            particle.pickSprite(this.sprite);
            particle.setAlpha(1.0F);
            return particle;
        }
    }
}
