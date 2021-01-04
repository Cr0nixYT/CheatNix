package de.cr0nixyt.cheatnix.checks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.util.Vector;

import de.cr0nixyt.cheatnix.Main;
import net.minecraft.server.v1_16_R3.EntityPlayer;

public class KillAuraChecks2 implements Listener {
	
	public static ArrayList<Player> checked = new ArrayList<>();
	public static HashMap<Player, EntityPlayer> check = new HashMap<>();
	
	@EventHandler
	public void onfgas(PlayerInteractEntityEvent e) {
		Bukkit.broadcastMessage(e.getRightClicked()+"");
	}
	
	
	@EventHandler
	public void ogs(EntityTargetLivingEntityEvent e) {
		Bukkit.broadcastMessage(e.getTarget()+"");
		if(e.getTarget().getName().equals("§7"+e.getEntity().getName())) {
			Main.FlagPlayer((Player) e.getEntity(), e.getEntity().getLocation(), "KillAura", 0);
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			if(e.getEntity().getName().equalsIgnoreCase("§7"+p.getName())) {
				Main.FlagPlayer(p, p.getLocation(), "KillAura", 0);
			}else Bukkit.broadcastMessage("HitError");
			
			
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
		de.cr0nixyt.cheatnix.npc.NPC.createNPC(p, p.getName());
		for(EntityPlayer i : de.cr0nixyt.cheatnix.npc.NPC.getNPCs()) {
			if(i.getName().equalsIgnoreCase("§7"+p.getName()))
				check.put(p, i);
			else Bukkit.broadcastMessage("SpawnError");
		}
			
		
	}
	
	public static Location getBlockBehind(Player p) {
		Location loc = p.getLocation();
		Vector vec = loc.getDirection().multiply(-0.5D);
		vec.setY(0);
		return loc.add(vec);
	}
	
	

	
	public static void despawn(Player p) {
		if(check.containsKey(p)) {
			Bukkit.broadcastMessage("DeSpawnError");
			EntityPlayer npc = check.get(p);
			check.remove(p);
			npc.killEntity();
			
		}
	}

}
