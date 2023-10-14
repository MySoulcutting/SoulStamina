package com.whitesoul.soulstamina.command;

import com.whitesoul.soulstamina.file.Config;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class MainCommandTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> tabCompletions = new ArrayList<>();
        if (strings.length == 1) {
            tabCompletions.add("add");
            tabCompletions.add("reduce");
            tabCompletions.add("reset");
            tabCompletions.add("set");
            tabCompletions.add("get");
            tabCompletions.add("help");
            tabCompletions.add("reload");
            tabCompletions.add("changegroup");
        } else if (strings.length == 2) {
            // 全服玩家
            Bukkit.getOnlinePlayers().forEach(player -> tabCompletions.add(player.getName()));
        } else if (strings.length == 3 && strings[0].equals("changegroup")) {
            // 配置文件中的组
            tabCompletions.addAll(Config.get().getConfigurationSection("Stamina").getKeys(false));
        }
        return tabCompletions;
    }
}
