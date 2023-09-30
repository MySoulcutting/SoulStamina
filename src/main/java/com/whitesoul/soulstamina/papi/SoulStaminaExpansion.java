package com.whitesoul.soulstamina.papi;

import com.whitesoul.soulstamina.api.StaminaAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

public class SoulStaminaExpansion extends PlaceholderExpansion {
    @Override
    public String getIdentifier() {
        return "soulstamina";
    }

    @Override
    public String getAuthor() {
        return "whitesoul";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }
    @Override
    public boolean persist() {
        return true;
    }
    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("stamina")){
            return StaminaAPI.getStamina(player.getName()) + "";
        }

        if(params.equalsIgnoreCase("max_stamina")){
            return StaminaAPI.getMaxStamina(player.getName()) + "";
        }

        return null;
    }
}
