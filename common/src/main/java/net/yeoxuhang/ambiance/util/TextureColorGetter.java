package net.yeoxuhang.ambiance.util;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class TextureColorGetter {
    public static String getHexColorFromTexture(String modid, String texturePath, int x, int y) {
        try {
            ResourceLocation resourceLocation = ResourceLocation.tryBuild(modid, texturePath);
            InputStream inputStream = Minecraft.getInstance().getResourceManager().getResource(resourceLocation).get().open();
            BufferedImage image = ImageIO.read(inputStream);
            int pixelColor = image.getRGB(x, y);
            Color color = new Color(pixelColor, true); // Create a Color object

            String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
            return hex;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
