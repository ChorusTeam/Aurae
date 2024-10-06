package net.yeoxuhang.ambiance.mixin;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LavaCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.yeoxuhang.ambiance.config.AmbianceConfig;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LavaCauldronBlock.class)
public class LavaCauldronBlockMixin extends Block {

    public LavaCauldronBlockMixin(Properties properties) {
        super(properties);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        super.animateTick(blockState, level, blockPos, randomSource);
        double x = blockPos.getX() + randomSource.nextDouble();
        double y = blockPos.getY() + 1.2;
        double z = blockPos.getZ() + randomSource.nextDouble();
        if(blockState.getBlock() instanceof LavaCauldronBlock) {
            if (AmbianceConfig.enableLavaCauldron && randomSource.nextInt(10) == 1){
                level.addParticle(ParticleTypes.LAVA, x, y, z, 0, 0, 0);
            }
            if (AmbianceConfig.enableLavaCauldronSound && randomSource.nextInt(100) == 1){
                level.playLocalSound(x, y, z, SoundEvents.LAVA_POP, SoundSource.BLOCKS, 0.2f + randomSource.nextFloat() * 0.2f, 0.9f + randomSource.nextFloat() * 0.15f, false);
            }
        }
    }
}
