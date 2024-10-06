package net.yeoxuhang.ambiance.client.particle;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.yeoxuhang.ambiance.Ambiance;
import net.yeoxuhang.ambiance.client.model.EnderEyeModel;
import org.joml.Quaternionf;

public class EnderEyeParticle extends TextureSheetParticle {
    private int heartAnimation;
    private int heartAnimationO;
    EnderEyeModel enderEye;
    RenderType renderType;
    ResourceLocation renderEyeLayer = new ResourceLocation(Ambiance.MOD_ID, "textures/particle/ender_eye.png");

    EnderEyeParticle(ClientLevel clientLevel, double d, double e, double f) {
        super(clientLevel, d, e, f, 0.0, 0.0, 0.0);
        this.enderEye = new EnderEyeModel(Minecraft.getInstance().getEntityModels().bakeLayer(EnderEyeModel.LAYER_LOCATION));
        this.quadSize = 0.85F;
        this.lifetime = 50;
        this.gravity = -0.5F;
        this.xd = 0.0;
        this.yd = 0.0;
        this.zd = 0.0;
    }

    public float getQuadSize(float f) {
        return this.quadSize * Mth.clamp(((float)this.age + f) / (float)this.lifetime * 0.5F, 0.0F, 1.0F);
    }

    public void render(VertexConsumer vertexConsumer, Camera camera, float f) {
        /*this.alpha = 1.0F - Mth.clamp(((float)this.age + f) / (float)this.lifetime, 0.0F, 1.0F);

        this.renderType = RenderType.entityTranslucent(renderEyeLayer);

        Vec3 vec3d = camera.getPosition();
        float x = (float) (Mth.lerp(f, this.xo, this.x) - vec3d.x);
        float y = (float) (Mth.lerp(f, this.yo, this.y) - vec3d.y);
        float z = (float) (Mth.lerp(f, this.zo, this.z) - vec3d.z);

        PoseStack poseStack = new PoseStack();
        poseStack.translate(x, y, z);
        poseStack.translate(0, 0.8, 0);
        MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
        VertexConsumer vertexConsumer2 = bufferSource.getBuffer(renderType);

        float m = this.getHeartAnimation(f * 0.00003F);
        int n = FastColor.ARGB32.color(Mth.floor(m * 255.0F), 255, 255, 255);
        int light = this.getLightColor(f);
        this.enderEye.renderToBuffer(poseStack, vertexConsumer2, light, OverlayTexture.NO_OVERLAY, n);

        bufferSource.endBatch();*/

        this.alpha = 1.0F - Mth.clamp(((float)this.age + f) / (float)this.lifetime, 0.0F, 1.0F);
        Quaternionf quaternionf = new Quaternionf();
        quaternionf.rotationX(-1.0472F);
        this.renderRotatedQuad(vertexConsumer, camera, quaternionf, f);
        quaternionf.rotationYXZ(-3.1415927F, 1.0472F, 0.0F);
        this.renderRotatedQuad(vertexConsumer, camera, quaternionf, f);
    }

    @Override
    public int getLightColor(float f) {
        int i = 255;
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

    @Override
    public void tick() {
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else if (age == 1){
            this.heartAnimation = 0;
        }
        this.heartAnimationO = this.heartAnimation;
        if (this.heartAnimation < 25) {
            ++this.heartAnimation;
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
            EnderEyeParticle particle = new EnderEyeParticle(clientLevel, d, e, f);
            particle.pickSprite(this.sprite);
            particle.setAlpha(1.0F);
            return particle;
        }
    }

    public float getHeartAnimation(float f) {
        return Mth.lerp(f, (float)this.heartAnimationO, (float)this.heartAnimation) / 0.1F;
    }

}
