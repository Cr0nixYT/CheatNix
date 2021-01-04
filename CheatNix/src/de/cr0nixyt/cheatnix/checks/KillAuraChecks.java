package de.cr0nixyt.cheatnix.checks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.util.Vector;

import de.cr0nixyt.cheatnix.Main;
import net.minecraft.server.v1_16_R3.EntityPlayer;

public class KillAuraChecks implements Listener {
	
	public static ArrayList<Player> checked = new ArrayList<>();
	public static HashMap<Player, Zombie> check = new HashMap<>();
	

	
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			if(e.getEntity().getType().equals(EntityType.ZOMBIE) && e.getEntity().getCustomName().equalsIgnoreCase("§cCheatNix")) {
				Main.FlagPlayer(p, p.getLocation(), "KillAura", 0);
			}
			
			
			if(!(checked.contains(p))) {
				checked.add(p);
				performCheck(p);
				Bukkit.getScheduler().runTaskLaterAsynchronously(Main.plugin, new Runnable() {
					
					@Override
					public void run() {
						checked.remove(p);
					}
				}, 20*5);
			}
		}
	}
	
	
	public static void performCheck(Player p) {
		spawn(p);
		Bukkit.getScheduler().runTaskLaterAsynchronously(Main.plugin, new Runnable() {
			
			@Override
			public void run() {
				despawn(p);
			}
		}, 15);
	}
	
	
	public static void spawn(Player p) {
		Zombie z = (Zombie) p.getWorld().spawnEntity(getBlockBehind(p), EntityType.ZOMBIE);
		z.setCustomNameVisible(true);
		z.setCustomName("§cCheatNix");
		z.setSilent(true);
		check.put(p, z);
	}
	
	public static Location getBlockBehind(Player p) {
		Location loc = p.getLocation();
		Vector vec = loc.getDirection().multiply(-0.5D);
		vec.setY(0);
		return loc.add(vec);
	}
	
	

	
	public static void despawn(Player p) {
		if(check.containsKey(p)) {
			Zombie z = check.get(p);
			check.remove(p);
			z.remove();
		}
	}

}
