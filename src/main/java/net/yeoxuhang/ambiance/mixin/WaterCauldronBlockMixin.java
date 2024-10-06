package net.yeoxuhang.ambiance.mixin;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LavaCauldronBlock;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.yeoxuhang.ambiance.config.AmbianceConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LayeredCauldronBlock.class)
public class WaterCauldronBlockMixin {

    @Inject(method = "entityInside", at = @At("RETURN"))
    protected void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity, CallbackInfo ci) {
        RandomSource randomSource = level.random;
        double x = blockPos.getX() + randomSource.nextDouble();
        double y = blockPos.getY() + 1.2;
        double z = blockPos.getZ() + randomSource.nextDouble();

        if (entity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D && randomSource.nextInt(2) == 1){
            if (AmbianceConfig.enableWaterCauldronSound){
                level.playLocalSound(x, y, z, SoundEvents.PLAYER_SPLASH, SoundSource.BLOCKS, 0.2f + randomSource.nextFloat() * 0.2f, 0.9f + randomSource.nextFloat() * 0.15f, false);
            }
            if (AmbianceConfig.enableWaterCauldron){
                level.addParticle(ParticleTypes.SPLASH, (double)blockPos.getX() + level.random.nextDouble(), blockPos.getY() + 1, (double)blockPos.getZ() + level.random.nextDouble(), 0, 0.0, 0);

            }
        }
        if (entity.getDeltaMovement().y > 0){
            if (AmbianceConfig.enableWaterCauldronSound){
                level.playLocalSound(x, y, z, SoundEvents.PLAYER_SPLASH, SoundSource.BLOCKS, 0.2f + randomSource.nextFloat() * 0.2f, 0.9f + randomSource.nextFloat() * 0.15f, false);
            }
            if (AmbianceConfig.enableWaterCauldron){
                level.addParticle(ParticleTypes.SPLASH, (double)blockPos.getX() + level.random.nextDouble(), blockPos.getY() + 1, (double)blockPos.getZ() + level.random.nextDouble(), 0, 0.0, 0);
            }
        }

    }
}
