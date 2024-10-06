package net.yeoxuhang.ambiance.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.yeoxuhang.ambiance.client.particle.option.TrialOption;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnderMan.class)
public class EndermanMixin extends Monster {
    protected EndermanMixin(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
    public void aiStep(CallbackInfo ci) {
        if (random.nextInt(10) == 0){
            for(int i = 0; i < 2; ++i) {
                this.level().addParticle(TrialOption.create((int)(Math.random() * 10.0) + 40, 1F, 0.01F,0.1F,13369594, 1F), this.getRandomX(0.5), this.getRandomY(), this.getRandomZ(0.5), (this.random.nextDouble() - 0.5) * 1.5, -this.random.nextDouble(), (this.random.nextDouble() - 0.5) * 1.5);
            }
        }
    }
}
