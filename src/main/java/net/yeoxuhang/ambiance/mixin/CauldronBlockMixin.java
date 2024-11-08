package net.yeoxuhang.ambiance.mixin;
import net.minecraft.block.BlockState;
import net.minecraft.block.CauldronBlock;
import net.minecraft.entity.Entity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.yeoxuhang.ambiance.Ambiance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(CauldronBlock.class)
public class CauldronBlockMixin {
    @Inject(method = "entityInside", at = @At("RETURN"))
    protected void entityInside(BlockState p_196262_1_, World level, BlockPos blockPos, Entity entity, CallbackInfo ci) {
        Random randomSource = level.random;
        double x = blockPos.getX() + randomSource.nextDouble();
        double y = blockPos.getY() + 1.2;
        double z = blockPos.getZ() + randomSource.nextDouble();
        double horizontalSpeedSquared = entity.getDeltaMovement().x * entity.getDeltaMovement().x + entity.getDeltaMovement().z * entity.getDeltaMovement().z;

        if (horizontalSpeedSquared > 1.0E-6D && randomSource.nextInt(2) == 1){
            if (Ambiance.config.blocks.cauldron.water.enableSound){
                level.playLocalSound(x, y, z, SoundEvents.PLAYER_SPLASH, SoundCategory.BLOCKS, Ambiance.config.blocks.cauldron.water.soundVolume, 0.9f + randomSource.nextFloat() * 0.15f, false);
            }
            if (Ambiance.config.blocks.cauldron.water.enableParticle){
                level.addAlwaysVisibleParticle(ParticleTypes.SPLASH, (double)blockPos.getX() + level.random.nextDouble(), blockPos.getY() + 1, (double)blockPos.getZ() + level.random.nextDouble(), 0, 0.0, 0);

            }
        }
        if (entity.getDeltaMovement().y > 0){
            if (Ambiance.config.blocks.cauldron.water.enableSound){
                level.playLocalSound(x, y, z, SoundEvents.PLAYER_SPLASH, SoundCategory.BLOCKS, Ambiance.config.blocks.cauldron.water.soundVolume, 0.9f + randomSource.nextFloat() * 0.15f, false);
            }
            if (Ambiance.config.blocks.cauldron.water.enableParticle){
                level.addAlwaysVisibleParticle(ParticleTypes.SPLASH, (double)blockPos.getX() + level.random.nextDouble(), blockPos.getY() + 1, (double)blockPos.getZ() + level.random.nextDouble(), 0, 0.0, 0);
            }
        }

    }
}
