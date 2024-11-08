package net.yeoxuhang.ambiance.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.yeoxuhang.ambiance.Ambiance;
import net.yeoxuhang.ambiance.client.particle.option.AshOption;
import net.yeoxuhang.ambiance.config.AmbianceConfig;
import net.yeoxuhang.ambiance.util.MthHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(AbstractFireBlock.class)
public abstract class BaseFireBlockMixin extends Block {

    @Shadow protected abstract boolean canBurn(BlockState blockState);

    public BaseFireBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "animateTick", at = @At(value = "RETURN"))
    public void ambiance$animateTick(BlockState blockState, World World, BlockPos blockPos, Random randomSource, CallbackInfo ci) {
        BlockPos blockPos2 = blockPos.below();
        BlockState blockState2 = World.getBlockState(blockPos2);
        int i;
        double d;
        double e;
        double f;
        int fire = MthHelper.createRandomColor(13200387, 15715670);
        int soulFire = MthHelper.randomDarkerColor("7CF2F5");
        if (Ambiance.config.blocks.fire.enableParticle){
            if (randomSource.nextInt(6) == 0){
                if (blockState.is(Blocks.FIRE)){
                    if (!this.canBurn(blockState2) && !blockState2.isFaceSturdy(World, blockPos2, Direction.UP)) {
                        if (this.canBurn(World.getBlockState(blockPos.west()))) {
                            for(i = 0; i < 2; ++i) {
                                d = (double)blockPos.getX() + randomSource.nextDouble() * 0.10000000149011612;
                                e = (double)blockPos.getY() + randomSource.nextDouble();
                                f = (double)blockPos.getZ() + randomSource.nextDouble();
                                World.addAlwaysVisibleParticle(AshOption.create((int) (Math.random() * 10.0 + 50), 0.1F, 1.5F, 0.1F, fire, 1.0F), d, e, f, 0.0, 0.0, 0.0);
                            }
                        }

                        if (this.canBurn(World.getBlockState(blockPos.east()))) {
                            for(i = 0; i < 2; ++i) {
                                d = (double)(blockPos.getX() + 1) - randomSource.nextDouble() * 0.10000000149011612;
                                e = (double)blockPos.getY() + randomSource.nextDouble();
                                f = (double)blockPos.getZ() + randomSource.nextDouble();
                                World.addAlwaysVisibleParticle(AshOption.create((int) (Math.random() * 10.0 + 50), 0.1F, 1.5F, 0.1F, fire, 1.0F), d, e, f, 0.0, 0.0, 0.0);
                            }
                        }

                        if (this.canBurn(World.getBlockState(blockPos.north()))) {
                            for(i = 0; i < 2; ++i) {
                                d = (double)blockPos.getX() + randomSource.nextDouble();
                                e = (double)blockPos.getY() + randomSource.nextDouble();
                                f = (double)blockPos.getZ() + randomSource.nextDouble() * 0.10000000149011612;
                                World.addAlwaysVisibleParticle(AshOption.create((int) (Math.random() * 10.0 + 50), 0.1F, 1.5F, 0.1F, fire, 1.0F), d, e, f, 0.0, 0.0, 0.0);
                            }
                        }

                        if (this.canBurn(World.getBlockState(blockPos.south()))) {
                            for(i = 0; i < 2; ++i) {
                                d = (double)blockPos.getX() + randomSource.nextDouble();
                                e = (double)blockPos.getY() + randomSource.nextDouble();
                                f = (double)(blockPos.getZ() + 1) - randomSource.nextDouble() * 0.10000000149011612;
                                World.addAlwaysVisibleParticle(AshOption.create((int) (Math.random() * 10.0 + 50), 0.1F, 1.5F, 0.1F, fire, 1.0F), d, e, f, 0.0, 0.0, 0.0);
                            }
                        }

                        if (this.canBurn(World.getBlockState(blockPos.above()))) {
                            for(i = 0; i < 2; ++i) {
                                d = (double)blockPos.getX() + randomSource.nextDouble();
                                e = (double)(blockPos.getY() + 1) - randomSource.nextDouble() * 0.10000000149011612;
                                f = (double)blockPos.getZ() + randomSource.nextDouble();
                                World.addAlwaysVisibleParticle(AshOption.create((int) (Math.random() * 10.0 + 50), 0.1F, 1.5F, 0.1F, fire, 1.0F), d, e, f, 0.0, 0.0, 0.0);
                            }
                        }
                    } else {
                        for(i = 0; i < 3; ++i) {
                            d = (double)blockPos.getX() + randomSource.nextDouble();
                            e = (double)blockPos.getY() + randomSource.nextDouble() * 0.5 + 0.5;
                            f = (double)blockPos.getZ() + randomSource.nextDouble();
                            World.addAlwaysVisibleParticle(AshOption.create((int) (Math.random() * 10.0 + 50), 0.1F, 1.5F, 0.1F, fire, 1.0F), d, e, f, 0.0, 0.0, 0.0);
                        }
                    }
                } else if (blockState.is(Blocks.SOUL_FIRE)){
                    if (!this.canBurn(blockState2) && !blockState2.isFaceSturdy(World, blockPos2, Direction.UP)) {
                        if (this.canBurn(World.getBlockState(blockPos.west()))) {
                            for(i = 0; i < 2; ++i) {
                                d = (double)blockPos.getX() + randomSource.nextDouble() * 0.10000000149011612;
                                e = (double)blockPos.getY() + randomSource.nextDouble();
                                f = (double)blockPos.getZ() + randomSource.nextDouble();
                                World.addAlwaysVisibleParticle(AshOption.create((int) (Math.random() * 10.0 + 50), 0.1F, 1.5F, 0.1F, soulFire, 1.0F), d, e, f, 0.0, 0.0, 0.0);
                            }
                        }

                        if (this.canBurn(World.getBlockState(blockPos.east()))) {
                            for(i = 0; i < 2; ++i) {
                                d = (double)(blockPos.getX() + 1) - randomSource.nextDouble() * 0.10000000149011612;
                                e = (double)blockPos.getY() + randomSource.nextDouble();
                                f = (double)blockPos.getZ() + randomSource.nextDouble();
                                World.addAlwaysVisibleParticle(AshOption.create((int) (Math.random() * 10.0 + 50), 0.1F, 1.5F, 0.1F, soulFire, 1.0F), d, e, f, 0.0, 0.0, 0.0);
                            }
                        }

                        if (this.canBurn(World.getBlockState(blockPos.north()))) {
                            for(i = 0; i < 2; ++i) {
                                d = (double)blockPos.getX() + randomSource.nextDouble();
                                e = (double)blockPos.getY() + randomSource.nextDouble();
                                f = (double)blockPos.getZ() + randomSource.nextDouble() * 0.10000000149011612;
                                World.addAlwaysVisibleParticle(AshOption.create((int) (Math.random() * 10.0 + 50), 0.1F, 1.5F, 0.1F, soulFire, 1.0F), d, e, f, 0.0, 0.0, 0.0);
                            }
                        }

                        if (this.canBurn(World.getBlockState(blockPos.south()))) {
                            for(i = 0; i < 2; ++i) {
                                d = (double)blockPos.getX() + randomSource.nextDouble();
                                e = (double)blockPos.getY() + randomSource.nextDouble();
                                f = (double)(blockPos.getZ() + 1) - randomSource.nextDouble() * 0.10000000149011612;
                                World.addAlwaysVisibleParticle(AshOption.create((int) (Math.random() * 10.0 + 50), 0.1F, 1.5F, 0.1F, soulFire, 1.0F), d, e, f, 0.0, 0.0, 0.0);
                            }
                        }

                        if (this.canBurn(World.getBlockState(blockPos.above()))) {
                            for(i = 0; i < 2; ++i) {
                                d = (double)blockPos.getX() + randomSource.nextDouble();
                                e = (double)(blockPos.getY() + 1) - randomSource.nextDouble() * 0.10000000149011612;
                                f = (double)blockPos.getZ() + randomSource.nextDouble();
                                World.addAlwaysVisibleParticle(AshOption.create((int) (Math.random() * 10.0 + 50), 0.1F, 1.5F, 0.1F, soulFire, 1.0F), d, e, f, 0.0, 0.0, 0.0);
                            }
                        }
                    } else {
                        for(i = 0; i < 3; ++i) {
                            d = (double)blockPos.getX() + randomSource.nextDouble();
                            e = (double)blockPos.getY() + randomSource.nextDouble() * 0.5 + 0.5;
                            f = (double)blockPos.getZ() + randomSource.nextDouble();
                            World.addAlwaysVisibleParticle(AshOption.create((int) (Math.random() * 10.0 + 50), 0.1F, 1.5F, 0.1F, soulFire, 1.0F), d, e, f, 0.0, 0.0, 0.0);
                        }
                    }
                }
            }
        }
    }

    @WrapWithCondition(method = "animateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particles/IParticleData;DDDDDD)V"))
    public boolean canBurn(World instance, IParticleData particleOptions, double d, double e, double f, double g, double h, double i) {
        return Ambiance.config.blocks.fire.smokeType == AmbianceConfig.ambiance$type2.VANILLA;
    }
    /*@WrapWithCondition(method = "animateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World/World;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V", ordinal = 1))
    public boolean canBurn1(World instance, ParticleOptions particleOptions, double d, double e, double f, double g, double h, double i) {
        return Ambiance.config.blocks.fire.smokeType == AmbianceConfig.ambiance$type2.VANILLA;
    }
    @WrapWithCondition(method = "animateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World/World;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V", ordinal = 2))
    public boolean canBurn2(World instance, ParticleOptions particleOptions, double d, double e, double f, double g, double h, double i) {
        return Ambiance.config.blocks.fire.smokeType == AmbianceConfig.ambiance$type2.VANILLA;
    }
    @WrapWithCondition(method = "animateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World/World;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V", ordinal = 3))
    public boolean canBurn3(World instance, ParticleOptions particleOptions, double d, double e, double f, double g, double h, double i) {
        return Ambiance.config.blocks.fire.smokeType == AmbianceConfig.ambiance$type2.VANILLA;
    }
    @WrapWithCondition(method = "animateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World/World;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V", ordinal = 4))
    public boolean canBurn4(World instance, ParticleOptions particleOptions, double d, double e, double f, double g, double h, double i) {
        return Ambiance.config.blocks.fire.smokeType == AmbianceConfig.ambiance$type2.VANILLA;
    }
    @WrapWithCondition(method = "animateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World/World;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V", ordinal = 5))
    public boolean canBurn5(World instance, ParticleOptions particleOptions, double d, double e, double f, double g, double h, double i) {
        return Ambiance.config.blocks.fire.smokeType == AmbianceConfig.ambiance$type2.VANILLA;
    }*/
}
