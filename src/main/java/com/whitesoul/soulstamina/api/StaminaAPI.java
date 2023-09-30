package com.whitesoul.soulstamina.api;

import com.whitesoul.soulstamina.data.StaminaHashMap;
import org.bukkit.Bukkit;

import java.util.UUID;

public class StaminaAPI {
    // 增加体力
    public static void addStamina(String playerName, int stamina) {
        UUID uuid = Bukkit.getPlayer(playerName).getUniqueId();
        // 获取玩家当前体力
        int currentStamina = StaminaHashMap.staminaData.get(uuid);
        // 计算增加后的体力是否超过最大体力
        int newStamina = currentStamina + stamina;
        if (newStamina > StaminaHashMap.maxStaminaData.get(uuid)) {
            newStamina = StaminaHashMap.maxStaminaData.get(uuid);
        }
        // 更新体力
        StaminaHashMap.staminaData.put(uuid, newStamina);


    }
    // 减少体力
    public static void reduceStamina(String playerName, int stamina) {
        UUID uuid = Bukkit.getPlayer(playerName).getUniqueId();
        // 获取玩家当前体力
        int currentStamina = StaminaHashMap.staminaData.get(uuid);
        // 计算减少后的体力
        int newStamina = currentStamina - stamina;
        if (newStamina < 0) {
            newStamina = 0;
        }
        // 更新体力
        StaminaHashMap.staminaData.put(uuid, newStamina);
    }
    // 重置体力
    public static void resetStamina(String playerName) {
        UUID uuid = Bukkit.getPlayer(playerName).getUniqueId();
        // 获取玩家当前体力
        int currentStamina = StaminaHashMap.staminaData.get(uuid);
        // 计算重置后的体力
        int newStamina = StaminaHashMap.maxStaminaData.get(uuid);
        // 更新体力
        StaminaHashMap.staminaData.put(uuid, newStamina);
    }
    // 获取当前体力
    public static int getStamina(String playerName) {
        // 返回当前体力
        UUID uuid = Bukkit.getPlayer(playerName).getUniqueId();
        return StaminaHashMap.staminaData.get(uuid);
    }
    // 获取最大体力
    public static int getMaxStamina(String playerName) {
        // 返回最大体力
        UUID uuid = Bukkit.getPlayer(playerName).getUniqueId();
        return StaminaHashMap.maxStaminaData.get(uuid);
    }
    // 设置体力
    public static void setStamina(String playerName, int stamina) {
        // 计算设置后的体力是否超过最大体力
        UUID uuid = Bukkit.getPlayer(playerName).getUniqueId();
        if (stamina > StaminaHashMap.maxStaminaData.get(uuid)) {
            stamina = StaminaHashMap.maxStaminaData.get(uuid);
        }
        // 更新体力
        StaminaHashMap.staminaData.put(uuid, stamina);
    }
}
