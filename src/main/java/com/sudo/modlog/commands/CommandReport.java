package com.sudo.modlog.commands;

import com.sudo.modlog.sql.ReportMode;
import com.sudo.modlog.sql.SQL;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class CommandReport implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.GOLD + "[ModLog - Report Mode] " + ChatColor.WHITE + "You've now entered report mode. Enter the username of the player you're reporting. Type \"exit\" to leave Report Mode.");
            ReportMode.addToMap((Player) sender);
            return true;
        }
        else {
            sender.sendMessage(ChatColor.GOLD + "[ModLog] " + ChatColor.WHITE + "Usage: /mlreport");
            return true;
        }
    }
}
