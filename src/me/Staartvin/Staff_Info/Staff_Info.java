package me.Staartvin.Staff_Info;

import me.Staartvin.Files.Config;
import me.Staartvin.Groups.Groups;
import me.Staartvin.Permissions.PermissionsCheck;
import me.Staartvin.Permissions.VaultClass;
import net.milkbowl.vault.Vault;

import org.bukkit.plugin.java.JavaPlugin;

public class Staff_Info extends JavaPlugin {

	public Logger log = new Logger(this);
	public Groups groups;
	public me.Staartvin.Commands.CommandExecutor CommandExecutor = new me.Staartvin.Commands.CommandExecutor(this);
	public Config config;
	public Vault vault;
	public VaultClass vaultClass = new VaultClass(this);
	public PermissionsCheck permCheck = new PermissionsCheck(this);
	
	public void onEnable() {
		config = new Config(this, "config.yml", "config");
		
		groups =  new Groups(this);
		groups.getSubClasses(this);
		
		getCommand("staff").setExecutor(CommandExecutor);
		
		if (getServer().getPluginManager().getPlugin("Vault") == null){
			log.logVerbose("Vault has not been found. Some features of Staff Info will not work without Vault.");
		}
		else {
			vault = new Vault();
			vaultClass.setupVault();
			log.logVerbose("Hooked into Vault successfully!");
		}
		
		permCheck.runCheck();
		
		log.logNormal("Staff Info v" + getDescription().getVersion() + " has been enabled!");
	}
	
	public void onDisable() {
		reloadConfig();
		saveConfig();
		permCheck.stopCheck();
		getServer().getScheduler().cancelAllTasks();
		
		log.logNormal("Staff Info v" + getDescription().getVersion() + " has been disabled!");
	}
	
    public void reload() {
    	log.debug("Reload triggered");
    	getServer().getPluginManager().disablePlugin(this);
		getServer().getPluginManager().enablePlugin(this);
    	log.logNormal("Reloaded!");
    }
}
