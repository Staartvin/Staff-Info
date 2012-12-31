package me.Staartvin.Staff_Info;

public class Logger {
	
	Staff_Info plugin;
	
	protected Logger(Staff_Info instance) {
		plugin = instance;
	}
	
	protected void logNormal(String message){
		System.out.print("[Staff Info] " + message);
	}
	
	public boolean logVerbose(String message){
		if (plugin.getConfig().getBoolean("Options.verboselogging")) {
			System.out.print("[Staff Info] " + message);
			return true;
		}
		return false;
	}
	
	public boolean debug(String message){
		if (plugin.getConfig().getBoolean("Options.debug")) {
			System.out.print("[Staff Info DEBUG] " + message);
			return true;
		}
		return false;
	}
}
