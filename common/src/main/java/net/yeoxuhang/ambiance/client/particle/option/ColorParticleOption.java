package net.yeoxuhang.ambiance.client.particle.option;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;


public class ColorParticleOption implements ParticleOptions {

    public static final Codec<ColorParticleOption> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Codec.INT.fieldOf("color").forGetter(ColorParticleOption::getColor)
    ).apply(instance, color -> new ColorParticleOption(null, color)));


    public static final Deserializer<ColorParticleOption> DESERIALIZER = new Deserializer<>() {
        @Override
        public ColorParticleOption fromCommand(ParticleType<ColorParticleOption> type, StringReader reader) throws CommandSyntaxException {
            reader.expect(' ');
            return new ColorParticleOption(type, reader.readInt());
        }

        @Override
        public ColorParticleOption fromNetwork(ParticleType<ColorParticleOption> type, FriendlyByteBuf buffer) {
            return new ColorParticleOption(type, buffer.readInt());
        }
    };

    private final ParticleType<ColorParticleOption> particleType;
    private final int color;

    public ColorParticleOption(ParticleType<ColorParticleOption> particleType, int color) {
        this.particleType = particleType;
        this.color = color;
    }

    @Override
    public ParticleType<ColorParticleOption> getType() {
        return this.particleType;
    }

    @Override
    public void writeToNetwork(FriendlyByteBuf buffer) {
        buffer.writeInt(this.color);
    }

    @Override
    public String writeToString() {
        return String.format("ColorParticleOption{color=%d}", this.color);
    }

    public int getColor() { return this.color; }
    public float getRed() { return (this.color >> 16 & 255) / 255.0F; }
    public float getGreen() { return (this.color >> 8 & 255) / 255.0F; }
    public float getBlue() { return (this.color & 255) / 255.0F; }

    public static ColorParticleOption create(ParticleType<ColorParticleOption> particleType, int id){
        return new ColorParticleOption(particleType, id);
    }
}

