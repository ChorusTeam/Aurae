package net.yeoxuhang.ambiance.mixin;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.yeoxuhang.ambiance.client.particle.ParticleRegistry;
import net.yeoxuhang.ambiance.client.particle.option.TrialOption;
import net.yeoxuhang.ambiance.config.AmbianceConfig;
import net.yeoxuhang.ambiance.util.MthHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnderMan.class)
public class EndermanMixin extends Monster {
    protected EndermanMixin(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
    public void aiStep(CallbackInfo ci) {
        if (random.nextInt(8) == 0){
            if (AmbianceConfig.enableEnderMan && AmbianceConfig.enderManParticles == AmbianceConfig.TYPE.FANCY) {
                this.level().addParticle(TrialOption.create(ParticleRegistry.PORTAL ,(int)(Math.random() * 10.0) + 40, 1F, 0.01F,0.1F, MthHelper.randomDarkerColor("CC00FA") , 1F),this.getRandomX(0.5), this.getRandomY() - 0.25, this.getRandomZ(0.5), (this.random.nextDouble() - 0.5) * 2.5, -this.random.nextDouble(), (this.random.nextDouble() - 0.5) * 2.5);
            }
        }
    }

    @ModifyArg(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
    public ParticleOptions animateTick(ParticleOptions particleOptions) {
        if (AmbianceConfig.enderManParticles == AmbianceConfig.TYPE.VANILLA){
            return ParticleTypes.PORTAL;
        } else if (AmbianceConfig.enderManParticles == AmbianceConfig.TYPE.FANCY){
            return ParticleRegistry.AIR;
        } else return ParticleRegistry.AIR;
    }
}
