package de.cr0nixyt.cheatnix.checks;

import de.cr0nixyt.cheatnix.Main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

public class SpeedCheck implements Listener {
  private Map<Player, Location> Update = new HashMap<>();
  
  private List<Player> Countdown = new ArrayList<>();
  
  @EventHandler
  public void onMove(final PlayerMoveEvent e) {
    final Player p = e.getPlayer();
    if(!p.hasPermission("cn.ByPass.Speed")) {
    	
    	if(p.getGameMode().equals(GameMode.CREATIVE)) return;
    	
    	 if (!this.Countdown.contains(p)) {
    	      this.Countdown.add(p);
    	      this.Update.put(p, e.getFrom());
    	      Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
    	            public void run() {
    	              Location loc = (Location)SpeedCheck.this.Update.get(p);
    	              double x1 = loc.getX();
    	              double z1 = loc.getZ();
    	              double x2 = p.getLocation().getX();
    	              double z2 = p.getLocation().getZ();
    	              
    	              if(p.hasPotionEffect(PotionEffectType.SPEED)) {
    	            	  if(p.getPotionEffect(PotionEffectType.SPEED).getAmplifier() == 0) {
    	            		  CheckPlayer(p, loc, "Speed", 8.6D, x1, x2, z1, z2);
    	            	  }else if(p.getPotionEffect(PotionEffectType.SPEED).getAmplifier() == 1) {
    	            		  CheckPlayer(p, loc, "Speed", 9.6D, x1, x2, z1, z2);
    	            	  }else if(p.getPotionEffect(PotionEffectType.SPEED).getAmplifier() == 2) {
    	            		  CheckPlayer(p, loc, "Speed", 10D, x1, x2, z1, z2);
    	            	  } 
    	              }else CheckPlayer(p, loc, "Speed", 7.7D, x1, x2, z1, z2);
    	              
    	              if (SpeedCheck.this.Update.containsKey(p))
    	                SpeedCheck.this.Update.remove(p); 
    	              SpeedCheck.this.Update.put(p, e.getFrom());
    	              SpeedCheck.this.Countdown.remove(p);
    	            }
    	          }, 20);
    	    } 
    }
   
  }
  
  public void CheckPlayer(Player p, Location loc, String Reason, double FlagSpeed, double x1, double x2, double z1, double z2) {
	  if ((x2 - x1) >= 7.7D) {
          Main.FlagPlayer(p, loc, "Speed", (x2-x1));
        } else if ((x1 - x2) >= FlagSpeed) {
          Main.FlagPlayer(p, loc, "Speed", (x1-x2));
        } else if ((z2 - z1) >= FlagSpeed) {
          Main.FlagPlayer(p, loc, "Speed", (z2-z1));
        } else if ((z1 - z2) >= FlagSpeed) {
          Main.FlagPlayer(p, loc, "Speed", (z1-z2));
        } 
  }
  
  
  
}
