package us.mcfriendly.tags.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class PlaceholderManager extends PlaceholderExpansion {

	public String getIdentifier() {
		return "mcftags";
	}

	public String getPlugin() {
		return null;
	}

	public String getAuthor() {
		return "GoSwimmy";
	}

	public String getVersion() {
		return "1.0";
	}

	public String onPlaceholderRequest(Player player, String identifier) {
		if (player == null) {
			return "";
		}
		if (identifier.equalsIgnoreCase("tag")) {
			if (SQLManager.connect()) {
				if (SQLManager.isConnected()) {
					Connection connection = SQLManager.getConnection();
					try {
						String sql = "SELECT * FROM `player_tags` WHERE uuid = ?";
						PreparedStatement stmt = connection.prepareStatement(sql);
						stmt.setString(1, player.getUniqueId().toString());
						ResultSet res = stmt.executeQuery();
						if (res.next()) {
							if (res.getString("tag") == null) {
								return "";
							} else {
								try {
									String esql = "SELECT * FROM `tags` WHERE id = ?";
									PreparedStatement estmt = connection.prepareStatement(esql);
									estmt.setInt(1, res.getInt("tag"));
									ResultSet eres = estmt.executeQuery();
									if (eres.next()) {
										return Util.color(eres.getString("tag")) + "§f §f";
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						} else {
							return "";
						}
					} catch (Exception e) {
						e.printStackTrace();
						return "";
					}
				} else {
					return "";
				}
			} else {
				return "";
			}
		}
		return null;
	}
}
