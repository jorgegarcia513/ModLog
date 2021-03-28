package com.sudo.modlog.events;

import com.sudo.modlog.sql.SQL;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.sql.SQLException;

public class OverrideMute implements Listener {

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {

        Player sender = event.getPlayer();

        if (!event.getMessage().contains("/mute")) {
            return;
        }

        SQL sql = new SQL();
        String[] cmd = event.getMessage().split(" ");
        StringBuilder reason = new StringBuilder();
        String username = cmd[1];
        String length = cmd[2];

        if (Bukkit.getPlayer(username) == null) {
            return;
        }

        for (int i = 3; i < cmd.length; i++) {
            reason.append(cmd[i]);
            reason.append(" ");
        }

        if (event.getMessage().charAt(0) == '/') {
            try {
                sql.addRow(username, "Mute", reason.toString(), length, sender.getName());
                sender.sendMessage(ChatColor.GOLD + "[ModLog] " + ChatColor.WHITE + "Muted " + username + " and a report was submitted.");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
