package me.Staartvin.Staff_Info.Groups;

import me.Staartvin.Staff_Info.Staff_Info;

public class initialiseGroups{

	Staff_Info plugin;
	
	public initialiseGroups(Staff_Info instance) {
		plugin = instance;
	}
	
	public void initialiseAllGroups() {
		plugin.groups.groups = plugin.getConfig().getStringList("Staff Groups");
		
		plugin.log.debug(plugin.groups.groups.size() + " groups found!");
		for (String group: plugin.getConfig().getStringList("Staff Groups")) {
			plugin.log.debug("Group: " + group);
		}
	}	
}
