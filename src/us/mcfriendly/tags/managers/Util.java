package us.mcfriendly.tags.managers;

import net.md_5.bungee.api.ChatColor;

public class Util {

	public static String getPrefix() {
		return ChatColor.translateAlternateColorCodes('&', DataManager.config.getString("options.prefix"));
	}

	public static String color(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}

	public static String getString(String loc) {
		return Util.color(DataManager.config.getString(loc));
	}
	
}
