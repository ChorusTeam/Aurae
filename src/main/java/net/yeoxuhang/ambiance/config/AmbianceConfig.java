package net.yeoxuhang.ambiance.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class AmbianceConfig extends MidnightConfig {

    public static final String PARTICLE = "particle";



    public static final String blocks = "blocks";
    public static final String items = "items";
    public static final String entities = "entities";

    @Comment(category = blocks, centered = true)
    public static Comment endPortalFrame;
    @Entry(category = blocks, name = "Enable Particle")
    public static boolean enableEnderEyePlace = true;
    @Entry(category = blocks, name = "Particle Size", isSlider = true, min = 0.5f, max = 1.5f)
    public static float enderEyePlaceSize = 1f;
    @Entry(category = blocks, width = 7, min = 0, max = 7, name = "Color", isColor = true)
    public static String endPortalEyePlaced = "#C7FFBF";
    @Entry(category = blocks, name = "Eye Place Sound")
    public static TYPE eyePlaceSoundType = TYPE.FANCY;
    @Entry(category = blocks, name = "Sound Volume", isSlider = true, min = 0.0f, max = 1f)
    public static float eyePlaceSoundVolume = 0.8f;


    @Comment(category = blocks, centered = true)
    public static Comment endPortal;
    @Entry(category = blocks, name = "Enable Particle")
    public static boolean enableEndPortal = true;
    @Entry(category = blocks, name = "Particle Size", isSlider = true, min = 0.5f, max = 1.5f)
    public static float enderEndPortalSize = 1f;
    @Entry(category = blocks, name = "Smoke")
    public static TYPE smokeType = TYPE.VANILLA;

    @Comment(category = blocks, centered = true)
    public static Comment endGetaway;
    @Entry(category = blocks, name = "Enable Particle")
    public static boolean enableEndGetaway = true;
    @Entry(category = blocks, name = "Particle Size", isSlider = true, min = 0.5f, max = 1.5f)
    public static float enderEndGetawaySize = 1f;

    @Comment(category = entities, centered = true)
    public static Comment blaze;
    @Entry(category = entities, name = "Enable Blaze Sparkle")
    public static boolean enableBlaze = true;
    @Entry(category = entities, name = "Smoke")
    public static smokesType blazeParticles = smokesType.VANILLA;
    @Comment(category = entities, centered = true)
    public static Comment enderman;
    @Entry(category = entities, name = "Enable Particle")
    public static boolean enableEnderMan = true;
    @Entry(category = entities, name = "Particle")
    public static TYPE enderManParticles = TYPE.FANCY;
    @Comment(category = entities, centered = true)
    public static Comment endermite;
    @Entry(category = entities, name = "Enable Particle")
    public static boolean enableEnderMite = true;
    @Entry(category = entities, name = "Particle")
    public static TYPE enderMiteParticles = TYPE.FANCY;

    @Comment(category = blocks, centered = true)
    public static Comment fire;
    @Entry(category = blocks, name = "Enable Particle")
    public static boolean enableFire = true;
    @Entry(category = blocks, name = "Smoke")
    public static smokesType fireSmoke = smokesType.VANILLA;

    @Comment(category = blocks, centered = true)
    public static Comment campfire;
    @Entry(category = blocks, name = "Enable Particle")
    public static boolean enableCampfire = true;
    @Entry(category = blocks, name = "Smoke")
    public static smokesType campfireSmoke = smokesType.VANILLA;
    @Entry(category = blocks, name = "Flame")
    public static smokesType campfireFlame = smokesType.VANILLA;

    @Comment(category = blocks, centered = true)
    public static Comment torch;
    @Entry(category = blocks, name = "Enable Particle")
    public static boolean enableTorch = true;
    @Entry(category = blocks, name = "Smoke")
    public static smokesType torchSmoke = smokesType.VANILLA;
    @Entry(category = blocks, name = "Flame")
    public static smokesType torchFlame = smokesType.VANILLA;

    @Comment(category = blocks, centered = true)
    public static Comment candle;
    @Entry(category = blocks, name = "Enable Particle")
    public static boolean enableCandle = true;
    @Entry(category = blocks, name = "Smoke")
    public static smokesType candleSmoke = smokesType.VANILLA;
    @Entry(category = blocks, name = "Flame")
    public static smokesType candleFlame = smokesType.VANILLA;

    @Comment(category = blocks, centered = true)
    public static Comment soulSand;
    @Entry(category = blocks, name = "Enable Particle")
    public static boolean enableSoulSand = true;

    @Comment(category = blocks, centered = true)
    public static Comment decoratedPot;
    @Entry(category = blocks, name = "Enable Particle")
    public static boolean enableDecoratedPot = true;
    @Entry(category = blocks, name = "Water Bubble Sound")
    public static boolean decoratedPotUnderwaterBubble = true;
    @Entry(category = blocks, name = "Sound Volume", isSlider = true, min = 0.0f, max = 1f)
    public static float decoratedPotUnderwaterBubbleSoundVolume = 0.2f;

    @Comment(category = blocks, centered = true)
    public static Comment sculkBlock;
    @Entry(category = blocks, name = "Enable Particle")
    public static boolean enableSculkCharge = true;

    @Comment(category = blocks, centered = true)
    public static Comment waterCauldron;
    @Entry(category = blocks, name = "Enable Splash Particle")
    public static boolean enableWaterCauldron = true;
    @Entry(category = blocks, name = "Splash Sound")
    public static boolean waterSplashSound = true;
    @Entry(category = blocks, name = "Sound Volume", isSlider = true, min = 0.0f, max = 1f)
    public static float waterSplashSoundVolume = 0.2f;
    @Comment(category = blocks, centered = true)
    public static Comment lavaCauldron;
    @Entry(category = blocks, name = "Enable Lava Pop Particle")
    public static boolean enableLavaCauldron = true;
    @Entry(category = blocks, name = "Pop Sound")
    public static boolean lavaPopSound = true;
    @Entry(category = blocks, name = "Sound Volume", isSlider = true, min = 0.0f, max = 1f)
    public static float lavaPopSoundVolume = 0.2f;

    //@Comment(category = blocks) public static Comment spacer1; // Comments containing the word "spacer" will just appear as a blank line
    @Comment(category = blocks, centered = true)
    public static Comment trialChamberBlocks;
    @Entry(category = blocks, name = "Enable Spawner")
    public static boolean enableSpawner = true;
    @Entry(category = blocks, name = "Enable Vault")
    public static boolean enableVault = true;
    @Entry(category = blocks, name = "Enable Item Plume")
    public static boolean enableItemPop = true;


    @Comment(category = items, centered = true)
    public static Comment eyeEnder;
    @Entry(category = items, name = "Enable Particle")
    public static boolean enableEyeEnder = true;
    @Entry(category = items, name = "Particle")
    public static TYPE eyeEnderParticles = TYPE.FANCY;

    public static final String otherMods = "otherMods";
    @Entry(category = otherMods, name = "End Remastered Compat")
    public static boolean endremCompat = true;

    public enum TYPE {
        FANCY, VANILLA, NONE
    }

    public enum smokesType {
        VANILLA, NONE
    }
}
