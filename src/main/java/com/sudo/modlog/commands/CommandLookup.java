package com.sudo.modlog.commands;

import com.sudo.modlog.sql.SQL;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;

public class CommandLookup implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        SQL sql = new SQL();
        Formatter f = new Formatter();

        if (args.length != 1) {
            sender.sendMessage(ChatColor.GOLD + "[ModLog] Usage: /mllookup <username>");
            return true;
        }

        String username = args[0];

        try {
            ArrayList<ArrayList<String>> master = sql.getValue(username);

            if (master.size() <= 0) {
                sender.sendMessage(ChatColor.GOLD + "[ModLog] " + ChatColor.WHITE + "No results were returned for username: " + username);
                return true;
            }

            f.format("%-17s %-25s %s\n", ChatColor.GRAY + "Action", ChatColor.GRAY + "Reason", ChatColor.GRAY + "Notes" + ChatColor.WHITE);
            sender.sendMessage(ChatColor.GOLD + "[ModLog] Listing " + master.size() + " entry(s) for: " + ChatColor.GREEN + master.get(0).get(0));

            master.forEach(list -> {
                f.format("%-15s %-24s %s\n", list.get(2), list.get(1), list.get(3));
            });

            sender.sendMessage(f.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}
