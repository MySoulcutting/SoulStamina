package com.whitesoul.soulstamina.listener;

import com.whitesoul.soulstamina.api.StaminaAPI;
import com.whitesoul.soulstamina.file.Config;
import com.whitesoul.soulstamina.file.Messages;
import com.whitesoul.soulstamina.task.RecoveryStamina;
import com.whitesoul.soulstamina.util.Log;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import static com.whitesoul.soulstamina.task.RecoveryStamina.isRecovery;


public class PlayerSprintListener implements Listener {
    @EventHandler
    public void onPlayerSprintListener(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        String playerName = event.getPlayer().getName();
        // 移动消耗体力
        if (player.isSprinting()){
            Log.debug("停止恢复"+isRecovery.get(player.getUniqueId()));
            isRecovery.put(player.getUniqueId(),false);
            if (Config.get().getBoolean("Listener.Sprint.Enable")){
                // 获取移动消耗的体力
                int moveReduce = Config.get().getInt("Listener.Sprint.Value");
                // 减少体力
                StaminaAPI.reduceStamina(playerName, moveReduce);
                // 当体力为0时
                if (StaminaAPI.getStamina(playerName) == 0){
                    // 停止移动
                    player.setSprinting(false);
                    // 提示
                    player.sendMessage(Messages.get().getString("Messages.NoStaminaSprint").replace("&", "§"));
                }
            }
        } else {
            Log.debug("恢复体力"+isRecovery.get(player.getUniqueId()));
            isRecovery.put(player.getUniqueId(),true);
        }
    }
}
