package net.yeoxuhang.ambiance.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class AmbianceConfig extends MidnightConfig {
    public static final String SOUND = "sound";
    public static final String PARTICLE = "particle";

    @Entry(category = SOUND, name = "Enable Eye of Ender")
    public static boolean enableEyePlaceSound = true;
    @Entry(category = SOUND, name = "Enable Lava Cauldron")
    public static boolean enableLavaCauldronSound = true;
    @Entry(category = SOUND, name = "Enable Water Cauldron")
    public static boolean enableWaterCauldronSound = true;

    @Comment(category = PARTICLE, centered = true)
    public static Comment endPortal;
    @Entry(category = PARTICLE, name = "Enable Particle")
    public static boolean enableEndPortal = true;
    @Entry(category = PARTICLE, name = "Particle Size", isSlider = true, min = 0.05f, max = 0.1f)
    public static float enderEndPortalSize = 0.05f;


    @Comment(category = PARTICLE, centered = true)
    public static Comment endGetaway;
    @Entry(category = PARTICLE, name = "Enable Particle")
    public static boolean enableEndGetaway = true;
    @Entry(category = PARTICLE, name = "Particle Size", isSlider = true, min = 0.5f, max = 1.5f)
    public static float enderEndGetawaySize = 0.5f;


    @Comment(category = PARTICLE, centered = true)
    public static Comment endPortalFrame;
    @Entry(category = PARTICLE, name = "Enable Particle")
    public static boolean enableEnderEyePlace = true;
    @Entry(category = PARTICLE, name = "Particle Size", isSlider = true, min = 0.1f, max = 0.2f)
    public static float enderEyePlaceSize = 0.1f;
    @Entry(category = PARTICLE, width = 7, min = 0, max = 7, name = "Color", isColor = true)
    public static String endPortalEyePlaced = "#C7FFBF";

    @Comment(category = PARTICLE, centered = true)
    public static Comment entities;
    @Entry(category = PARTICLE, name = "Enable Blaze Sparkle")
    public static boolean enableBlaze = true;
    @Entry(category = PARTICLE, name = "Enable Enderman Particle")
    public static boolean enableEnderMan = true;
    @Entry(category = PARTICLE, name = "Enable Endermite Particle")
    public static boolean enableEnderMite = true;

    @Comment(category = PARTICLE, centered = true)
    public static Comment burning;
    @Entry(category = PARTICLE, name = "Enable Fire")
    public static boolean enableFire = true;
    @Entry(category = PARTICLE, name = "Enable Campfire")
    public static boolean enableCampfire = true;
    @Entry(category = PARTICLE, name = "Enable Torch")
    public static boolean enableTorch = true;
    @Entry(category = PARTICLE, name = "Enable Candle")
    public static boolean enableCandle = true;
    @Entry(category = PARTICLE, name = "Enable Lava Cauldron")
    public static boolean enableLavaCauldron = true;

    @Comment(category = PARTICLE, centered = true)
    public static Comment otherBlocks;
    @Entry(category = PARTICLE, name = "Enable Soul Sand")
    public static boolean enableSoulSand = true;
    @Entry(category = PARTICLE, name = "Enable Decorated Pot")
    public static boolean enableDecoratedPot = true;
    @Entry(category = PARTICLE, name = "Enable Respawn Anchor")
    public static boolean enableRespawnAnchor = true;
    @Entry(category = PARTICLE, name = "Respawn Anchor Particle Size", isSlider = true, min = 0.01f, max = 0.1f)
    public static float respawnAnchorSize = 0.06f;
    @Entry(category = PARTICLE, name = "Enable Sculk Charges")
    public static boolean enableSculkCharge = true;
    @Entry(category = PARTICLE, name = "Enable Water Cauldron")
    public static boolean enableWaterCauldron = true;

    @Comment(category = PARTICLE, centered = true)
    public static Comment trialChamber;
    @Entry(category = PARTICLE, name = "Enable Spawner")
    public static boolean enableSpawner = true;
    @Entry(category = PARTICLE, name = "Enable Vault")
    public static boolean enableVault = true;
    @Entry(category = PARTICLE, name = "Enable Item Plume")
    public static boolean enableItemPop = true;
}
