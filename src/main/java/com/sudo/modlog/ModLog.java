package com.sudo.modlog;

import com.sudo.modlog.commands.*;
import com.sudo.modlog.events.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public final class ModLog extends JavaPlugin {

    public static ModLog plugin;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        plugin = this;

        // Plugin startup logic
        this.getCommand("mllookup").setExecutor(new CommandLookup());
        this.getCommand("mlreport").setExecutor(new CommandReport());
        this.getCommand("mlhelp").setExecutor(new CommandHelp());
        this.getCommand("mlinfo").setExecutor(new CommandInfo());
        this.getCommand("mlinit").setExecutor(new CommandInit());

        this.getCommand("mllookup").setTabCompleter(new ModLogTabCompleter());

        getServer().getPluginManager().registerEvents(new ReportModeChat(), this);
        getServer().getPluginManager().registerEvents(new OverrideBan(), this);
        getServer().getPluginManager().registerEvents(new OverrideKick(), this);
        getServer().getPluginManager().registerEvents(new OverrideTempBan(), this);
        getServer().getPluginManager().registerEvents(new OverrideMute(), this);

        getServer().getConsoleSender().sendMessage("[ModLog] ModLog was successfully ENABLED.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage("[ModLog] ModLog was successfully DISABLED.");
    }
}















