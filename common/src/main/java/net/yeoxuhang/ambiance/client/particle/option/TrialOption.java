package net.yeoxuhang.ambiance.client.particle.option;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import net.yeoxuhang.ambiance.client.particle.ParticleRegistry;
import net.yeoxuhang.ambiance.util.ColorUtil;

public class TrialOption implements ParticleOptions {
    public static final Codec<TrialOption> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Codec.INT.fieldOf("age").forGetter(TrialOption::getAge),
            Codec.FLOAT.fieldOf("gravity").forGetter(TrialOption::getGravity),
            Codec.FLOAT.fieldOf("speed").forGetter(TrialOption::getSpeed),
            Codec.FLOAT.fieldOf("size").forGetter(TrialOption::getSize),
            Codec.INT.fieldOf("color").forGetter(TrialOption::getColor),
            Codec.FLOAT.fieldOf("alpha").forGetter(TrialOption::getAlpha)
    ).apply(instance, (age, gravity, speed, size, color, alpha) -> new TrialOption(null, age, gravity, speed, size, color, alpha)));

    public static final Deserializer<TrialOption> DESERIALIZER = new Deserializer<TrialOption>() {
        @Override
        public TrialOption fromCommand(ParticleType<TrialOption> type, StringReader reader) throws CommandSyntaxException {
            reader.expect(' ');
            int age = reader.readInt();
            float gravity = reader.readFloat();
            float speed = reader.readFloat();
            float size = reader.readFloat();
            int color = reader.readInt();
            float alpha = reader.readFloat();
            return new TrialOption(type, age, gravity, speed, size, color, alpha);
        }

        @Override
        public TrialOption fromNetwork(ParticleType<TrialOption> type, FriendlyByteBuf buffer) {
            int age = buffer.readInt();
            float gravity = buffer.readFloat();
            float speed = buffer.readFloat();
            float size = buffer.readFloat();
            int color = buffer.readInt();
            float alpha = buffer.readFloat();
            return new TrialOption(type, age, gravity, speed, size, color, alpha);
        }
    };

    private final int age;
    private final float gravity;
    private final float speed;
    private final float size;
    private final int color;
    private final float alpha;
    private final ParticleType<TrialOption> particleType;

    // Constructors
    public TrialOption(int age, float gravity, float speed, float size, int color, float alpha) {
        this(null, age, gravity, speed, size, color, alpha);
    }

    public TrialOption(ParticleType<TrialOption> particleType, int age, float gravity, float speed, float size, int color, float alpha) {
        this.particleType = particleType;
        this.age = age;
        this.gravity = gravity;
        this.speed = speed;
        this.size = size;
        this.color = color;
        this.alpha = alpha;
    }

    // Getters for each property
    public int getAge() {
        return age;
    }

    public float getGravity() {
        return gravity;
    }

    public float getSpeed() {
        return speed;
    }

    public float getSize() {
        return size;
    }

    public int getColor() {
        return color;
    }

    public float getAlpha() {
        return alpha;
    }

    // Color Helper Methods
    public float getRed() {
        return (float) (this.color >> 16 & 255) / 255.0F;
    }

    public float getGreen() {
        return (float) (this.color >> 8 & 255) / 255.0F;
    }

    public float getBlue() {
        return (float) (this.color & 255) / 255.0F;
    }

    @Override
    public ParticleType<TrialOption> getType() {
        return particleType != null ? particleType : ParticleRegistry.TRIAL.get();
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf buffer) {
        buffer.writeInt(this.age);
        buffer.writeFloat(this.gravity);
        buffer.writeFloat(this.speed);
        buffer.writeFloat(this.size);
        buffer.writeInt(this.color);
        buffer.writeFloat(this.alpha);
    }

    @Override
    public String writeToString() {
        return String.format("TrialOption{age=%d, gravity=%.2f, speed=%.2f, size=%.2f, color=%d, alpha=%.2f}",
                this.age, this.gravity, this.speed, this.size, this.color, this.alpha);
    }

    // Factory methods to create instances with different parameters
    public static TrialOption create(int age, float gravity, float speed, float size, int color, float alpha) {
        return new TrialOption(age, gravity, speed, size, color, alpha);
    }

    public static TrialOption create(ParticleType<TrialOption> particleType, int age, float gravity, float speed, float size, int color, float alpha) {
        return new TrialOption(particleType, age, gravity, speed, size, color, alpha);
    }

    public static TrialOption create(int age, float gravity, float speed, float size, float red, float green, float blue) {
        return create(age, gravity, speed, size, ColorUtil.ARGB32.colorFromFloat(1.0F, red, green, blue), 1.0F);
    }
}
