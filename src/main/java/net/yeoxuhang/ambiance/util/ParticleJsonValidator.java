package net.yeoxuhang.ambiance.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.resources.IResource;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.Minecraft;

import java.io.InputStreamReader;
import java.io.IOException;

public class ParticleJsonValidator {
    public static void validateParticleJson(ResourceLocation particleJson) {
        try {
            IResource resource = Minecraft.getInstance().getResourceManager().getResource(particleJson);
            try (InputStreamReader reader = new InputStreamReader(resource.getInputStream())) {
                // Create an instance of JsonParser to parse the JSON data
                JsonParser jsonParser = new JsonParser();
                JsonObject json = jsonParser.parse(reader).getAsJsonObject();

                // Check if `textures` field exists and is a JSON array
                if (json.has("textures") && json.get("textures").isJsonArray()) {
                    JsonArray texturesArray = JSONUtils.getAsJsonArray(json, "textures");

                    for (JsonElement textureElement : texturesArray) {
                        if (textureElement.isJsonPrimitive() && textureElement.getAsJsonPrimitive().isString()) {
                            String texturePath = textureElement.getAsString();

                            // Validate each texture path
                            ResourceLocation textureLocation = new ResourceLocation(texturePath);
                            if (Minecraft.getInstance().getResourceManager().hasResource(textureLocation)) {
                                System.out.println("Valid texture path: " + texturePath);
                            } else {
                                System.err.println("Invalid texture path: " + texturePath);
                            }
                        } else {
                            System.err.println("Non-string entry found in `textures` array");
                        }
                    }
                } else {
                    System.err.println("`textures` field is missing or not a JSON array");
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading particle JSON: " + e.getMessage());
        }
    }
}
