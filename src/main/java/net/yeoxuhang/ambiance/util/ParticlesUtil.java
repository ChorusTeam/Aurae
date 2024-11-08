package net.yeoxuhang.ambiance.util;

import net.minecraft.particles.IParticleData;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.yeoxuhang.ambiance.Ambiance;
import net.yeoxuhang.ambiance.client.AmbianceClient;
import net.yeoxuhang.ambiance.client.particle.ParticleRegistry;
import net.yeoxuhang.ambiance.client.particle.option.AshOption;
import net.yeoxuhang.ambiance.client.particle.option.ColorParticleOption;
import net.yeoxuhang.ambiance.config.AmbianceConfig;

import java.util.Objects;
import java.util.Random;

public class ParticlesUtil {

    public static boolean isBiome(World world, BlockPos pos, RegistryKey<Biome> biome) {
        ResourceLocation biomeKey = world.getBiome(pos).getRegistryName();

        return biomeKey.equals(biome.getRegistryName());
    }

    public static void spawnParticleBelow(World pLevel, BlockPos pPos, Random pRandom, IParticleData pParticle) {
        double d0 = (double)pPos.getX() + pRandom.nextDouble();
        double d1 = (double)pPos.getY() - 0.05;
        double d2 = (double)pPos.getZ() + pRandom.nextDouble();
        pLevel.addParticle(pParticle, d0, d1, d2, 0.0, 0.0, 0.0);
    }

    public static void endremEyePlace(World level, BlockPos pos){
        AmbianceClient.schedule(level, 1, (clientWorld) -> {
            if (pos != null) {
                String endRemasteredEye = Objects.requireNonNull(NbtGetter.endrem$getEyeType(level, pos));
                int o;
                Ambiance.LOGGER_DEBUG.debug("End Remastered Eye Type: " + endRemasteredEye);
                for (o = 0; o < 4; ++o) {
                    double p = pos.getY() + (1 - level.random.nextDouble()) + 0.6;
                    VoxelShape voxelShape = level.getBlockState(pos).getShape(level, pos);
                    Vector3d vec3 = voxelShape.bounds().getCenter();
                    double d = (double) pos.getX() + vec3.x;
                    double e = (double) pos.getZ() + vec3.z;
                    if (Ambiance.config.blocks.endPortalFrame.ashType == AmbianceConfig.ambiance$type.FANCY){
                        level.addAlwaysVisibleParticle(AshOption.create(20 + level.random.nextInt(10), Ambiance.config.blocks.endPortalFrame.particleSize / 10, 0.5F, 0F, MthHelper.convertHexToDec(TextureColorGetter.getHexColorFromTexture("endrem", "textures/block/eyes/" + endRemasteredEye + ".png", 12, 1)), 0.7F), d + level.random.nextDouble() / 5.0, p, e + level.random.nextDouble() / 5.0, 0.0, 0.0, 0.0);
                    } else if (Ambiance.config.blocks.endPortalFrame.ashType == AmbianceConfig.ambiance$type.VANILLA){
                        double d11;
                        double d16;
                        double d21;
                        d11 = (double)pos.getX() + (5.0 + level.random.nextDouble() * 6.0) / 16.0;
                        d16 = (double)pos.getY() + 0.8125;
                        d21 = (double)pos.getZ() + (5.0 + level.random.nextDouble() * 6.0) / 16.0;
                        level.addParticle(ColorParticleOption.create(ParticleRegistry.COLOR_ASH.get(), MthHelper.convertHexToDec(TextureColorGetter.getHexColorFromTexture("endrem", "textures/block/eyes/" + endRemasteredEye + ".png", 12, 1))), d11, d16, d21, 0,0,0);
                    }
                }
                if (Ambiance.config.blocks.endPortalFrame.enableParticle){
                    level.addAlwaysVisibleParticle(ColorParticleOption.create(ParticleRegistry.ENDER_EYE_PLACE.get(), MthHelper.convertHexToDec(TextureColorGetter.getHexColorFromTexture("endrem", "textures/block/eyes/" + endRemasteredEye + ".png", 12, 1))), pos.getX() + 0.5, pos.getY() + 1.075, pos.getZ() + 0.5, 0.0, 0.0, 0.0);
                }
            }
        });
    }
}