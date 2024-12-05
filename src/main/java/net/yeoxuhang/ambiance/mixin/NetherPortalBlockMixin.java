package net.yeoxuhang.ambiance.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.NetherPortalBlock;
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

@Mixin(NetherPortalBlock.class)
public class NetherPortalBlockMixin extends Block {

    public NetherPortalBlockMixin(Properties p_i48440_1_) {
        super(p_i48440_1_);
    }

    @Inject(method = "animateTick", at = @At("RETURN"))
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, Random randomSource, CallbackInfo ci) {
        if (Ambiance.config.blocks.netherPortal.portalType == AmbianceConfig.ambiance$type.FANCY){
            for(int i = 0; i < 4; ++i) {
                double d0 = (double)blockPos.getX() + randomSource.nextDouble();
                double d1 = (double)blockPos.getY() + randomSource.nextDouble();
                double d2 = (double)blockPos.getZ() + randomSource.nextDouble();
                double d3 = ((double)randomSource.nextFloat() - 0.5D) * 0.5D;
                double d4 = ((double)randomSource.nextFloat() - 0.5D) * 0.5D;
                double d5 = ((double)randomSource.nextFloat() - 0.5D) * 0.5D;
                int j = randomSource.nextInt(2) * 2 - 1;
                if (!level.getBlockState(blockPos.west()).is(this) && !level.getBlockState(blockPos.east()).is(this)) {
                    d0 = (double)blockPos.getX() + 0.5D + 0.25D * (double)j;
                    d3 = randomSource.nextFloat() * 2.0F * (float)j;
                } else {
                    d2 = (double)blockPos.getZ() + 0.5D + 0.25D * (double)j;
                    d5 = randomSource.nextFloat() * 2.0F * (float)j;
                }

                level.addAlwaysVisibleParticle(TrialOption.create(ParticleRegistry.PORTAL ,(int)(Math.random() * 10.0) + 40, 1F, 0.01F,Ambiance.config.blocks.netherPortal.particleSize/10, MthHelper.randomDarkerColor("CC00FA") , 1F), d0, d1, d2, d3, d4, d5);
            }
        }

    }
    @ModifyArg(method = "animateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
    public ParticleOptions animateTick(ParticleOptions p_195594_1_) {
        if (Ambiance.config.blocks.netherPortal.portalType == AmbianceConfig.ambiance$type.VANILLA){
            return ParticleTypes.PORTAL;
        } else if (Ambiance.config.blocks.netherPortal.portalType == AmbianceConfig.ambiance$type.FANCY){
            return ParticleRegistry.AIR;
        } else return ParticleRegistry.AIR;
    }
}
