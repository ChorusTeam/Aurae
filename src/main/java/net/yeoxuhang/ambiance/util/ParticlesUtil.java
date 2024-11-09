package net.yeoxuhang.ambiance.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yeoxuhang.ambiance.Ambiance;
import net.yeoxuhang.ambiance.client.AmbianceClient;
import net.yeoxuhang.ambiance.client.particle.ParticleRegistry;
import net.yeoxuhang.ambiance.client.particle.option.AshOption;
import net.yeoxuhang.ambiance.client.particle.option.ColorParticleOption;
import net.yeoxuhang.ambiance.config.AmbianceConfig;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public class ParticlesUtil {

    public static boolean isBiome(Level world, BlockPos pos, ResourceKey<Biome> biome) {
        // Get the biome at the specified position
        Biome biomeAtPos = world.getBiome(pos);
        // Retrieve the key for the biome
        Optional<ResourceKey<Biome>> biomeKey = world.registryAccess().registryOrThrow(Registry.BIOME_REGISTRY).getResourceKey(biomeAtPos);
        // Compare the biome keys
        return biomeKey.isPresent() && biomeKey.get().equals(biome);
    }

    public static void spawnParticleBelow(Level level, BlockPos pos, Random random, ParticleOptions particle) {
        double x = (double) pos.getX() + random.nextDouble();
        double y = (double) pos.getY() - 0.05;
        double z = (double) pos.getZ() + random.nextDouble();
        level.addParticle(particle, x, y, z, 0.0, 0.0, 0.0);
    }

    public static void enderEyePlace(Level level, BlockPos pos) {
        AmbianceClient.schedule(level, 1, (clientLevel) -> {
            if (pos != null) {
                String enderEye = Objects.requireNonNull(NbtGetter.getBlockStateProperty(level, pos, "eye")).replace("_eye", "");
                System.out.println(enderEye);

                int o;
                Ambiance.LOGGER_DEBUG.debug("End Remastered Eye Type: " + enderEye);
                for (o = 0; o < 4; ++o) {
                    double p = pos.getY() + (1 - level.random.nextDouble()) + 0.6;
                    VoxelShape voxelShape = level.getBlockState(pos).getShape(level, pos);
                    Vec3 vec3 = voxelShape.bounds().getCenter();
                    double d = (double) pos.getX() + vec3.x;
                    double e = (double) pos.getZ() + vec3.z;

                    if (Ambiance.config.blocks.endPortalFrame.ashType == AmbianceConfig.ambiance$type.FANCY) {
                        level.addAlwaysVisibleParticle(
                                AshOption.create(20 + level.random.nextInt(10),
                                        Ambiance.config.blocks.endPortalFrame.particleSize / 10, 0.5F, 0F,
                                        MthHelper.convertHexToDec(TextureColorGetter.getHexColorFromTexture("endrem",
                                                "textures/blocks/eyes/" + enderEye + ".png",
                                                7, 5)),
                                        0.7F),
                                d + level.random.nextDouble() / 5.0, p, e + level.random.nextDouble() / 5.0, 0.0, 0.0, 0.0
                        );
                    } else if (Ambiance.config.blocks.endPortalFrame.ashType == AmbianceConfig.ambiance$type.VANILLA) {
                        double x = pos.getX() + (5.0 + level.random.nextDouble() * 6.0) / 16.0;
                        double y = pos.getY() + 0.8125;
                        double z = pos.getZ() + (5.0 + level.random.nextDouble() * 6.0) / 16.0;
                        level.addParticle(ColorParticleOption.create(
                                        ParticleRegistry.COLOR_ASH,
                                        MthHelper.convertHexToDec(TextureColorGetter.getHexColorFromTexture("endrem",
                                                "textures/blocks/eyes/" + enderEye + ".png",
                                                7, 5))),
                                x, y, z, 0.0, 0.0, 0.0
                        );
                    }
                }

                if (Ambiance.config.blocks.endPortalFrame.enableParticle) {
                    level.addAlwaysVisibleParticle(
                            ColorParticleOption.create(ParticleRegistry.ENDER_EYE_PLACE,
                                    MthHelper.convertHexToDec(TextureColorGetter.getHexColorFromTexture("endrem",
                                            "textures/blocks/eyes/" + enderEye + ".png",
                                            7, 5))),
                            pos.getX() + 0.5, pos.getY() + 1.075, pos.getZ() + 0.5, 0.0, 0.0, 0.0
                    );
                }
            }
        });
    }
}
