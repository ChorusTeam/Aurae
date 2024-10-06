package net.yeoxuhang.ambiance.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RespawnAnchorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.yeoxuhang.ambiance.client.particle.AshParticle;
import net.yeoxuhang.ambiance.client.particle.ParticleRegistry;
import net.yeoxuhang.ambiance.client.particle.option.AshOption;
import net.yeoxuhang.ambiance.config.AmbianceConfig;
import net.yeoxuhang.ambiance.util.MthHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RespawnAnchorBlock.class)
public class RespawnAnchorBlockMixin {
    @Shadow @Final public static IntegerProperty CHARGE;

    @Inject(method = "animateTick", at = @At("RETURN"))
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource, CallbackInfo ci) {
        if (randomSource.nextInt(5) == 0 && AmbianceConfig.enableRespawnAnchor && blockState.getValue(CHARGE) > 0){
            for(int j = 0; j < 5; ++j) {
                double d = (double)blockPos.getX() + 0.5 + (0.5 - randomSource.nextDouble());
                double e = (double)blockPos.getY() + 1.1;
                double f = (double)blockPos.getZ() + 0.5 + (0.5 - randomSource.nextDouble());
                double g = (double)randomSource.nextFloat() * 0.04;
                int color = MthHelper.createRandomColor(1840161, 4995184);
                level.addParticle(AshOption.create(ParticleRegistry.RESPAWN_ANCHOR, (int) (Math.random() * 10.0 + 10), AmbianceConfig.respawnAnchorSize, 1F, 0.1F, color, 1F), d, e, f, 0.0, g, 0.0);

            }
        }
    }
}
