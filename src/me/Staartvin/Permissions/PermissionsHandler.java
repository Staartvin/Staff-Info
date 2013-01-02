package me.Staartvin.Permissions;

import java.util.List;

import org.bukkit.World;
import org.bukkit.entity.Player;

import me.Staartvin.Staff_Info.Staff_Info;

public class PermissionsHandler {

	Staff_Info plugin;
		
	public PermissionsHandler(Staff_Info instance) {
		plugin = instance;
	}
	
	public boolean autoAsignGroups() {
		if (!plugin.getConfig().getBoolean("Options.automatically asign members")) {
			return false;
		}
		
		List<String> groups = plugin.groups.getGroups();
		
		for (Player onlinePlayer:plugin.getServer().getOnlinePlayers()) {
			
			for (String group:groups) {
				if (onlinePlayer.hasPermission("staffinfo.member." + group)) {
					addPlayerToGroup(onlinePlayer.getName(), group);					
				} else {
					removePlayerFromGroup(onlinePlayer.getName(), group);
				}
			}
		}
		return false;
	}
	// Automated version
	private boolean addPlayerToGroup(String playerName, String groupName) {
		
		List<String> members;
		groupName = plugin.groups.searchInGroups(groupName);
		
		if (plugin.getServer().getPlayer(playerName) == null) {
			return false;
		}
		
		playerName = plugin.getServer().getPlayer(playerName).getName();
		
		if (groupName == null) {
			return false;
		}
		
		members = plugin.getConfig().getStringList("Groups." + groupName + ".members");
		
		if (members.contains(playerName)) {
			return false;
		}
		
		members.add(playerName);
		plugin.getConfig().set("Groups." + groupName + ".members", members);
		plugin.saveConfig();
		return true;
	}
	// Automated version
	private boolean removePlayerFromGroup(String playerName, String groupName) {
		
		List<String> members;
		groupName = plugin.groups.searchInGroups(groupName);
		
		if (plugin.getServer().getPlayer(playerName) == null) {
			return false;
		}
		
		playerName = plugin.getServer().getPlayer(playerName).getName();
		
		if (groupName == null) {
			return false;
		}
		
		members = plugin.getConfig().getStringList("Groups." + groupName + ".members");
		
		if (!members.contains(playerName)) {
			return false;
		}
		
		members.remove(playerName);
		plugin.getConfig().set("Groups." + groupName + ".members", members);
		plugin.saveConfig();
		
		if (plugin.getServer().getPluginManager().getPlugin("Vault") != null){
			for (World world:plugin.getServer().getWorlds()) {
				plugin.vaultClass.removePermission(playerName, world.getName(), "staffinfo.member." + groupName);
			}
		}
		return true;
	}
}
