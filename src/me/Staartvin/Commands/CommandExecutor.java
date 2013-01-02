package me.Staartvin.Commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import me.Staartvin.Staff_Info.Staff_Info;

public class CommandExecutor implements org.bukkit.command.CommandExecutor {

	Staff_Info plugin;
	
	public CommandExecutor(Staff_Info instance) {
		plugin = instance;
	}

	public boolean hasPermission(String permission, CommandSender sender) {
		if (!sender.hasPermission(permission)) {
			sender.sendMessage(ChatColor.RED + "You need to have (" + permission + ") to do this!");
			return false;
		}
		return true;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
			String[] args) {
		if (args.length == 0) {
			sender.sendMessage(ChatColor.BLUE + "-----------------------------------------------------");
			  sender.sendMessage(ChatColor.GOLD + "Developed by: " + ChatColor.GRAY + "Staartvin");
			  sender.sendMessage(ChatColor.GOLD + "Version: " + ChatColor.GRAY + plugin.getDescription().getVersion());
			  sender.sendMessage(ChatColor.YELLOW + "Type /staff help for a list of commands.");
			  return true;
		} else if (args.length == 1) {
			if (args[0].equalsIgnoreCase("groups")) {
				if (!hasPermission("staffinfo.groups.view", sender)) return true;
				
				List<String> groups = plugin.groups.getGroups();
			    sender.sendMessage(ChatColor.GOLD + "Groups: " + ChatColor.BLUE + groups.toString());
			    return true;
			}
			else if (args[0].equalsIgnoreCase("help")) {
				if (!hasPermission("staffinfo.help", sender)) return true;
				showHelpPages(1, sender);
				return true;
			}
			else if (args[0].equalsIgnoreCase("reload")) {
				if (!hasPermission("staffinfo.reload", sender)) return true;
				
					plugin.reload();
					sender.sendMessage(ChatColor.GREEN + "Staff Info has been reloaded!");
					return true;
			}
			else {
				plugin.groups.showShortGroupInfo(args[0], sender);
				return true;
			}
		} else if (args.length == 2) {
			if (args[0].equalsIgnoreCase("info")) {
				if (!hasPermission("staffinfo.groups.info", sender)) return true;
				
				plugin.groups.showLongGroupInfo(args[1], sender);
				return true;
			}
			else if (args[0].equalsIgnoreCase("create")) {
				if (!hasPermission("staffinfo.groups.create", sender)) return true;
				
				if (plugin.groups.createGroup(args[1], sender)) {
					plugin.config.reloadConfig();
				}
				return true;
			}
			else if (args[0].equalsIgnoreCase("delete")) {
				if (!hasPermission("staffinfo.groups.delete", sender)) return true;
				
				if (plugin.groups.deleteGroup(args[1], sender)) {
					plugin.config.reloadConfig();
				}
				return true;
			}
			else if (args[0].equalsIgnoreCase("reload")) {
				if (args[1].equalsIgnoreCase("config")) {
					if (!hasPermission("staffinfo.reload.config", sender)) return true;	
					
					plugin.config.reloadConfig();
					sender.sendMessage(ChatColor.GREEN + "Config has been reloaded!");
					return true;
				}
			}
			else if (args[0].equalsIgnoreCase("help")) {
				if (!hasPermission("staffinfo.help", sender)) return true;
				int pageNumber = 1;
				try {
					pageNumber = Integer.parseInt(args[1]);
				} catch(Exception e) {
					sender.sendMessage(ChatColor.RED + args[1] + " is not a number!");
					return true;
				}
				showHelpPages(pageNumber, sender);
				return true;
			}
			else if (args[0].equalsIgnoreCase("run")) {
				if (args[1].equalsIgnoreCase("autoasign")) {
					if (!hasPermission("staffinfo.run.autoasign", sender)) return true;
					sender.sendMessage(ChatColor.GREEN + "Auto asign permissions check successfully forced!");
					plugin.log.debug("Forced a permissions check by " + sender.getName());
			        plugin.vaultClass.permHandler.autoAsignGroups();
			        return true;
				}
			}
		} else if (args.length == 3) {
			if (args[0].equalsIgnoreCase("add")) {
				plugin.groups.addPlayerToGroup(args[2], args[1], sender);
				return true;
			}
			else if (args[0].equalsIgnoreCase("remove")) {
				plugin.groups.removePlayerFromGroup(args[2], args[1], sender);
				return true;
			}
		} else if (args.length >= 4) {
			if (args[0].equalsIgnoreCase("edit")) {
				
				plugin.groups.editVariable(args, sender);
				return true;
			}
		}
		sender.sendMessage(ChatColor.BLUE + "-----------------------------------------------------");
		sender.sendMessage(ChatColor.RED + "Command not recognised!");
		sender.sendMessage(ChatColor.GOLD + "Type /staff help to get a list of commands.");
		return true;
	}
	
	protected void showHelpPages(int page, CommandSender sender) {
		int maxpages = 2;
		
		if (page == 1) {
			sender.sendMessage(ChatColor.BLUE + "-------------------[" + ChatColor.GOLD + "Staff Info" + ChatColor.BLUE + "]------------------------");
			sender.sendMessage(ChatColor.GOLD + "/staff" + ChatColor.BLUE + " --- Shows info about Staff Info");
			sender.sendMessage(ChatColor.GOLD + "/staff help <page>" + ChatColor.BLUE + " --- Shows a list of commands");
			sender.sendMessage(ChatColor.GOLD + "/staff groups" + ChatColor.BLUE + " --- Shows a list of groups");
			sender.sendMessage(ChatColor.GOLD + "/staff <groupname>" + ChatColor.BLUE + " --- Shows a short description of a group");
		    sender.sendMessage(ChatColor.GOLD + "/staff info <groupname>" + ChatColor.BLUE + " --- Shows a full description of a group");
			sender.sendMessage(ChatColor.GOLD + "/staff create <groupname>" + ChatColor.BLUE + " --- Creates a new group");
			sender.sendMessage(ChatColor.GOLD + "/staff delete <groupname>" + ChatColor.BLUE + " --- Deletes a group");
			sender.sendMessage(ChatColor.GOLD + "Page " + ChatColor.BLUE + page + ChatColor.GOLD + " of " + maxpages);
		} else if (page == 2) {
			sender.sendMessage(ChatColor.BLUE + "-------------------[" + ChatColor.GOLD + "Staff Info" + ChatColor.BLUE + "]------------------------");
			sender.sendMessage(ChatColor.GOLD + "/staff edit <groupname> <variable> <variableresult>" + ChatColor.BLUE + " --- Edit a variable of a group");
			sender.sendMessage(ChatColor.GOLD + "/staff add <playername> <groupname>" + ChatColor.BLUE + " --- Adds a player to the member list of a group");
			sender.sendMessage(ChatColor.GOLD + "/staff remove <playername> <groupname>" + ChatColor.BLUE + " --- Removes a player from the member list of a group");
			sender.sendMessage(ChatColor.GOLD + "/staff reload" + ChatColor.BLUE + " --- Reloads Staff Info");
			sender.sendMessage(ChatColor.GOLD + "/staff reload config" + ChatColor.BLUE + " --- Reloads Staff Info's config");
			sender.sendMessage(ChatColor.GOLD + "/staff run autoasign" + ChatColor.BLUE + " --- Force an auto asign permissions check");
			sender.sendMessage(ChatColor.GOLD + "Page " + ChatColor.BLUE + page + ChatColor.GOLD + " of " + maxpages);
		} else {
			sender.sendMessage(ChatColor.BLUE + "-------------------[" + ChatColor.GOLD + "Staff Info" + ChatColor.BLUE + "]------------------------");
			sender.sendMessage(ChatColor.GOLD + "/staff" + ChatColor.BLUE + " --- Shows info about Staff Info");
			sender.sendMessage(ChatColor.GOLD + "/staff help" + ChatColor.BLUE + " --- Shows a list of commands");
			sender.sendMessage(ChatColor.GOLD + "/staff groups" + ChatColor.BLUE + " --- Shows a list of groups");
			sender.sendMessage(ChatColor.GOLD + "/staff <groupname>" + ChatColor.BLUE + " --- Shows a short description of a group");
		    sender.sendMessage(ChatColor.GOLD + "/staff info <groupname>" + ChatColor.BLUE + " --- Shows a full description of a group");
			sender.sendMessage(ChatColor.GOLD + "/staff create <groupname>" + ChatColor.BLUE + " --- Creates a new group");
			sender.sendMessage(ChatColor.GOLD + "/staff delete <groupname>" + ChatColor.BLUE + " --- Deletes a group");
			sender.sendMessage(ChatColor.GOLD + "Page " + ChatColor.BLUE + page + ChatColor.GOLD + " of " + maxpages);
		}
		 
	}
}
