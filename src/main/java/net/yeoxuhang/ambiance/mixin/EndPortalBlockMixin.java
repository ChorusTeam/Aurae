package net.yeoxuhang.ambiance.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.EndPortalBlock;
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

@Mixin(EndPortalBlock.class)
public class EndPortalBlockMixin {

    @Inject(method = "animateTick", at = @At("RETURN"))
    public void animateTick(BlockState blockState, World level, BlockPos blockPos, Random randomSource, CallbackInfo ci) {
        if (Ambiance.config.blocks.endPortal.enableParticle) {
            for(int i = 0; i < 3; ++i) {
                double d = (double)blockPos.getX() + randomSource.nextDouble();
                double e = (double)blockPos.getY() + randomSource.nextDouble();
                double f = (double)blockPos.getZ() + randomSource.nextDouble();
                double g = ((double)randomSource.nextFloat() - 0.5) * 0.5;
                double h = ((double)randomSource.nextFloat() - 0.5) * 0.5;
                double j = ((double)randomSource.nextFloat() - 0.5) * 0.5;
                int k = randomSource.nextInt(2) * 2 - 1;
                if (randomSource.nextBoolean()) {
                    d = (double)blockPos.getX() + 0.5 + 0.25 * (double)k;
                    g = randomSource.nextFloat() * 2.0F * (float)k;
                } else {
                    f = (double)blockPos.getZ() + 0.5 + 0.25 * (double)k;
                    j = randomSource.nextFloat() * 2.0F * (float)k;
                }
                int randomColor = MthHelper.randomDarkerColor("489F98");
                if (randomSource.nextInt(5) == 1){
                    level.addAlwaysVisibleParticle(TrialOption.create((int)(Math.random() * 2.0) + 60, 1F, 2.5F, Ambiance.config.blocks.endPortal.particleSize / 10, randomColor, 240), d, e, f, g + randomSource.nextDouble(), h + 3, j + randomSource.nextDouble());
                }
            }
        }
        if (Ambiance.config.blocks.endPortal.smokeType == AmbianceConfig.ambiance$type.FANCY){
            for (int i = 0; i < 5; i++) {
                int randomColor = MthHelper.randomDarkerColor("85C6C1");
                double d = (double)blockPos.getX() + randomSource.nextDouble();
                double e = (double)blockPos.getZ() + randomSource.nextDouble();
                double p = blockPos.getY() + (1 - randomSource.nextDouble()) + 0.6;
                if (randomSource.nextInt(10) == 0) {
                    level.addAlwaysVisibleParticle(AshOption.create(ParticleRegistry.END_PORTAL_ASH.get() ,(int)(Math.random() * 2.0) + 35, Ambiance.config.blocks.endPortal.particleSize / 10, 0.0001F, 0.0F, randomColor, 0.3F), d + randomSource.nextDouble() / 5.0, p, e + randomSource.nextDouble() / 5.0, 0.0, 0.0, 0.0);
                }
            }
        }
    }
    @ModifyArg(method = "animateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particles/IParticleData;DDDDDD)V"))
    public IParticleData animateTick(IParticleData p_195594_1_) {
        if (Ambiance.config.blocks.endPortal.smokeType == AmbianceConfig.ambiance$type.VANILLA){
            return ParticleTypes.SMOKE;
        } else if (Ambiance.config.blocks.endPortal.smokeType == AmbianceConfig.ambiance$type.FANCY){
            return ParticleRegistry.AIR.get();
        } else return ParticleRegistry.AIR.get();
    }
}
