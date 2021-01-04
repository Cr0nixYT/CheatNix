package de.cr0nixyt.cheatnix;

import de.cr0nixyt.cheatnix.checks.FastBreakCheck;
import de.cr0nixyt.cheatnix.checks.FlightCheck;
import de.cr0nixyt.cheatnix.checks.InventoryMoveCheck;
import de.cr0nixyt.cheatnix.checks.KillAuraChecks;
import de.cr0nixyt.cheatnix.checks.KillAuraChecks2;
import de.cr0nixyt.cheatnix.checks.NoFallCheck;
import de.cr0nixyt.cheatnix.checks.SpeedCheck;
import de.cr0nixyt.cheatnix.checks.TowerCheck;

import java.text.DecimalFormat;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
  public static final String Prefix = "§8[§6Cheat§4Nix§8]§7 ";
  
  public static Main plugin;
  
  public void onEnable() {
    plugin = this;
    Bukkit.getConsoleSender().sendMessage("Geladen!");
    RegisterChecks();
  }
  
  public void RegisterChecks() {
    Bukkit.getPluginManager().registerEvents(new SpeedCheck(), plugin);
    Bukkit.getPluginManager().registerEvents(new FastBreakCheck(), plugin);
    Bukkit.getPluginManager().registerEvents(new InventoryMoveCheck(), plugin);
    Bukkit.getPluginManager().registerEvents(new FlightCheck(), plugin);
    Bukkit.getPluginManager().registerEvents(new KillAuraChecks(), plugin);
    Bukkit.getPluginManager().registerEvents(new TowerCheck(), plugin);
    Bukkit.getPluginManager().registerEvents(new NoFallCheck(), plugin);
  }
  
  
  public static void FlagPlayer(Player p, Location loc, String Reason, double FlagSpeed) {
	  DecimalFormat f = new DecimalFormat("#0.00");
	    p.teleport(loc);
	    for(Player players : Bukkit.getOnlinePlayers()) {
	    	if(players.hasPermission("cn.notify")) {
	    		if(FlagSpeed != 0) players.sendMessage(Main.Prefix+"§7Der Spieler §e"+p.getName()+" §7wurde für §b"+Reason+" §7geflagt! §8[§c"+(f.format(FlagSpeed))+"§8]");
	    		else players.sendMessage(Main.Prefix+"§7Der Spieler §e"+p.getName()+" §7wurde für §b"+Reason+" §7geflagt!");
	    	}
	    }
	  }
}
