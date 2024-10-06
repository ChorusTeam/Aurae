package net.yeoxuhang.ambiance.mixin;

import com.google.common.base.Predicates;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EndPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockPredicate;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.yeoxuhang.ambiance.client.particle.option.AshOption;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.world.level.block.EndPortalFrameBlock.FACING;
import static net.minecraft.world.level.block.EndPortalFrameBlock.HAS_EYE;

@Mixin(EndPortalBlock.class)
public abstract class EndPortalBlockMixin extends Block {

    private int particleColdDown;
    private static BlockPattern portalShape;
    private static BlockPattern podiumShape;

    @Unique
    private static BlockPattern getOrCreatePortalShape() {
        if (portalShape == null) {
            portalShape = BlockPatternBuilder.start()
                    .aisle("?????", "?sss?", "?sss?", "?sss?", "?????")
                    .aisle("?vvv?", ">ooo<", ">ooo<", ">ooo<", "?^^^?")
                    .where('s', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.AIR)))
                    .where('o', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.END_PORTAL)))
                    .where('?', BlockInWorld.hasState(BlockStatePredicate.ANY))
                    .where('^', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.END_PORTAL_FRAME)
                            .where(HAS_EYE, Predicates.equalTo(true))
                            .where(FACING, Predicates.equalTo(Direction.SOUTH))))
                    .where('>', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.END_PORTAL_FRAME)
                            .where(HAS_EYE, Predicates.equalTo(true))
                            .where(FACING, Predicates.equalTo(Direction.WEST))))
                    .where('v', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.END_PORTAL_FRAME)
                            .where(HAS_EYE, Predicates.equalTo(true))
                            .where(FACING, Predicates.equalTo(Direction.NORTH))))
                    .where('<', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.END_PORTAL_FRAME)
                            .where(HAS_EYE, Predicates.equalTo(true))
                            .where(FACING, Predicates.equalTo(Direction.EAST)))).build();
        }
        return portalShape;
    }

    @Unique
    private static BlockPattern getOrCreatePodiumShape() {
        if (podiumShape == null) {
            podiumShape = BlockPatternBuilder.start()
                    .aisle("       ", "       ", "       ", "   #   ", "       ", "       ", "       ")
                    .aisle("       ", "       ", "       ", "   #   ", "       ", "       ", "       ")
                    .aisle("???????", "???????", "???????", "???#???", "???????", "???????", "???????")
                    .aisle("  ###  ", " #ooo# ", "#ooooo#", "#oo#oo#", "#ooooo#", " #ooo# ", "  ###  ")
                    .aisle("       ", "  ###  ", " ##### ", " ##### ", " ##### ", "  ###  ", "       ")
                    .where('?', BlockInWorld.hasState(BlockPredicate.forBlock(Blocks.AIR)))
                    .where('o', BlockInWorld.hasState(BlockPredicate.forBlock(Blocks.END_PORTAL)))
                    .where('#', BlockInWorld.hasState(BlockPredicate.forBlock(Blocks.BEDROCK))).build();
        }
        return podiumShape;
    }
    public EndPortalBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "animateTick", at = @At("RETURN"))
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource, CallbackInfo ci) {
        if (randomSource.nextInt(3) == 0) {
            double d = blockPos.getX();
            double e = (double) blockPos.getY() + 1.5;
            double f = blockPos.getZ();
            level.addParticle(AshOption.create(30 + randomSource.nextInt() * 10, 0.1F, 1.5F, 0.2F,4759448, 1F), d, e, f, 0.0, 0.0, 0.0);
        }
        BlockPattern.BlockPatternMatch portal = getOrCreatePortalShape().find(level, blockPos);
        BlockPattern.BlockPatternMatch podium = getOrCreatePodiumShape().find(level, blockPos);
        /*if (particleColdDown > 0){
            particleColdDown--;
        } else if (particleColdDown == 0){
            particleColdDown = 104;
            if (podium != null) {
                BlockPos blockPos3 = podium.getFrontTopLeft().offset(-3, -3, -3);
                BlockPos endStone = podium.getFrontTopLeft().offset(-3, -5, -3);
                if (level.getBlockState(endStone).is(Blocks.END_STONE)){
                    level.addParticle(ParticleRegistry.END_PORTAL_EXIT, blockPos3.getX(), blockPos3.getY(), blockPos3.getZ(), 0.0, 0.0, 0.0);
                }
            }
            if (portal != null) {
                BlockPos blockPos2 = portal.getFrontTopLeft().offset(-1, 0, -1);
                BlockPos air = portal.getFrontTopLeft().offset(-2, -2, -2);
                if (!level.getBlockState(air).is(Blocks.BEDROCK)){
                    level.addParticle(ParticleRegistry.END_PORTAL, blockPos2.getX() - 0.5, blockPos2.getY() + 0.3, blockPos2.getZ() - 0.5, 0.0, 0.0, 0.0);
                }
            }
        }*/

    }
}
