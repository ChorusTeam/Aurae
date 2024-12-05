package net.yeoxuhang.ambiance.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.EndPortalBlock;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.yeoxuhang.ambiance.Ambiance;
import net.yeoxuhang.ambiance.client.particle.ParticleRegistry;
import net.yeoxuhang.ambiance.client.particle.option.AshOption;
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
    public void animateTick(BlockState blockState, World level, BlockPos blockPos, Random randomSource, CallbackInfo ci) {
        if (Ambiance.config.blocks.netherPortal.portal_type == AmbianceConfig.ambiance$type.FANCY){
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

                level.addAlwaysVisibleParticle(TrialOption.create(ParticleRegistry.PORTAL.get() ,(int)(Math.random() * 10.0) + 40, 1F, 0.01F,Ambiance.config.blocks.netherPortal.particleSize/10, MthHelper.randomDarkerColor("CC00FA") , 1F), d0, d1, d2, d3, d4, d5);
            }
        }

    }
    @ModifyArg(method = "animateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particles/IParticleData;DDDDDD)V"))
    public IParticleData animateTick(IParticleData p_195594_1_) {
        if (Ambiance.config.blocks.netherPortal.portal_type == AmbianceConfig.ambiance$type.VANILLA){
            return ParticleTypes.PORTAL;
        } else if (Ambiance.config.blocks.netherPortal.portal_type == AmbianceConfig.ambiance$type.FANCY){
            return ParticleRegistry.AIR.get();
        } else return ParticleRegistry.AIR.get();
    }
}
