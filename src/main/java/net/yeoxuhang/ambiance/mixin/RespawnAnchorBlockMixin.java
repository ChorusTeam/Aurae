package net.yeoxuhang.ambiance.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RespawnAnchorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.yeoxuhang.ambiance.Ambiance;
import net.yeoxuhang.ambiance.client.particle.ParticleRegistry;
import net.yeoxuhang.ambiance.client.particle.option.TrialOption;
import net.yeoxuhang.ambiance.config.AmbianceConfig;
import net.yeoxuhang.ambiance.util.MthHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

import static net.minecraft.world.level.block.RespawnAnchorBlock.CHARGE;

@Mixin(RespawnAnchorBlock.class)
public class RespawnAnchorBlockMixin {

    @Inject(method = "animateTick", at = @At("RETURN"))
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, Random randomSource, CallbackInfo ci) {
        if (Ambiance.config.blocks.respawnAnchor.portalType == AmbianceConfig.ambiance$type.FANCY){
            double d0 = (double)blockPos.getX() + 0.5D + (0.5D - randomSource.nextDouble());
            double d1 = (double)blockPos.getY() + 1.0D;
            double d2 = (double)blockPos.getZ() + 0.5D + (0.5D - randomSource.nextDouble());
            double d3 = (double)randomSource.nextFloat() * 0.04D;
            if (blockState.getValue(CHARGE) != 0) {
                level.addParticle(TrialOption.create(ParticleRegistry.REVERSE_PORTAL ,(int)(Math.random() * 10.0) + 40, 1F, 0.01F,Ambiance.config.blocks.respawnAnchor.particleSize/ 10, MthHelper.randomDarkerColor("4501B5") , 1F), d0, d1, d2, 0.0D, d3, 0.0D);
            }
        }
    }

    @ModifyArg(method = "animateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
    public ParticleOptions animateTick(ParticleOptions p_195594_1_) {
        if (Ambiance.config.blocks.respawnAnchor.portalType == AmbianceConfig.ambiance$type.VANILLA){
            return ParticleTypes.REVERSE_PORTAL;
        } else if (Ambiance.config.blocks.respawnAnchor.portalType == AmbianceConfig.ambiance$type.FANCY){
            return ParticleRegistry.AIR;
        } else return ParticleRegistry.AIR;
    }
}
