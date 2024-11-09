package net.yeoxuhang.ambiance.util;

import java.awt.*;
import java.util.Random;

public class MthHelper {
    private static final Random random = new Random();

    public static int createRandomColor(int color1, int color2) {
        // Extract RGB components of both colors
        int r1 = (color1 >> 16) & 0xFF;
        int g1 = (color1 >> 8) & 0xFF;
        int b1 = color1 & 0xFF;

        int r2 = (color2 >> 16) & 0xFF;
        int g2 = (color2 >> 8) & 0xFF;
        int b2 = color2 & 0xFF;

        // Ensure the range is valid for each component
        int rMin = Math.min(r1, r2);
        int rMax = Math.max(r1, r2);
        int gMin = Math.min(g1, g2);
        int gMax = Math.max(g1, g2);
        int bMin = Math.min(b1, b2);
        int bMax = Math.max(b1, b2);

        // Generate random RGB components between the min and max values
        int rRandom = rMin + random.nextInt((rMax - rMin) + 1);
        int gRandom = gMin + random.nextInt((gMax - gMin) + 1);
        int bRandom = bMin + random.nextInt((bMax - bMin) + 1);

        // Combine RGB components into a single color
        return (rRandom << 16) | (gRandom << 8) | bRandom;
    }

    public static double generateRandomNumber(double min, double max) {
        if (min > max) {
            throw new IllegalArgumentException("Minimum value cannot be greater than maximum value.");
        }
        double randomNumber = min + (max - min) * random.nextDouble();
        return Math.round(randomNumber * 100.0) / 100.0; // Round to 2 decimal places
    }

    public static int convertHexToDec(String hex) {
        // Check if the input string is null or empty
        if (hex == null || hex.isEmpty()) {
            return 0;
        }

        // Remove any characters that are not valid in hexadecimal notation
        hex = hex.replaceAll("[^0-9A-Fa-f]", "");
        if (hex.isEmpty()) {
            return 0;
        }

        // Parse the cleaned-up hexadecimal string
        return Integer.parseInt(hex.replace("#", ""), 16);
    }

    public static String convertDecToHex(int dec) {
        return Integer.toHexString(dec);
    }

    public static int randomDarkerColor(String hex) {
        Color color = Color.decode("#" + hex);

        Random random = new Random();
        float darkenFactor = 0.5f + random.nextFloat() * 0.5f; // Between 0.5 and 1.0

        int red = (int) (color.getRed() * darkenFactor);
        int green = (int) (color.getGreen() * darkenFactor);
        int blue = (int) (color.getBlue() * darkenFactor);

        // Make sure the values are within the valid range (0-255)
        red = Math.max(0, Math.min(255, red));
        green = Math.max(0, Math.min(255, green));
        blue = Math.max(0, Math.min(255, blue));

        return (red << 16) | (green << 8) | blue;
    }
}
