package net.yeoxuhang.ambiance.mixin.client;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import com.mojang.math.Vector3d;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yeoxuhang.ambiance.Ambiance;
import net.yeoxuhang.ambiance.client.SoundRegistry;
import net.yeoxuhang.ambiance.client.particle.ParticleRegistry;
import net.yeoxuhang.ambiance.client.particle.option.AshOption;
import net.yeoxuhang.ambiance.client.particle.option.ColorParticleOption;
import net.yeoxuhang.ambiance.client.particle.option.TrialOption;
import net.yeoxuhang.ambiance.config.AmbianceConfig;
import net.yeoxuhang.ambiance.util.MthHelper;
import net.yeoxuhang.ambiance.util.ParticlesUtil;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin implements ResourceManagerReloadListener, AutoCloseable{
    @Shadow @Nullable
    private ClientLevel level;


    @WrapWithCondition(method = "levelEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V", ordinal = 2))
    public boolean ambiance$levelEvent(ClientLevel instance, ParticleOptions p_195594_1_, double p_195594_2_, double p_195594_4_, double p_195594_6_, double p_195594_8_, double p_195594_10_, double p_195594_12_) {
        return Ambiance.config.blocks.endPortalFrame.ashType == AmbianceConfig.ambiance$type.VANILLA;
    }

    @WrapWithCondition(method = "levelEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;playLocalSound(Lnet/minecraft/core/BlockPos;Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FFZ)V", ordinal = 42))
    public boolean ambiance$endPortalFrameSound(ClientLevel instance, BlockPos p_184156_1_, SoundEvent p_184156_2_, SoundSource p_184156_3_, float p_184156_4_, float p_184156_5_, boolean p_184156_6_) {
        return Ambiance.config.blocks.endPortalFrame.soundType == AmbianceConfig.ambiance$type.VANILLA;
    }

    @WrapWithCondition(method = "levelEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/LevelRenderer;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V", ordinal = 3))
    public <T extends ParticleOptions> boolean ambiance$levelEvent$EyeOfEnderPop(LevelRenderer instance, T p_195467_1_, double p_195467_2_, double p_195467_4_, double p_195467_6_, double p_195467_8_, double p_195467_10_, double p_195467_12_) {
        return Ambiance.config.items.eyeOfEnder.eyeOfEnderType == AmbianceConfig.ambiance$type.VANILLA;
    }
    @WrapWithCondition(method = "levelEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/LevelRenderer;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V", ordinal = 4))
    public <T extends ParticleOptions> boolean ambiance$levelEvent$EyeOfEnderPop1(LevelRenderer instance, T p_195467_1_, double p_195467_2_, double p_195467_4_, double p_195467_6_, double p_195467_8_, double p_195467_10_, double p_195467_12_) {
        return Ambiance.config.items.eyeOfEnder.eyeOfEnderType == AmbianceConfig.ambiance$type.VANILLA;
    }

    @Inject(method = "levelEvent", at = @At("HEAD"))
    public void ambiance$levelEvent(Player p_180439_1_ , int i, BlockPos blockPos, int j, CallbackInfo ci) {
        assert this.level != null;
        Random randomSource = this.level.random;
        int o;
        switch (i) {
            case 1503:
                if (Ambiance.config.blocks.endPortalFrame.soundType == AmbianceConfig.ambiance$type.FANCY){
                    this.level.playLocalSound(blockPos, SoundRegistry.ENDER_EYE_PLACED, SoundSource.BLOCKS, Ambiance.config.blocks.endPortalFrame.soundVolume, 1.0F, false);
                }
                if (level.getBlockState(blockPos.above()).isAir()){
                    if (Ambiance.isModLoaded("endrem") && Ambiance.config.otherMods.endremCompat){
                        ParticlesUtil.enderEyePlace(level, blockPos);
                    } else {
                        if (Ambiance.config.blocks.endPortalFrame.enableParticle){
                            this.level.addAlwaysVisibleParticle(ColorParticleOption.create(ParticleRegistry.ENDER_EYE_PLACE, MthHelper.convertHexToDec(Ambiance.config.blocks.endPortalFrame.particleColor)), blockPos.getX() + 0.5, blockPos.getY() + 1.075, blockPos.getZ() + 0.5, 0.0, 0.0, 0.0);
                        }
                        for(o = 0; o < 4; ++o) {
                            double p = blockPos.getY() + (1 - randomSource.nextDouble()) + 0.6;
                            VoxelShape voxelShape = level.getBlockState(blockPos).getShape(level, blockPos);
                            Vec3 vec3 = voxelShape.bounds().getCenter();
                            double d = (double)blockPos.getX() + vec3.x;
                            double e = (double)blockPos.getZ() + vec3.z;
                            if (Ambiance.config.blocks.endPortalFrame.ashType == AmbianceConfig.ambiance$type.FANCY){
                                this.level.addAlwaysVisibleParticle(AshOption.create(20 + randomSource.nextInt(10), Ambiance.config.blocks.endPortalFrame.particleSize / 10, 0.5F, 0F, MthHelper.convertHexToDec(Ambiance.config.blocks.endPortalFrame.particleColor), 0.7F), d + randomSource.nextDouble() / 5.0, p, e + randomSource.nextDouble() / 5.0, 0.0, 0.0, 0.0);

                            } else if (Ambiance.config.blocks.endPortalFrame.ashType == AmbianceConfig.ambiance$type.VANILLA){
                                double d11;
                                double d16;
                                double d21;
                                d11 = (double)blockPos.getX() + (5.0 + randomSource.nextDouble() * 6.0) / 16.0;
                                d16 = (double)blockPos.getY() + 0.8125;
                                d21 = (double)blockPos.getZ() + (5.0 + randomSource.nextDouble() * 6.0) / 16.0;
                                this.level.addParticle(ColorParticleOption.create(ParticleRegistry.COLOR_ASH, MthHelper.convertHexToDec(Ambiance.config.blocks.endPortalFrame.particleColor)), d11, d16, d21, 0.0, 0.0, 0.0);
                            }
                        }
                    }
                }
                break;
            case 2003:
                if (Ambiance.config.items.eyeOfEnder.eyeOfEnderType == AmbianceConfig.ambiance$type.FANCY){
                    double d = (double)blockPos.getX() + 0.5;
                    double e = blockPos.getY();
                    double f = (double)blockPos.getZ() + 0.5;
                    for (double g = 0.0; g < Math.PI * 2; g += 0.15707963267948966) {
                        level.addAlwaysVisibleParticle(TrialOption.create(ParticleRegistry.PORTAL ,(int)(Math.random() * 10.0) + 40, 1F, 0.01F,0.1F, MthHelper.randomDarkerColor("CC00FA") , 1F), d + Math.cos(g) * 5.0, e - 0.4, f + Math.sin(g) * 5.0, Math.cos(g) * -5.0, 0.0, Math.sin(g) * -5.0);
                        level.addAlwaysVisibleParticle(TrialOption.create(ParticleRegistry.PORTAL ,(int)(Math.random() * 10.0) + 40, 1F, 0.01F,0.1F, MthHelper.randomDarkerColor("CC00FA") , 1F), d + Math.cos(g) * 5.0, e - 0.4, f + Math.sin(g) * 5.0, Math.cos(g) * -7.0, 0.0, Math.sin(g) * -7.0);
                    }
                }
                break;
        }

    }
}
