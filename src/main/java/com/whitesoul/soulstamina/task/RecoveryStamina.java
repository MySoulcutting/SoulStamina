package com.whitesoul.soulstamina.task;

import com.whitesoul.soulstamina.SoulStamina;
import com.whitesoul.soulstamina.api.StaminaAPI;
import com.whitesoul.soulstamina.file.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class RecoveryStamina {
    private static int recoveryTime = Config.get().getInt("recoveryTime");
    public static boolean isRecovery = true;
    // 恢复体力
    public static void recoveryStamina(Player player) {
        if (isRecovery) {
            Bukkit.getScheduler().runTaskTimerAsynchronously(SoulStamina.INSTANCE, () -> {
                for (String key : Config.get().getConfigurationSection("Stamina").getKeys(false)) {
                    if (player.hasPermission(Config.get().getString("Stamina." + key + ".Permission"))) {
                        // 获取恢复的值
                        int min = Config.get().getInt("Stamina." + key + ".Recovery.Min");
                        int max = Config.get().getInt("Stamina." + key + ".Recovery.Max");
                        int recovery = (int) (Math.random() * (max - min + 1) + min);
                        // 增加体力
                        StaminaAPI.addStamina(player.getName(), recovery);
                    } else {
                        // 获取恢复的值
                        int min = Config.get().getInt("Stamina.Default.Recovery.Min");
                        int max = Config.get().getInt("Stamina.Default.Recovery.Max");
                        int recovery = (int) (Math.random() * (max - min + 1) + min);
                        // 增加体力
                        StaminaAPI.addStamina(player.getName(), recovery);
                    }
                }
            }, 0, recoveryTime);
        }
    }
}
