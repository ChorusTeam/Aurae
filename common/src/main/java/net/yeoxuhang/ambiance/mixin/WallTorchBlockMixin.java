package net.yeoxuhang.ambiance.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
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

@Mixin(WallTorchBlock.class)
public class WallTorchBlockMixin extends TorchBlock{
    @Shadow @Final public static DirectionProperty FACING;

    public WallTorchBlockMixin(SimpleParticleType simpleParticleType, Properties properties) {
        super(simpleParticleType, properties);
    }

    @Inject(method = "animateTick", at = @At("HEAD"))
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource, CallbackInfo ci) {
        Direction direction = blockState.getValue(FACING);
        double d = (double)blockPos.getX() + 0.5;
        double e = (double)blockPos.getY() + 0.7;
        double f = (double)blockPos.getZ() + 0.5;
        Direction direction2 = direction.getOpposite();
        if (randomSource.nextInt(2) == 0 && Ambiance.config.blocks.torch.enableParticle) {
            if (flameParticle == ParticleTypes.FLAME){
                int randomColor = MthHelper.createRandomColor(13200387, 15715670);
                level.addAlwaysVisibleParticle(AshOption.create((int) (Math.random() * 10.0 + 50), 0.05F, 1.5F, 0.1F, randomColor, 1.0F), d + 0.27 * (double)direction2.getStepX(), e + 0.22, f + 0.27 * (double)direction2.getStepZ(), 0.0, 0.0, 0.0);
            }
            if (flameParticle == ParticleTypes.SOUL_FIRE_FLAME){
                int soulFire = MthHelper.randomDarkerColor("7CF2F5");
                level.addAlwaysVisibleParticle(AshOption.create((int) (Math.random() * 10.0 + 50), 0.05F, 1.5F, 0.1F, soulFire, 1.0F), d + 0.27 * (double)direction2.getStepX(), e + 0.22, f + 0.27 * (double)direction2.getStepZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    @WrapWithCondition(method = "animateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V", ordinal = 0))
    public boolean smokeType(Level instance, ParticleOptions particleOptions, double d, double e, double f, double g, double h, double i) {
        return Ambiance.config.blocks.torch.smokeType == AmbianceConfig.ambiance$type2.VANILLA;
    }

    @WrapWithCondition(method = "animateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V", ordinal = 1))
    public boolean flameType(Level instance, ParticleOptions particleOptions, double d, double e, double f, double g, double h, double i) {
        return Ambiance.config.blocks.torch.flameType == AmbianceConfig.ambiance$type2.VANILLA;
    }
}
