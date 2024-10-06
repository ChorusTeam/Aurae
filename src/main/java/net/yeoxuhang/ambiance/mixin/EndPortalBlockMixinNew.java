package net.yeoxuhang.ambiance.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EndPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yeoxuhang.ambiance.client.particle.option.AshOption;
import net.yeoxuhang.ambiance.config.AmbianceConfig;
import net.yeoxuhang.ambiance.util.MthHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(EndPortalBlock.class)
public class EndPortalBlockMixinNew{

    @Inject(method = "animateTick", at = @At("RETURN"))
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource, CallbackInfo ci) {
        if (AmbianceConfig.enableEndPortal){
            for (int i = 0; i < 5; i++) {
                int randomColor = MthHelper.createRandomColor(4759448, 4759492);

                double d = (double)blockPos.getX() + randomSource.nextDouble();
                double e = (double)blockPos.getZ() + randomSource.nextDouble();
                double p = blockPos.getY() + (1 - randomSource.nextDouble()) + 0.6;
                if (randomSource.nextInt(10) == 0) {
                    level.addParticle(AshOption.create(25, AmbianceConfig.enderEndPortalSize, 0.5F, 0F, randomColor, 0.7F), d + randomSource.nextDouble() / 5.0, p, e + randomSource.nextDouble() / 5.0, 0.0, 0.0, 0.0);

                }
            }
        }
    }
}
