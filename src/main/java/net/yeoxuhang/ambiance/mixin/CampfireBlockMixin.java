package net.yeoxuhang.ambiance.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.particles.IParticleData;
import net.minecraft.state.BooleanProperty;
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

@Mixin(CampfireBlock.class)
public class CampfireBlockMixin extends Block {
    @Shadow @Final public static BooleanProperty LIT;

    public CampfireBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "animateTick", at = @At("RETURN"))
    public void ambiance$animateTick(BlockState blockState, World level, BlockPos blockPos, Random randomSource, CallbackInfo ci) {
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

    @WrapWithCondition(method = "makeParticles", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particles/IParticleData;DDDDDD)V"))
    private static boolean smokeType(World instance, IParticleData p_195594_1_, double p_195594_2_, double p_195594_4_, double p_195594_6_, double p_195594_8_, double p_195594_10_, double p_195594_12_) {
        return Ambiance.config.blocks.campfire.smokeType == AmbianceConfig.ambiance$type2.VANILLA;
    }

    @WrapWithCondition(method = "makeParticles", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addAlwaysVisibleParticle(Lnet/minecraft/particles/IParticleData;ZDDDDDD)V"))
    private static boolean smokeType2(World instance, IParticleData p_217404_1_, boolean p_217404_2_, double p_217404_3_, double p_217404_5_, double p_217404_7_, double p_217404_9_, double p_217404_11_, double p_217404_13_) {
        return Ambiance.config.blocks.campfire.smokeType == AmbianceConfig.ambiance$type2.VANILLA;
    }

    @WrapWithCondition(method = "animateTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particles/IParticleData;DDDDDD)V"))
    public boolean flameType(World instance, IParticleData p_195594_1_, double p_195594_2_, double p_195594_4_, double p_195594_6_, double p_195594_8_, double p_195594_10_, double p_195594_12_) {
        return Ambiance.config.blocks.campfire.flameType == AmbianceConfig.ambiance$type2.VANILLA;
    }
}
