package net.yeoxuhang.ambiance.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.SoulSandBlock;
import net.minecraft.block.material.Material;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biomes;
import net.yeoxuhang.ambiance.Ambiance;
import net.yeoxuhang.ambiance.util.ParticlesUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Random;

@Mixin(SoulSandBlock.class)
public class SoulSandBlockMixin extends Block {
    public SoulSandBlockMixin(Properties properties) {
        super(properties);
    }

    public void animateTick(BlockState blockState, World level, BlockPos blockPos, Random randomSource) {
        super.animateTick(blockState, level, blockPos, randomSource);
        if(blockState.getBlock() instanceof SoulSandBlock && randomSource.nextInt(1000) == 1 && Ambiance.config.blocks.soulSand.enableParticle && ParticlesUtil.isBiome(level, blockPos, Biomes.SOUL_SAND_VALLEY)) {
            double x = blockPos.getX() + randomSource.nextDouble();
            double y = blockPos.getY() + 1.2;
            double z = blockPos.getZ() + randomSource.nextDouble();
            level.addAlwaysVisibleParticle(ParticleTypes.SOUL, x, y, z, 0, 0, 0);
        }
        if (randomSource.nextInt(16) == 0 && isFree(level.getBlockState(blockPos.below())) && Ambiance.config.blocks.soulSand.enableParticle) {
            ParticlesUtil.spawnParticleBelow(level, blockPos, randomSource, new BlockParticleData(ParticleTypes.FALLING_DUST, blockState));
        }
    }

    @Unique
    private static boolean isFree(BlockState p_185759_0_) {
        Material material = p_185759_0_.getMaterial();
        return p_185759_0_.isAir() || p_185759_0_.is(BlockTags.FIRE) || material.isLiquid() || material.isReplaceable();
    }
}
