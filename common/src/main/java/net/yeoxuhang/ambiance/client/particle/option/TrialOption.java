package net.yeoxuhang.ambiance.client.particle.option;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.FastColor;
import net.yeoxuhang.ambiance.client.particle.ParticleRegistry;

public class TrialOption implements ParticleOptions {
    public static final MapCodec<TrialOption> CODEC;
    public static final StreamCodec<RegistryFriendlyByteBuf, TrialOption> STREAM_CODEC;
    private final int age;
    private final float gravity;
    private final float speed;
    private final float size;
    private final int color;
    private final float alpha;
    private ParticleType<TrialOption> particleType;

    public TrialOption(int age, float gravity, float speed, float size, int color, float alpha) {
        this.age = age;
        this.gravity = gravity;
        this.speed = speed;
        this.size = size;
        this.color = color;
        this.alpha = alpha;
    }

    public TrialOption(ParticleType<TrialOption> particleType, int age, float gravity, float speed, float size, int color, float alpha) {
        this.particleType = particleType;
        this.speed = speed;
        this.age = age;
        this.color = color;
        this.alpha = alpha;
        this.size = size;
        this.gravity = gravity;
    }

    public ParticleType<TrialOption> getType() {
        if (particleType != null) {
            return particleType;
        } else {
            return ParticleRegistry.TRIAL.get();
        }
    }

    public int getAge() {
        return this.age;
    }

    public float getGravity() {
        return this.gravity;
    }

    public float getSpeed() {
        return speed;
    }

    public float getSize() {
        return size;
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
        return alpha;
    }

    public static TrialOption create(ParticleType<TrialOption> particleType, int age, float gravity, float speed, float size, int color, float alpha) {
        return new TrialOption(particleType, age, gravity, speed, size, color, alpha);
    }

    public static TrialOption create(int age, float gravity, float speed, float size, int color, float alpha) {
        return new TrialOption(age, gravity, speed, size, color, alpha);
    }

    public static TrialOption create(int age, float gravity, float speed, float size,float red, float green, float blue) {
        return create(age, gravity, speed, size, FastColor.ARGB32.colorFromFloat(1.0F, red, green, blue), 1.0F);
    }

    static {
        CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
                Codec.INT.fieldOf("age").forGetter((EndOption) -> EndOption.age),
                Codec.FLOAT.fieldOf("gravity").forGetter((EndOption) -> EndOption.gravity),
                Codec.FLOAT.fieldOf("speed").forGetter((EndOption) -> EndOption.speed),
                Codec.FLOAT.fieldOf("size").forGetter((EndOption) -> EndOption.size),
                ExtraCodecs.ARGB_COLOR_CODEC.fieldOf("color").forGetter(EndOption -> EndOption.color),
                Codec.FLOAT.fieldOf("alpha").forGetter(EndOption -> EndOption.alpha)).
                apply(instance, TrialOption::new)
        );
        STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.VAR_INT,
                EndOption -> EndOption.age,
                ByteBufCodecs.FLOAT,
                EndOption -> EndOption.gravity,
                ByteBufCodecs.FLOAT,
                EndOption -> EndOption.speed,
                ByteBufCodecs.FLOAT,
                EndOption -> EndOption.size,
                ByteBufCodecs.INT,
                EndOption -> EndOption.color,
                ByteBufCodecs.FLOAT,
                EndOption -> EndOption.alpha,
                TrialOption::new);
    }
}
