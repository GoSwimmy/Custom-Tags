package us.mcfriendly.tags.managers;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class DataManager {

	public static FileConfiguration config;
	public static File configFile;

	public static void setup(Plugin pl) {
		if (!pl.getDataFolder().exists()) {
			pl.getDataFolder().mkdir();
		}

		configFile = new File(pl.getDataFolder(), "config.yml");
		config = pl.getConfig();
		config.options().copyDefaults(false);
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			pl.saveResource("config.yml", true);
		}
		config = YamlConfiguration.loadConfiguration(configFile);

	}
	
	public static void saveConfig() {
		try {
			config.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
