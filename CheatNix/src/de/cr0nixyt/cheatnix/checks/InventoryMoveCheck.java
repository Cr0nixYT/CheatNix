package de.cr0nixyt.cheatnix.checks;

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

public class InventoryMoveCheck implements Listener {
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		
		if(!(e.getFrom().getX() == e.getTo().getX()) || !(e.getFrom().getZ() == e.getTo().getZ()))
			p.closeInventory();
			//Main.FlagPlayer(p, p.getLocation(), "InventoryMove", 0);
		
	}

}
