package com.whitesoul.soulstamina.file;

import com.whitesoul.soulstamina.SoulStamina;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Messages {
    private static File file;
    private static FileConfiguration config;
    public static void initConfig(){
        file = new File(SoulStamina.INSTANCE.getDataFolder(), "Messages.yml");
        config = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()){
            SoulStamina.INSTANCE.saveResource("Messages.yml",false);
        }
    }
    public static FileConfiguration get(){
        return config;
    }
    public static void reload(){
        config = YamlConfiguration.loadConfiguration(file);
    }
}
