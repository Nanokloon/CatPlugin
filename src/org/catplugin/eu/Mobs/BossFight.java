package org.catplugin.eu.Mobs;

import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.plugin.Plugin;
import org.catplugin.eu.Main;

import java.util.List;
import java.util.Random;

public class BossFight implements Listener {
    private final Main main;
    public static WorldServer worldServer;
    public BossFight(Main main) {
        this.main = main;
    }

    private static Plugin plugin= Bukkit.getPluginManager().getPlugin("CatPlugin");
    @EventHandler
    public void onBossSpawn(EntitySpawnEvent e) {
        if (ChatColor.stripColor(e.getEntity().getName()).equals("Wither Skeleton BOSS")) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
                @Override
                public void run() {
                    if (!e.getEntity().isDead()) {
                        Location loc = e.getLocation();
                        NetheriteDefender defender = new NetheriteDefender(loc);
                        worldServer.addEntity(defender);


                    }
                }
            }, 99L);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
                @Override
                public void run() {
                    List<Entity> nearby = e.getEntity().getNearbyEntities(32, 32, 32);
                    for (Entity tmp : nearby) {
                        if (tmp instanceof Player) {
                            Random ra = new Random();
                            if (ra.nextInt(100) >= 50) {
                                Location location = tmp.getLocation();
                                TNTPrimed tnt = (TNTPrimed) e.getEntity().getWorld().spawnEntity(location, EntityType.PRIMED_TNT);
                                tnt.setFuseTicks(20);

                            }
                        }
                    }
                }
            }, 200);
        }
    }

        @EventHandler
        public void onDamage (EntityDamageByEntityEvent e){
            Player player = (Player) e.getDamager();
            if (ChatColor.stripColor(e.getEntity().getName()).equals("Wither Skeleton BOSS")) {
                if (((WitherSkele) e.getEntity()).getMaxHealth() / 2 > ((WitherSkele) e.getEntity()).getHealth()) {
                    Mikid.spawn(player);
                }

                Random rnd = new Random();
                if (rnd.nextInt(100) <= 25) {
                    NetheriteDefender nether = new NetheriteDefender(e.getEntity().getLocation());
                    WorldServer world = ((CraftWorld) Bukkit.getWorld(player.getWorld().getName())).getHandle();
                    world.addEntity(nether);
                }
            }
        }
    }
