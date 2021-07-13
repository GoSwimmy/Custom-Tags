package us.mcfriendly.tags.features;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.mcfriendly.tags.managers.SQLManager;
import us.mcfriendly.tags.managers.Util;

public class TagManager {

	public static void createTag(Player p, String name, String tag) {
		if (SQLManager.connect()) {
			if (SQLManager.isConnected()) {
				Connection connection = SQLManager.getConnection();
				try {
					String sql = "SELECT * FROM `tags` WHERE name = ?";
					PreparedStatement stmt = connection.prepareStatement(sql);
					stmt.setString(1, name);
					ResultSet res = stmt.executeQuery();
					if (!res.next()) {
						try {
							String roundstable = "INSERT INTO `tags` (name, tag) VALUES (?, ?)";
							PreparedStatement roundstablestmt = connection.prepareStatement(roundstable);
							roundstablestmt.setString(1, name);
							roundstablestmt.setString(2, tag);
							roundstablestmt.executeUpdate();
							p.sendMessage(Util.color(Util.getPrefix() + "&aTag created!"));
						} catch (Exception e) {
							e.printStackTrace();
							p.sendMessage(Util.color(Util.getPrefix() + "&cAn error occurred!"));
						}
					} else {
						p.sendMessage(Util.color(Util.getPrefix() + "&cTag already exists!"));
					}
					return;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		p.sendMessage(Util.color(Util.getPrefix() + "&cAn error occurred!"));
	}

	public static void createTag(CommandSender p, String name, String tag) {
		if (SQLManager.connect()) {
			if (SQLManager.isConnected()) {
				Connection connection = SQLManager.getConnection();
				try {
					String sql = "SELECT * FROM `tags` WHERE name = ?";
					PreparedStatement stmt = connection.prepareStatement(sql);
					stmt.setString(1, name);
					ResultSet res = stmt.executeQuery();
					if (!res.next()) {
						try {
							String roundstable = "INSERT INTO `tags` (name, tag) VALUES (?, ?)";
							PreparedStatement roundstablestmt = connection.prepareStatement(roundstable);
							roundstablestmt.setString(1, name);
							roundstablestmt.setString(2, tag);
							roundstablestmt.executeUpdate();
							p.sendMessage(Util.color(Util.getPrefix() + "&aTag created!"));
						} catch (Exception e) {
							e.printStackTrace();
							p.sendMessage(Util.color(Util.getPrefix() + "&cAn error occurred!"));
						}
					} else {
						p.sendMessage(Util.color(Util.getPrefix() + "&cTag already exists!"));
					}
					return;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		p.sendMessage(Util.color(Util.getPrefix() + "&cAn error occurred!"));
	}

	public static void setTag(Player p, String name) {
		if (SQLManager.connect()) {
			if (SQLManager.isConnected()) {
				Connection connection = SQLManager.getConnection();
				try {
					String sql = "SELECT * FROM `tags` WHERE name = ?";
					PreparedStatement stmt = connection.prepareStatement(sql);
					stmt.setString(1, name);
					ResultSet res = stmt.executeQuery();
					if (res.next()) {
						if (p.hasPermission("mcftags.tag." + name)) {
							String roundstable = "UPDATE `player_tags` SET tag = ? WHERE uuid = ?";
							PreparedStatement roundstablestmt = connection.prepareStatement(roundstable);
							roundstablestmt.setInt(1, res.getInt("id"));
							roundstablestmt.setString(2, p.getUniqueId().toString());
							roundstablestmt.executeUpdate();
							p.closeInventory();
							p.sendMessage(
									Util.color(Util.getPrefix() + "&aYour tag has been set to " + res.getString("tag") + "&a!"));
						} else {
							p.sendMessage(
									Util.color(Util.getPrefix() + "&cYou do not have permission to use this tag!"));
						}
					} else {
						p.sendMessage(Util.color(Util.getPrefix() + "&cTag does not exist!"));
					}
					return;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		p.sendMessage(Util.color(Util.getPrefix() + "&cAn error occurred!"));
	}

	public static void clearTag(Player p) {
		if (SQLManager.connect()) {
			if (SQLManager.isConnected()) {
				Connection connection = SQLManager.getConnection();
				try {
					String roundstable = "UPDATE `player_tags` SET tag = ? WHERE uuid = ?";
					PreparedStatement roundstablestmt = connection.prepareStatement(roundstable);
					roundstablestmt.setString(1, null);
					roundstablestmt.setString(2, p.getUniqueId().toString());
					roundstablestmt.executeUpdate();
					p.closeInventory();
					p.sendMessage(Util.color(Util.getPrefix() + "&aTag Cleared!"));
					return;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		p.sendMessage(Util.color(Util.getPrefix() + "&cAn error occurred!"));
	}
}
