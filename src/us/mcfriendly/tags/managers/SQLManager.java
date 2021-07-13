package us.mcfriendly.tags.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SQLManager {

	public static Connection con;

	// connect
	public static boolean connect() {
		FileConfiguration c = DataManager.config;
		String host = c.getString("mysql.host");
		String port = c.getString("mysql.port");
		String database = c.getString("mysql.database");
		String username = c.getString("mysql.username");
		String password = c.getString("mysql.password");
		String ssl = c.getString("mysql.ssl");
		String autoreconnect = c.getString("mysql.autoreconnect");
		if (!isConnected()) {
			try {
				con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database
						+ "?autoReconnect=" + autoreconnect + "&useSSL=" + ssl, username, password);
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	// disconnect
	public static void disconnect() {
		if (isConnected()) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// isConnected
	public static boolean isConnected() {
		return (con == null ? false : true);
	}

	// getConnection
	public static Connection getConnection() {
		return con;
	}
	
	public static void initializePlayer(Player p) {
		if (SQLManager.connect()) {
			if (SQLManager.isConnected()) {
				Connection connection = SQLManager.getConnection();
				try {
					String sql = "SELECT * FROM `player_tags` WHERE uuid = ?";
					PreparedStatement stmt = connection.prepareStatement(sql);
					stmt.setString(1, p.getUniqueId().toString());
					ResultSet res = stmt.executeQuery();
					if (!res.next()) {
						try {
							String roundstable = "INSERT INTO `player_tags` (uuid) VALUES (?)";
							PreparedStatement roundstablestmt = connection.prepareStatement(roundstable);
							roundstablestmt.setString(1, p.getUniqueId().toString());
							roundstablestmt.executeUpdate();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					return;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
