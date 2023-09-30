package com.whitesoul.soulstamina.data;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class StaminaHashMap {
    public static ConcurrentHashMap<UUID, Integer> staminaData = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<UUID, Integer> maxStaminaData = new ConcurrentHashMap<>();
}
