package com.whitesoul.soulstamina;

import com.whitesoul.soulsql.database.Mysql;
import com.whitesoul.soulsql.database.SQL;
import com.whitesoul.soulstamina.command.MainCommand;
import com.whitesoul.soulstamina.command.MainCommandTab;
import com.whitesoul.soulstamina.data.StaminaHashMap;
import com.whitesoul.soulstamina.file.Config;
import com.whitesoul.soulstamina.file.Messages;
import com.whitesoul.soulstamina.listener.*;
import com.whitesoul.soulstamina.papi.SoulStaminaExpansion;
import com.whitesoul.soulstamina.task.RecoveryStamina;
import com.whitesoul.soulstamina.util.Log;
import org.bukkit.plugin.java.JavaPlugin;

public final class SoulStamina extends JavaPlugin {
    public static SoulStamina INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        // 启动信息
        Log.info("§5魂之体力 §a欢迎你的使用");
        Log.info("§a作者: §fWhiteSoul");
        Log.info("§a版本: §f" + this.getDescription().getVersion());
        Log.info("§d当前版本为稳定版，请放心使用！");
        // 初始化配置文件
        Config.initConfig();
        Messages.initConfig();
        Mysql.createConfig("mysql",this);
        saveResource("mysql.properties",false);
        register();
        // 初始化数据库
        SQL.createTable("soulstamina",new String[]{"uuid","playerName","staminaGroup","stamina","maxStamina"},new String[]{"VARCHAR(255)","VARCHAR(255)","VARCHAR(255)","INT","INT"},new String[]{"NOT NULL","NOT NULL","NOT NULL","NOT NULL","NOT NULL"});
        Log.info("§a启动完成！");
    }
    @Override
    public void onDisable() {
        StaminaHashMap.staminaData.clear();
        StaminaHashMap.maxStaminaData.clear();
        StaminaHashMap.groupData.clear();
        RecoveryStamina.isRecovery.clear();
    }
    public void register(){
        // 注册命令
        getCommand("soulstamina").setExecutor(new MainCommand());
        getCommand("soulstamina").setTabCompleter(new MainCommandTab());
        // 注册监听器
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJumpListener(),this);
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerSprintListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerSwimListener(), this);
        getServer().getPluginManager().registerEvents(new StaminaGroupChangeListener(),this);
        // 注册变量
        new SoulStaminaExpansion().register();
    }
}
