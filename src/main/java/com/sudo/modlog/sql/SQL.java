package com.sudo.modlog.sql;

import com.sudo.modlog.ModLog;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.*;
import java.util.ArrayList;

public class SQL {
    private String host, port, database, table, username, password;
    public static Connection connection;


    public SQL() {
        FileConfiguration config = ModLog.plugin.getConfig();
        host = config.getString("hostname");
        port = config.getString("port");
        database = config.getString("database");
        table = config.getString("table");
        username = config.getString("username");
        password = config.getString("password");
    }

    public void openConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://"
                        + this.host + ":" + this.port + "/" + this.database,
                this.username, this.password);

    }

    public ArrayList<ArrayList<String>> getValue(String username) throws SQLException {
        ArrayList<String> vals;
        ArrayList<ArrayList<String>> master = new ArrayList<ArrayList<String>>();

        try {
            openConnection();
        } catch (ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        // Query
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM " + database + "." + table + " WHERE username='" + username +  "';");

        // Create a Hashmap with the usename as the key, and list of values as val
        while (result.next()) {
            vals = new ArrayList<String>();
            vals.add(result.getString("username"));
            vals.add(result.getString("reason"));
            vals.add(result.getString("action_taken"));
            vals.add(result.getString("notes"));
            master.add(vals);
        }

        result.close();
        statement.close();
        connection.close();

        return master;
    }

    public void addRow(String username, String action, String reason, String notes) throws SQLException {
        // Open the connection
        try {
            openConnection();
        } catch (ClassNotFoundException | SQLException throwables) {
            throwables.printStackTrace();
        }

        // Insert to DB
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO " + database + "." + table + " VALUES ('" + username + "', '" + reason + "', '" + action + "', '" + notes + "');");

        statement.close();
        connection.close();
    }

    public void initTable() throws SQLException {
        try {
            openConnection();
        } catch (ClassNotFoundException | SQLException throwables) {
            throwables.printStackTrace();
        }

        // Insert to DB
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE modlog (username TEXT, reason TEXT, action_taken TEXT, notes TEXT);");

        statement.close();
        connection.close();
    }
}
