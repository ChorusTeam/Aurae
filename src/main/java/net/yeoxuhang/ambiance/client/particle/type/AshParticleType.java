package net.yeoxuhang.ambiance.client.particle.type;

import com.mojang.serialization.Codec;
import net.minecraft.particles.ParticleType;
import net.yeoxuhang.ambiance.client.particle.option.AshOption;

public class AshParticleType extends ParticleType<AshOption> {
    public static final Codec<AshOption> CODEC = AshOption.CODEC;

    public AshParticleType(boolean alwaysShow) {
        super(alwaysShow, AshOption.DESERIALIZER);
    }

    @Override
    public Codec<AshOption> codec() {
        return CODEC;
    }
}
