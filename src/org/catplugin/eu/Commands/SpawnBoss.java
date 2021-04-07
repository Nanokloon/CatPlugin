package org.catplugin.eu.Commands;

import org.catplugin.eu.Mobs.WitherSkele;
import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Player;

public class SpawnBoss implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

            Player player = (Player) commandSender;
            WitherSkele skele = new WitherSkele(player.getLocation(), player);
            WorldServer world = ((CraftWorld) Bukkit.getWorld(player.getWorld().getName())).getHandle();
            world.addEntity(skele);

        return false;
    }
}