package net.yeoxuhang.ambiance.client.particle.option;

import com.mojang.serialization.MapCodec;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.FastColor;
public class FireAshOption implements ParticleOptions {
    private final ParticleType<FireAshOption> type;
    private final int color;

    public static MapCodec<FireAshOption> codec(ParticleType<FireAshOption> particleType) {
        return ExtraCodecs.ARGB_COLOR_CODEC.xmap((integer) -> new FireAshOption(particleType, integer), (FireAshOption) -> FireAshOption.color).fieldOf("color");
    }

    public static StreamCodec<? super ByteBuf, FireAshOption> streamCodec(ParticleType<FireAshOption> particleType) {
        return ByteBufCodecs.INT.map((integer) -> {
            return new FireAshOption(particleType, integer);
        }, (FireAshOption) -> {
            return FireAshOption.color;
        });
    }

    private FireAshOption(ParticleType<FireAshOption> particleType, int i) {
        this.type = particleType;
        this.color = i;
    }

    public ParticleType<FireAshOption> getType() {
        return this.type;
    }

    public float getRed() {
        return (float) FastColor.ARGB32.red(this.color) / 255.0F;
    }

    public float getGreen() {
        return (float) FastColor.ARGB32.green(this.color) / 255.0F;
    }

    public float getBlue() {
        return (float) FastColor.ARGB32.blue(this.color) / 255.0F;
    }

    public float getAlpha() {
        return (float) FastColor.ARGB32.alpha(this.color) / 255.0F;
    }

    public static FireAshOption create(ParticleType<FireAshOption> particleType, int i) {
        return new FireAshOption(particleType, i);
    }

    public static FireAshOption create(ParticleType<FireAshOption> particleType, float f, float g, float h) {
        return create(particleType, FastColor.ARGB32.colorFromFloat(1.0F, f, g, h));
    }
}
