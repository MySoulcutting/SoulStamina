package com.whitesoul.soulstamina.task;

import com.whitesoul.soulstamina.SoulStamina;
import com.whitesoul.soulstamina.api.StaminaAPI;
import com.whitesoul.soulstamina.data.StaminaHashMap;
import com.whitesoul.soulstamina.file.Config;
import com.whitesoul.soulstamina.util.Log;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashMap;
import java.util.UUID;

public class RecoveryStamina {
    private static int recoveryTime = Config.get().getInt("RecoveryTime");
    public static HashMap<UUID,Boolean> isRecovery = new HashMap<>();
    // 恢复体力
    public static void recoveryStamina(Player player) {
        if (isRecovery.get(player.getUniqueId())) {
            Bukkit.getScheduler().runTaskTimerAsynchronously(SoulStamina.INSTANCE, () -> {
                if (StaminaHashMap.groupData.get(player.getUniqueId()) != null) {
                    String group = StaminaAPI.getGroup(player.getName());
                    int min = Config.get().getInt("Stamina." + group + ".Recovery.Min");
                    int max = Config.get().getInt("Stamina." + group + ".Recovery.Max");
                    int recovery = (int) (Math.random() * (max - min + 1) + min);
                    StaminaAPI.addStamina(player.getName(), recovery);
                }
            }, 0, recoveryTime);
        }
    }
}
