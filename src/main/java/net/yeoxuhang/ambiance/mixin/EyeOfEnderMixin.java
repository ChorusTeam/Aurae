package net.yeoxuhang.ambiance.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.EyeOfEnderEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.yeoxuhang.ambiance.Ambiance;
import net.yeoxuhang.ambiance.client.particle.ParticleRegistry;
import net.yeoxuhang.ambiance.client.particle.option.TrialOption;
import net.yeoxuhang.ambiance.config.AmbianceConfig;
import net.yeoxuhang.ambiance.util.MthHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EyeOfEnderEntity.class)
public abstract class EyeOfEnderMixin extends Entity {


    public EyeOfEnderMixin(EntityType<?> p_i48580_1_, World p_i48580_2_) {
        super(p_i48580_1_, p_i48580_2_);
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particles/IParticleData;DDDDDD)V", ordinal = 1))
    public void tick(CallbackInfo ci) {
        Vector3d vec3 = this.getDeltaMovement();
        double d = this.getX() + vec3.x;
        double e = this.getY() + vec3.y;
        double f = this.getZ() + vec3.z;
        if (Ambiance.config.items.eyeOfEnder.eyeOfEnderType == AmbianceConfig.ambiance$type.FANCY){
            this.level.addAlwaysVisibleParticle(TrialOption.create(ParticleRegistry.PORTAL.get() ,(int)(Math.random() * 10.0) + 40, 1F, 0.01F,0.1F, MthHelper.randomDarkerColor("CC00FA") , 1F), d - vec3.x * 0.25 + this.random.nextDouble() * 0.6 - 0.3, e - vec3.y * 0.25 - 0.5, f - vec3.z * 0.25 + this.random.nextDouble() * 0.6 - 0.3, vec3.x, vec3.y, vec3.z);
        }
    }

    @ModifyArg(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particles/IParticleData;DDDDDD)V", ordinal = 1))
    public IParticleData eyeTrial(IParticleData p_195594_1_) {
        if (Ambiance.config.items.eyeOfEnder.eyeOfEnderType == AmbianceConfig.ambiance$type.VANILLA){
            return ParticleTypes.PORTAL;
        } else return ParticleRegistry.AIR.get();
    }
}
