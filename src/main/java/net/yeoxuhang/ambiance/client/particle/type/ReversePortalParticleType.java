package net.yeoxuhang.ambiance.client.particle.type;

import com.mojang.serialization.Codec;
import net.minecraft.particles.ParticleType;
import net.yeoxuhang.ambiance.client.particle.option.TrialOption;

public class ReversePortalParticleType extends ParticleType<TrialOption> {
    public static final Codec<TrialOption> CODEC = TrialOption.CODEC;

    public ReversePortalParticleType(boolean alwaysShow) {
        super(alwaysShow, TrialOption.DESERIALIZER);
    }

    @Override
    public Codec<TrialOption> codec() {
        return CODEC;
    }
}
