package me.Staartvin.Staff_Info.Permissions;

import org.bukkit.scheduler.BukkitTask;

import me.Staartvin.Staff_Info.Staff_Info;

public class PermissionsCheck {
	
	Staff_Info plugin;
	BukkitTask id;
	
	public PermissionsCheck(Staff_Info instance) {
		plugin = instance;
	}
	
	public void runCheck() {
		id = plugin.getServer().getScheduler().runTaskTimer(plugin, new Runnable() {
		    @Override  
		    public void run() {
		        plugin.log.debug("Running permissions check..");
		        plugin.vaultClass.permHandler.autoAsignGroups();
		    }
		}, 200L, 12000L);
	}
	
	@SuppressWarnings("finally")
	public boolean stopCheck() {
		try {
			id.cancel();
			return true;
		} catch (Exception e) {
			plugin.getServer().getScheduler().cancelAllTasks();
			return true;
		} finally {
			return false;
		}
		
	}
}
