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

            //f.format("&cAction: &f%s &cReason&f%s &cNotes/Length%s &aStaff&f%s\n", ChatColor.GRAY + "Action", ChatColor.GRAY + "Reason", ChatColor.GRAY + "Notes" + ChatColor.WHITE);
            sender.sendMessage(ChatColor.GOLD + "[ModLog] Listing " + master.size() + " entry(s) for: " + ChatColor.GREEN + master.get(0).get(0));
            f.format(ChatColor.GRAY + "-----------------------------------------------\n");
            master.forEach(list -> {
                if (list.size() <= 4) {
                    f.format(ChatColor.RED + "Action Taken: " + ChatColor.WHITE + "%s \n" + ChatColor.RED + "Reason: " + ChatColor.WHITE
                            + "%s \n" + ChatColor.RED + "Notes/Length: " + ChatColor.WHITE + "%s \n" + ChatColor.RED + "Reporting Staff: " + ChatColor.WHITE + "%s\n", list.get(2), list.get(1), list.get(3), "");
                }
                else {
                    f.format(ChatColor.RED + "Action Taken: " + ChatColor.WHITE + "%s \n" + ChatColor.RED + "Reason: " + ChatColor.WHITE
                            + "%s \n" + ChatColor.RED + "Notes/Length: " + ChatColor.WHITE + "%s \n" + ChatColor.RED + "Reporting Staff: " + ChatColor.WHITE + "%s\n", list.get(2), list.get(1), list.get(3), list.get(4));
                }
                f.format(ChatColor.GRAY + "-----------------------------------------------\n");
            });

            sender.sendMessage(f.toString());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}
