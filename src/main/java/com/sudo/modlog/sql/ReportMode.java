package com.sudo.modlog.sql;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ReportMode {

    private static HashMap<String, ReportSession> map = new HashMap<String, ReportSession>();
    private static String[] vals = new String[4];
    private static final SQL sql = new SQL();

    public ReportMode() {

    }

    public static void reportMode(String name, String message, AsyncPlayerChatEvent event) throws SQLException {

        if (message.equals("exit")) {
            map.remove(name);
            event.getPlayer().sendMessage(ChatColor.GOLD + "[ModLog - Report Mode] " + ChatColor.WHITE + "Exited Report Mode.");
            return;
        }

        event.getPlayer().sendMessage(ChatColor.GREEN + message);

        switch (map.get(name).getReportStage()) {
            case 1:
                vals[0] = message;
                map.get(name).incStage();

                event.getPlayer().sendMessage(ChatColor.GOLD + "[ModLog - Report Mode] " + ChatColor.WHITE + "Enter action taken against the player. Type \"exit\" to quit.");
                break;
            case 2:
                vals[1] = message;
                map.get(name).incStage();
                event.getPlayer().sendMessage(ChatColor.GOLD + "[ModLog - Report Mode] " + ChatColor.WHITE + "Enter the reason for this report. Type \"exit\" to quit.");
                break;
            case 3:
                vals[2] = message;
                map.get(name).incStage();
                event.getPlayer().sendMessage(ChatColor.GOLD + "[ModLog - Report Mode] " + ChatColor.WHITE + "Enter any additional notes you have. Type \"exit\" to quit.");
                break;
            case 4:
                vals[3] = message;
                event.getPlayer().sendMessage(ChatColor.GOLD + "[ModLog - Report Mode] " + ChatColor.WHITE + "Sending report!");
                sql.addRow(vals[0], vals[1],vals[2], vals[3]);
                event.getPlayer().sendMessage(ChatColor.GOLD + "[ModLog - Report Mode] " + ChatColor.WHITE + "Report sent successfully.");
                map.remove(name);
                break;
            default:
                return;
        }
    }

    public static void addToMap(Player p){
        if(playerInMap(p)) {
            map.remove(p.getName());
        }else{
            map.put(p.getName(), new ReportSession(p.getName()));
        }
    }

    public static boolean playerInMap(Player p) {
        return map.containsKey(p.getName());
    }
}
