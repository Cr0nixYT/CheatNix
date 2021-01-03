package de.cr0nixyt.cheatnix;

import de.cr0nixyt.cheatnix.checks.SpeedCheck;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
  public static final String Prefix = "";
  
  public static Main plugin;
  
  public void onEnable() {
    plugin = this;
    Bukkit.getConsoleSender().sendMessage("Geladen!");
    RegisterChecks();
  }
  
  public void RegisterChecks() {
    Bukkit.getPluginManager().registerEvents((Listener)new SpeedCheck(), (Plugin)plugin);
  }
}
