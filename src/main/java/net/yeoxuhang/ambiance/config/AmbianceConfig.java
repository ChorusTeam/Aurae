package net.yeoxuhang.ambiance.config;

import dev.toma.configuration.config.Config;
import dev.toma.configuration.config.Configurable;
import net.yeoxuhang.ambiance.Ambiance;

@Config(id = Ambiance.MOD_ID)
public class AmbianceConfig {

    public AmbianceConfig(){}

    @Configurable
    public blocksCategory blocks = new blocksCategory();

    public static class blocksCategory {
        

        
        @Configurable
        public endPortalFrameCategory endPortalFrame = new endPortalFrameCategory();
        public static class endPortalFrameCategory {
            @Configurable
            @Configurable.Comment(value = "Enable Eye of Ender Placement")
            public boolean enableParticle = true;

            @Configurable

            @Configurable.DecimalRange(min = 0.5, max = 1.5)
            @Configurable.Comment("Particle Size")
            public float particleSize = 1;

            @Configurable
            @Configurable.StringPattern("#[0-9a-fA-F]{1,8}")
            @Configurable.Gui.ColorValue
            @Configurable.Comment("Particle Color")
            public String particleColor = "#C7FFBF";

            @Configurable
            @Configurable.Comment("Ash Type")
            public ambiance$type ashType = ambiance$type.FANCY;

            @Configurable
            @Configurable.Comment("Sound Volume")

            @Configurable.DecimalRange(min = 0.0, max = 1)
            public float soundVolume = 0.8f;

            @Configurable
            @Configurable.Comment("Sound Type")
            public ambiance$type soundType = ambiance$type.FANCY;
        }
        @Configurable
        public endPortalCategory endPortal = new endPortalCategory();
        public static class endPortalCategory {
            @Configurable
            @Configurable.Comment(value = "Enable End Portal Ambient")
            public boolean enableParticle = true;

            @Configurable

            @Configurable.DecimalRange(min = 0.5, max = 1.5)
            @Configurable.Comment("Particle Size")
            public float particleSize = 1;

            @Configurable
            @Configurable.Comment("Smoke Type")
            public ambiance$type smokeType = ambiance$type.VANILLA;
        }

        @Configurable
        public endGatewayCategory endGateway = new endGatewayCategory();
        public static class endGatewayCategory {
            @Configurable
            @Configurable.Comment(value = "Enable End Gateway Ambient")
            public boolean enableParticle = true;

            @Configurable
            @Configurable.DecimalRange(min = 0.5, max = 1.5)
            @Configurable.Comment("Particle Size")
            public float particleSize = 1;
        }

        @Configurable
        public fireCategory fire = new fireCategory();
        public static class fireCategory {
            @Configurable
            @Configurable.Comment(value = "Enable Fire Particle")
            public boolean enableParticle = true;

            @Configurable
            @Configurable.Comment("Smoke Type")
            public ambiance$type2 smokeType = ambiance$type2.VANILLA;
        }

        @Configurable
        public campfireCategory campfire = new campfireCategory();
        public static class campfireCategory {
            @Configurable
            @Configurable.Comment(value = "Enable Campfire Particle")
            public boolean enableParticle = true;

            @Configurable
            @Configurable.Comment("Smoke Type")
            public ambiance$type2 smokeType = ambiance$type2.VANILLA;
            @Configurable
            @Configurable.Comment("Flame Type")
            public ambiance$type2 flameType = ambiance$type2.VANILLA;
        }

        @Configurable
        public torchCategory torch = new torchCategory();
        public static class torchCategory {
            @Configurable
            @Configurable.Comment(value = "Enable Torch Particle")
            public boolean enableParticle = true;

            @Configurable
            @Configurable.Comment("Smoke Type")
            public ambiance$type2 smokeType = ambiance$type2.VANILLA;
            @Configurable
            @Configurable.Comment("Flame Type")
            public ambiance$type2 flameType = ambiance$type2.VANILLA;
        }

        @Configurable
        public cauldronCategory cauldron = new cauldronCategory();
        public static class cauldronCategory {
            @Configurable
            public waterCategory water = new waterCategory();
            public static class waterCategory {
                @Configurable
                @Configurable.Comment(value = "Enable Water Splash")
                public boolean enableParticle = true;
                @Configurable
                @Configurable.Comment(value = "Enable Water Splash Sound")
                public boolean enableSound = true;

                @Configurable
                @Configurable.Comment("Sound Volume")
                @Configurable.DecimalRange(min = 0.0, max = 1)
                public float soundVolume = 0.2f;
            }

            @Configurable
            public lavaCategory lava = new lavaCategory();
            public static class lavaCategory {
                @Configurable
                @Configurable.Comment(value = "Enable Lava Pop")
                public boolean enableParticle = true;
                @Configurable
                @Configurable.Comment(value = "Enable Lava Pop Sound")
                public boolean enableSound = true;

                @Configurable
                @Configurable.Comment("Sound Volume")

                @Configurable.DecimalRange(min = 0.0, max = 1)
                public float soundVolume = 0.2f;
            }
        }

        @Configurable
        public soulSandCategory soulSand = new soulSandCategory();
        public static class soulSandCategory {
            @Configurable
            @Configurable.Comment(value = "Enable Soul Sand Ambient")
            public boolean enableParticle = true;
        }

        @Configurable
        public netherPortalCategory netherPortal = new netherPortalCategory();
        public static class netherPortalCategory {
            @Configurable
            @Configurable.DecimalRange(min = 0.5, max = 1.5)
            @Configurable.Comment("Particle Size")
            public float particleSize = 1;

            @Configurable
            @Configurable.Comment("Portal Type")
            public ambiance$type portalType = ambiance$type.FANCY;
        }

        @Configurable
        public respawnAnchorCategory respawnAnchor = new respawnAnchorCategory();
        public static class respawnAnchorCategory {
            @Configurable
            @Configurable.DecimalRange(min = 0.5, max = 1.5)
            @Configurable.Comment("Particle Size")
            public float particleSize = 1;

            @Configurable
            @Configurable.Comment("Portal Type")
            public ambiance$type portalType = ambiance$type.FANCY;
        }
    }

    @Configurable
    public itemsCategory items = new itemsCategory();
    public static class itemsCategory {
        @Configurable
        public eyeOfEnderCategory eyeOfEnder = new eyeOfEnderCategory();
        public static class eyeOfEnderCategory {
            @Configurable
            @Configurable.Comment("Particle Type")
            public ambiance$type eyeOfEnderType = ambiance$type.FANCY;
        }
    }

    @Configurable
    public entitiesCategory entities = new entitiesCategory();
    public static class entitiesCategory {
        @Configurable
        public blazeCategory blaze = new blazeCategory();
        public static class blazeCategory {
            @Configurable
            public boolean enableParticle = true;
            @Configurable
            @Configurable.Comment("Particle Type")
            public ambiance$type2 blazeParticle = ambiance$type2.VANILLA;
        }

        @Configurable
        public endermanCategory enderman = new endermanCategory();
        public static class endermanCategory {
            @Configurable
            @Configurable.Comment("Particle Type")
            public ambiance$type enderManParticle = ambiance$type.FANCY;
        }
        @Configurable
        public endermiteCategory endermite = new endermiteCategory();
        public static class endermiteCategory {
            @Configurable
            @Configurable.Comment("Particle Type")
            public ambiance$type enderMiteParticle = ambiance$type.FANCY;
        }
    }

    @Configurable
    public otherModsCategory otherMods = new otherModsCategory();
    public static class otherModsCategory {
        @Configurable
        @Configurable.Comment("Enable End Remastered Eye of Ender Placement")
        public boolean endremCompat = true;
    }
    
    public enum ambiance$type {
        FANCY, VANILLA, NONE
    }

    public enum ambiance$type2 {
        VANILLA, NONE
    }
}
