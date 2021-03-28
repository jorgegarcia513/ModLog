package com.sudo.modlog.events;

import com.sudo.modlog.sql.SQL;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.sql.SQLException;

public class OverrideTempBan implements Listener {

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {

        Player sender = event.getPlayer();

        if (!event.getMessage().contains("/tempban")) {
            return;
        }

        SQL sql = new SQL();
        String[] cmd = event.getMessage().split(" ");
        StringBuilder reason = new StringBuilder();
        String username = cmd[1];
        // TODO: length support
        String length = cmd[2];

        for (int i = 3; i < cmd.length; i++) {
            reason.append(cmd[i]);
            reason.append(" ");
        }

        if (event.getMessage().charAt(0) == '/') {
            try {
                sql.addRow(username, "Temp Ban", reason.toString(), length, sender.getName());
                sender.sendMessage(ChatColor.GOLD + "[ModLog] " + ChatColor.WHITE + "Temporarily banned " + username + " and a report was submitted.");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
