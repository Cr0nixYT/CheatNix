package de.cr0nixyt.cheatnix.checks;


import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import de.cr0nixyt.cheatnix.Main;

public class FlightCheck implements Listener {
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		Location to = e.getTo();
		Location from = e.getFrom();
		 if(!p.hasPermission("cn.ByPass.Speed")) {
		Vector vec = to.toVector();
		double i = vec.distance(from.toVector());
		if(p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.SPONGE)) {
			return;
		}
	
		if(p.getGameMode().equals(GameMode.CREATIVE)) return;
		if(p.getEntityId() == 100) {
			return;
		}
		if(p.getVehicle() != null) {
			return;
		}
		if(p.getAllowFlight() == true) return;
	
		
		if((p.getFallDistance() == 0.0F) && (p.getLocation().getBlock().getRelative(BlockFace.UP).getType().equals(Material.AIR))){
			if(i > 0.80D) {
				if(p.isOnGround()) {
					return;
				}
				e.setCancelled(true);
				Main.FlagPlayer(p, e.getFrom(), "Fly", i*10);
			}
		}
		 }
			
		}
	

}
