package us.mcfriendly.tags.features;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class TagEvent implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		String title = ChatColor.stripColor(e.getView().getTitle());
		if (title.contains(") Tags")) {
			e.setCancelled(true);
			String name = "";
			for (Player all : Bukkit.getOnlinePlayers()) {
				if (title.contains(all.getName())) {
					name = all.getName();
				}
			}
			if (e.getCurrentItem() == null)
				return;
			if (e.getCurrentItem().getType() != Material.AIR) {
				int page = Integer.parseInt(title.split("Page")[1].replace(" ", ""));
				if (e.getCurrentItem().getType() == Material.WHITE_BANNER
						&& e.getCurrentItem().getItemMeta().getDisplayName().contains("§6§lPrevious Page")) {
					TagGui.openSpecificGui(p, name, page - 1);
					return;
				}
				if (e.getCurrentItem().getType() == Material.BLACK_BANNER
						&& e.getCurrentItem().getItemMeta().getDisplayName().contains("§6§lNext Page")) {
					TagGui.openSpecificGui(p, name, page + 1);
					return;
				}
				if (e.getCurrentItem().getType() == Material.SUNFLOWER
						&& e.getCurrentItem().getItemMeta().getDisplayName().contains("§6§lRefresh")) {
					TagGui.openSpecificGui(p, name, page);
					return;
				}
				String tagname = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).replace("Tag: ",
						"");
				TagManager.setTag(p, tagname);
			}
			return;
		}
		if (e.getView().getTitle().contains("Tags")) {
			e.setCancelled(true);
			if (e.getCurrentItem() == null)
				return;
			if (e.getCurrentItem().getType() != Material.AIR) {
				int page = Integer.parseInt(ChatColor.stripColor(e.getView().getTitle()).replace("Tags | Page ", ""));
				if (e.getCurrentItem().getType() == Material.WHITE_BANNER
						&& e.getCurrentItem().getItemMeta().getDisplayName().contains("§6§lPrevious Page")) {
					TagGui.openGui(p, page - 1);
					return;
				}
				if (e.getCurrentItem().getType() == Material.BARRIER
						&& e.getCurrentItem().getItemMeta().getDisplayName().contains("§6§lReset Tag")) {
					TagManager.clearTag(p);
					return;
				}
				if (e.getCurrentItem().getType() == Material.BLACK_BANNER
						&& e.getCurrentItem().getItemMeta().getDisplayName().contains("§6§lNext Page")) {
					TagGui.openGui(p, page + 1);
					return;
				}
				if (e.getCurrentItem().getType() == Material.SUNFLOWER
						&& e.getCurrentItem().getItemMeta().getDisplayName().contains("§6§lRefresh")) {
					TagGui.openGui(p, page);
					return;
				}
				String name = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).replace("Tag: ",
						"");
				TagManager.setTag(p, name);
			}
		}
	}
}
