package net.yeoxuhang.ambiance.util;

import net.minecraft.util.math.MathHelper;

public class ColorUtil {
    public ColorUtil() {
    }

    public static int as8BitChannel(float pValue) {
        return MathHelper.floor(pValue * 255.0F);
    }

    public static class ARGB32 {
        public ARGB32() {
        }

        public static int alpha(int pPackedColor) {
            return pPackedColor >>> 24;
        }

        public static int red(int pPackedColor) {
            return pPackedColor >> 16 & 255;
        }

        public static int green(int pPackedColor) {
            return pPackedColor >> 8 & 255;
        }

        public static int blue(int pPackedColor) {
            return pPackedColor & 255;
        }

        public static int color(int pAlpha, int pRed, int pGreen, int pBlue) {
            return pAlpha << 24 | pRed << 16 | pGreen << 8 | pBlue;
        }

        public static int color(int pRed, int pGreen, int pBlue) {
            return color(255, pRed, pGreen, pBlue);
        }

        public static int multiply(int pPackedColourOne, int pPackedColorTwo) {
            return color(alpha(pPackedColourOne) * alpha(pPackedColorTwo) / 255, red(pPackedColourOne) * red(pPackedColorTwo) / 255, green(pPackedColourOne) * green(pPackedColorTwo) / 255, blue(pPackedColourOne) * blue(pPackedColorTwo) / 255);
        }
        public static int opaque(int pColor) {
            return pColor | -16777216;
        }

        public static int color(int pAlpha, int pColor) {
            return pAlpha << 24 | pColor & 16777215;
        }

        public static int colorFromFloat(float pAlpha, float pRed, float pGreen, float pBlue) {
            return color(ColorUtil.as8BitChannel(pAlpha), ColorUtil.as8BitChannel(pRed), ColorUtil.as8BitChannel(pGreen), ColorUtil.as8BitChannel(pBlue));
        }

        public static int average(int pColor1, int pColor2) {
            return color((alpha(pColor1) + alpha(pColor2)) / 2, (red(pColor1) + red(pColor2)) / 2, (green(pColor1) + green(pColor2)) / 2, (blue(pColor1) + blue(pColor2)) / 2);
        }
    }

    public static class ABGR32 {
        public ABGR32() {
        }

        public static int alpha(int pPackedColor) {
            return pPackedColor >>> 24;
        }

        public static int red(int pPackedColor) {
            return pPackedColor & 255;
        }

        public static int green(int pPackedColor) {
            return pPackedColor >> 8 & 255;
        }

        public static int blue(int pPackedColor) {
            return pPackedColor >> 16 & 255;
        }

        public static int transparent(int pPackedColor) {
            return pPackedColor & 16777215;
        }

        public static int opaque(int pPackedColor) {
            return pPackedColor | -16777216;
        }

        public static int color(int pAlpha, int pBlue, int pGreen, int pRed) {
            return pAlpha << 24 | pBlue << 16 | pGreen << 8 | pRed;
        }

        public static int color(int pAlpha, int pPackedColor) {
            return pAlpha << 24 | pPackedColor & 16777215;
        }

        public static int fromArgb32(int pColor) {
            return pColor & -16711936 | (pColor & 16711680) >> 16 | (pColor & 255) << 16;
        }
    }
}
