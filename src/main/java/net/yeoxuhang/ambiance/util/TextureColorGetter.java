package net.yeoxuhang.ambiance.util;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class TextureColorGetter {
    public static String getHexColorFromTexture(String modid, String texturePath, int x, int y) {
        ResourceLocation resourceLocation = new ResourceLocation(modid, texturePath);

        try {
            // Access the resource
            Resource resource = Minecraft.getInstance().getResourceManager().getResource(resourceLocation);
            InputStream inputStream = resource.getInputStream();

            // Read the image and retrieve the pixel color
            BufferedImage image = ImageIO.read(inputStream);
            int pixelColor = image.getRGB(x, y);

            // Convert to hex
            Color color = new Color(pixelColor, true);
            String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());

            return hex;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
