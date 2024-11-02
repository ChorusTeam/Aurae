package net.yeoxuhang.ambiance.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
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

@Mixin(CampfireBlock.class)
public class CampfireBlockMixin extends Block {
    @Shadow @Final public static BooleanProperty LIT;

    public CampfireBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "animateTick", at = @At("RETURN"))
    public void ambiance$animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource, CallbackInfo ci) {
        if (blockState.getValue(LIT) && Ambiance.config.blocks.campfire.enableParticle){
            if (blockState.is(Blocks.CAMPFIRE)){
                int randomColor = MthHelper.createRandomColor(13200387, 15715670);
                level.addAlwaysVisibleParticle(AshOption.create((int) (Math.random() * 10.0 + 50), 0.1F, 1.5F, 0.5F, randomColor, 1.0F), (double)blockPos.getX() + 0.5, (double)blockPos.getY() + 1, (double)blockPos.getZ() + 0.5, randomSource.nextFloat() / 3.0F, 5.0E-5, randomSource.nextFloat() / 3.0F);
            }
            if (blockState.is(Blocks.SOUL_CAMPFIRE)){
                int soulFire = MthHelper.randomDarkerColor("7CF2F5");
                level.addAlwaysVisibleParticle(AshOption.create((int) (Math.random() * 10.0 + 50), 0.1F, 1.5F, 0.5F, soulFire, 1.0F), (double)blockPos.getX() + 0.5, (double)blockPos.getY() + 1, (double)blockPos.getZ() + 0.5, randomSource.nextFloat() / 3.0F, 5.0E-5, randomSource.nextFloat() / 3.0F);
            }
        }
    }

    @WrapWithCondition(method = "makeParticles", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
    private static boolean smokeType(Level instance, ParticleOptions particleOptions, double d, double e, double f, double g, double h, double i) {
        return Ambiance.config.blocks.campfire.smokeType == AmbianceConfig.ambiance$type2.VANILLA;
    }

    @WrapWithCondition(method = "makeParticles", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addAlwaysVisibleParticle(Lnet/minecraft/core/particles/ParticleOptions;ZDDDDDD)V"))
    private static boolean smokeType2(Level instance, ParticleOptions particleOptions, boolean bl, double d, double e, double f, double g, double h, double i) {
        return Ambiance.config.blocks.campfire.smokeType == AmbianceConfig.ambiance$type2.VANILLA;
    }

    @WrapWithCondition(method = "animateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
    public boolean flameType(Level instance, ParticleOptions particleOptions, double d, double e, double f, double g, double h, double i) {
        return Ambiance.config.blocks.campfire.flameType == AmbianceConfig.ambiance$type2.VANILLA;
    }
}
