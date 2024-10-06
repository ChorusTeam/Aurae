package net.yeoxuhang.ambiance.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.yeoxuhang.ambiance.config.AmbianceConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CauldronBlock.class)
public class CauldronBlockMixin {
    @Inject(method = "receiveStalactiteDrip", at = @At("RETURN"))
    private void receiveStalactiteDrip(BlockState blockState, Level level, BlockPos blockPos, Fluid fluid, CallbackInfo ci) {
        double x = blockPos.getX() + 0.5;
        double y = blockPos.getY() + 1.2;
        double z = blockPos.getZ() + 0.5;
        if (fluid == Fluids.WATER && AmbianceConfig.enableWaterCauldron) {
            level.addParticle(ParticleTypes.SPLASH, x, y, z, 0, 0.0, 0);
        } else if (fluid == Fluids.LAVA && AmbianceConfig.enableLavaCauldron) {
            level.addParticle(ParticleTypes.LAVA, x, y, z, 0, 0.0, 0);
        }
    }
}
