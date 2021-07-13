package us.mcfriendly.tags.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import us.mcfriendly.tags.managers.SQLManager;

public class JoinEvent implements Listener {

	@EventHandler
	public void Join(PlayerJoinEvent e) {
		SQLManager.initializePlayer(e.getPlayer());
	}
}
