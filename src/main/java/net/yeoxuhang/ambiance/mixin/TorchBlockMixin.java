package net.yeoxuhang.ambiance.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.minecraft.block.BlockState;
import net.minecraft.block.TorchBlock;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.yeoxuhang.ambiance.Ambiance;
import net.yeoxuhang.ambiance.client.particle.option.AshOption;
import net.yeoxuhang.ambiance.config.AmbianceConfig;
import net.yeoxuhang.ambiance.util.MthHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(TorchBlock.class)
public class TorchBlockMixin {
    @Shadow @Final protected IParticleData flameParticle;

    @Inject(method = "animateTick", at = @At("HEAD"))
    public void animateTick(BlockState blockState, World level, BlockPos blockPos, Random randomSource, CallbackInfo ci) {
        double d = (double)blockPos.getX() + 0.5;
        double e = (double)blockPos.getY() + 0.7;
        double f = (double)blockPos.getZ() + 0.5;
        if (randomSource.nextInt(2) == 0 && Ambiance.config.blocks.torch.enableParticle) {
            if (flameParticle == ParticleTypes.FLAME) {
                int randomColor = MthHelper.createRandomColor(13200387, 15715670);
                level.addAlwaysVisibleParticle(AshOption.create((int) (Math.random() * 10.0 + 50), 0.05F, 1.5F, 0.5F, randomColor, 1.0F), d, e, f, 0.0, 0.0, 0.0);
            }
            if (flameParticle == ParticleTypes.SOUL_FIRE_FLAME) {
                int soulFire = MthHelper.randomDarkerColor("7CF2F5");
                level.addAlwaysVisibleParticle(AshOption.create((int) (Math.random() * 10.0 + 50), 0.05F, 1.5F, 0.5F, soulFire, 1.0F), d, e, f, 0.0, 0.0, 0.0);
            }
        }
    }

    @WrapWithCondition(method = "animateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particles/IParticleData;DDDDDD)V", ordinal = 0))
    public boolean smokeType(World instance, IParticleData p_195594_1_, double p_195594_2_, double p_195594_4_, double p_195594_6_, double p_195594_8_, double p_195594_10_, double p_195594_12_) {
        return Ambiance.config.blocks.torch.smokeType == AmbianceConfig.ambiance$type2.VANILLA;
    }

    @WrapWithCondition(method = "animateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particles/IParticleData;DDDDDD)V", ordinal = 1))
    public boolean flameType(World instance, IParticleData p_195594_1_, double p_195594_2_, double p_195594_4_, double p_195594_6_, double p_195594_8_, double p_195594_10_, double p_195594_12_) {
        return Ambiance.config.blocks.torch.flameType == AmbianceConfig.ambiance$type2.VANILLA;
    }
}
