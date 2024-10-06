package net.yeoxuhang.ambiance.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCandleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.yeoxuhang.ambiance.client.particle.option.AshOption;
import net.yeoxuhang.ambiance.config.AmbianceConfig;
import net.yeoxuhang.ambiance.util.MthHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractCandleBlock.class)
public class CandleBlockMixin {

    @Inject(method = "addParticlesAndSound", at = @At(value = "TAIL"))
    private static void ambiance$addParticlesAndSound(Level level, Vec3 vec3, RandomSource randomSource, CallbackInfo ci) {
        if (randomSource.nextBoolean() && AmbianceConfig.enableCandle) {
            int randomColor = MthHelper.createRandomColor(13200387, 15715670);
            level.addParticle(AshOption.create((int) (Math.random() * 10.0 + 5), 0.025F, -0.1F, randomSource.nextFloat() * 0.1F, randomColor, 1.0F), vec3.x, vec3.y, vec3.z, 0.0, 0.0, 0.0);
        }
    }
}
