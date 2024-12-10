package net.yeoxuhang.ambiance.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.phys.Vec3;
import net.yeoxuhang.ambiance.Ambiance;
import net.yeoxuhang.ambiance.client.particle.option.AshOption;
import net.yeoxuhang.ambiance.config.AmbianceConfig;
import net.yeoxuhang.ambiance.util.MthHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractCandleBlock.class)
public class CandleBlockMixin {

    @Inject(method = "addParticlesAndSound", at = @At(value = "TAIL"))
    private static void ambiance$addAlwaysVisibleParticlesAndSound(Level level, Vec3 vec3, RandomSource randomSource, CallbackInfo ci) {
        if (randomSource.nextBoolean() && Ambiance.config.blocks.candle.enableParticle) {
            int randomColor = MthHelper.createRandomColor(13200387, 15715670);
            level.addAlwaysVisibleParticle(AshOption.create((int) (Math.random() * 10.0 + 5), 0.025F, -0.1F, randomSource.nextFloat() * 0.1F, randomColor, 1.0F), vec3.x, vec3.y, vec3.z, 0.0, 0.0, 0.0);
        }
    }

    @WrapWithCondition(method = "addParticlesAndSound", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V", ordinal = 0))
    private static boolean smokeType(Level instance, ParticleOptions particleOptions, double d, double e, double f, double g, double h, double i) {
        return Ambiance.config.blocks.candle.smokeType == AmbianceConfig.ambiance$type2.VANILLA;
    }

    @WrapWithCondition(method = "addParticlesAndSound", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V", ordinal = 0))
    private static boolean flameType(Level instance, ParticleOptions particleOptions, double d, double e, double f, double g, double h, double i) {
        return Ambiance.config.blocks.candle.flameType == AmbianceConfig.ambiance$type2.VANILLA;
    }
}
