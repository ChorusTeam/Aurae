package net.yeoxuhang.ambiance.mixin.client;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MultifaceBlock;
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

import java.util.function.Supplier;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin implements ResourceManagerReloadListener, AutoCloseable{
    @Shadow @Nullable private ClientLevel level;


    @WrapWithCondition(method = "levelEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V", ordinal = 2))
    public boolean ambiance$levelEvent(ClientLevel instance, ParticleOptions particleOptions, double d, double e, double f, double g, double h, double i) {
        return Ambiance.config.blocks.endPortalFrame.ashType == AmbianceConfig.ambiance$type.VANILLA;
    }

    @WrapWithCondition(method = "levelEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientLevel;playLocalSound(Lnet/minecraft/core/BlockPos;Lnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FFZ)V", ordinal = 37))
    public boolean ambiance$endPortalFrameSound(ClientLevel instance, BlockPos pos, SoundEvent soundEvent, SoundSource soundSource, float v, float v1, boolean b) {
        return Ambiance.config.blocks.endPortalFrame.soundType == AmbianceConfig.ambiance$type.VANILLA;
    }

    @WrapWithCondition(method = "levelEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/LevelRenderer;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V", ordinal = 3))
    public <T extends ParticleOptions> boolean ambiance$levelEvent$EyeOfEnderPop(LevelRenderer instance, T particleOptions, double d, double e, double f, double g, double h, double i) {
        return Ambiance.config.items.eyeOfEnder.eyeOfEnderType == AmbianceConfig.ambiance$type.VANILLA;
    }
    @WrapWithCondition(method = "levelEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/LevelRenderer;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V", ordinal = 4))
    public <T extends ParticleOptions> boolean ambiance$levelEvent$EyeOfEnderPop1(LevelRenderer instance, T particleOptions, double d, double e, double f, double g, double h, double i) {
        return Ambiance.config.items.eyeOfEnder.eyeOfEnderType == AmbianceConfig.ambiance$type.VANILLA;
    }

    @Inject(method = "levelEvent", at = @At("HEAD"))
    public void ambiance$levelEvent(int i, BlockPos blockPos, int j, CallbackInfo ci) {
        assert this.level != null;
        RandomSource randomSource = this.level.random;
        int o;
        switch (i) {
            case 1503:
                if (Ambiance.config.blocks.endPortalFrame.soundType == AmbianceConfig.ambiance$type.FANCY){
                    this.level.playLocalSound(blockPos, SoundRegistry.ENDER_EYE_PLACED.get(), SoundSource.BLOCKS, Ambiance.config.blocks.endPortalFrame.soundVolume, 1.0F, false);
                }
                if (level.getBlockState(blockPos.above()).isAir()){
                    if (Ambiance.isModLoaded("endrem").get() && Ambiance.config.otherMods.endremCompat){
                        ParticlesUtil.endremEyePlace(level, blockPos);
                    } else {
                        if (Ambiance.config.blocks.endPortalFrame.enableParticle){
                            this.level.addAlwaysVisibleParticle(ColorParticleOption.create(ParticleRegistry.ENDER_EYE_PLACE.get(), MthHelper.convertHexToDec(Ambiance.config.blocks.endPortalFrame.particleColor)), blockPos.getX() + 0.5, blockPos.getY() + 1.075, blockPos.getZ() + 0.5, 0.0, 0.0, 0.0);
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
                                this.level.addParticle(ColorParticleOption.create(ParticleRegistry.COLOR_ASH.get(), MthHelper.convertHexToDec(Ambiance.config.blocks.endPortalFrame.particleColor)), d11, d16, d21, 0.0, 0.0, 0.0);
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
                        level.addAlwaysVisibleParticle(TrialOption.create(ParticleRegistry.PORTAL.get() ,(int)(Math.random() * 10.0) + 40, 1F, 0.01F,0.1F, MthHelper.randomDarkerColor("CC00FA") , 1F), d + Math.cos(g) * 5.0, e - 0.4, f + Math.sin(g) * 5.0, Math.cos(g) * -5.0, 0.0, Math.sin(g) * -5.0);
                        level.addAlwaysVisibleParticle(TrialOption.create(ParticleRegistry.PORTAL.get() ,(int)(Math.random() * 10.0) + 40, 1F, 0.01F,0.1F, MthHelper.randomDarkerColor("CC00FA") , 1F), d + Math.cos(g) * 5.0, e - 0.4, f + Math.sin(g) * 5.0, Math.cos(g) * -7.0, 0.0, Math.sin(g) * -7.0);
                    }
                }
                break;

            case 3006:
                int u = j >> 6;
                if (Ambiance.config.blocks.sculkSpread.enableParticle){
                    if (u > 0) {
                        if (randomSource.nextFloat() < 0.3f + (float)u * 0.1f) {
                            float n = 0.15f + 0.02f * (float)u * (float)u * randomSource.nextFloat();
                            float y = 0.4f + 0.3f * (float)u * randomSource.nextFloat();
                            this.level.playLocalSound(blockPos, SoundEvents.SCULK_BLOCK_CHARGE, SoundSource.BLOCKS, n, y, false);
                        }
                        byte b = (byte)(j & 0x3F);
                        UniformInt intProvider = UniformInt.of(0, u);
                        Supplier<Vec3> supplier = () -> new Vec3(Mth.nextDouble(randomSource, -0.005f, 0.005f), Mth.nextDouble(randomSource, -0.005f, 0.005f), Mth.nextDouble(randomSource, -0.005f, 0.005f));
                        if (b == 0) {
                            for (Direction direction : Direction.values()) {
                                double r = direction.getAxis() == Direction.Axis.Y ? 0.65 : 0.57;
                                ParticleUtils.spawnParticlesOnBlockFace(this.level, blockPos.above(), new BlockParticleOption(ParticleTypes.BLOCK, Blocks.SCULK.defaultBlockState()), intProvider, direction, supplier, r);
                            }
                        } else {
                            for (Direction direction2 : MultifaceBlock.unpack(b)) {
                                double q = 0.35;
                                ParticleUtils.spawnParticlesOnBlockFace(this.level, blockPos.above(), new BlockParticleOption(ParticleTypes.BLOCK, Blocks.SCULK.defaultBlockState()), intProvider, direction2, supplier, q);

                            }
                        }
                    }
                }
                break;
        }

    }
}
