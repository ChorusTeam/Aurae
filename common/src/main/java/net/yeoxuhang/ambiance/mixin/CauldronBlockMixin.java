package net.yeoxuhang.ambiance.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.yeoxuhang.ambiance.Ambiance;
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
        if (fluid == Fluids.WATER && Ambiance.config.blocks.cauldron.water.enableParticle) {
            level.addAlwaysVisibleParticle(ParticleTypes.SPLASH, x, y, z, 0, 0.0, 0);
        } else if (fluid == Fluids.LAVA && Ambiance.config.blocks.cauldron.lava.enableParticle) {
            level.addAlwaysVisibleParticle(ParticleTypes.LAVA, x, y, z, 0, 0.0, 0);
        }
    }
}
