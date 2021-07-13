package us.mcfriendly.tags.features;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import us.mcfriendly.tags.managers.DataManager;
import us.mcfriendly.tags.managers.Util;

public class TagCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 0) {
				TagGui.openGui(p, 1);
				return false;
			}
			if (p.hasPermission("mcf.admin")) {
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("reload")) {
						p.sendMessage(Util.getPrefix() + Util.getString("options.tag.messages.reload-config"));
						DataManager.saveConfig();
						return false;
					}
				}
				if (args.length == 2) {
					if (args[0].equalsIgnoreCase("list")) {
						TagGui.openSpecificGui(p, args[1], 1);
						return false;
					}
					if (args[0].equalsIgnoreCase("clear")) {
						
						return false;
					}
					if (args[0].equalsIgnoreCase("delete")) {

						return false;
					}
				}
				if (args.length == 3) {
					if (args[0].equalsIgnoreCase("set")) {
						Player t = Bukkit.getPlayer(args[1]);
						if (t == null) {
							p.sendMessage(Util.color(Util.getPrefix() + "&cThat user is not online!"));
						} else {
							TagManager.setTag(t, args[1]);
							p.sendMessage(Util.color(Util.getPrefix() + "&aTag set request sent!"));
						}
						return false;
					}
					if (args[0].equalsIgnoreCase("create")) {
						TagManager.createTag(p, args[1], args[2]);
						return false;
					}
				}
				p.sendMessage(Util.color("&f&l&m---------------=[&6&l Tags &f&l&m]=---------------"));
				p.sendMessage(Util.color("&7&o<> = required | () = options"));
				p.sendMessage(Util.color(" "));
				p.sendMessage(Util.color("&f/tags"));
				if (p.hasPermission("mcf.admin")) {
					p.sendMessage(Util.color("&f/tags &6reload"));
					p.sendMessage(Util.color("&f/tags &6delete &f<tag>"));
					p.sendMessage(Util.color("&f/tags &6list &f<player>"));
					p.sendMessage(Util.color("&f/tags &6clear &f<player>"));
					p.sendMessage(Util.color("&f/tags &6set &f<player> &6<tag>"));
					p.sendMessage(Util.color("&f/tags &6create &f<name> &6<tag>"));
				}
			} else {
				p.sendMessage(Util.getPrefix() + Util.getString("options.tag.messages.no-permission"));
			}
		} else {
			if (args.length == 3) {
				if (args[0].equalsIgnoreCase("create")) {
					TagManager.createTag(sender, args[1], args[2]);
					return false;
				}
			}
		}
		return false;
	}
}
