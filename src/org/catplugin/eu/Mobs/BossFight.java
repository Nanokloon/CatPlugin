package org.catplugin.eu.Mobs;

import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.catplugin.eu.Main;

public class BossFight implements Listener {
    Main plugin;
    @EventHandler
    public void onBossSpawn(CreatureSpawnEvent e, Player player) {
        if (e.getEntity() instanceof WitherSkele) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (!((WitherSkele) e.getEntity()).dead) {
                        Location loc = e.getLocation();
                        NetheriteDefender defender = new NetheriteDefender(loc);
                        WorldServer world = ((CraftWorld) Bukkit.getWorld(player.getWorld().getName())).getHandle();
                        world.addEntity(defender);


                    }
                }
            }.runTaskTimerAsynchronously(plugin, 100, 20);
        }
    }
}
