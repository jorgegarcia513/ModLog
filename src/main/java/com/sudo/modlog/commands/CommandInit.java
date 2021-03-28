package com.sudo.modlog.commands;

import com.sudo.modlog.sql.SQL;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.sql.SQLException;

public class CommandInit implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        SQL sql = new SQL();

        try {
            sql.addColumn(args[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return true;
    }
}
