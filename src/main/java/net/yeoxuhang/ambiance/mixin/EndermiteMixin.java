package net.yeoxuhang.ambiance.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.EndermiteEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
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

@Mixin(EndermiteEntity.class)
public class EndermiteMixin extends MonsterEntity {

    protected EndermiteMixin(EntityType<? extends MonsterEntity> p_i48553_1_, World p_i48553_2_) {
        super(p_i48553_1_, p_i48553_2_);
    }

    @Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particles/IParticleData;DDDDDD)V"))
    public void aiStep(CallbackInfo ci) {
        if (random.nextInt(8) == 0){
            if (Ambiance.config.entities.endermite.enderMiteParticle == AmbianceConfig.ambiance$type.FANCY) {
                this.level.addAlwaysVisibleParticle(TrialOption.create(ParticleRegistry.PORTAL.get() ,(int)(Math.random() * 10.0) + 40, 1F, 0.01F,0.1F, MthHelper.randomDarkerColor("CC00FA") , 1F),this.getRandomX(0.5), this.getRandomY() - 0.25, this.getRandomZ(0.5), (this.random.nextDouble() - 0.5) * 2.5, -this.random.nextDouble(), (this.random.nextDouble() - 0.5) * 2.5);
            }
        }
    }

    @ModifyArg(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particles/IParticleData;DDDDDD)V"))
    public IParticleData animateTick(IParticleData p_195594_1_) {
        if (Ambiance.config.entities.endermite.enderMiteParticle == AmbianceConfig.ambiance$type.VANILLA){
            return ParticleTypes.PORTAL;
        } else if (Ambiance.config.entities.endermite.enderMiteParticle == AmbianceConfig.ambiance$type.FANCY){
            return ParticleRegistry.AIR.get();
        } else return ParticleRegistry.AIR.get();
    }
}
