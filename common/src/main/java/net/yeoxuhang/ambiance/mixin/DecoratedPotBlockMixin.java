package net.yeoxuhang.ambiance.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DecoratedPotBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.yeoxuhang.ambiance.Ambiance;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DecoratedPotBlock.class)
public class DecoratedPotBlockMixin extends Block {
    @Shadow @Final private static BooleanProperty WATERLOGGED;

    public DecoratedPotBlockMixin(Properties properties) {
        super(properties);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        super.animateTick(blockState, level, blockPos, randomSource);
        double d = blockPos.getX();
        double e = blockPos.getY();
        double f = blockPos.getZ();
        if (blockState.getBlock() instanceof DecoratedPotBlock && blockState.getValue(WATERLOGGED) && randomSource.nextInt(200) == 0 && Ambiance.config.blocks.decoratedPot.enableParticle) {
            for(int i = 0; i < 3; ++i) {
                level.addAlwaysVisibleParticle(ParticleTypes.BUBBLE_COLUMN_UP, (double)blockPos.getX() + 0.5, (double)blockPos.getY() + 1.25, (double)blockPos.getZ() + 0.5, 0.0, 0.0, 0.0);
            }
            if (Ambiance.config.blocks.decoratedPot.enableSound){
                level.playLocalSound(d, e, f, SoundEvents.BUBBLE_COLUMN_UPWARDS_AMBIENT, SoundSource.BLOCKS, Ambiance.config.blocks.decoratedPot.soundVolume, 0.9f + randomSource.nextFloat() * 0.15f, false);
            }
        }
    }
}
