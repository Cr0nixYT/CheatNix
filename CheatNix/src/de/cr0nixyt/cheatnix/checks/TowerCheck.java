package de.cr0nixyt.cheatnix.checks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import de.cr0nixyt.cheatnix.Main;

public class TowerCheck implements Listener {
	
	private List<Player> Placed = new ArrayList<>();
	private Map<Player, Location> BlockLoc = new HashMap<>();
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		
		
		if(!Placed.contains(p)) {
			Placed.add(p);
			BlockLoc.put(p, p.getLocation());
			Bukkit.getScheduler().runTaskLater(Main.plugin, new Runnable() {
				
				@Override
				public void run() {
					Placed.remove(p);
					BlockLoc.remove(p);
				}
			}, 7);
		}else {
			Location bLoc = BlockLoc.get(p);
			if(BlockLoc.get(p).getBlockY()+1 == p.getLocation().getBlockY() && bLoc.getBlockX() == e.getBlock().getX() && bLoc.getBlockZ() == e.getBlock().getZ()) {
				Main.FlagPlayer(p, new Location(p.getWorld(), e.getBlock().getX(), e.getBlock().getY()-2, e.getBlock().getZ()), "Tower", 0);
				e.setCancelled(true);
			}
		}
		
		/* {
			e.setCancelled(true);
			Main.FlagPlayer(p, new Location(p.getWorld(), e.getBlock().getX(), e.getBlock().getY()-2, e.getBlock().getZ()), "Tower", 0);
		}else p.sendMessage(e.getItemInHand()+"");*/
	}

}
