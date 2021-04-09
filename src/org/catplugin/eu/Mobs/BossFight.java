package org.catplugin.eu.Mobs;

import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import org.catplugin.eu.Main;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class BossFight implements Listener {
    private final Main main;

    public BossFight(Main main) {
        this.main = main;
    }


    @EventHandler
    public void onBossSpawn(CreatureSpawnEvent e, Player player) {
        if (e.getEntity() instanceof WitherSkele) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
                @Override
                public void run() {
                    if (!((WitherSkele) e.getEntity()).dead) {
                        Location loc = e.getLocation();
                        NetheriteDefender defender = new NetheriteDefender(loc);
                        WorldServer world = ((CraftWorld) Bukkit.getWorld(player.getWorld().getName())).getHandle();
                        world.addEntity(defender);



                    }
                }
            },60L);
        }
    }
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        Player player = (Player) e.getDamager();
        if (e.getEntity() instanceof WitherSkele) {
            if(((WitherSkele) e.getEntity()).getMaxHealth() / 2 > ((WitherSkele) e.getEntity()).getHealth()) {
                Location loc = e.getEntity().getLocation();
                MikidController mikid = new MikidController(loc);
                WorldServer world = ((CraftWorld) Bukkit.getWorld(player.getWorld().getName())).getHandle();
                Mikid m = new Mikid();
                m.spawn(player);
            }
            List<Entity> nearby = e.getEntity().getNearbyEntities(32, 32, 32);
            for (Entity tmp : nearby) {
                if (tmp instanceof Player) {
                    Location location = tmp.getLocation();
                    TNTPrimed tnt = (TNTPrimed) e.getEntity().getWorld().spawnEntity(location, EntityType.PRIMED_TNT);
                    tnt.setFuseTicks(20);

                }
            }
            Random rnd = new Random();
            if(rnd.nextInt(100) <= 25) {
                NetheriteDefender nether = new NetheriteDefender(e.getEntity().getLocation());
                WorldServer world = ((CraftWorld) Bukkit.getWorld(player.getWorld().getName())).getHandle();
                world.addEntity(nether);
            }
        }
    }
}
