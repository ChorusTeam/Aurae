package net.yeoxuhang.ambiance.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yeoxuhang.ambiance.Ambiance;
import net.yeoxuhang.ambiance.client.AmbianceClient;
import net.yeoxuhang.ambiance.client.particle.ParticleRegistry;
import net.yeoxuhang.ambiance.client.particle.option.AshOption;
import net.yeoxuhang.ambiance.config.AmbianceConfig;

import java.util.Objects;

public class ParticlesUtil {

    public static void endremEyePlace(Level level, BlockPos pos){
        AmbianceClient.schedule(level, 1, (clientWorld) -> {
            if (pos != null) {
                String endRemasteredEye = Objects.requireNonNull(NbtGetter.endrem$getEyeType(level, pos));
                int o;
                Ambiance.LOGGER_DEBUG.debug("End Remastered Eye Type: " + endRemasteredEye);
                for (o = 0; o < 4; ++o) {
                    double p = pos.getY() + (1 - level.random.nextDouble()) + 0.6;
                    VoxelShape voxelShape = level.getBlockState(pos).getShape(level, pos);
                    Vec3 vec3 = voxelShape.bounds().getCenter();
                    double d = (double) pos.getX() + vec3.x;
                    double e = (double) pos.getZ() + vec3.z;
                    level.addParticle(AshOption.create(20 + level.random.nextInt(10), AmbianceConfig.enderEyePlaceSize / 10, 0.5F, 0F, MthHelper.convertHexToDec(TextureColorGetter.getHexColorFromTexture("endrem", "textures/block/eyes/" + endRemasteredEye + ".png", 12, 1)), 0.7F), d + level.random.nextDouble() / 5.0, p, e + level.random.nextDouble() / 5.0, 0.0, 0.0, 0.0);
                }
                level.addAlwaysVisibleParticle(ColorParticleOption.create(ParticleRegistry.ENDER_EYE_PLACE, MthHelper.convertHexToDec(TextureColorGetter.getHexColorFromTexture("endrem", "textures/block/eyes/" + endRemasteredEye + ".png", 12, 1))), pos.getX() + 0.5, pos.getY() + 1.075, pos.getZ() + 0.5, 0.0, 0.0, 0.0);
            }
        });
    }
}
