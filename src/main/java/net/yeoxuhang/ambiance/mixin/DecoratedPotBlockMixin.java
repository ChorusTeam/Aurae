package net.yeoxuhang.ambiance.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DecoratedPotBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.yeoxuhang.ambiance.config.AmbianceConfig;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DecoratedPotBlock.class)
public class DecoratedPotBlockMixin extends Block {
    @Shadow @Final private static BooleanProperty WATERLOGGED;

    public DecoratedPotBlockMixin(Properties properties) {
        super(properties);
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        super.animateTick(blockState, level, blockPos, randomSource);
        if (blockState.getBlock() instanceof DecoratedPotBlock && blockState.getValue(WATERLOGGED) && randomSource.nextInt(5) == 0 && AmbianceConfig.enableDecoratedPot) {
            for(int i = 0; i < 3; ++i) {
                level.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, (double)blockPos.getX() + 0.5, (double)blockPos.getY() + 1.25, (double)blockPos.getZ() + 0.5, 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    protected void spawnDestroyParticles(Level level, Player player, BlockPos blockPos, BlockState blockState) {
        super.spawnDestroyParticles(level, player, blockPos, blockState);
        if (blockState.getBlock() instanceof DecoratedPotBlock && blockState.getValue(WATERLOGGED) && AmbianceConfig.enableDecoratedPot) {
            for(int i = 0; i < 20; ++i) {
                level.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, blockPos.getX() + level.random.nextDouble(), blockPos.getY() + level.random.nextDouble(),  blockPos.getZ() + level.random.nextDouble(), 0.0, 0.0, 0.0);
            }
        }
    }
}
