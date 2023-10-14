package com.whitesoul.soulstamina.listener;

import com.whitesoul.soulstamina.api.StaminaAPI;
import com.whitesoul.soulstamina.event.StaminaGroupChangeEvent;
import com.whitesoul.soulstamina.file.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;



public class StaminaGroupChangeListener implements Listener {
    @EventHandler
    public void onStaminaGroupChange(StaminaGroupChangeEvent event) {
        Player player = event.getPlayer();
        String name = player.getName();
        String group = StaminaAPI.getGroup(name);
        int maxStamina = Config.get().getInt("Stamina." + group + ".Value");
        StaminaAPI.setMaxStamina(name, maxStamina);
        StaminaAPI.resetStamina(name);
    }
}
