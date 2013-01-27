package me.Staartvin.Staff_Info.Files;

import java.util.Arrays;

import me.Staartvin.Staff_Info.Staff_Info;

public class Config {

    Staff_Info plugin;
    String[] array = {"Moderators", "Admins", "Owners"};
    String[] modArray = {"Moderator1", "Moderator2", "Moderator3"};
    String[] adminArray = {"Admin1", "Admin2", "Admin3"};
    String[] ownerArray = {"Owner1", "Owner2", "Owner3"};

    public Config(Staff_Info plugin, String fileName, String name) {
    this.plugin = plugin;
    if (fileName.equals("config.yml")) {
    	loadConfiguration(fileName, name);
    }
    }
    
    public void loadConfiguration(String fileName, String name) {
    	
    	
       	if (plugin.getConfig().getList("Staff Groups") == null) {
    			plugin.getConfig().set("Staff Groups",
    					Arrays.asList(array));
    			plugin.saveConfig();
    		}
       	
		plugin.getConfig()
				.options()
				.header("Staff Info v"
						+ plugin.getDescription().getVersion()
						+ "\n\nVersion " + plugin.getDescription().getVersion() + " Config"
						+ "\nLast CraftBukkit test on: CB 1.4.6 R0.2 #2570"
						+ "\nThanks for using Staff Info!"
						+ "\nAny suggestions or questions?"
						+ "\nhttp://dev.bukkit.org/server-mods/staff-info/"
						+ "\n\nPut all your staff groups in 'Staff Groups'. This is just for Staff Info to initialise the groups."
						+ "\nGroups ARE case sensitive!"
						+ "\n\n'Groups' contains all info about the groups. Names ARE case sensitive!"
						+ "\nEXAMPLE:"
						+ "\n\nAwesome Group:"
						+ "\n  displayname: Awesome guys"
						+ "\n  visible: true"
						+ "\n  shortdescription: Moderators are sociable human beings that provide help to other players."
						+ "\n  longdescription: Moderators are players familiar with Minecraft. They provide an excellent experience for new players as well as older players.Moderators should always be kind and are frequently online."
						+ "\n  members:"
						+ "\n    - Pewdiepie"
						+ "\n    - Etho"
						+ "\n    - Dinnerbone"
						+ "\n\n 'Awesome Group' -> Name of the group. This has to be the same name as listed in staff groups."
						+ "\n 'displayname' -> Display name ingame"
						+ "\n 'visible' -> Is this group visible? Visibility can be set to false when someone is editing the group"
						+ "\n 'shortdescription' -> Short description of the group used as example."
						+ "\n 'longdescription' -> Longdescription is the description shown when info about group is shown."
						+ "\n 'members' -> Members of the group"
						+ "\n\nColour codes can be used:"
						+ "\nBlack = \u00A70 \nNavy = \u00A71 \nGreen = \u00A72 \nBlue = \u00A73 \nRed = \u00A74"
						+ "\nPurple = \u00A75 \nGold = \u00A76 \nLight Gray = \u00A77 \nGray = \u00A78 \nDark Purple = \u00A79"
						+ "\nLight Green = \u00A7a \nLight Blue = \u00A7b \nRose = \u00A7c \nLight Purple = \u00A7d \nYellow = \u00A7e \nWhite = \u00A7f"
						+ "\n\nOther codes to use: (Remove the (-) between it)"
						+ "\nNew line: \\-n \nTab: \\-t \nCarriage-return: \\-r \nForm feed: \\-f"
						+ "\n\n\n");
		
		// Setup example moderator group
		plugin.getConfig().addDefault("Groups.Moderators.displayname", "Moderators");
		plugin.getConfig().addDefault("Groups.Moderators.visible", true);
		plugin.getConfig().addDefault("Groups.Moderators.shortdescription", "Moderators are sociable human beings that provide help to other players.");
		plugin.getConfig().addDefault("Groups.Moderators.longdescription", "Moderators are players familiar with Minecraft. They provide an excellent experience for new players as well as older players. Moderators should always be kind and are frequently online.");
		plugin.getConfig().addDefault("Groups.Moderators.members", Arrays.asList(modArray));
		plugin.getConfig().addDefault("Groups.Moderators.prefix.use", false);
		plugin.getConfig().addDefault("Groups.Moderators.prefix.prefix", "\u00A72(Moderator)");
		
		
		// Setup example admin group
		plugin.getConfig().addDefault("Groups.Admins.displayname", "Admins");
		plugin.getConfig().addDefault("Groups.Admins.visible", true);
		plugin.getConfig().addDefault("Groups.Admins.shortdescription", "Admins are players that rule the server.");
		plugin.getConfig().addDefault("Groups.Admins.longdescription", "Admins usually don't help people out, but they work on fixing problems with plugins or permissions. They also keep an eye on moderators.");
		plugin.getConfig().addDefault("Groups.Admins.members", Arrays.asList(adminArray));
		plugin.getConfig().addDefault("Groups.Admins.prefix.use", false);
		plugin.getConfig().addDefault("Groups.Admins.prefix.prefix", "\u00A71(Admin)");
		
		
		// Setup example owner group
		plugin.getConfig().addDefault("Groups.Owners.displayname", "Gods");
		plugin.getConfig().addDefault("Groups.Owners.visible", true);
		plugin.getConfig().addDefault("Groups.Owners.shortdescription", "Owners are the 'gods' of the server. They can turn the server off/on, install plugins, etc.");
		plugin.getConfig().addDefault("Groups.Owners.longdescription", "Owners don't have to be online that much. They mostly work behind the scenes.They keep installed plugins up-to-date, fix permissions, remove old plugins, monitor RAM usage and pay the server cost.");
		plugin.getConfig().addDefault("Groups.Owners.members", Arrays.asList(ownerArray));
		plugin.getConfig().addDefault("Groups.Owners.prefix.use", false);
		plugin.getConfig().addDefault("Groups.Owners.prefix.prefix", "\u00A76(Owner)");
		
		
		//Setup extra options
		plugin.getConfig().addDefault("Options.debug", false);
		plugin.getConfig().addDefault("Options.verboselogging", true);
		plugin.getConfig().addDefault("Options.automatically asign members", false);
		
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
		
		plugin.log.logVerbose("Config.yml has been loaded!");
		plugin.log.debug("Config.yml has been setup and loaded.");
    }
    
    public void reloadConfig() {
    	plugin.reloadConfig();
    	plugin.saveConfig();
    	plugin.groups.iGroups.initialiseAllGroups();
    }
    
    public String replaceColours(String message) {
		return message.replaceAll("(?i)&([a-f0-9])", "\u00A7$1");
	}
}
