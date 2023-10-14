package com.whitesoul.soulstamina.listener;

import com.whitesoul.soulsql.database.SQL;
import com.whitesoul.soulstamina.SoulStamina;
import com.whitesoul.soulstamina.api.StaminaAPI;
import com.whitesoul.soulstamina.data.StaminaHashMap;
import com.whitesoul.soulstamina.file.Config;
import com.whitesoul.soulstamina.task.RecoveryStamina;
import com.whitesoul.soulstamina.util.Log;
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
            String group = StaminaHashMap.groupData.get(uuid);
            int stamina = StaminaHashMap.staminaData.get(uuid);
            int maxStamina = Config.get().getInt("Stamina." + group + ".Value");
            // 更新数据
            SQL.update("stamina",stamina+"","soulstamina","uuid",uuid.toString());
            SQL.update("maxStamina",maxStamina+"","soulstamina","uuid",uuid.toString());
            SQL.update("staminaGroup",group,"soulstamina","uuid",uuid.toString());
            Log.debug("玩家"+event.getPlayer().getName()+"退出游戏，体力为"+stamina+"，最大体力为"+maxStamina);
            StaminaHashMap.staminaData.remove(uuid);
            StaminaHashMap.maxStaminaData.remove(uuid);
            StaminaHashMap.groupData.remove(uuid);
            RecoveryStamina.isRecovery.remove(uuid);
        });
    }
}
