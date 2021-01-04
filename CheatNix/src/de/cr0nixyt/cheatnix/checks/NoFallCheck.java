package de.cr0nixyt.cheatnix.checks;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import de.cr0nixyt.cheatnix.Main;

public class NoFallCheck implements Listener {
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		Location from = e.getFrom().clone();
		Location to = e.getTo().clone();
		
		Vector vec = to.toVector();
		double i = vec.distance(from.toVector());
		
		if(i == 0) return;
		if(p.getGameMode().equals(GameMode.CREATIVE)) return;
		if(p.getVehicle() != null) return;
		
		if((p.getFallDistance() == 0.0F) && (i > 0.79D) && (p.isOnGround())) {
			e.setCancelled(true);
			Main.FlagPlayer(p, from, "NoFall", 0);
		}
		
	}

}
