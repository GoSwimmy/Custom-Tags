package us.mcfriendly.tags.features;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import us.mcfriendly.tags.managers.SQLManager;
import us.mcfriendly.tags.managers.Util;

public class TagGui {

	public static void openSpecificGui(Player p, String target, int page) {
		Player t = Bukkit.getPlayer(target);
		if (t == null) {
			p.sendMessage(Util.color(Util.getPrefix() + "&cTarget not online!"));
			return;
		}
		Inventory inv = Bukkit.createInventory(null, 54, "§6§l(" + target + ") Tags | Page " + page);

		if (SQLManager.connect()) {
			if (SQLManager.isConnected()) {
				Connection connection = SQLManager.getConnection();
				try {
					String sql = "";
					if (p.hasPermission("mcf.admin")) {
						sql += "SELECT * FROM tags LIMIT 45";
						if (page > 1) {
							sql += " OFFSET " + ((page - 1) * 45);
						}
					} else {
						sql += "SELECT * FROM tags";
					}
					PreparedStatement stmt = connection.prepareStatement(sql);
					ResultSet res = stmt.executeQuery();
					while (res.next()) {
						if (t.hasPermission("mcftags.tag." + res.getString("name")) || t.hasPermission("mcf.admin")) {
							ItemStack i = new ItemStack(Material.NAME_TAG);
							ItemMeta m = i.getItemMeta();
							m.setDisplayName(Util.color("&eTag: &f" + res.getString("name")));
							List<String> lore = new ArrayList<String>();
							lore.add(Util.color(""));
							lore.add(Util.color(res.getString("tag")));
							m.setLore(lore);
							i.setItemMeta(m);
							inv.addItem(i);
						}
					}

					ItemStack next = new ItemStack(Material.BLACK_BANNER);
					ItemMeta nextm = next.getItemMeta();
					nextm.setDisplayName("§6§lNext Page");
					next.setItemMeta(nextm);
					if (inv.getItem(44) != null) {
						inv.setItem(53, next);
					}

					ItemStack refresh = new ItemStack(Material.SUNFLOWER);
					ItemMeta refreshm = refresh.getItemMeta();
					refreshm.setDisplayName("§6§lRefresh");
					refresh.setItemMeta(refreshm);
					inv.setItem(49, refresh);

					ItemStack previous = new ItemStack(Material.WHITE_BANNER);
					ItemMeta previousm = previous.getItemMeta();
					previousm.setDisplayName("§6§lPrevious Page");
					previous.setItemMeta(previousm);
					if (page >= 2) {
						inv.setItem(45, previous);
					}

					p.openInventory(inv);
				} catch (Exception e) {
					e.printStackTrace();
					p.sendMessage(Util.color(Util.getPrefix() + "&cDatabase Error!"));
				}
			} else {
				p.sendMessage(Util.color(Util.getPrefix() + "&cDatabase Error!"));
			}
		} else {
			p.sendMessage(Util.color(Util.getPrefix() + "&cDatabase Error!"));
		}
	}

	public static void openGui(Player p, int page) {
		Inventory inv = Bukkit.createInventory(null, 54, "§6§lTags | Page " + page);

		if (SQLManager.connect()) {
			if (SQLManager.isConnected()) {
				Connection connection = SQLManager.getConnection();
				try {
					String sql = "";
					if (p.hasPermission("mcf.admin")) {
						sql += "SELECT * FROM tags LIMIT 45";
						if (page > 1) {
							sql += " OFFSET " + ((page - 1) * 45);
						}
					} else {
						sql += "SELECT * FROM tags";
					}
					PreparedStatement stmt = connection.prepareStatement(sql);
					ResultSet res = stmt.executeQuery();
					while (res.next()) {
						if (p.hasPermission("mcftags.tag." + res.getString("name")) || p.hasPermission("mcf.admin")) {
							ItemStack i = new ItemStack(Material.NAME_TAG);
							ItemMeta m = i.getItemMeta();
							m.setDisplayName(Util.color("&eTag: &f" + res.getString("name")));
							List<String> lore = new ArrayList<String>();
							lore.add(Util.color(""));
							lore.add(Util.color(res.getString("tag")));
							m.setLore(lore);
							i.setItemMeta(m);
							inv.addItem(i);
						}
					}

					ItemStack next = new ItemStack(Material.BLACK_BANNER);
					ItemMeta nextm = next.getItemMeta();
					nextm.setDisplayName("§6§lNext Page");
					next.setItemMeta(nextm);
					if (inv.getItem(44) != null) {
						inv.setItem(53, next);
					}

					ItemStack clear = new ItemStack(Material.BARRIER);
					ItemMeta clearm = clear.getItemMeta();
					clearm.setDisplayName("§6§lReset Tag");
					clear.setItemMeta(clearm);
					inv.setItem(48, clear);

					ItemStack refresh = new ItemStack(Material.SUNFLOWER);
					ItemMeta refreshm = refresh.getItemMeta();
					refreshm.setDisplayName("§6§lRefresh");
					refresh.setItemMeta(refreshm);
					inv.setItem(50, refresh);

					ItemStack previous = new ItemStack(Material.WHITE_BANNER);
					ItemMeta previousm = previous.getItemMeta();
					previousm.setDisplayName("§6§lPrevious Page");
					previous.setItemMeta(previousm);
					if (page >= 2) {
						inv.setItem(45, previous);
					}

					p.openInventory(inv);
				} catch (Exception e) {
					e.printStackTrace();
					p.sendMessage(Util.color(Util.getPrefix() + "&cDatabase Error!"));
				}
			} else {
				p.sendMessage(Util.color(Util.getPrefix() + "&cDatabase Error!"));
			}
		} else {
			p.sendMessage(Util.color(Util.getPrefix() + "&cDatabase Error!"));
		}
	}

}
