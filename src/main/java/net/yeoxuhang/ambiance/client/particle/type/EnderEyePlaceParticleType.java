package net.yeoxuhang.ambiance.client.particle.type;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleType;
import net.yeoxuhang.ambiance.client.particle.option.ColorParticleOption;

public class EnderEyePlaceParticleType extends ParticleType<ColorParticleOption> {
    public static final Codec<ColorParticleOption> CODEC = ColorParticleOption.CODEC;

    public EnderEyePlaceParticleType(boolean alwaysShow) {
        super(alwaysShow, ColorParticleOption.DESERIALIZER);
    }

    @Override
    public Codec<ColorParticleOption> codec() {
        return CODEC;
    }
}
