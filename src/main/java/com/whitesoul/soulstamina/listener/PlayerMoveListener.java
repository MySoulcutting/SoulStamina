package com.whitesoul.soulstamina.listener;

import com.whitesoul.soulstamina.api.StaminaAPI;
import com.whitesoul.soulstamina.file.Config;
import com.whitesoul.soulstamina.file.Messages;
import com.whitesoul.soulstamina.task.RecoveryStamina;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;


public class PlayerMoveListener implements Listener {
    @EventHandler
    public void onPlayerMoveListener(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        String playerName = event.getPlayer().getName();
        // 移动消耗体力
        if (Config.get().getBoolean("Listener.Move.Enable")){
            if (event.getTo().distanceSquared(event.getFrom()) == 0) {
                RecoveryStamina.isRecovery.put(player.getUniqueId(),true);
                return;
            }
            RecoveryStamina.isRecovery.put(player.getUniqueId(),false);
            // 获取移动消耗的体力
            int moveReduce = Config.get().getInt("Listener.Move.Value");
            // 减少体力
            StaminaAPI.reduceStamina(playerName, moveReduce);
            // 当体力为0时
            if (StaminaAPI.getStamina(playerName) == 0) {
                // 停止移动
                event.setCancelled(true);
                player.setSprinting(false);
                // 提示
                player.sendMessage(Messages.get().getString("Messages.NoStaminaMove").replace("&", "§"));
            }
        }
    }
}
