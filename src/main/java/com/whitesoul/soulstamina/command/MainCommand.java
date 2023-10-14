package com.whitesoul.soulstamina.command;

import com.whitesoul.soulstamina.api.StaminaAPI;
import com.whitesoul.soulstamina.file.Config;
import com.whitesoul.soulstamina.file.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String arg, String[] args) {
        String prefix = Messages.get().getString("Messages.Prefix").replace("&","§");
        if (args.length == 0){
            commandSender.sendMessage(prefix + " §e你输入的指令有误！请查看帮助 §6/soulstamina help");
            return false;
        }
            switch (args[0]){
                case "help":
                    if (commandSender.hasPermission("soulstamina.admin")){
                    commandSender.sendMessage(prefix + " §e/soulstamina help §f- §e查看帮助");
                    commandSender.sendMessage(prefix + " §e/soulstamina add <玩家名> <体力值> §f- §e给予玩家体力");
                    commandSender.sendMessage(prefix + " §e/soulstamina reduce <玩家名> <体力值> §f- §e减少玩家体力");
                    commandSender.sendMessage(prefix + " §e/soulstamina reset <玩家名> §f- §e重置玩家体力");
                    commandSender.sendMessage(prefix + " §e/soulstamina set <玩家名> <体力值> §f- §e设置玩家体力");
                    commandSender.sendMessage(prefix + " §e/soulstamina get <玩家名> §f- §e获取玩家体力");
                    commandSender.sendMessage(prefix + " §e/soulstamina reload §f- §e重载配置文件");
                    commandSender.sendMessage(prefix + " §e/soulstamina changegroup <玩家名> <组名> §f- §e改变玩家体力组");
                } else {
                        commandSender.sendMessage(prefix + " §e你没有权限！");
                    }
                    break;
                //增加 stamina add <playerName> <stamina>
                case "add":
                    if (commandSender.hasPermission("soulstamina.admin")) {
                        StaminaAPI.addStamina(args[1], Integer.parseInt(args[2]));
                        commandSender.sendMessage(prefix + " §e你给予玩家 §6" + args[1] + " §6" + args[2] + " §e点体力值！" + " §e当前体力值为：§6" + StaminaAPI.getStamina(args[1]));
                    } else {
                        commandSender.sendMessage(prefix + " §e你没有权限！");
                    }
                    break;
                //减少 stamina reduce <playerName> <stamina>
                 case "reduce":
                     if (commandSender.hasPermission("soulstamina.admin")) {
                     StaminaAPI.reduceStamina(args[1], Integer.parseInt(args[2]));
                     commandSender.sendMessage(prefix + " §e你减少玩家 §6" + args[1] + " §6" + args[2] + " §e点体力值！" + " §e当前体力值为：§6" + StaminaAPI.getStamina(args[1]));
                 } else {
                         commandSender.sendMessage(prefix + " §e你没有权限！");
                     }
                    break;
                // 重置 stamina reset <playerName>
                 case "reset":
                     if (commandSender.hasPermission("soulstamina.admin")) {
                     StaminaAPI.resetStamina(args[1]);
                     commandSender.sendMessage(prefix + " §e你重置玩家 §6" + args[1] + " §e的体力值！" + " §e当前体力值为：§6" + StaminaAPI.getStamina(args[1]));
                 } else {
                         commandSender.sendMessage(prefix + " §e你没有权限！");
                     }
                    break;
                //获取 stamina get <playerName>
                case "get":
                    if (commandSender.hasPermission("soulstamina.admin")) {
                        commandSender.sendMessage(prefix + " §e玩家 §6" + args[1] + " §e的体力值为：§6" + StaminaAPI.getStamina(args[1]));
                    } else {
                        commandSender.sendMessage(prefix + " §e你没有权限！");
                    }
                    break;
                case "reload":
                    if (commandSender.hasPermission("soulstamina.admin")) {
                        Config.reload();
                        Messages.reload();
                        commandSender.sendMessage(prefix + " §e重载配置文件！");
                    } else {
                        commandSender.sendMessage(prefix + " §e你没有权限！");
                    }
                    break;
                case "set":
                    if (commandSender.hasPermission("soulstamina.admin")) {
                        StaminaAPI.setStamina(args[1], Integer.parseInt(args[2]));
                        commandSender.sendMessage(prefix + " §e你设置玩家 §6" + args[1] + " §e的体力值为：§6" + StaminaAPI.getStamina(args[1]));
                    } else {
                        commandSender.sendMessage(prefix + " §e你没有权限！");
                    }
                    break;
                case "changegroup":
                    if (commandSender.hasPermission("soulstamina.admin")){
                        StaminaAPI.changeGroup(args[1], args[2]);
                        commandSender.sendMessage(prefix + " §e你将玩家 §6" + args[1] + " §e的体力组改为：§6" + args[2]);
                    } else {
                        commandSender.sendMessage(prefix + " §e你没有权限！");
                    }
                    break;
                default:
                    commandSender.sendMessage(prefix + " §e你输入的指令有误！请查看帮助 §6/soulstamina help");
                    break;
            }
        return false;
    }
}
