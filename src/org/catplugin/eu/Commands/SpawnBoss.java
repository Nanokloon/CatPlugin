package org.catplugin.eu.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.catplugin.eu.Main;
import org.catplugin.eu.Mobs.WitherSkele;
import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Player;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SpawnBoss implements CommandExecutor {
    int number = 0;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player player = (Player) commandSender;
        WitherSkele skele = new WitherSkele(player.getLocation(), player);
        WorldServer world = ((CraftWorld) Bukkit.getWorld(player.getWorld().getName())).getHandle();
        Main.worldServer= world;
        world.addEntity(skele);


        return false;
    }

    @EventHandler
    public void spawn(EntityDamageByEntityEvent event) throws InterruptedException {
        if (event.getEntity() instanceof Wither && event.getDamager() instanceof Player && event.getEntity().isDead()) {
            Player player = (Player) event.getDamager();
            number += 1;
            List<Entity> nearby = player.getNearbyEntities(32, 32, 32);
            if (number < 5) {
                for (Entity entity : nearby)
                    if (entity instanceof Player) {
                        entity.sendMessage(ChatColor.DARK_RED + "For every one you kill you get closer to the final boss");
                        TimeUnit.SECONDS.sleep(1);
                        entity.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "I WILL AVENGE MY BROTHERS");
                        TimeUnit.SECONDS.sleep(1);
                        entity.sendMessage(ChatColor.DARK_RED + "We have been trapped forever, but soon....");
                        TimeUnit.SECONDS.sleep(1);
                        entity.sendMessage(ChatColor.DARK_RED + "WE WILL BE OUT");
                        long time = entity.getWorld().getTime();
                        entity.getWorld().setTime(18000);
                        entity.getWorld().strikeLightning(entity.getLocation());
                        TimeUnit.SECONDS.sleep(3);
                        entity.getWorld().setTime(time);

                    }
            } else {
                Bukkit.getServer().broadcastMessage("I have awoken");
                player.getWorld().createExplosion(event.getEntity().getLocation(), 5);
                TimeUnit.MILLISECONDS.sleep(250);
                player.getWorld().createExplosion(event.getEntity().getLocation(), 5);
                TimeUnit.MILLISECONDS.sleep(250);
                player.getWorld().createExplosion(event.getEntity().getLocation(), 5);
                TimeUnit.MILLISECONDS.sleep(250);
                player.getWorld().createExplosion(event.getEntity().getLocation(), 5);
                TimeUnit.MILLISECONDS.sleep(250);
                player.getWorld().createExplosion(event.getEntity().getLocation(), 5);
                TimeUnit.MILLISECONDS.sleep(250);
                player.getWorld().createExplosion(event.getEntity().getLocation(), 5);
                TimeUnit.MILLISECONDS.sleep(250);
                player.getWorld().createExplosion(event.getEntity().getLocation(), 5);
                TimeUnit.MILLISECONDS.sleep(250);
                player.getWorld().createExplosion(event.getEntity().getLocation(), 5);
                TimeUnit.MILLISECONDS.sleep(250);


            }
        }
    }
}
