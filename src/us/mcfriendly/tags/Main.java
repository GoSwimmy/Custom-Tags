package us.mcfriendly.tags;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import us.mcfriendly.tags.events.JoinEvent;
import us.mcfriendly.tags.features.TagCommand;
import us.mcfriendly.tags.features.TagEvent;
import us.mcfriendly.tags.managers.DataManager;
import us.mcfriendly.tags.managers.PlaceholderManager;
import us.mcfriendly.tags.managers.SQLManager;

public class Main extends JavaPlugin {

	public void onEnable() {
		if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			new PlaceholderManager().register();
		}
		
		DataManager.setup(this);
		
		Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
		Bukkit.getPluginManager().registerEvents(new TagEvent(), this);
		
		getCommand("tags").setExecutor(new TagCommand());
		
		for(Player all : Bukkit.getOnlinePlayers()) {
			SQLManager.initializePlayer(all);
		}
	}

}
