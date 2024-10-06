package net.yeoxuhang.ambiance.mixin.client;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.*;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yeoxuhang.ambiance.client.particle.ParticleRegistry;
import net.yeoxuhang.ambiance.client.particle.option.AshOption;
import net.yeoxuhang.ambiance.common.sound.SoundRegistry;
import net.yeoxuhang.ambiance.config.AmbianceConfig;
import net.yeoxuhang.ambiance.util.MthHelper;
import net.yeoxuhang.ambiance.util.VaultAndTrialSpawnerUtil;
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


    @Inject(method = "levelEvent", at = @At("HEAD"))
    public void ambiance$levelEvent(int i, BlockPos blockPos, int j, CallbackInfo ci) {
        assert this.level != null;
        RandomSource randomSource = this.level.random;
        int o;
        switch (i) {
            case 1503:
                if (AmbianceConfig.enableEyePlaceSound){
                    this.level.playLocalSound(blockPos, SoundRegistry.ENDER_EYE_PLACED, SoundSource.BLOCKS, 0.8F, 1.0F, false);
                }
                if (AmbianceConfig.enableEnderEyePlace){
                    if (level.getBlockState(blockPos.above()).isAir()){
                        this.level.addAlwaysVisibleParticle(ColorParticleOption.create(ParticleRegistry.ENDER_EYE_PLACE, MthHelper.convertHexToDec(AmbianceConfig.endPortalEyePlaced)), blockPos.getX() + 0.5, blockPos.getY() + 1.075, blockPos.getZ() + 0.5, 0.0, 0.0, 0.0);
                    }
                    for(o = 0; o < 4; ++o) {
                        double p = blockPos.getY() + (1 - randomSource.nextDouble()) + 0.6;
                        VoxelShape voxelShape = level.getBlockState(blockPos).getShape(level, blockPos);
                        Vec3 vec3 = voxelShape.bounds().getCenter();
                        double d = (double)blockPos.getX() + vec3.x;
                        double e = (double)blockPos.getZ() + vec3.z;
                        if (AmbianceConfig.enableEnderEyePlace){}
                        if (level.getBlockState(blockPos.above()).isAir()){
                            this.level.addParticle(AshOption.create(20 + randomSource.nextInt(10), AmbianceConfig.enderEyePlaceSize, 0.5F, 0F, MthHelper.convertHexToDec(AmbianceConfig.endPortalEyePlaced), 0.7F), d + randomSource.nextDouble() / 5.0, p, e + randomSource.nextDouble() / 5.0, 0.0, 0.0, 0.0);
                        }
                    }
                }
                break;
            case 3006:
                int u = j >> 6;
                if (AmbianceConfig.enableSculkCharge){
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
            case 3011:
                if (AmbianceConfig.enableSpawner){
                    for(int count = 0; count < 15; ++count) {
                        VaultAndTrialSpawnerUtil.addTrialSpawnerParticles(level, blockPos, randomSource, j);
                    }
                }
                break;
            case 3012:
                if (AmbianceConfig.enableSpawner){
                    for(int count = 0; count < 15; ++count) {
                        VaultAndTrialSpawnerUtil.addTrialSpawnerMobParticles(level, blockPos, randomSource, j);
                    }
                }
                break;
            case 3013:
                if (AmbianceConfig.enableSpawner){
                    for(int count = 0; count < 15; ++count) {
                        VaultAndTrialSpawnerUtil.addTrialSpawnerDetectPlayersParticles(level, blockPos, randomSource);
                    }
                }
                break;
            case 3014:
                if (AmbianceConfig.enableItemPop){
                    VaultAndTrialSpawnerUtil.addEjectItemParticles(level, blockPos);
                }
                break;
            case 3016:
                if (AmbianceConfig.enableVault){
                    VaultAndTrialSpawnerUtil.addFlamesParticles(level, blockPos, randomSource, j);
                }
                break;
            case 3017:
                if (AmbianceConfig.enableItemPop){
                    VaultAndTrialSpawnerUtil.addEjectItemParticles(level, blockPos);
                }
                break;
            case 3019:
                if (AmbianceConfig.enableSpawner){
                    for(int count = 0; count < 5; ++count) {
                        VaultAndTrialSpawnerUtil.addOminousTrialSpawnerDetectPlayersParticles(level, blockPos, randomSource);
                    }
                }
                break;
            case 3020:
                if (AmbianceConfig.enableSpawner){
                    for(int count = 0; count < 20; ++count) {
                        VaultAndTrialSpawnerUtil.addBecomeOminousParticles(level, blockPos, randomSource);
                    }
                }
                break;
        }

    }
}
