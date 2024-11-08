package net.yeoxuhang.ambiance.mixin;

import net.minecraft.block.EndGatewayBlock;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.yeoxuhang.ambiance.Ambiance;
import net.yeoxuhang.ambiance.client.particle.option.TrialOption;
import net.yeoxuhang.ambiance.util.MthHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EndGatewayBlock.class)
public class EndGatewayBlockMixin {

    @ModifyArg(method = "animateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particles/IParticleData;DDDDDD)V"))
    public IParticleData animateTick(IParticleData p_195594_1_) {
        if (Ambiance.config.blocks.endGateway.enableParticle){
            int randomColor = MthHelper.createRandomColor(2842463, 4759492);
            return TrialOption.create((int)(Math.random() * 2.0) + 60, 1F, 2.5F, Ambiance.config.blocks.endGateway.particleSize / 10, randomColor, 0.7F);
        } else return ParticleTypes.PORTAL;
    }
}
