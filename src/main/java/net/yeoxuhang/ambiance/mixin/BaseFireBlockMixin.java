package net.yeoxuhang.ambiance.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.yeoxuhang.ambiance.Ambiance;
import net.yeoxuhang.ambiance.client.particle.option.AshOption;
import net.yeoxuhang.ambiance.config.AmbianceConfig;
import net.yeoxuhang.ambiance.util.MthHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

@Mixin(BaseFireBlock.class)
public abstract class BaseFireBlockMixin extends Block {

    @Shadow
    protected abstract boolean canBurn(BlockState blockState);

    public BaseFireBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "animateTick", at = @At("RETURN"))
    public void ambiance$animateTick(BlockState blockState, Level world, BlockPos blockPos, Random randomSource, CallbackInfo ci) {
        BlockPos blockPos2 = blockPos.below();
        BlockState blockState2 = world.getBlockState(blockPos2);
        int fire = MthHelper.createRandomColor(13200387, 15715670);
        int soulFire = MthHelper.randomDarkerColor("7CF2F5");

        if (Ambiance.config.blocks.fire.enableParticle) {
            if (randomSource.nextInt(6) == 0) {
                if (blockState.is(Blocks.FIRE)) {
                    spawnFireParticles(world, blockPos, blockState2, randomSource, fire);
                } else if (blockState.is(Blocks.SOUL_FIRE)) {
                    spawnFireParticles(world, blockPos, blockState2, randomSource, soulFire);
                }
            }
        }
    }

    @Unique
    private void spawnFireParticles(Level world, BlockPos blockPos, BlockState blockState2, Random randomSource, int color) {
        int i;
        double d, e, f;

        if (!this.canBurn(blockState2) && !blockState2.isFaceSturdy(world, blockPos.below(), Direction.UP)) {
            for (Direction direction : Direction.values()) {
                BlockPos offsetPos = blockPos.relative(direction);
                if (this.canBurn(world.getBlockState(offsetPos))) {
                    for (i = 0; i < 2; ++i) {
                        d = blockPos.getX() + (direction.getStepX() > 0 ? 1.0 - randomSource.nextDouble() * 0.1 : randomSource.nextDouble() * 0.1);
                        e = blockPos.getY() + randomSource.nextDouble();
                        f = blockPos.getZ() + (direction.getStepZ() > 0 ? 1.0 - randomSource.nextDouble() * 0.1 : randomSource.nextDouble() * 0.1);
                        world.addAlwaysVisibleParticle(AshOption.create((int) (Math.random() * 10.0 + 50), 0.1F, 1.5F, 0.1F, color, 1.0F), d, e, f, 0.0, 0.0, 0.0);
                    }
                }
            }
        } else {
            for (i = 0; i < 3; ++i) {
                d = blockPos.getX() + randomSource.nextDouble();
                e = blockPos.getY() + randomSource.nextDouble() * 0.5 + 0.5;
                f = blockPos.getZ() + randomSource.nextDouble();
                world.addAlwaysVisibleParticle(AshOption.create((int) (Math.random() * 10.0 + 50), 0.1F, 1.5F, 0.1F, color, 1.0F), d, e, f, 0.0, 0.0, 0.0);
            }
        }
    }

    @WrapWithCondition(method = "animateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
    public boolean canBurn(Level instance, ParticleOptions particleOptions, double d, double e, double f, double g, double h, double i) {
        return Ambiance.config.blocks.fire.smokeType == AmbianceConfig.ambiance$type2.VANILLA;
    }
}

