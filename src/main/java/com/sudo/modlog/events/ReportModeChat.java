package com.sudo.modlog.events;

import com.sudo.modlog.ModLog;
import org.bukkit.entity.Player;
import org.bukkit.event.EventException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import com.sudo.modlog.sql.ReportMode;

import java.sql.SQLException;

public class ReportModeChat implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        // Is player in Report mode?
        if (ReportMode.playerInMap(event.getPlayer())) {
            event.getRecipients().clear();
            try {
                ReportMode.reportMode(event.getPlayer().getName(), event.getMessage(), event);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
