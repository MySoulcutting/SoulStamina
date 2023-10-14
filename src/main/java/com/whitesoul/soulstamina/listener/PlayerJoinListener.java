package com.whitesoul.soulstamina.listener;

import com.whitesoul.soulsql.database.SQL;
import com.whitesoul.soulstamina.SoulStamina;
import com.whitesoul.soulstamina.data.StaminaHashMap;
import com.whitesoul.soulstamina.file.Config;
import com.whitesoul.soulstamina.task.RecoveryStamina;
import com.whitesoul.soulstamina.util.Log;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.ResultSet;
import java.util.UUID;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoinListener(PlayerJoinEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        String playerName = event.getPlayer().getName();
        // 插入数据到HashMap
        Bukkit.getScheduler().runTaskAsynchronously(SoulStamina.INSTANCE,()->{
            // 数据库查询
            ResultSet resultSet;
            ResultSet init;
            try {
                // 查询数据
                resultSet = SQL.queruy("staminaGroup, stamina, maxStamina", "soulstamina", "uuid", uuid.toString());
                // 如果没有体力数据
                while (resultSet.next()) {
                    // 获取数据
                    String group = resultSet.getString("staminaGroup");
                    int stamina = resultSet.getInt("stamina");
                    int maxStamina = resultSet.getInt("maxStamina");
                    // 插入数据
                    StaminaHashMap.staminaData.put(uuid, stamina);
                    StaminaHashMap.maxStaminaData.put(uuid, maxStamina);
                    StaminaHashMap.groupData.put(uuid, group);
                }
                init = SQL.queruy("uuid", "soulstamina", "uuid", uuid.toString());
                if (!init.next()) {
                    // 获取默认体力
                    int defaultStamina = Config.get().getInt("Stamina.Default.Value");
                    // 插入数据
                    StaminaHashMap.staminaData.put(uuid, defaultStamina);
                    StaminaHashMap.maxStaminaData.put(uuid, defaultStamina);
                    StaminaHashMap.groupData.put(uuid, "Default");
                    // 插入数据到数据库
                    SQL.insert("soulstamina", new String[]{"uuid", "playerName", "staminaGroup", "stamina", "maxStamina"}, new String[]{uuid.toString(), playerName, "Default", defaultStamina + "", defaultStamina + ""});
                }

            }catch (Exception e){
                e.printStackTrace();
                Log.error("数据库查询失败");
            }
            RecoveryStamina.isRecovery.put(uuid,true);
            RecoveryStamina.recoveryStamina(event.getPlayer());
        });
    }
}
