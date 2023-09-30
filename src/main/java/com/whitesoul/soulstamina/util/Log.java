package com.whitesoul.soulstamina.util;

import com.whitesoul.soulstamina.SoulStamina;
import com.whitesoul.soulstamina.file.Config;
import org.bukkit.plugin.Plugin;

public class Log {
    private static Plugin plugin = SoulStamina.INSTANCE;
    public static void info(String message) {
        plugin.getLogger().info("§f" + message);
    }
    public static void warning(String message) {
        plugin.getLogger().warning(message);
    }
    public static void error(String message) {
        plugin.getLogger().info("§c" + message);
    }
    public static void debug(String message) {
        if (Config.get().getBoolean("Debug")) {
            info("§f[DEBUG] §e" + message);
        }
    }
}
