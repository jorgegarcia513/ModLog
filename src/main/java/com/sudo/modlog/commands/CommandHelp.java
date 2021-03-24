package com.sudo.modlog.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandHelp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.GOLD + "[ModLog] " + ChatColor.WHITE + "Commands");
        sender.sendMessage(ChatColor.GRAY + "/mllookup <username> " + ChatColor.WHITE + "- Lookup a user." );
        sender.sendMessage(ChatColor.GRAY + "/mlreport" + ChatColor.WHITE + "- Report a user." );
        return true;
    }
}
