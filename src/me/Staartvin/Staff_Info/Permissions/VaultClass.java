package me.Staartvin.Staff_Info.Permissions;

import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import me.Staartvin.Staff_Info.Staff_Info;
import me.Staartvin.Staff_Info.Permissions.PermissionsHandler;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;

public class VaultClass {
	
	Staff_Info plugin;
	public PermissionsHandler permHandler;
	
	public VaultClass (Staff_Info instance) {
		plugin = instance;
		permHandler = new PermissionsHandler(instance);
	}
	
	public static Permission permission = null;
	public static Chat chat = null;
	 
	private boolean setupPermissions()
	    {
	        RegisteredServiceProvider<Permission> permissionProvider = plugin.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
	        if (permissionProvider != null) {
	            permission = permissionProvider.getProvider();
	        }
	        return (permission != null);
	    }
	
	private boolean setupChat()
	    {
	        RegisteredServiceProvider<Chat> chatProvider = plugin.getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
	        if (chatProvider != null) {
	            chat = chatProvider.getProvider();
	        }

	        return (chat != null);
	    }
	  
	public boolean setupVault() {
		if (setupPermissions() && setupChat()) {
		return true;	
		} else {
		return false;
		}	
	}
	
	public void setPrefix(Player player, String prefix) {
		chat.setPlayerPrefix(player, prefix);
	}
	
	public void setSuffix(Player player, String suffix) {
		chat.setPlayerSuffix(player, suffix);
	}
	
	public void removePermission(String player, String world, String permission) {
		VaultClass.permission.playerRemove(world, player, permission);
	}
	
	public void addPermission(String player, String world, String permission) {
		VaultClass.permission.playerAdd(world, player, permission);
	}
}
