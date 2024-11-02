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


public class AshOption implements ParticleOptions {
    public static final MapCodec<AshOption> CODEC;
    public static final StreamCodec<RegistryFriendlyByteBuf, AshOption> STREAM_CODEC;
    private final int age;
    private final int color;
    private final float alpha;
    private final float size;
    private final float gravity;
    private final float movementXY;
    private ParticleType<AshOption> particleType;

    public AshOption(int age, float size, float gravity, int color, float alpha, float movementXY) {
        this.age = age;
        this.color = color;
        this.alpha = alpha;
        this.size = size;
        this.gravity = gravity;
        this.movementXY = movementXY;
    }

    public AshOption(ParticleType<AshOption> particleType, int age, float size, float gravity, int color, float alpha, float movementXY) {
        this.particleType = particleType;
        this.age = age;
        this.color = color;
        this.alpha = alpha;
        this.size = size;
        this.gravity = gravity;
        this.movementXY = movementXY;
    }

    public ParticleType<AshOption> getType() {
        if (particleType != null) {
            return particleType;
        } else {
            return ParticleRegistry.ASH.get();
        }
    }

    public int getAge() {
        return this.age;
    }

    public float getSize() {
        return size;
    }

    public float getGravity() {
        return gravity;
    }

    public float getMovementXY() {
        return movementXY;
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

    public static AshOption create(int age, float size, float gravity, float movementXY, int color, float alpha) {
        return new AshOption(age, size, gravity, color, alpha, movementXY);
    }

    public static AshOption create(ParticleType<AshOption> particleType, int age, float size, float gravity, float movementXY, int color, float alpha) {
        return new AshOption(particleType, age, size, gravity, color, alpha, movementXY);
    }

    public static AshOption create(int age, float size, float gravity, float movementXY, float red, float green, float blue) {
        return create(age, size , gravity, movementXY, FastColor.ARGB32.colorFromFloat(1.0F, red, green, blue), 1.0F);
    }

    static {
        CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
                Codec.INT.fieldOf("age").forGetter((AshOption) -> AshOption.age),
                Codec.FLOAT.fieldOf("size").forGetter((AshOption) -> AshOption.size),
                Codec.FLOAT.fieldOf("gravity").forGetter((AshOption) -> AshOption.gravity),
                ExtraCodecs.ARGB_COLOR_CODEC.fieldOf("color").forGetter((AshOption) -> AshOption.color),
                Codec.FLOAT.fieldOf("alpha").forGetter(AshOption -> AshOption.alpha),
                Codec.FLOAT.fieldOf("movementXY").forGetter((AshOption) -> AshOption.movementXY)
                ).
                apply(instance, AshOption::new)
        );
        STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.VAR_INT,
                ashOption -> ashOption.age,
                ByteBufCodecs.FLOAT,
                ashOption -> ashOption.size,
                ByteBufCodecs.FLOAT,
                ashOption -> ashOption.gravity,
                ByteBufCodecs.INT,
                ashOption -> ashOption.color,
                ByteBufCodecs.FLOAT,
                ashOption -> ashOption.alpha,
                ByteBufCodecs.FLOAT,
                ashOption -> ashOption.movementXY,
                AshOption::new);
    }
}
