package com.whitesoul.soulstamina.listener;

import com.whitesoul.soulsql.database.SQL;
import com.whitesoul.soulstamina.SoulStamina;
import com.whitesoul.soulstamina.data.StaminaHashMap;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onPlayerQuitListener(PlayerQuitEvent event) {
        // 存储数据
        Bukkit.getScheduler().runTaskAsynchronously(SoulStamina.INSTANCE,()->{
            UUID uuid = event.getPlayer().getUniqueId();
            int stamina = StaminaHashMap.staminaData.get(uuid);
            int maxStamina = StaminaHashMap.maxStaminaData.get(uuid);
            // 更新数据
            SQL.update("stamina",stamina+"","soulstamina","uuid",uuid.toString());
            SQL.update("maxStamina",maxStamina+"","soulstamina","uuid",uuid.toString());
            StaminaHashMap.staminaData.remove(uuid);
            StaminaHashMap.maxStaminaData.remove(uuid);
        });
    }
}
