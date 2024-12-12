package net.yeoxuhang.ambiance.client.particle.option;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import net.yeoxuhang.ambiance.client.particle.ParticleRegistry;

public class AshOption implements ParticleOptions {

    public static final Codec<AshOption> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Codec.INT.fieldOf("age").forGetter(AshOption::getAge),
            Codec.FLOAT.fieldOf("size").forGetter(AshOption::getSize),
            Codec.FLOAT.fieldOf("gravity").forGetter(AshOption::getGravity),
            Codec.INT.fieldOf("color").forGetter(AshOption::getColor),
            Codec.FLOAT.fieldOf("alpha").forGetter(AshOption::getAlpha),
            Codec.FLOAT.fieldOf("movementXY").forGetter(AshOption::getMovementXY)
    ).apply(instance, (age, size, gravity, color, alpha, movementXY) -> new AshOption(null, age, size, gravity, color, alpha, movementXY)));


    public static final Deserializer<AshOption> DESERIALIZER = new Deserializer<>() {
        @Override
        public AshOption fromCommand(ParticleType<AshOption> type, StringReader reader) throws CommandSyntaxException {
            reader.expect(' ');
            int age = reader.readInt();
            float size = reader.readFloat();
            float gravity = reader.readFloat();
            int color = reader.readInt();
            float alpha = reader.readFloat();
            float movementXY = reader.readFloat();
            return new AshOption(type, age, size, gravity, color, alpha, movementXY);
        }

        @Override
        public AshOption fromNetwork(ParticleType<AshOption> type, FriendlyByteBuf buffer) {
            return new AshOption(type, buffer.readInt(), buffer.readFloat(), buffer.readFloat(),
                    buffer.readInt(), buffer.readFloat(), buffer.readFloat());
        }
    };

    private final ParticleType<AshOption> particleType;
    private final int age;
    private final float size;
    private final float gravity;
    private final int color;
    private final float alpha;
    private final float movementXY;

    public AshOption(ParticleType<AshOption> particleType, int age, float size, float gravity, int color, float alpha, float movementXY) {
        this.particleType = particleType;
        this.age = age;
        this.size = size;
        this.gravity = gravity;
        this.color = color;
        this.alpha = alpha;
        this.movementXY = movementXY;
    }

    @Override
    public ParticleType<AshOption> getType() {
        if (particleType != null) {
            return ParticleRegistry.ASH.get();
        } else return this.particleType;
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf buffer) {
        buffer.writeInt(this.age);
        buffer.writeFloat(this.size);
        buffer.writeFloat(this.gravity);
        buffer.writeInt(this.color);
        buffer.writeFloat(this.alpha);
        buffer.writeFloat(this.movementXY);
    }

    @Override
    public String writeToString() {
        return String.format("AshOption{age=%d, size=%.2f, gravity=%.2f, color=%d, alpha=%.2f, movementXY=%.2f}",
                this.age, this.size, this.gravity, this.color, this.alpha, this.movementXY);
    }

    public int getAge() { return this.age; }
    public float getSize() { return this.size; }
    public float getGravity() { return this.gravity; }
    public int getColor() { return this.color; }
    public float getAlpha() { return this.alpha; }
    public float getMovementXY() { return this.movementXY; }

    public float getRed() { return (this.color >> 16 & 255) / 255.0F; }
    public float getGreen() { return (this.color >> 8 & 255) / 255.0F; }
    public float getBlue() { return (this.color & 255) / 255.0F; }

    public static AshOption create(int age, float size, float gravity, float movementXY, int color, float alpha) {
        return new AshOption(ParticleRegistry.ASH.get() , age, size, gravity, color, alpha, movementXY);
    }

    public static AshOption create(ParticleType<AshOption> particleType, int age, float size, float gravity, float movementXY, int color, float alpha) {
        return new AshOption(particleType, age, size, gravity, color, alpha, movementXY);
    }
}

