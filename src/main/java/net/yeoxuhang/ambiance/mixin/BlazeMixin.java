package net.yeoxuhang.ambiance.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.yeoxuhang.ambiance.Ambiance;
import net.yeoxuhang.ambiance.client.particle.ParticleRegistry;
import net.yeoxuhang.ambiance.client.particle.option.ColorParticleOption;
import net.yeoxuhang.ambiance.config.AmbianceConfig;
import net.yeoxuhang.ambiance.util.MthHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Blaze.class)
public class BlazeMixin extends Monster {


    protected BlazeMixin(EntityType<? extends Monster> p_i48553_1_, Level p_i48553_2_) {
        super(p_i48553_1_, p_i48553_2_);
    }

    @WrapWithCondition(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
    public boolean ambiance$aiStep(Level instance, ParticleOptions p_195594_1_, double p_195594_2_, double p_195594_4_, double p_195594_6_, double p_195594_8_, double p_195594_10_, double p_195594_12_) {
        return random.nextInt(8) == 0;
    }

    @Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
    public void ambiance$aiStep(CallbackInfo ci) {
        if (Ambiance.config.entities.blaze.enableParticle) {
            if (random.nextInt(8) == 0){
                int random = MthHelper.createRandomColor(16758599, 16775239);
                this.level.addAlwaysVisibleParticle(ColorParticleOption.create(ParticleRegistry.FIRE_ASH, random), this.getRandomX(0.5), this.getRandomY(), this.getRandomZ(0.5), 0.0, 0.0, 0.0);
            }
        }
    }

    @ModifyArg(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
    public ParticleOptions animateTick(ParticleOptions p_195594_1_) {
        if (Ambiance.config.entities.blaze.blazeParticle == AmbianceConfig.ambiance$type2.VANILLA){
            return ParticleTypes.LARGE_SMOKE;
        } else return ParticleRegistry.AIR;
    }
}
