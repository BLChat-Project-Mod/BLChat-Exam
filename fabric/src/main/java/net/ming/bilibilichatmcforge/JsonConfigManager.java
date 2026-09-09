package net.ming.bilibilichatmcforge;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class JsonConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static File configFile;
    public static String identityCode = "";

    public static void load() {
        configFile = new File(net.minecraft.client.Minecraft.getInstance().gameDirectory, "config/bilibilichat-config.json");
        if (configFile.exists()) {
            try (Reader reader = new FileReader(configFile)) {
                var map = GSON.fromJson(reader, java.util.Map.class);
                if (map != null && map.containsKey("identityCode")) {
                    identityCode = String.valueOf(map.get("identityCode"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setIdentityCode(String code) {
        identityCode = code;
        try (Writer writer = new FileWriter(configFile)) {
            var map = new java.util.HashMap<String, String>();
            map.put("identityCode", identityCode);
            GSON.toJson(map, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}