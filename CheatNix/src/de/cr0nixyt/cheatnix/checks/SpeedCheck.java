package de.cr0nixyt.cheatnix.checks;

import de.cr0nixyt.cheatnix.Main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

public class SpeedCheck implements Listener {
  private Map<Player, Location> Update = new HashMap<>();
  
  private List<Player> Countdown = new ArrayList<>();
  
  @EventHandler
  public void onMove(final PlayerMoveEvent e) {
    final Player p = e.getPlayer();
    if(!p.hasPermission("cn.ByPass.Speed")) {
    	 if (!this.Countdown.contains(p)) {
    	      this.Countdown.add(p);
    	      this.Update.put(p, e.getFrom());
    	      Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
    	            public void run() {
    	              Location loc = (Location)SpeedCheck.this.Update.get(p);
    	              int x1 = loc.getBlockX();
    	              int z1 = loc.getBlockZ();
    	              int x2 = p.getLocation().getBlockX();
    	              int z2 = p.getLocation().getBlockZ();
    	              if ((x2 - x1) >= 5.6D) {
    	                SpeedCheck.this.FlagPlayer(p, loc, "Speed");
    	              } else if ((x1 - x2) >= 7.7D) {
    	                SpeedCheck.this.FlagPlayer(p, loc, "Speed");
    	              } else if ((z2 - z1) >= 7.7D) {
    	                SpeedCheck.this.FlagPlayer(p, loc, "Speed");
    	              } else if ((z1 - z2) >= 7.7D) {
    	                SpeedCheck.this.FlagPlayer(p, loc, "Speed");
    	              } 
    	              if (SpeedCheck.this.Update.containsKey(p))
    	                SpeedCheck.this.Update.remove(p); 
    	              SpeedCheck.this.Update.put(p, e.getFrom());
    	              SpeedCheck.this.Countdown.remove(p);
    	            }
    	          }, 20);
    	    } 
    }
   
  }
  
  public void FlagPlayer(Player p, Location loc, String Reason) {
    p.teleport(loc);
    for(Player players : Bukkit.getOnlinePlayers()) {
    	if(players.hasPermission("cn.notify")) {
    		players.sendMessage(Main.Prefix+"§aDer Spieler "+p.getName()+" wurde für §e"+Reason+" §ageflagt!");
    	}
    }
  }
}
