package com.whitesoul.soulstamina.command;

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
        } else if (strings.length == 2) {
            // 全服玩家
            Bukkit.getOnlinePlayers().forEach(player -> tabCompletions.add(player.getName()));
        }
        return tabCompletions;

    }
}
