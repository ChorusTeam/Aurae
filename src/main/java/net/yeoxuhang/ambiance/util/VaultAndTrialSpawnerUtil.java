package net.yeoxuhang.ambiance.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.yeoxuhang.ambiance.client.particle.option.TrialOption;

public class VaultAndTrialSpawnerUtil {

    public static void addTrialSpawnerDetectPlayersParticles(Level level, BlockPos blockPos, RandomSource randomSource) {
        double d = (double)blockPos.getX() + 0.5 + (randomSource.nextDouble() - 0.5) * 2.0;
        double e = (double)blockPos.getY() + 0.5 + (randomSource.nextDouble() - 0.5) * 2.0;
        double f = (double)blockPos.getZ() + 0.5 + (randomSource.nextDouble() - 0.5) * 2.0;
        level.addParticle(TrialParticle.decode(0).particleType, d, e, f, 0.0, 0.0, 0.0);
    }

    public static void addOminousTrialSpawnerDetectPlayersParticles(Level level, BlockPos blockPos, RandomSource randomSource) {
        double d = (double)blockPos.getX() + 0.5 + (randomSource.nextDouble() - 0.5) * 2.0;
        double e = (double)blockPos.getY() + 0.5 + (randomSource.nextDouble() - 0.5) * 2.0;
        double f = (double)blockPos.getZ() + 0.5 + (randomSource.nextDouble() - 0.5) * 2.0;
        level.addParticle(TrialParticle.decode(1).particleType, d, e, f, 0.0, 0.0, 0.0);
    }

    public static void addTrialSpawnerMobParticles(Level level, BlockPos blockPos, RandomSource randomSource, int type) {
        double d = (double)blockPos.getX() + 0.5 + (randomSource.nextDouble() - 0.5) * 2.0;
        double e = (double)blockPos.getY() + 0.5 + (randomSource.nextDouble() - 0.5) * 2.0;
        double f = (double)blockPos.getZ() + 0.5 + (randomSource.nextDouble() - 0.5) * 2.0;
        level.addParticle(TrialParticle.decode(type).particleType, d, e, f, 0.0, 0.0, 0.0);
    }

    public static void addTrialSpawnerParticles(Level level, BlockPos blockPos, RandomSource randomSource, int type) {
        Vec3 vec3 = randomPosInsideCage(blockPos, randomSource);
        level.addParticle(TrialParticle.decode(type).particleType, vec3.x(), vec3.y() - 0.6, vec3.z(), 0.0, 0.0, 0.0);
    }

    public static void addBecomeOminousParticles(Level level, BlockPos blockPos, RandomSource randomSource) {
        double d = (double)blockPos.getX() + 0.5 + (randomSource.nextDouble() - 0.5) * 1.1;
        double e = (double)blockPos.getY() + 0.5 + (randomSource.nextDouble() - 0.5) * 1.1;
        double f = (double)blockPos.getZ() + 0.5 + (randomSource.nextDouble() - 0.5) * 1.1;
        double g = randomSource.nextGaussian() * 0.02;
        double h = randomSource.nextGaussian() * 0.02;
        double j = randomSource.nextGaussian() * 0.02;
        level.addParticle(TrialParticle.OMINOUS.particleType, d, e, f, g, h, j);
    }

    public static void addFlamesParticles(Level level, BlockPos blockPos, RandomSource randomSource, int type) {
        for(int count = 0; count < 10; ++count) {
            Vec3 vec3 = randomPosInsideCage(blockPos, randomSource);
            level.addParticle(FlameParticle.decode(type).particleType, vec3.x(), vec3.y() + 0.9, vec3.z(), 0.0, 0.0, 0.0);
        }
    }


    public static void addEjectItemParticles(Level level, BlockPos blockPos) {
        for(int count = 0; count < 8; ++count) {
            level.addParticle(ParticleTypes.DUST_PLUME,(double)blockPos.getX() + 0.5, (double)blockPos.getY() + 1.2, (double)blockPos.getZ() + 0.5, 0.0, 0.0, 0.0);
        }
    }


    public static Vec3 randomPosInsideCage(BlockPos blockPos, RandomSource randomSource) {
        return Vec3.atLowerCornerOf(blockPos).add(Mth.nextDouble(randomSource, 0.1, 0.9), Mth.nextDouble(randomSource, 0.25, 0.75), Mth.nextDouble(randomSource, 0.1, 0.9));
    }

    public enum TrialParticle {
        NORMAL(TrialOption.create((int)(Math.random() * 10.0) + 40, -0.1F, 0.01F,0.1F,14656027, 0.7F)),
        OMINOUS(TrialOption.create((int)(Math.random() * 10.0) + 40, -0.1F, 0.01F, 0.1F, 1290163, 0.7F));

        public final TrialOption particleType;

        TrialParticle(final TrialOption simpleParticleType) {
            this.particleType = simpleParticleType;
        }

        public static TrialParticle decode(int i) {
            TrialParticle[] trialParticles = values();
            return i <= trialParticles.length && i >= 0 ? trialParticles[i] : NORMAL;
        }

        public int encode() {
            return this.ordinal();
        }
    }

    public enum FlameParticle {
        NORMAL(ParticleTypes.FLAME),
        OMINOUS(ParticleTypes.SOUL_FIRE_FLAME);

        public final SimpleParticleType particleType;

        FlameParticle(final SimpleParticleType simpleParticleType) {
            this.particleType = simpleParticleType;
        }

        public static FlameParticle decode(int i) {
            FlameParticle[] flameParticles = values();
            return i <= flameParticles.length && i >= 0 ? flameParticles[i] : NORMAL;
        }

        public int encode() {
            return this.ordinal();
        }
    }
}
