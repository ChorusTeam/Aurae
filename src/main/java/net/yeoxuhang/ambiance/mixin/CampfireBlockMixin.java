package net.yeoxuhang.ambiance.mixin;

import net.minecraft.client.color.block.BlockColors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.yeoxuhang.ambiance.client.particle.option.AshOption;
import net.yeoxuhang.ambiance.config.AmbianceConfig;
import net.yeoxuhang.ambiance.util.MthHelper;
import net.yeoxuhang.ambiance.util.ParticlesUtil;
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
    public void ambiance$animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource, CallbackInfo ci) {
        if (blockState.getValue(LIT) && AmbianceConfig.enableCampfire){
            if (blockState.is(Blocks.CAMPFIRE)){
                int randomColor = MthHelper.createRandomColor(13200387, 15715670);
                level.addParticle(AshOption.create((int) (Math.random() * 10.0 + 50), 0.1F, 1.5F, 0.5F, randomColor, 1.0F), (double)blockPos.getX() + 0.5, (double)blockPos.getY() + 1, (double)blockPos.getZ() + 0.5, randomSource.nextFloat() / 3.0F, 5.0E-5, randomSource.nextFloat() / 3.0F);
            }
            if (blockState.is(Blocks.SOUL_CAMPFIRE)){
                int soulFire = MthHelper.randomDarkerColor("7CF2F5");
                level.addParticle(AshOption.create((int) (Math.random() * 10.0 + 50), 0.1F, 1.5F, 0.5F, soulFire, 1.0F), (double)blockPos.getX() + 0.5, (double)blockPos.getY() + 1, (double)blockPos.getZ() + 0.5, randomSource.nextFloat() / 3.0F, 5.0E-5, randomSource.nextFloat() / 3.0F);
            }
        }
    }
}
