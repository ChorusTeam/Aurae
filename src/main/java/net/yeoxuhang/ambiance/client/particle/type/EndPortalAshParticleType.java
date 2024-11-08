package net.yeoxuhang.ambiance.client.particle.type;

import com.mojang.serialization.Codec;
import net.minecraft.particles.ParticleType;
import net.yeoxuhang.ambiance.client.particle.option.AshOption;

public class EndPortalAshParticleType extends ParticleType<AshOption> {
    public static final Codec<AshOption> CODEC = AshOption.CODEC;

    public EndPortalAshParticleType(boolean alwaysShow) {
        super(alwaysShow, AshOption.DESERIALIZER);
    }

    @Override
    public Codec<AshOption> codec() {
        return CODEC;
    }
}
