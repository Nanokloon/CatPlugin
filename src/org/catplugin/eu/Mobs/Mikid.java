package org.catplugin.eu.Mobs;

import com.mojang.authlib.GameProfile;
import javafx.util.Pair;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.catplugin.eu.Main;

import java.util.*;

public class Mikid {
    private static List<EntityPlayer> Mikid = new ArrayList<EntityPlayer>();
    private static Map<EntityPlayer, List<Pair<EnumItemSlot, ItemStack>>> equipmentMap = new HashMap<>();
    private static org.catplugin.eu.Main plugin;
    private static double prevx = 0;
    private static double prevy = 0;
    private static double prevz = 0;
    public static UUID id;
    private static float currentHealth;
    public Mikid(Plugin plugin) {this.plugin = plugin; }

    public static void spawn(Player player) {

        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer world = ((CraftWorld) Bukkit.getWorld(player.getWorld().getName())).getHandle();
        GameProfile gameProfile = new GameProfile(UUID.fromString("42b661f7-137d-4188-8ad4-0dbf5ee5c810"), ChatColor.DARK_RED + "" + ChatColor.BOLD + "MiKid2015");
        EntityPlayer npc = new EntityPlayer(server, world, gameProfile, new PlayerInteractManager(world));
        for (Entity e : Bukkit.getServer().getWorld("world").getLivingEntities()) {
            if (ChatColor.stripColor(e.getName()).equals("Wither Skeleton BOSS")) {
                npc.setLocation(e.getLocation().getX(), e.getLocation().getZ(), e.getLocation().getY(), player.getLocation().getYaw(), player.getLocation().getPitch());
            } else {
                npc.setLocation(Main.x, Main.y, Main.z, player.getLocation().getYaw(), player.getLocation().getPitch());

            }
            addMikidPacket(npc);
            Mikid.add(npc);
            Location loc = player.getLocation();
            MikidController mob = new MikidController(loc);
            world.addEntity(mob);
            id = mob.getUniqueID();
            prevx = mob.locX();
            prevy = mob.locY();
            prevz = mob.locZ();
            currentHealth = mob.getHealth();
            new BukkitRunnable() {
                public void run() {
                    mob.setSilent(true);
                    mob.setInvisible(true);

                }
            }.runTaskLater(plugin, 2);
            new BukkitRunnable() {

                public void run() {
                    update(npc, mob);

                    double x = mob.locX();
                    double y = mob.locY();
                    double z = mob.locZ();

                    if (x != prevx || y != prevy || z != prevz) {
                        double getx = x - prevx;
                        double gety = y - prevy;
                        double getz = z - prevz;

                        npcMove(npc, getx, gety, getz);

                        prevx = x;
                        prevy = y;
                        prevz = z;
                    }
                    if (mob.getHealth() < currentHealth) {
                        npcTakeDamage(npc);
                        currentHealth = mob.getHealth();

                    }
                    if (mob.dead == true) {
                        removeMikidPacket(npc);
                        Mikid.remove(npc);
                        cancel();
                    }
                }
            }.runTaskTimer(plugin, 0, 0);
        }
        }




    public void remove() {
        EntityPlayer npc = Mikid.get(Mikid.size() - 1);
        Mikid.remove(npc);
        removeMikidPacket(npc);

    }



    public static void addMikidPacket(EntityPlayer npc) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
            connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 356 / 360)));

        }
    }
    public static void removeMikidPacket(EntityPlayer npc) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
            DataWatcher watcher = npc.getDataWatcher();
            watcher.set(new DataWatcherObject<>(16, DataWatcherRegistry.a), (byte) 255);
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npc));
            connection.sendPacket(new PacketPlayOutEntityDestroy(npc.getId()));

        }
    }

    public void addJoinPacket(Player player) {
        for (EntityPlayer npc : Mikid) {
            PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
            connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 356 / 360)));


        }

    }

    public static void update(EntityPlayer npc, MikidController mob) {
        for (Player player : Bukkit.getOnlinePlayers()) {

            PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;

            connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (mob.lastYaw * 256 / 360)));
            connection.sendPacket(new PacketPlayOutEntity.PacketPlayOutEntityLook(npc.getId(), (byte) (mob.lastYaw * 256 / 360), (byte) (mob.lastPitch * 256 / 360), true));

        }
    }

    public static void npcMove(EntityPlayer npc, double x, double y, double z) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutEntity.PacketPlayOutRelEntityMove(npc.getId(), (short) (x * 4096), (short) (y * 4096), (short) (z * 4096), true));


        }
    }

    public static void npcTakeDamage(EntityPlayer npc) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutAnimation(npc, 1));
        }
    }

    public static List<EntityPlayer> getNpcs() {
        return Mikid;
    }
}

